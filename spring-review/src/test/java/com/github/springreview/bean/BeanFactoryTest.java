package com.github.springreview.bean;

import com.github.springreview.controller.OrderController;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("deprecation")
public class BeanFactoryTest {

    @Test
    public void testSimpleLoad() {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
//        MyTestFactoryBean bean1 = factory.getBean(MyTestFactoryBean.class);
//        MyTestFactoryBean bean2 = (MyTestFactoryBean) factory.getBean("&myTestFactoryBean");
//        MyTestBean bean3 = (MyTestBean) factory.getBean("myTestFactoryBean");
        Person person = factory.getBean(Person.class);
        assertEquals(11, person.getAge());
        assertEquals("Person{name='constructorPerson', age=11}", person.toString());
    }




    @Test
    public void testSimpleLoad1() {
        try {
//            BeanFactory factory = new XmlBeanFactory(new ClassPathResource("xunhuanyilai.xml"));
            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("xunhuanyilai.xml");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testSimpleLoad2() {
        try {
            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("xunhuanyilai1.xml");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testSimpleLoad3() {
        try {
            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beanFactoryTest.xml");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testSimpleLoad4() {
        try {
            ClassPathXmlApplicationContext classPathXmlApplicationContext
                    = new ClassPathXmlApplicationContext("spring几种注入方式.xml");
            OrderController controller = classPathXmlApplicationContext.getBean(OrderController.class);
            controller.addOrder();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
