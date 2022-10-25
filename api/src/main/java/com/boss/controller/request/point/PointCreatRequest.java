package com.boss.controller.request.point;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Create point object without system info")
public class PointCreatRequest {

    @Schema(example = "Address", required = true, minLength = 10, maxLength = 70)
    @Size(min = 10, max = 70)
    @NotBlank
    private String address;

    @Schema(example = "8", required = true, minLength = 1, maxLength = 20)
    @Size(min = 1, max = 20)
    @NotBlank
    private String hours;


}
