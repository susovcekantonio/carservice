package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.AllClientsResponseDto;
import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        Client client = new Client();
        client.setFirstName(clientRequestDto.getFirstName());
        client.setLastName(clientRequestDto.getLastName());
        client.setOib(clientRequestDto.getOib());
        client.setCity(clientRequestDto.getCity());
        client.setStreet(clientRequestDto.getStreet());
        client.setStreetNumber(clientRequestDto.getStreetNumber());
        client.setZipCode(clientRequestDto.getZipCode());
        client.setCountry(clientRequestDto.getCountry());

        Client savedClient = clientRepository.save(client);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setFirstName(savedClient.getFirstName());
        clientResponseDto.setLastName(savedClient.getLastName());
        clientResponseDto.setOib(savedClient.getOib());
        clientResponseDto.setCity(savedClient.getCity());
        clientResponseDto.setStreet(savedClient.getStreet());
        clientResponseDto.setStreetNumber(savedClient.getStreetNumber());
        clientResponseDto.setZipCode(savedClient.getZipCode());
        clientResponseDto.setCountry(savedClient.getCountry());

        return clientResponseDto;
    }

    @Override
    public List<AllClientsResponseDto> getAll() {
        List<Client> all = clientRepository.findAll();
        List<AllClientsResponseDto> savedClients = new ArrayList<>();
        for(Client client : all) {
            AllClientsResponseDto allClientsResponseDto = new AllClientsResponseDto();
            allClientsResponseDto.setId(client.getId());
            allClientsResponseDto.setFirstName(client.getFirstName());
            allClientsResponseDto.setLastName(client.getLastName());
            allClientsResponseDto.setOib(client.getOib());
            allClientsResponseDto.setCity(client.getCity());
            allClientsResponseDto.setStreet(client.getStreet());
            allClientsResponseDto.setStreetNumber(client.getStreetNumber());
            allClientsResponseDto.setZipCode(client.getZipCode());
            allClientsResponseDto.setCountry(client.getCountry());
            savedClients.add(allClientsResponseDto);
        }
        return savedClients;
        }

    @Override
    public ClientResponseDto getById(Long id) {
        Client searchedClient = clientRepository.findById(id).orElse(null);

        ClientResponseDto clientResponseDto = new ClientResponseDto();

        if(searchedClient!=null) {
            clientResponseDto.setFirstName(searchedClient.getFirstName());
            clientResponseDto.setLastName(searchedClient.getLastName());
            clientResponseDto.setOib(searchedClient.getOib());
            clientResponseDto.setCity(searchedClient.getCity());
            clientResponseDto.setStreet(searchedClient.getStreet());
            clientResponseDto.setStreetNumber(searchedClient.getStreetNumber());
            clientResponseDto.setZipCode(searchedClient.getZipCode());
            clientResponseDto.setCountry(searchedClient.getCountry());
        }
        return clientResponseDto;
    }
}


