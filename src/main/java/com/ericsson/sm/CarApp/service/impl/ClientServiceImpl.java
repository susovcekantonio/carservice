package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.ClientService;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientDtoMapper clientDtoMapper;

    @Override
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        Client client = clientDtoMapper.toDto(clientRequestDto);

        Client savedClient = clientRepository.save(client);

        ClientResponseDto clientResponseDto = clientDtoMapper.toDto(savedClient);

        return clientResponseDto;
    }

    @Override
    public Page<ClientResponseDto> getAll(String firstName, String lastName, Pageable pageable) {
            return clientRepository.findByFirstOrLastName(firstName,lastName,pageable).map(clientDtoMapper::toDto);
        }

    @Override
    public ClientResponseDto getById(Long id) {
        Client searchedClient = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client doesn't exist"));

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto = clientDtoMapper.toDto(searchedClient);

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


        searchedClient = clientDtoMapper.toDto(clientRequestDto);

        clientRepository.save(searchedClient);

        ClientResponseDto clientResponseDto = clientDtoMapper.toDto(searchedClient);

        return ResponseEntity.ok(clientResponseDto);
    }
}


