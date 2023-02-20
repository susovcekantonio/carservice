package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.ClientService;
import com.ericsson.sm.CarApp.service.mapper.ClientMapper;
import com.ericsson.sm.CarApp.validation.ClientValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientValidation clientValidation;

    @Override
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        Client client = clientMapper.toEntity(clientRequestDto);
        clientValidation.validate(client);

        Client savedClient = clientRepository.save(client);
        List<Car> cars = new ArrayList<>();
        savedClient.setCars(cars);

        ClientResponseDto clientResponseDto = clientMapper.toDto(savedClient);

        return clientResponseDto;
    }

    @Override
    public Page<ClientResponseDto> getAll(String firstName, String lastName, Pageable pageable) {
            return clientRepository.findByFirstOrLastName(firstName,lastName,pageable).map(clientMapper::toDto);
        }

    @Override
    public ClientResponseDto getById(Long id) {
        Client searchedClient = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist"));

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto = clientMapper.toDto(searchedClient);

        return clientResponseDto;
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Client searchedClient = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist"));
        clientRepository.deleteById(id);

        return ResponseEntity.ok("Deleted");
    }

    @Override
    public ResponseEntity<?> update(Long id, ClientRequestDto clientRequestDto) {

        Client searchedClient = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist"));


        searchedClient = clientMapper.toEntity(clientRequestDto);

        clientRepository.save(searchedClient);

        ClientResponseDto clientResponseDto = clientMapper.toDto(searchedClient);

        return ResponseEntity.ok(clientResponseDto);
    }
}


