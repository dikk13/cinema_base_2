package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.request.AddressRequestDto;
import com.kata.cinema.base.dto.response.AddressResponseDto;
import com.kata.cinema.base.mappers.AddressMapper;
import com.kata.cinema.base.service.entity.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/address")
@AllArgsConstructor
public class AdminAddressRestController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping
    public ResponseEntity<List<AddressResponseDto>> getAllAddress() {
        return ResponseEntity.ok(addressMapper.toDTOList(addressService.getAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable("id") long id) {
        addressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable Long id, @RequestBody AddressRequestDto addressRequestDto) {
        addressService.updateById(id, addressRequestDto);
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<Void> addNewAddress(@RequestBody AddressRequestDto addressRequestDto) {
        addressService.create(addressMapper.toAddress(addressRequestDto));
        return ResponseEntity.ok(null);
    }
}
