package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DtoMapper {
    public ClientResponseDto toDto(Client client){
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setFirstName(client.getFirstName());
        clientResponseDto.setLastName(client.getLastName());
        clientResponseDto.setOib(client.getOib());
        clientResponseDto.setCity(client.getCity());
        clientResponseDto.setStreet(client.getStreet());
        clientResponseDto.setStreetNumber(client.getStreetNumber());
        clientResponseDto.setZipCode(client.getZipCode());
        clientResponseDto.setCountry(client.getCountry());

        return clientResponseDto;
    }

    public static Client toDto(ClientRequestDto clientRequestDto){
        Client client = new Client();
        client.setFirstName(clientRequestDto.getFirstName());
        client.setLastName(clientRequestDto.getLastName());
        client.setOib(clientRequestDto.getOib());
        client.setCity(clientRequestDto.getCity());
        client.setStreet(clientRequestDto.getStreet());
        client.setStreetNumber(clientRequestDto.getStreetNumber());
        client.setZipCode(clientRequestDto.getZipCode());
        client.setCountry(clientRequestDto.getCountry());

        return client;
    }


}
