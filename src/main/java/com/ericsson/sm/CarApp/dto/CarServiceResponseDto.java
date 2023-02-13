package com.ericsson.sm.CarApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CarServiceResponseDto {
    private Date dateOfService;
    private String workerFirstName;
    private String workerLastName;
    private String workDescription;
    private Float price;
    private boolean isPaid;
}
