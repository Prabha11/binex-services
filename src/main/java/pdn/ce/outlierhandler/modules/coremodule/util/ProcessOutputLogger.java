package pdn.ce.outlierhandler.modules.coremodule.util;

import pdn.ce.outlierhandler.modules.coremodule.sevice.ExecutionOrderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessOutputLogger extends Thread {
    private Process process;
    private ExecutionOrderService executionOrderService;
    private long executionOrderID;

    public ProcessOutputLogger(Process process, ExecutionOrderService executionOrderService, long executionOrderID) {
        this.process = process;
        this.executionOrderService = executionOrderService;
        this.executionOrderID = executionOrderID;
    }

    public void run() {

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));

        String s = null;
        StringBuilder outputBuffer = new StringBuilder();
        while (true) {
            try {
                if ((s = stdInput.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            executionOrderService.updateLog(executionOrderID, s);
            System.out.println(s);
        }

        while (true) {
            try {
                if ((s = stdError.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            executionOrderService.updateLog(executionOrderID, s);
            System.out.println(s);
        }
    }
}
