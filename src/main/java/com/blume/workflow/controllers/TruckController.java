package com.blume.workflow.controllers;

import com.blume.workflow.models.Truck;
import com.blume.workflow.services.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/truck")
public class TruckController {
    @Autowired
    private TruckService truckService;

    @PostMapping("/save")
    public ResponseEntity<Truck> saveTruck(@RequestBody Truck truck){
        Truck newTruck= this.truckService.saveTruck(truck);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTruck);
    }
}
