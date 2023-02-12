package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.CarService;
import com.ericsson.sm.CarApp.service.mapper.CarDtoMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
import com.ericsson.sm.CarApp.validation.CarValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final CarDtoMapper carDtoMapper;
    private final ClientDtoMapper clientDtoMapper;

    public ClientResponseDto save(Long id, CarRequestDto carRequestDto){
        Car car= carDtoMapper.toEntity(carRequestDto);
        CarValidation carValidation = new CarValidation();
        carValidation.validate(car);
        car.setClient(clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist")));
        carRepository.save(car);
        ClientResponseDto clientResponseDto= clientDtoMapper.toDto(clientRepository.findById(id).orElse(null));

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

        car=carDtoMapper.toEntityWithId(carId,carRequestDto);
        car.setClient(clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist")));
        carRepository.save(car);
        client.getCars().add(car);
        CarResponseDto carResponseDto=carDtoMapper.toDto(car);

        return ResponseEntity.ok(carResponseDto);
    }
}
