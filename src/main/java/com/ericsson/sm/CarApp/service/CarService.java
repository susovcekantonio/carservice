package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import org.springframework.http.ResponseEntity;

public interface CarService {
    public ClientResponseDto save(Long id, CarRequestDto carRequestDto);

    ResponseEntity<String> deleteById(Long clientId, Long carId);

    ResponseEntity<?> updateById(Long clientId, Long carId, CarRequestDto carRequestDto);
}
