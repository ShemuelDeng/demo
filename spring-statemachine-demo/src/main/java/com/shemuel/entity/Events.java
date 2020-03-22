package com.shemuel.entity;

/**
 * @date: 2020/3/22 15:47
 * @author: dengshaoxiang
 * @description:
 */
public enum Events {
    ORDER("下单"),
    PAY("支付"),
    RECIEVE("收货"),
    EVALUE("评价");
    private String msg;
    private Events(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }
}
