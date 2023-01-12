package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.response.CountryResponseDto;
import com.kata.cinema.base.service.dto.CountryResponseDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountriesRestController {
    private final CountryResponseDtoService countryResponseDtoService;

    public CountriesRestController(CountryResponseDtoService countryResponseDtoService) {
        this.countryResponseDtoService = countryResponseDtoService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryResponseDto>> getAllCountries() {
        return new ResponseEntity<>(countryResponseDtoService.getAllCountryResponseDto(), HttpStatus.OK);
    }

    @GetMapping("/search/countries")
    public ResponseEntity<List<CountryResponseDto>> getListCountryResponseDtoByName(
            @RequestParam("filterPattern") String filterPattern) {
        return new ResponseEntity<>(countryResponseDtoService.
                getListCountryResponseDtoByName(filterPattern), HttpStatus.OK);
    }
}
