package com.ericsson.sm.CarApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClientResponseDto {

    private String firstName;
    private String lastName;
    private String oib;
    private String city;
    private String street;
    private Integer streetNumber;
    private String zipCode;
    private String country;
}
