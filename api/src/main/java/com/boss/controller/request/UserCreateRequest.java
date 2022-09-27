package com.boss.controller.request;


import lombok.Data;

@Data
public class UserCreateRequest {

    private String nameUsers;

    private String surnameUsers;

    private Double buys;
}
