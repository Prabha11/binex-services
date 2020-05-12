package pdn.ce.outlierhandler.modules.coremodule.model;

import javax.persistence.*;

@Entity
@Table(name = "oh_execution_request")
public class ExecutionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "dataset")
    private String dataset;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure inputFile1;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure inputFile2;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure inputFile3;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure inputFile4;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure inputFile5;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure inputFile6;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure outputFile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public FileStructure getInputFile4() {
        return inputFile4;
    }

    public void setInputFile4(FileStructure inputFile4) {
        this.inputFile4 = inputFile4;
    }

    public FileStructure getInputFile5() {
        return inputFile5;
    }

    public void setInputFile5(FileStructure inputFile5) {
        this.inputFile5 = inputFile5;
    }

    public FileStructure getInputFile6() {
        return inputFile6;
    }

    public void setInputFile6(FileStructure inputFile6) {
        this.inputFile6 = inputFile6;
    }

    public FileStructure getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(FileStructure outputFile) {
        this.outputFile = outputFile;
    }
}
