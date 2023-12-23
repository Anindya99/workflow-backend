package com.blume.workflow.repositories;

import com.blume.workflow.models.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderRepository extends JpaRepository<WorkOrder,Long> {
}
