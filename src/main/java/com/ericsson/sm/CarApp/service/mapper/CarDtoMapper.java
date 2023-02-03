package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<CarResponseDto> toDto(List<Car> cars){
        List<CarResponseDto> savedCars = new ArrayList<>();

        if (cars != null && !cars.isEmpty()) {
            for (Car car : cars) {
                CarResponseDto carResponseDto = new CarResponseDto();
                carResponseDto.setCarType(car.getCarType());
                carResponseDto.setManufactureYear(car.getManufactureYear());
                carResponseDto.setRegistrationMark(car.getRegistrationMark());
                carResponseDto.setColor(car.getColor());
                savedCars.add(carResponseDto);
            }
        }
        return savedCars;
    }
}
