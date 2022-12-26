package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.AddressRequestDto;
import com.kata.cinema.base.dto.response.AddressResponseDto;
import com.kata.cinema.base.models.Address;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressRequestDto addressRequestDto);

    AddressResponseDto toDto (Address address);

    List<AddressResponseDto> toDTOList(List<Address> list);

    Address toAddress(AddressResponseDto addressResponseDto);
}
