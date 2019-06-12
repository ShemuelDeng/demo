package com.shemuel.welcom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/6 10:40
 * @Description:
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(UserName.class)
public class WelcomAutoConfiguration {
    @Autowired
    private UserName userName;
    @Bean
    public Welcom welcom() {
        Welcom welcom = new Welcom();
        welcom.setUserName(userName);
        System.out.println("bean welcom initialized");
        return welcom;
    }
}
