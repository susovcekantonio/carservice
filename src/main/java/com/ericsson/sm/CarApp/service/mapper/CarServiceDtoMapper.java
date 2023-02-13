package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.CarServiceResponseDto;
import com.ericsson.sm.CarApp.model.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceDtoMapper {

    public CarService toEntity(CarServiceRequestDto carServiceRequestDto) {
        CarService carService = new CarService();

        carService.setDateOfService(carServiceRequestDto.getDateOfService());
        carService.setWorkerFirstName(carServiceRequestDto.getWorkerFirstName());
        carService.setWorkerLastName(carServiceRequestDto.getWorkerLastName());
        carService.setWorkDescription(carServiceRequestDto.getWorkDescription());
        carService.setPrice(carServiceRequestDto.getPrice());
        carService.setPaid(carServiceRequestDto.isPaid());

        return carService;
    }

    public List<CarServiceResponseDto> toDto(List<CarService> carServices) {
        List<CarServiceResponseDto> savedCarServices = new ArrayList<>();
        if (!carServices.isEmpty() && carServices != null) {
            for(CarService carService : carServices) {
                CarServiceResponseDto carServiceResponseDto = new CarServiceResponseDto();
                carServiceResponseDto.setDateOfService(carService.getDateOfService());
                carServiceResponseDto.setWorkerFirstName(carService.getWorkerFirstName());
                carServiceResponseDto.setWorkerLastName(carService.getWorkerLastName());
                carServiceResponseDto.setWorkDescription(carService.getWorkDescription());
                carServiceResponseDto.setPrice(carService.getPrice());
                carServiceResponseDto.setPaid(carService.isPaid());
                savedCarServices.add(carServiceResponseDto);
            }
        }
        return savedCarServices;
    }
}
