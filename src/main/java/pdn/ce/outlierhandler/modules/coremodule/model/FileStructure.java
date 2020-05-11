package pdn.ce.outlierhandler.modules.coremodule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "oh_file_structure")
public class FileStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "is_folder")
    private boolean folder;
    @Column(name = "name")
    private String name;
    @OneToMany
    private List<FileStructure> childFileStructures;
    @JsonIgnore
    @Column(name = "file_location")
    private String fileLocation;
    @ManyToOne
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFolder() {
        return folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    public List<FileStructure> getChildFileStructures() {
        return childFileStructures;
    }

    public void setChildFileStructures(List<FileStructure> childFileStructures) {
        this.childFileStructures = childFileStructures;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
