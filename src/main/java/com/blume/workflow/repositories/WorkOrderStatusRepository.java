package com.blume.workflow.repositories;

import com.blume.workflow.models.WorkOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderStatusRepository extends JpaRepository<WorkOrderStatus,Long> {
}
