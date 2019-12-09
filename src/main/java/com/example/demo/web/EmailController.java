package com.example.demo.web;

import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RequestMapping("email/")
@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "simple", method = RequestMethod.GET)
    public String sendSimpleEmail(){
        emailService.sendSimpleMail("17695530803@163.com","test","javaEmailTest");
        return "success";
    }

    @RequestMapping(value = "attachment", method = RequestMethod.GET)
    public String sendAttachmentEmail(){
        File file = new File("src/main/resources/static/katong.jpg");
        emailService.sendAttachmentMail("17695530803@163.com","test","javaEmailTest",file);
        return "success";
    }

    @RequestMapping(value = "template", method = RequestMethod.GET)
    public String sendTemplateEmail(){
        emailService.sendTemplateMail("17695530803@163.com","test","demo.html");
        return "success";
    }
}
