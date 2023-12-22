package com.blume.workflow.controllers;

import com.blume.workflow.models.Carrier;
import com.blume.workflow.services.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrier")
public class CarrierController {
    @Autowired
    private CarrierService carrierService;
    @PostMapping("/save")
    public ResponseEntity<Carrier> saveCarrier(@RequestBody Carrier carrier){
       Carrier newCarrier= this.carrierService.saveCarrier(carrier);
       return ResponseEntity.status(HttpStatus.CREATED).body(newCarrier);
    }
}
