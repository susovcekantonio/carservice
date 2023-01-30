package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.ClientService;
import com.ericsson.sm.CarApp.service.mapper.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final DtoMapper dtoMapper;

    @Override
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        Client client = dtoMapper.toDto(clientRequestDto);

        Client savedClient = clientRepository.save(client);

        ClientResponseDto clientResponseDto = dtoMapper.toDto(savedClient);

        return clientResponseDto;
    }

    @Override
    public Page<ClientResponseDto> getAll(String firstName, String lastName, Pageable pageable) {
            return clientRepository.findByFirstOrLastName(firstName,lastName,pageable).map(dtoMapper::toDto);
        }

    @Override
    public ClientResponseDto getById(Long id) {
        Client searchedClient = clientRepository.findById(id).orElse(null);

        ClientResponseDto clientResponseDto = new ClientResponseDto();

        if(searchedClient!=null) {
            clientResponseDto = dtoMapper.toDto(searchedClient);
        }
        return clientResponseDto;
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Client searchedClient = clientRepository.findById(id).orElse(null);
        if(searchedClient!=null){
            clientRepository.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.ok("Doesn't exist");

    }

    @Override
    public ResponseEntity<?> update(Long id, ClientRequestDto clientRequestDto) {
        Client searchedClient = clientRepository.findById(id).orElse(null);

        if(searchedClient==null) {
            return ResponseEntity.ok("Doesn't exist");
        }

        searchedClient = dtoMapper.toDto(clientRequestDto);

        clientRepository.save(searchedClient);

        ClientResponseDto clientResponseDto = dtoMapper.toDto(searchedClient);

        return ResponseEntity.ok(clientResponseDto);
    }
}


