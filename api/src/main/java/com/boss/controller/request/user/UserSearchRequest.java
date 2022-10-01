package com.boss.controller.request.user;

import lombok.Data;

@Data
public class UserSearchRequest {

    private String limit;

    private String offset;

    private String searchQuery;
}
