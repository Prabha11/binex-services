package pdn.ce.outlierhandler.modules.coremodule.model;

import javax.persistence.*;

@Entity
@Table(name = "oh_bin_data")
public class BinData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "number_of_contigs_in_old_bin")
    private int numberOfContigsInOldBin;
    @Column(name = "number_of_contigs_in_new_bin")
    private int numberOfContigsInNewBin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfContigsInOldBin() {
        return numberOfContigsInOldBin;
    }

    public void setNumberOfContigsInOldBin(int numberOfContigsInOldBin) {
        this.numberOfContigsInOldBin = numberOfContigsInOldBin;
    }

    public int getNumberOfContigsInNewBin() {
        return numberOfContigsInNewBin;
    }

    public void setNumberOfContigsInNewBin(int numberOfContigsInNewBin) {
        this.numberOfContigsInNewBin = numberOfContigsInNewBin;
    }
}
