package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;

public interface CarService {
    public ClientResponseDto save(Long id, CarRequestDto carRequestDto);
}
