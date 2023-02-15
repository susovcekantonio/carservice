package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.*;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.CarService;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.CarServiceRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.CarServiceService;
import com.ericsson.sm.CarApp.service.mapper.CarServiceDtoMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CarServiceServiceImpl implements CarServiceService {
    private final CarServiceRepository carServiceRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final CarServiceDtoMapper carServiceDtoMapper;
    private final ClientDtoMapper clientDtoMapper;

    @Override
    public ClientResponseDto save(Long clientId, Long carId, CarServiceRequestDto carServiceRequestDto) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist"));
        Car car = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Car doesn't exist"));
        CarService carService = carServiceDtoMapper.toEntity(carServiceRequestDto);
        carService.setCar(car);
        carServiceRepository.save(carService);
        car.getCarServices().add(carService);

        return clientDtoMapper.toDto(client);
    }

    @Override
    public ResponseEntity<String> deleteById(Long clientId, Long carId, Long carServiceId) {
        CarService carService = carServiceRepository.findById(carServiceId).orElseThrow(() -> new EntityNotFoundException("CarService doesn't exist"));
        Car car = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Car doesn't exist"));
        car.getCarServices().remove(carService);
        carServiceRepository.deleteById(carServiceId);
        return ResponseEntity.ok("Deleted");
    }

    @Override
    public ResponseEntity<?> updateById(Long clientId, Long carId, Long carServiceId, CarServiceRequestDto carServiceRequestDto) {
        CarService carService = carServiceRepository.findById(carServiceId).orElseThrow(() -> new EntityNotFoundException("CarService doesn't exist"));
        Car car = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Car doesn't exist"));

        car.getCarServices().remove(carService);

        carService = carServiceDtoMapper.toEntity(carServiceRequestDto);
        carService.setId(carServiceId);
        carService.setCar(car);
        carServiceRepository.save(carService);
        car.getCarServices().add(carService);
        CarServiceResponseDto carServiceResponseDto = carServiceDtoMapper.toDto(carService);

        return ResponseEntity.ok(carServiceResponseDto);
    }

    @Override
    public CarServiceIsPaidResponseDto updateIsPaid(Long clientId, Long carId, Long carServiceId, CarServiceIsPaidRequestDto carServiceIsPaidRequestDto) {
        CarService carService = carServiceRepository.findById(carServiceId).orElseThrow(() -> new EntityNotFoundException("CarService doesn't exist"));
        carService.setPaid(carServiceIsPaidRequestDto.isPaid());
        carServiceRepository.save(carService);
        CarServiceIsPaidResponseDto carServiceIsPaidResponseDto = new CarServiceIsPaidResponseDto();
        carServiceIsPaidResponseDto.setMessage("success");

        return  carServiceIsPaidResponseDto;
    }
}
