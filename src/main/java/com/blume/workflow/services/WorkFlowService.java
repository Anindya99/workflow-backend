package com.blume.workflow.services;

import com.blume.workflow.controllers.dtos.CreateWorkFlowRequestDTO;
import com.blume.workflow.models.WorkFlow;
import com.blume.workflow.repositories.WorkFlowRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkFlowService {
    @Autowired
    private WorkFlowRepository workFlowRepository;

    @Autowired
    private ModelMapper modelMapper;

    public WorkFlow saveWorkFlow(CreateWorkFlowRequestDTO createWorkFlowRequestDTO) {
        WorkFlow newWorkFlow = modelMapper.map(createWorkFlowRequestDTO, WorkFlow.class);
        return workFlowRepository.save(newWorkFlow);
    }

}
