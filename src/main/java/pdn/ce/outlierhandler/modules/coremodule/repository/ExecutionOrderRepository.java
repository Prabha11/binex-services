package pdn.ce.outlierhandler.modules.coremodule.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionOrder;

import java.util.List;

@Repository
public interface ExecutionOrderRepository extends JpaRepository<ExecutionOrder, Long> {
    @Query(value = "SELECT eo FROM ExecutionOrder eo WHERE eo.finished = false AND eo.progress = 0")
    List<ExecutionOrder> getOneUnfinishedExecutionOrder(Pageable pageable);
}
