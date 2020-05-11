package pdn.ce.outlierhandler.modules.coremodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdn.ce.outlierhandler.modules.coremodule.sevice.EngineExecutionService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class EngineRunningController {
    @Autowired
    EngineExecutionService engineExecuteService;

    @GetMapping("/run")
    public String runBinningProcess() throws IOException {
        List<String> args = new LinkedList<>();
        Process process = engineExecuteService.executeProcess(args);
        int read = process.getInputStream().read();
        return process.toString() + read;
    }
}
