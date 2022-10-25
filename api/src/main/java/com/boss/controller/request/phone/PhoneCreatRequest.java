package com.boss.controller.request.phone;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PhoneCreatRequest {

    @Schema(example = "Apple", required = true, minLength = 3, maxLength = 20)
    @Size(min = 3, max = 20)
    @NotBlank
    private String brand;

    @Schema(example = "Iphone9999", required = true, minLength = 3, maxLength = 20)
    @Size(min = 3, max = 20)
    @NotBlank
    private String model;

    @Schema(example = "Red", required = true, minLength = 3, maxLength = 20)
    @Size(min = 3, max = 20)
    @NotBlank
    private String color;

    @Schema(example = "1000000", required = true, minLength = 1)
    @NotBlank
    private Double price;

    @Schema(example = "Vedro", required = true, minLength = 3, maxLength = 20)
    @Size(min = 3, max = 20)
    @NotBlank
    private String description;
}
