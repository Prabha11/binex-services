package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.model.*;

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

    public ExecutionResponseData startAExecutionProcess(long executionOrderID) throws IOException {
        ExecutionOrder executionOrder = executionOrderService.getExecutionServiceByID(executionOrderID);
        List<String> args = getArgsListByExecutionRequest(executionOrder.getExecutionRequest());

        Process process = engineExecutionService.executeProcess(args);

        ExecutionResponseData executionResponseData = new ExecutionResponseData();

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        StringBuilder outputBuffer = new StringBuilder();

        List<BinData> binDataList = new LinkedList<>();
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
            outputBuffer.append(s);

            if (s.equals("start;")) {
                String noOfBinsLine = stdInput.readLine();
                int noOfBins = this.getNoOfBins(noOfBinsLine);
                System.out.println("noOfBins");
                System.out.println(noOfBins);
                if (noOfBins > 0) {
                    for (int i = 0; i < noOfBins; i++) {
                        String binDetailsLine;
                        binDetailsLine = stdInput.readLine();
                        int preNumber = getValue(binDetailsLine);
                        binDetailsLine = stdInput.readLine();
                        int newNumber = getValue(binDetailsLine);

                        BinData binData = new BinData();
                        binData.setBinName(getBinName(binDetailsLine));
                        binData.setNumberOfContigsInOldBin(preNumber);
                        binData.setNumberOfContigsInNewBin(newNumber);

                        binDataList.add(binData);
                    }
                    String oldAccuracyLine = stdInput.readLine();
                    double oldAccuracy = getValueDouble(oldAccuracyLine);
                    executionResponseData.setOldAccuracy(oldAccuracy);

                    String newAccuracyLine = stdInput.readLine();
                    double newAccuracy = getValueDouble(newAccuracyLine);
                    executionResponseData.setNewAccuracy(newAccuracy);

                    String oldPercentageLine = stdInput.readLine();
                    double oldPercentage = getValueDouble(oldPercentageLine);
                    executionResponseData.setOldPercentage(oldPercentage);

                    String newPercentageLine = stdInput.readLine();
                    double newPercentage = getValueDouble(newPercentageLine);
                    executionResponseData.setNewPercentage(newPercentage);
                }
            }
        }

        boolean error = false;
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
            outputBuffer.append(s);
            error = true;
        }

        if (error) return null;

        executionResponseData.setBins(binDataList);

        return executionResponseData;
    }

    private int getNoOfBins(String noOfBinsLine) {
        String[] noOfBinsLineSplit = noOfBinsLine.split(":");

        if ((noOfBinsLineSplit.length == 2) && noOfBinsLineSplit[0].equals("no_of_bins")) {
            try {
                return Integer.parseInt(noOfBinsLineSplit[1].trim());
            } catch (Exception e) {
                return -1;
            }
        } else return -1;
    }

    private int getValue(String binDetailsLine) {
        String[] noOfBinsLineSplit = binDetailsLine.split(":");

        if (noOfBinsLineSplit.length == 2) {
            try {
                return Integer.parseInt(noOfBinsLineSplit[1].trim());
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        } else return -1;
    }

    private double getValueDouble(String line) {
        String[] noOfBinsLineSplit = line.split(":");

        if (noOfBinsLineSplit.length == 2) {
            try {
                return Double.parseDouble(noOfBinsLineSplit[1].trim());
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        } else return -1;
    }

    private String getBinName(String binDetailsLine) {
        String[] noOfBinsLineSplit = binDetailsLine.split("_");

        if (noOfBinsLineSplit.length > 2) {
            try {
                return noOfBinsLineSplit[1].trim();
            } catch (Exception e) {
                return "Bin";
            }
        } else return "Unknown Bin";
    }

    private List<String> getArgsListByExecutionRequest(ExecutionRequest executionRequest) {
        List<String> args = new LinkedList<>();
        args.add(executionRequest.getDataset());
        args.add(fileReadService.getFilePathForPython(executionRequest.getInputFile1()));
        args.add(fileReadService.getFilePathForPython(executionRequest.getInputFile2()));
        args.add(fileReadService.getFilePathForPython(executionRequest.getInputFile3()));
        args.add(fileReadService.getFilePathForPython(executionRequest.getOutputFile()));
        return args;
    }

    public static void main(String[] args) {
        ProcessManagementService processManagementService = new ProcessManagementService();
        System.out.println(processManagementService.getBinName("bin_ 51 _no_of_binned_pre: 29"));
    }
}
