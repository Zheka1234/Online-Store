package com.boss.controller.request.suppliers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Create suppliers object without system info")
public class SuppliersCreat {

    @Schema(example = "Apple", required = true, minLength = 3, maxLength = 20)
    @Size(min = 3, max = 20)
    @NotBlank
    private String nameSuppliers;

    @Schema(example = "Leningrad", required = true, minLength = 3, maxLength = 20)
    @Size(min = 3, max = 20)
    @NotBlank
    private String addressSuppliers;

    @Schema(example = "2020327", required = true, minLength = 3, maxLength = 20)
    @Size(min = 3, max = 20)
    @NotBlank
    private String phoneSuppliers;
}
