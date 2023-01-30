package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class ClientController  {

    private final ClientService clientService;

    @PostMapping("/api/customers")
    public ClientResponseDto save(@RequestBody ClientRequestDto clientRequestDto){
        return clientService.save(clientRequestDto);
    }

    @GetMapping("/api/customers")
    public Page<ClientResponseDto> getAll(@RequestParam(name="firstname",required = false) String firstName,
                                          @RequestParam(name="lastname",required = false) String lastname,
                                          Pageable pageable){
        return clientService.getAll(firstName,lastname,pageable);
    }

    @GetMapping("/api/customers/{id}")
    public ClientResponseDto getById(@PathVariable Long id){
        return clientService.getById(id);
    }

    @DeleteMapping("/api/customers/delete/{id}")
        public ResponseEntity<String> deleteById(@PathVariable Long id){
        return clientService.deleteById(id);
    }
    @PutMapping("/api/customers/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClientRequestDto clientRequestDto){
        return clientService.update(id, clientRequestDto);
    }
}
