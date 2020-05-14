package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionOrder;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionRequest;
import pdn.ce.outlierhandler.modules.coremodule.util.ProcessOutputLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProcessManagementService {
    @Autowired
    private EngineExecutionService engineExecutionService;
    @Autowired
    FileReadService fileReadService;
    @Autowired
    ExecutionOrderService executionOrderService;
    @Autowired
    TaskExecutor taskExecutor;

    public String startAExecutionProcess(long executionOrderID) throws IOException {
        ExecutionOrder executionOrder = executionOrderService.getExecutionServiceByID(executionOrderID);
        List<String> args = getArgsListByExecutionRequest(executionOrder.getExecutionRequest());

//        ProcessListener processListener = process -> {
//            System.out.println("The subprocess has finished. execution order ID : " + executionOrderID);
//            executionOrderService.markAsFinishedOrder(executionOrderID);
//        };

        Process process = engineExecutionService.executeProcess(args);

//        taskExecutor.execute(new ProcessOutputLogger(process, executionOrderService, executionOrderID));

//        Thread processOutputLogger =
//                new ProcessOutputLogger(process, executionOrderService, executionOrderID);
//        processOutputLogger.start();

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        StringBuilder outputBuffer = new StringBuilder();
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
            outputBuffer.append(s);
            executionOrderService.updateLog(executionOrderID, s);
        }
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
            outputBuffer.append(s);
        }

        executionOrderService.markAsFinishedOrder(executionOrderID);

        return process.toString() + " " + outputBuffer.toString();
    }

    private List<String> getArgsListByExecutionRequest(ExecutionRequest executionRequest) {
        List<String> args = new LinkedList<>();
        args.add(executionRequest.getDataset());
        args.add(fileReadService.getFilePathForPython(executionRequest.getInputFile1()));
        args.add(fileReadService.getFilePathForPython(executionRequest.getInputFile2()));
        args.add(fileReadService.getFilePathForPython(executionRequest.getInputFile3()));
        args.add(fileReadService.getFilePathForPython(executionRequest.getInputFile4()));
        args.add(fileReadService.getFilePathForPython(executionRequest.getInputFile5()));
        args.add(fileReadService.getFilePathForPython(executionRequest.getInputFile6()));
        args.add(fileReadService.getFilePathForPython(executionRequest.getInputFile7()));
        args.add(fileReadService.getFilePathForPython(executionRequest.getOutputFile()));
        return args;
    }
}
