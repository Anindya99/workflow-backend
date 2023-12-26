package com.blume.workflow.repositories;

import com.blume.workflow.models.TruckStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckStatusRepository extends JpaRepository<TruckStatus,Long> {
}
