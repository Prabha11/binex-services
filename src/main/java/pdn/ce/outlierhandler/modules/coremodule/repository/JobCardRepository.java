package pdn.ce.outlierhandler.modules.coremodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdn.ce.outlierhandler.modules.coremodule.model.JobCard;

@Repository
public interface JobCardRepository extends JpaRepository<JobCard, Long> {
}
