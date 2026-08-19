package com.asule.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsuleApplicationContext {

    private Class config;

    // 单例池
    private ConcurrentHashMap<String, Object> singletonPool = new ConcurrentHashMap<>();
    // 创建类的信息，避免每次重新解析
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    // 创建BeanPostProcessor的实现类的对象列表
    private ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public AsuleApplicationContext(Class configClass){
        this.config = configClass;
        scan();
        // 扫描结束后，如果是单例的，创建bean，放入一级缓存
        for(Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()){
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")){
                Object bean = createBean(beanDefinition,beanName);
                singletonPool.put(beanName,bean);
            }
        }
    }


    /**
        获取要扫描的基础包名。
        扫描该包下的所有被@Component注解声明的类。
        将bean的类信息（该类的范围、该类的Class对象）转换为一个个beanDefinition。
        对于BeanPostProcessor类，实例化对象，保存到beanPostProcessorList中。
     */
    private void scan() {
        // 获取配置文件中声明的，需要扫描的包名。该包下的类，都需要进行扫描。
        ComponentScan componentScan = (ComponentScan) config.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScan.value();
        path = path.replace(".","/");

        ClassLoader classLoader = AsuleApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getFile());
        if (file.isDirectory()){
            File[] files = file.listFiles();
            // 遍历该路径下的文件
            for (File f:files){
                String absolutePath = f.getAbsolutePath();
                if (absolutePath.endsWith(".class")){
                    // 获取要扫描的类名
                    String className = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    className = className.replace("\\",".");

                    try {
                        Class<?> clazz = classLoader.loadClass(className);
                        // 被Component注解标识的类
                        if (clazz.isAnnotationPresent(Component.class)){
                            if (BeanPostProcessor.class.isAssignableFrom(clazz)){
                                BeanPostProcessor instance = (BeanPostProcessor) clazz.getDeclaredConstructor().newInstance();
                                beanPostProcessorList.add(instance);
                            }

                            Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                            String beanName = componentAnnotation.value();
                            if ("".equals(beanName)){
                                // 如果未声明beanName，默认以该类的类名为beanName（首字母小写）
                                beanName= Introspector.decapitalize(clazz.getSimpleName());
                            }

                            // 创建解析类, 并保存解析结果
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);
                            if (clazz.isAnnotationPresent(Scope.class)){
                                Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                                String scopeValue = scopeAnnotation.value();
                                beanDefinition.setScope(scopeValue);
                            }
                            else{
                                beanDefinition.setScope("singleton");
                            }
                            beanDefinitionMap.put(beanName,beanDefinition);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public Object getBean(String beanName){
        if (beanDefinitionMap.containsKey(beanName)){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")){
                Object o = singletonPool.get(beanName);
                return o;
            }else{
                // 创建bean对象
                Object o = createBean(beanDefinition,beanName);
                return o;
            }
        }else{
            throw new NullPointerException();
        }
    }

    public Object createBean(BeanDefinition beanDefinition, String beanName){
        Class clazz = beanDefinition.getClazz();
        Object o = null;
        try {
            // 实例化
            o = clazz.getDeclaredConstructor().newInstance();

            // 依赖注入
            for(Field field: clazz.getDeclaredFields()){
                if (field.isAnnotationPresent(Autowired.class)){
                    field.setAccessible(true);
                    Object bean = getBean(field.getName());
                    field.set(o,bean);
                }
            }

            // spring回调aware接口，把beanName传递给该bean。
            // 除了该aware，spring还提供有许多其他的aware接口。
            if (o instanceof BeanNameAware){
                ((BeanNameAware) o).setBeanName(beanName);
            }

            // 初始化前，bean后置处理器尝试做一些事
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                o = beanPostProcessor.postProcessBeforeInitialization(beanName,o);
            }

            // 调用bean的初始化方法
            if (o instanceof InitializingBean){
                ((InitializingBean) o).afterPropertiesSet();
            }

            // 初始化后，bean后置处理器尝试做一些事
            // 提供了一个灵活的方式，在bean的创建过程，允许程序员去操作bean对象。
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                o = beanPostProcessor.postProcessAfterInitialization(beanName,o); //对o进行额外的加工，可能不是同一个对象
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return o;
    }

}
