package com.shemuel.entity;

/**
 * @date: 2020/3/22 15:42
 * @author: dengshaoxiang
 * @description:
 */
public enum States {
    INITIAL_STATE("初始状态"),
    WAIT_PAY("待支付"),
    WAIT_DELIVERY("待发货"),
    WAIT_EVALUE("待评价"),
    FINISHED("已结束");
    private String msg ;
    private States(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }

}
