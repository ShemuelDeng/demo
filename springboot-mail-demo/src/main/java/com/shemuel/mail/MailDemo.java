package com.shemuel.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/12 09:26
 * @Description:
 */
@Component
public class MailDemo {
    @Value("${spring.mail.username}")
    private String from ;
    @Autowired
    private JavaMailSender jms;
    /**
     * @description: 发送普通html类型的邮件
     * @param to
     * @param content
     * @date: 2019/6/12 9:29
     * @author: dengshaoxiang
     */
    public void sendMail(String to ,String content) throws Exception{
        MimeMessage mimeMessage = jms.createMimeMessage();
        mimeMessage.setContent("<h1>你好,这是一封测试邮件!</h1>邮件内容:" + content,"text/html;charset=utf-8");
        mimeMessage.setSubject("测试邮件");
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
        mimeMessage.setFrom(this.from);
        mimeMessage.setSentDate(new Date());
        mimeMessage.setDescription("this is description");
        jms.send(mimeMessage);
    }
}
