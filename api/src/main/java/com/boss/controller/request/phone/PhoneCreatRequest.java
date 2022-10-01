package com.boss.controller.request.phone;


import lombok.Data;

@Data
public class PhoneCreatRequest {

    private String brand;

    private String model;

    private String color;

    private Double price;

    private String description;
}
