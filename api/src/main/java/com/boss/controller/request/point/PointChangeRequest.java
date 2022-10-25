package com.boss.controller.request.point;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PointChangeRequest extends PointCreatRequest {

    @Schema(example = "point id", required = true, minLength = 3, maxLength = 20)
    private Long id;
}
