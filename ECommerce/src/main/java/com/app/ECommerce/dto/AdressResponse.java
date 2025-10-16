package com.app.ECommerce.dto;

import lombok.Data;

@Data
public class AdressDTO {
    private String street;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
