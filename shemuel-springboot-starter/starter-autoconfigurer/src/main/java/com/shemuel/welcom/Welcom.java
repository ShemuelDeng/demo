package com.shemuel.welcom;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/6 10:36
 * @Description:
 */
public class Welcom {
    private UserName userName;

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public String sayWelcom(){
        return "welcom " + userName.getUserName() + "to the springboot world";
    }
}
