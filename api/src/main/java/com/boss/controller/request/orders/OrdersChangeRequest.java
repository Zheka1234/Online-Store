package com.boss.controller.request.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrdersChangeRequest extends OrdersCreatRequest{

    @Schema(example = "orders ID", required = true)
    private Long id;
}
