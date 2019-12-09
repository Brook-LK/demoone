package com.example.demo.service.impl;

import com.example.demo.email.EmailConfig;
import com.example.demo.service.EmailService;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailConfig emailConfig;  //得到了发件人的邮箱，也就是配置中的邮箱

    @Autowired
    private JavaMailSender mailSender;   //实现邮件的发送，依赖了mail  jar包就有这个类了

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;   //Springboot启动是自动注入的freemarker

    @Override
    public void sendSimpleMail(String sendTo, String title, String content) {
        //简单邮件的发送
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());  //谁发送邮件
        message.setTo(sendTo);        //发给谁
        message.setSubject(title);   //发送的邮件标题
        message.setText(content);

        mailSender.send(message);  //上面注入的JavaMailSender负责发送
    }

    //发送带附件的邮件
    @Override
    public void sendAttachmentMail(String sendTo, String title, String content, File file) {
        MimeMessage msg = mailSender.createMimeMessage();  //创建封装对象
        try{
            MimeMessageHelper helper = new MimeMessageHelper(msg,true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(content);
            FileSystemResource fileResource = new FileSystemResource(file);  //将文件分装成这个类型以供发送
            helper.addAttachment(file.getName(),fileResource);

        }catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(msg);
    }

    //发送模板邮件
    @Override
    public void sendTemplateMail(String sendTo, String title, String demo) {       //这里的demo为模板文件名
        MimeMessage msg = mailSender.createMimeMessage();  //创建封装对象
        try{
            MimeMessageHelper helper = new MimeMessageHelper(msg,true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(title);
            //封装模板使用的数据
            Map<String,Object> model = new HashMap<>();
            model.put("content","modelString");
            //得到模板
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(demo);
            //借助freemarker里的TemplateUtils工具类把模板转换为字符串
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
            helper.setText(html,true);
        }catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(msg);
    }
}
