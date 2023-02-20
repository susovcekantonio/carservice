package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "oib", source = "oib")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "street", source = "street")
    @Mapping(target = "streetNumber", source = "streetNumber")
    @Mapping(target = "zipCode", source = "zipCode")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "cars", source = "cars")
    ClientResponseDto toDto(Client client);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "oib", source = "oib")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "street", source = "street")
    @Mapping(target = "streetNumber", source = "streetNumber")
    @Mapping(target = "zipCode", source = "zipCode")
    @Mapping(target = "country", source = "country")
    Client toEntity(ClientRequestDto clientRequestDto);
}
