package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionOrder;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionRequest;
import pdn.ce.outlierhandler.modules.coremodule.model.User;
import pdn.ce.outlierhandler.modules.coremodule.repository.ExecutionOrderRepository;

import java.io.IOException;

@Service
public class ProcessSchedulingService {
    @Autowired
    ProcessManagementService processManagementService;
    @Autowired
    ExecutionOrderRepository executionOrderRepository;

    public String createAProcessSchedule(ExecutionRequest executionRequest, User user) throws IOException {
        ExecutionOrder executionOrder = new ExecutionOrder();
        executionOrder.setExecutionRequest(executionRequest);
        executionOrder.setUser(user);

        long executionOrderID = this.executionOrderRepository.save(executionOrder).getId();

        return processManagementService.startAExecutionProcess();
    }
}
