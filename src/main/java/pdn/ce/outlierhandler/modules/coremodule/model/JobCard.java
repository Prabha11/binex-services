package pdn.ce.outlierhandler.modules.coremodule.model;

import javax.persistence.*;

@Entity
@Table(name = "oh_job_card")
public class JobCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "execution_order_id")
    private long executionOrderID;
    @Column(name = "card_name")
    private String cardName;
    @ManyToOne
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExecutionOrderID() {
        return executionOrderID;
    }

    public void setExecutionOrderID(long executionOrderID) {
        this.executionOrderID = executionOrderID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
