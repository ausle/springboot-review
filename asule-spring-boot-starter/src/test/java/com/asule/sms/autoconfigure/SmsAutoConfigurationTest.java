package com.asule.sms.autoconfigure;

import com.asule.bean.SimpleBean;
import com.asule.bean.autoconfigure.SimpleBeanAutoConfiguration;
import com.asule.sms.SmsClient;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class SmsAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(SmsAutoConfiguration.class));

    @Test
    public void shouldCreateSmsClientWhenEnabled() {
        this.contextRunner
                .withPropertyValues("sms.access-key=abc", "sms.secret-key=xyz")
                .run(context -> {
                    assertThat(context).hasSingleBean(SmsClient.class);
                    assertThat(context.getBean(SmsClient.class).getAccessKey()).isEqualTo("abc");
                    assertThat(context.getBean(SmsClient.class).getSecretKey()).isEqualTo("xyz");
                });
    }

    @Test
    public void shouldBackOffWhenUserProvidesSmsClient() {
        this.contextRunner
                .withBean(SmsClient.class, () -> new SmsClient("custom", "custom"))
                .run(context -> {
                    assertThat(context).hasSingleBean(SmsClient.class);
                    assertThat(context.getBean(SmsClient.class).getAccessKey()).isEqualTo("custom");
                });
    }

    @Test
    public void shouldNotCreateSmsClientWhenDisabled() {
        this.contextRunner
                .withPropertyValues("sms.enabled=false")
                .run(context -> assertThat(context).doesNotHaveBean(SmsClient.class));
    }

    @Test
    public void shouldCreateSimpleBeanForExistingReviewTest() {
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(SimpleBeanAutoConfiguration.class))
                .run(context -> assertThat(context).hasSingleBean(SimpleBean.class));
    }
}
