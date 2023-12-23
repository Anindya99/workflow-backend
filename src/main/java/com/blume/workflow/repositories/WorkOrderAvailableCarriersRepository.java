package com.blume.workflow.repositories;

import com.blume.workflow.models.WorkOrderAvailableCarriers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderAvailableCarriersRepository extends JpaRepository<WorkOrderAvailableCarriers,Long> {
}
