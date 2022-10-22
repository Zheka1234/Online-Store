package com.boss.controller.request.phone;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PhoneChangeRequest extends PhoneCreatRequest{

    @Schema(example = "phone ID", required = true)
    private Long id;

}
