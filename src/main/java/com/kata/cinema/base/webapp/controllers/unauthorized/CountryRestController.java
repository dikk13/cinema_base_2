package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.models.Country;
import com.kata.cinema.base.service.entity.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/countries")
    public void saveNewCountry(@RequestBody Country country) {
        country.setId(null);
        countryService.update(country);
    }

    @PutMapping("/countries/{id}?")
    public ResponseEntity<Void> updateCountry(@RequestBody Country country) {
        countryService.update(country);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") long id) {
        countryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
