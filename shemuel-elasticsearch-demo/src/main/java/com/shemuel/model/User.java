package com.shemuel.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/14 17:22
 * @Description:
 */
@Document(indexName = "love", type = "lover")
public class User implements Serializable {
    private Integer id;
    private  String userName;
    private String cup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", cup='" + cup + '\'' +
                '}';
    }
}
