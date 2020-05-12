package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionOrder;
import pdn.ce.outlierhandler.modules.coremodule.repository.ExecutionOrderRepository;

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
}
