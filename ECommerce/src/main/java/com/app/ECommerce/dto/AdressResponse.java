package com.app.ECommerce.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AdressResponse {
    private String street;
    private String city;
    private String state;
    private String country;
    private String pinCode;
}
