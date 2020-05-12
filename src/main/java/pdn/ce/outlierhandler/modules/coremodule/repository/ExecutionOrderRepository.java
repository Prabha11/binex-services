package pdn.ce.outlierhandler.modules.coremodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionOrder;

@Repository
public interface ExecutionOrderRepository extends JpaRepository<ExecutionOrder, Long> {
}
