package com.blume.workflow.repositories;

import com.blume.workflow.models.TruckInfo;
import com.blume.workflow.models.Truck_Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckStatusRepository extends JpaRepository<Truck_Status,Long> {
}
