package pdn.ce.outlierhandler.modules.coremodule.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class EngineRunningController {

    @GetMapping("/run")
    public String runBinningProcess() {
        return "upload";
    }
}
