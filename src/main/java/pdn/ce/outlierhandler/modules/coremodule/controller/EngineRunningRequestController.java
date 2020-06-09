package pdn.ce.outlierhandler.modules.coremodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdn.ce.outlierhandler.modules.coremodule.model.*;
import pdn.ce.outlierhandler.modules.coremodule.repository.ExecutionOrderRepository;
import pdn.ce.outlierhandler.modules.coremodule.repository.JobCardRepository;
import pdn.ce.outlierhandler.modules.coremodule.repository.UserRepository;
import pdn.ce.outlierhandler.modules.coremodule.sevice.ExecutionRequestService;
import pdn.ce.outlierhandler.modules.coremodule.sevice.ProcessSchedulingService;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class EngineRunningRequestController {
    @Autowired
    ProcessSchedulingService processSchedulingService;
    @Autowired
    ExecutionRequestService executionRequestService;
    @Autowired
    ExecutionOrderRepository executionOrderRepository;
    @Autowired
    JobCardRepository jobCardRepository;
    @Autowired
    UserRepository ur;

    @PostMapping("/exec/run")
    public JobCard runBinningProcess(@RequestBody ExecutionRequest executionRequest) throws IOException {
        User user = ur.getOne((long) 1);
        ExecutionRequest validatedExecutionRequest = executionRequestService
                .validateExecutionRequest(executionRequest, user);
        return processSchedulingService.createAProcessSchedule(validatedExecutionRequest, user);
    }

    @PostMapping("/exec/result")
    public JobCard getResultFile(@RequestBody ExecutionJobCard executionJobCard) throws IOException {
        User user = ur.getOne((long) 1);
        return null;
    }

    @PostMapping("/exec/job-cards")
    public List<JobCard> getJobCards() throws IOException {
        return jobCardRepository.findAll();
    }

    @GetMapping("/exec/result/{orderID}")
    public ExecutionResponse getResultByOrderId(@PathVariable() long orderID) {
        ExecutionOrder executionOrder = executionOrderRepository.getOne(orderID);
        return new ExecutionResponse(executionOrder,executionOrder.getExecutionRequest());
    }
}
