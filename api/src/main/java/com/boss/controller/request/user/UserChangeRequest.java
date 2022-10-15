package com.boss.controller.request.user;


import lombok.Data;

@Data
public class UserChangeRequest extends UserCreateRequest {
    private Long id;
}