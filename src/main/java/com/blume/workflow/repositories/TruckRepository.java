package com.blume.workflow.repositories;

import com.blume.workflow.models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck,Long> {
}
