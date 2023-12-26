package com.blume.workflow.repositories;

import com.blume.workflow.models.WorkOrderAssignableCarriers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderAvailableCarriersRepository extends JpaRepository<WorkOrderAssignableCarriers,Long> {
}
