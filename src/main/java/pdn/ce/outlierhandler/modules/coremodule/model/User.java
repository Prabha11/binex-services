package pdn.ce.outlierhandler.modules.coremodule.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "oh_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "name")
    private boolean name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }
}
