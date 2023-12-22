package com.blume.workflow.services;

import com.blume.workflow.models.Carrier;
import com.blume.workflow.repositories.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrierService {
    @Autowired
    private CarrierRepository carrierRepository;
    public Carrier saveCarrier(Carrier carrier){
        return this.carrierRepository.save(carrier);
    }
}
