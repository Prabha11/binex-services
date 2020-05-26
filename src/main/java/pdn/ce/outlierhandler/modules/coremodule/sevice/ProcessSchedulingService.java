package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionOrder;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionRequest;
import pdn.ce.outlierhandler.modules.coremodule.model.JobCard;
import pdn.ce.outlierhandler.modules.coremodule.model.User;
import pdn.ce.outlierhandler.modules.coremodule.repository.ExecutionOrderRepository;
import pdn.ce.outlierhandler.modules.coremodule.repository.JobCardRepository;

import java.io.IOException;

@Service
public class ProcessSchedulingService {
    @Autowired
    ProcessManagementService processManagementService;
    @Autowired
    ExecutionOrderRepository executionOrderRepository;
    @Autowired
    ExecutionOrderService executionOrderService;
    @Autowired
    JobCardRepository jobCardRepository;

    public JobCard createAProcessSchedule(ExecutionRequest executionRequest, User user) throws IOException {
        ExecutionOrder executionOrder = new ExecutionOrder();
        executionOrder.setExecutionRequest(executionRequest);
        executionOrder.setUser(user);

        long executionOrderID = this.executionOrderRepository.save(executionOrder).getId();

        JobCard jobCard = this.getJobCard(executionOrderID, user, executionRequest);
        processManagementService.startAExecutionProcess(executionOrderID);

        return jobCard;
    }

    private JobCard getJobCard(long executionOrderID, User user, ExecutionRequest executionRequest) {
        JobCard jobCard = new JobCard();
        jobCard.setExecutionOrderID(executionOrderID);
        jobCard.setUser(user);
        jobCard.setCardName(executionRequest.getOutputFile().getName());
        return jobCardRepository.save(jobCard);
    }

    public boolean runUnfinishedTask() {
        ExecutionOrder executionOrder = executionOrderService.getOneUnfinishedExecutionOrder();
        if (executionOrder != null) {
            System.out.println(executionOrder.getId());
            executionOrder.setProgress(1);
            try {
                processManagementService.startAExecutionProcess(executionOrder.getId());
            } catch (IOException e) {
                e.printStackTrace();
                executionOrder.setProgress(0);
                return false;
            }
        }

        return true;
    }
}
