package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarDtoMapper {
    public CarResponseDto toDto(Car car){
        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setCarType(car.getCarType());
        carResponseDto.setManufactureYear(car.getManufactureYear());
        carResponseDto.setRegistrationMark(car.getRegistrationMark());
        carResponseDto.setColor(car.getColor());

        return carResponseDto;
    }

    public Car toEntity(CarRequestDto carRequestDto){
        Car car = new Car();
        car.setCarType(carRequestDto.getCarType());
        car.setManufactureYear(carRequestDto.getManufactureYear());
        car.setRegistrationMark(carRequestDto.getRegistrationMark());
        car.setColor(carRequestDto.getColor());

        return car;
    }
}
