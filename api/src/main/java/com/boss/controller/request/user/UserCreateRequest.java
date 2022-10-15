package com.boss.controller.request.user;



import lombok.Data;

@Data
public class UserCreateRequest {

    private String nameUsers;

    private String surnameUsers;

    private Double buys;

    private String loginUser;

    private String passwordUsers;

    private Boolean isDeleted;
}
