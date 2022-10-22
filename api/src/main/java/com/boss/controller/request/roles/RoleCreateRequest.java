package com.boss.controller.request.roles;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Schema(description = "Create role object without system info")
public class RoleCreateRequest {

    @Schema(example = "name", required = true, minLength = 1, maxLength = 20)
    private String roleName;

}