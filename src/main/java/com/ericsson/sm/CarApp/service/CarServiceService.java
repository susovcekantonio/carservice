package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.CarServiceIsPaidRequestDto;
import com.ericsson.sm.CarApp.dto.CarServiceIsPaidResponseDto;
import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import org.springframework.http.ResponseEntity;

public interface CarServiceService {
    ClientResponseDto save(Long clientId, Long carId, CarServiceRequestDto carServiceRequestDto);

    ResponseEntity<String> deleteById(Long clientId, Long carId, Long carServiceId);

    ResponseEntity<?> updateById(Long clientId, Long carId, Long carServiceId, CarServiceRequestDto carServiceRequestDto);

    CarServiceIsPaidResponseDto updateIsPaid(Long clientId, Long carId, Long carServiceId, CarServiceIsPaidRequestDto carServiceIsPaidRequestDto);
}
