package pdn.ce.outlierhandler.modules.coremodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pdn.ce.outlierhandler.modules.coremodule.model.FileStructure;

import java.util.List;

@Repository
public interface FileStructureRepository extends JpaRepository<FileStructure, Long> {
    @Query("SELECT fs FROM FileStructure fs WHERE fs.user.id = :userID AND fs.name = 'ROOT'")
    List<FileStructure> findRootByUserID(@Param("userID") long userID);

    @Query("SELECT fs FROM FileStructure fs WHERE fs.name = 'Sample Data'")
    List<FileStructure> getSampleDataFiles();
}
