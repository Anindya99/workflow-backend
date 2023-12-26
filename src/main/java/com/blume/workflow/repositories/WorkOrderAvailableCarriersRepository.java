package com.blume.workflow.repositories;

import com.blume.workflow.models.WorkOrderAvailableCarriers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderAvailableCarriersRepository extends JpaRepository<WorkOrderAvailableCarriers,Long> {
}
