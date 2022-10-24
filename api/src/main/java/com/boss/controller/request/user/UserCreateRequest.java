package com.boss.controller.request.user;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Create user object without system info")
public class UserCreateRequest {

    @Schema(example = "name", required = true, minLength = 1, maxLength = 30)
    @Size(min = 2, max = 30)
    @NotBlank
    private String nameUsers;

    @Schema(example = "surname", required = true, minLength =10, maxLength = 70)
    @Size(min = 2, max = 70)
    @NotBlank
    private String surnameUsers;

    @Schema(example = "0", required = true)
    private Double buys;

    @Schema(example = "loginUser", required = true, minLength = 2, maxLength = 30)
    @Size(min = 2, max = 20)
    private String loginUser;

    @Schema(example = "passwordUser", required = true, minLength = 2, maxLength = 30)
    @Size(min = 2, max = 30)
    private String passwordUsers;

    @Schema(example = "false", required = true)
    private Boolean isDeleted;
}
