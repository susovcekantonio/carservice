package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.CarService;
import com.ericsson.sm.CarApp.service.mapper.CarDtoMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final CarDtoMapper carDtoMapper;
    private final ClientDtoMapper clientDtoMapper;

    public ClientResponseDto save(Long id, CarRequestDto carRequestDto){
        Car car= carDtoMapper.toEntity(carRequestDto);
        car.setClient(clientRepository.findById(id).orElse(null));
        carRepository.save(car);
        ClientResponseDto clientResponseDto= clientDtoMapper.toDto(clientRepository.findById(id).orElse(null));

        return clientResponseDto;
    }

    public ResponseEntity<String> deleteById(Long clientId,Long carId){
        Client client = clientRepository.findById(clientId).orElse(null);
        Car car = carRepository.findById(carId).orElse(null);
        if(client==null) return ResponseEntity.ok("Client doesn't exist");
        if(car==null) return ResponseEntity.ok("Car doesn't exist");
        client.getCars().remove(car);
        carRepository.deleteById(carId);

        return ResponseEntity.ok("Deleted");
    }
}
