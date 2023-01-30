package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    ClientResponseDto save(ClientRequestDto clientRequestDto);
    Page<ClientResponseDto> getAll(String firstName, String lastName, Pageable pageable);

    public ClientResponseDto getById(Long id);
    public ResponseEntity<String> deleteById(Long id);

    ResponseEntity<?> update(Long id, ClientRequestDto clientRequestDto);
}
