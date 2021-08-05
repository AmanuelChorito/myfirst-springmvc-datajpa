package com.example.springclient.controller;

import com.example.springclient.model.CountryRegion;
import com.example.springclient.service.CountryRegionServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryRegionControllerClient {
    @Autowired
    private CountryRegionServiceClient countryRegionServiceClient;

    @GetMapping("/countries")
    public List<CountryRegion> findAll() {
        return countryRegionServiceClient.findAll();
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<CountryRegion> findById(@PathVariable String id) {
        return countryRegionServiceClient.findById(id);
    }
}
