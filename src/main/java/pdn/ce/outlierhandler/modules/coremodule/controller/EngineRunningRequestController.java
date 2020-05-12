package pdn.ce.outlierhandler.modules.coremodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionRequest;
import pdn.ce.outlierhandler.modules.coremodule.model.User;
import pdn.ce.outlierhandler.modules.coremodule.repository.UserRepository;
import pdn.ce.outlierhandler.modules.coremodule.sevice.ProcessSchedulingService;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class EngineRunningRequestController {
    @Autowired
    ProcessSchedulingService processSchedulingService;
    @Autowired
    UserRepository ur;

    @PostMapping("/exec/run")
    public String runBinningProcess(@RequestBody ExecutionRequest executionRequest) throws IOException {
        User user = ur.getOne((long) 1);
        return processSchedulingService.createAProcessSchedule(executionRequest, user);
    }
}
