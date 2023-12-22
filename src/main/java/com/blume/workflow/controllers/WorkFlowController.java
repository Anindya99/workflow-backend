package com.blume.workflow.controllers;

import com.blume.workflow.controllers.dtos.CreateWorkFlowRequestDTO;
import com.blume.workflow.models.WorkFlow;
import com.blume.workflow.services.WorkFlowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/workflow")
public class WorkFlowController {

    @Autowired
    private WorkFlowService workFlowService;
    @PostMapping("/save")
    public ResponseEntity<WorkFlow> saveWorkFlow(@Valid @RequestBody CreateWorkFlowRequestDTO createWorkFlowRequestDTO){
        WorkFlow newWorkFlow =this.workFlowService.saveWorkFlow(createWorkFlowRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newWorkFlow);
    }
    @GetMapping("/")
    public ResponseEntity<String> getAllWorkFlows(){
        return ResponseEntity.status(HttpStatus.OK).body("hello");
    }
}
