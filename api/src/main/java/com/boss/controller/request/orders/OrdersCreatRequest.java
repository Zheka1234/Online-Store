package com.boss.controller.request.orders;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Create orders object without system info")
public class OrdersCreatRequest {

    @Schema(example = "1", required = true, minLength = 1)
    private Long idUser;

    @Schema(example = "1", required = true, minLength = 1)
    private Long idPhone;

    @Schema(example = "1", required = true, minLength = 1)
    private Long idPoint;

    @Schema(example = "100", required = true, minLength = 1)
    private Double totalSum;

    @Schema(example = "cards", required = true, minLength = 5, maxLength = 10)
    @Size(min = 5, max = 10)
    @NotBlank
    private String paymentType;


}

