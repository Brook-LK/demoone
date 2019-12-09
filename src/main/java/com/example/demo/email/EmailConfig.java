package com.example.demo.email;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component  //创建Email的对象
@Data
public class EmailConfig {
    @Value("${spring.mail.username}")
    private String emailFrom;
}
