package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
//@SuperBuilder
public class User {
    private Integer id;
    private String name;
    private Integer age;

}
