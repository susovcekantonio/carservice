package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.service.CarServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarServiceController {
    private final CarServiceService carServiceService;

    @PostMapping("/api/customers/{clientId}/cars/{carId}/car-services")
    public ClientResponseDto save(@PathVariable Long clientId, @PathVariable Long carId, @RequestBody CarServiceRequestDto carServiceRequestDto){
        return carServiceService.save(clientId,carId,carServiceRequestDto);
    }
}
