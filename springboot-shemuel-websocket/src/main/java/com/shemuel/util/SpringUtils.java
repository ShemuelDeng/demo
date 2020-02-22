package com.shemuel.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by dengshaoxiang on 2019/12/9 15:41
 * description:
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.applicationContext == null){
            this.applicationContext =applicationContext;
        }
    }
    public Object getBean(Class clazz){
        return this.applicationContext.getBean(clazz);
    }
}
