package pdn.ce.outlierhandler.modules.coremodule.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "oh_execution_response_data")
public class ExecutionResponseData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BinData> bins;
    @OneToOne
    private FileStructure results;
    @Column(name = "old_percentage")
    private double oldPercentage;
    @Column(name = "new_percentage")
    private double newPercentage;
    @Column(name = "old_accuracy")
    private double oldAccuracy;
    @Column(name = "new_accuracy")
    private double newAccuracy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BinData> getBins() {
        return bins;
    }

    public void setBins(List<BinData> bins) {
        this.bins = bins;
    }

    public FileStructure getResults() {
        return results;
    }

    public void setResults(FileStructure results) {
        this.results = results;
    }

    public double getOldPercentage() {
        return oldPercentage;
    }

    public void setOldPercentage(double oldPercentage) {
        this.oldPercentage = oldPercentage;
    }

    public double getNewPercentage() {
        return newPercentage;
    }

    public void setNewPercentage(double newPercentage) {
        this.newPercentage = newPercentage;
    }

    public double getOldAccuracy() {
        return oldAccuracy;
    }

    public void setOldAccuracy(double oldAccuracy) {
        this.oldAccuracy = oldAccuracy;
    }

    public double getNewAccuracy() {
        return newAccuracy;
    }

    public void setNewAccuracy(double newAccuracy) {
        this.newAccuracy = newAccuracy;
    }
}
