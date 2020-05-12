package pdn.ce.outlierhandler.modules.coremodule.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "oh_execution_response_data")
public class ExecutionResponseData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @OneToMany
    private List<BinData> bins;
    @OneToOne
    private FileStructure results;

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
}
