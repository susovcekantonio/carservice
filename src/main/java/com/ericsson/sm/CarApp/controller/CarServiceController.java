package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.service.CarServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CarServiceController {
    private final CarServiceService carServiceService;

    @PostMapping("/api/customers/{clientId}/cars/{carId}/car-services")
    public ClientResponseDto save(@PathVariable Long clientId, @PathVariable Long carId, @RequestBody CarServiceRequestDto carServiceRequestDto){
        return carServiceService.save(clientId,carId,carServiceRequestDto);
    }

    @DeleteMapping("/api/customers/{clientId}/cars/{carId}/car-services/{carServiceId}")
    public ResponseEntity<String> deleteById(@PathVariable Long clientId, @PathVariable Long carId, @PathVariable Long carServiceId){
        return carServiceService.deleteById(clientId,carId, carServiceId);
    }
}
