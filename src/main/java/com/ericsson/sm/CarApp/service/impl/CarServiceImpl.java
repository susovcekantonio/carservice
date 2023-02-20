package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.CarService;
import com.ericsson.sm.CarApp.service.mapper.CarMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientMapper;
import com.ericsson.sm.CarApp.validation.CarValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final CarMapper carMapper;
    private final ClientMapper clientMapper;
    private final CarValidation carValidation;

    public ClientResponseDto save(Long id, CarRequestDto carRequestDto){
        Car car= carMapper.toEntity(carRequestDto);
        carValidation.validate(car);
        car.setClient(clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist")));
        List<com.ericsson.sm.CarApp.model.CarService> carServices = new ArrayList<>();
        car.setCarServices(carServices);
        carRepository.save(car);
        ClientResponseDto clientResponseDto= clientMapper.toDto(clientRepository.findById(id).orElse(null));

        return clientResponseDto;
    }

    public ResponseEntity<String> deleteById(Long clientId,Long carId){
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist"));
        Car car = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Car doesn't exist"));
        client.getCars().remove(car);
        carRepository.deleteById(carId);

        return ResponseEntity.ok("Deleted");
    }


    @Override
    public ResponseEntity<?> updateById(Long clientId, Long carId, CarRequestDto carRequestDto) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist"));
        Car car = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Car doesn't exist"));

        client.getCars().remove(car);

        car= carMapper.toEntity(carRequestDto);
        car.setId(carId);
        car.setClient(clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist")));
        carRepository.save(car);
        client.getCars().add(car);
        CarResponseDto carResponseDto= carMapper.toDto(car);

        return ResponseEntity.ok(carResponseDto);
    }
}
