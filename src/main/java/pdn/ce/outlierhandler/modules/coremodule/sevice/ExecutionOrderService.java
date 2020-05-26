package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionOrder;
import pdn.ce.outlierhandler.modules.coremodule.repository.ExecutionOrderRepository;

import java.util.List;

@Service
public class ExecutionOrderService {
    @Autowired
    ExecutionOrderRepository executionOrderRepository;

    public int updateProgress(long executionOrderID, int currentProgress) {
        ExecutionOrder executionOrder = executionOrderRepository.getOne(executionOrderID);
        executionOrder.setProgress(currentProgress);
        ExecutionOrder savedExecutionOrder = executionOrderRepository.save(executionOrder);
        return savedExecutionOrder.getProgress();
    }

    public void updateLog(long executionOrderID, String logSentence) {
        ExecutionOrder executionOrder = executionOrderRepository.getOne(executionOrderID);
        executionOrder.setLog(executionOrder.getLog() + logSentence);
        executionOrderRepository.save(executionOrder);
    }

    public void markAsFinishedOrder(long executionOrderID) {
        ExecutionOrder executionOrder = executionOrderRepository.getOne(executionOrderID);
        executionOrder.setFinished(true);
        executionOrderRepository.save(executionOrder);
    }

    public ExecutionOrder getExecutionServiceByID(long executionOrderID) {
        return executionOrderRepository.getOne(executionOrderID);
    }

    public ExecutionOrder getOneUnfinishedExecutionOrder() {
        Pageable pageable =  PageRequest.of(0, 1);
        List<ExecutionOrder> executionOrders = executionOrderRepository.getOneUnfinishedExecutionOrder(pageable);
        return !executionOrders.isEmpty() ? executionOrders.get(0) : null;
    }
}
