package com.blume.workflow.repositories;

import com.blume.workflow.models.WorkFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkFlowRepository extends JpaRepository<WorkFlow,Long> {
}
