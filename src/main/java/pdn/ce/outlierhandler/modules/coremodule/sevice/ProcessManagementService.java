package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProcessManagementService {
    @Autowired
    private EngineExecutionService engineExecutionService;
    public String startAExecutionProcess() throws IOException {
        List<String> args = new LinkedList<>();
        Process process = engineExecutionService.executeProcess(args);
        int read = process.getInputStream().read();

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
        }
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
            outputBuffer.append(s);
        }

        return process.toString() + " " + outputBuffer.toString();
    }
}
