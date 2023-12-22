package com.blume.workflow.services;

import com.blume.workflow.models.Truck;
import com.blume.workflow.repositories.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TruckService {
    @Autowired
    private TruckRepository truckRepository;

    public Truck saveTruck(Truck truck){
        return this.truckRepository.save(truck);
    }
}
