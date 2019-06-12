package com.shemuel;

import com.shemuel.mail.MailDemo;
import com.shemuel.welcom.Welcom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShemuelMailDemoApplicationTests {

    @Autowired
    private MailDemo mailDemo;

//    @Autowired
//    private Welcom welcom;
    @Test
    public void contextLoads() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        int coun = context.getBeanDefinitionCount();
        System.out.println(coun);
    }

    /**
     * @description: 测试邮件发送
     * @param
     * @date: 2019/6/12 9:35
     * @author: dengshaoxiang
     */
    @Test
    public void testMail(){
        try {
            mailDemo.sendMail("270988019@qq.com","测试邮件");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
