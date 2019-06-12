package com.shemuel.welcom;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/6 10:37
 * @Description:
 */
@ConfigurationProperties(prefix = "com.shemuel")
public class UserName {
    private String userName;

    public String getUserName() {
        System.out.println("getUserName method called");
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println("setUserName method called");
        this.userName = userName;
    }
}
