package com.boss.controller.request.suppliers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SuppliersChangeRequest extends SuppliersCreat {

    @Schema(example = "suppliers id", required = true, minLength = 3, maxLength = 20)
    private Long id;
}
