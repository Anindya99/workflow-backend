package com.blume.workflow.repositories;

import com.blume.workflow.models.WorkOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderStatusRepository extends JpaRepository<WorkOrderStatus,Long> {
}
