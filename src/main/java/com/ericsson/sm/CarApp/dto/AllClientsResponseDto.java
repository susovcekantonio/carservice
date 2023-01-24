package com.ericsson.sm.CarApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllClientsResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String oib;
    private String city;
    private String street;
    private String streetNumber;
    private String zipCode;
    private String country;


}
