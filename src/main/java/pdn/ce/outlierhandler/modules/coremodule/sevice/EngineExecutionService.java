package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.util.ProcessExitDetector;
import pdn.ce.outlierhandler.modules.coremodule.util.ProcessListener;

import java.util.*;
import java.io.IOException;

@Service
public class EngineExecutionService {
    private static final String PYTHON_PATH =
            "C:\\Users\\PRABHA\\AppData\\Local\\Programs\\Python\\Python36\\python  /c start python ";
    private static final String SCRIPT_PATH = "engine\\Model python file\\test_script.py ";

    public Process executeProcess(List<String> arguments) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(
                    PYTHON_PATH + SCRIPT_PATH + getArguments(arguments),
                    null);
            System.out.println(process);
            ProcessExitDetector processExitDetector = new ProcessExitDetector(process);
            processExitDetector.addProcessListener(new ProcessListener() {
                public void processFinished(Process process) {
                    System.out.println("The subprocess has finished.");
                }
            });
            processExitDetector.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return process;
    }

    private String getArguments(List<String> arguments) {
        String line = "";
        for (String argument : arguments) {
            line = line + " " + argument;
        }
        return line;
    }
}
