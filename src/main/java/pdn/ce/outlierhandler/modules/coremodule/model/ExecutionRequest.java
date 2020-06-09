package pdn.ce.outlierhandler.modules.coremodule.model;

import javax.persistence.*;

@Entity
@Table(name = "oh_execution_request")
public class ExecutionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "dataset")
    private String dataset;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure inputFile1;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure inputFile2;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure inputFile3;
    @ManyToOne(cascade = CascadeType.ALL)
    private FileStructure outputFile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
}
