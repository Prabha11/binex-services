package pdn.ce.outlierhandler.modules.coremodule.model;

import javax.persistence.*;

@Entity
@Table(name = "oh_execution_order")
public class ExecutionOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    private ExecutionRequest executionRequest;
    @OneToOne(cascade = CascadeType.ALL)
    private ExecutionResponseData executionResponseData;
    @Column(name = "finished")
    private boolean finished;
    @Column(name = "progress")
    private int progress;
    @Column(name = "log")
    private String log;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExecutionRequest getExecutionRequest() {
        return executionRequest;
    }

    public void setExecutionRequest(ExecutionRequest executionRequest) {
        this.executionRequest = executionRequest;
    }

    public ExecutionResponseData getExecutionResponseData() {
        return executionResponseData;
    }

    public void setExecutionResponseData(ExecutionResponseData executionResponseData) {
        this.executionResponseData = executionResponseData;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
