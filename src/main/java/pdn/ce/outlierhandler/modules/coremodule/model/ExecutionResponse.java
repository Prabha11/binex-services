package pdn.ce.outlierhandler.modules.coremodule.model;

public class ExecutionResponse {
    private String dataset;
    private FileStructure inputFile1;
    private FileStructure inputFile2;
    private FileStructure inputFile3;
    private FileStructure outputFile;
    private ExecutionResponseData executionResponseData;

    public ExecutionResponse(ExecutionOrder executionOrder, ExecutionRequest executionRequest) {
        this.dataset = executionRequest.getDataset();
        this.inputFile1 = executionRequest.getInputFile1();
        this.inputFile2 = executionRequest.getInputFile2();
        this.inputFile3 = executionRequest.getInputFile3();
        this.outputFile = executionRequest.getOutputFile();
        this.executionResponseData = executionOrder.getExecutionResponseData();
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public FileStructure getInputFile1() {
        return inputFile1;
    }

    public void setInputFile1(FileStructure inputFile1) {
        this.inputFile1 = inputFile1;
    }

    public FileStructure getInputFile2() {
        return inputFile2;
    }

    public void setInputFile2(FileStructure inputFile2) {
        this.inputFile2 = inputFile2;
    }

    public FileStructure getInputFile3() {
        return inputFile3;
    }

    public void setInputFile3(FileStructure inputFile3) {
        this.inputFile3 = inputFile3;
    }

    public FileStructure getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(FileStructure outputFile) {
        this.outputFile = outputFile;
    }

    public ExecutionResponseData getExecutionResponseData() {
        return executionResponseData;
    }

    public void setExecutionResponseData(ExecutionResponseData executionResponseData) {
        this.executionResponseData = executionResponseData;
    }
}
