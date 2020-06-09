package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.model.ExecutionRequest;
import pdn.ce.outlierhandler.modules.coremodule.model.FileStructure;
import pdn.ce.outlierhandler.modules.coremodule.model.User;
import pdn.ce.outlierhandler.modules.coremodule.repository.FileStructureRepository;

import java.util.List;

@Service
public class ExecutionRequestService {
    @Autowired
    FileStructureRepository fileStructureRepository;
    public ExecutionRequest validateExecutionRequest(ExecutionRequest executionRequest, User user)
            throws NullPointerException {
        ExecutionRequest validatedExecutionRequest = new ExecutionRequest();
        validatedExecutionRequest.setEmailAddress(executionRequest.getEmailAddress());
        validatedExecutionRequest.setDataset(executionRequest.getDataset());
        validatedExecutionRequest.setInputFile1(
                fileStructureRepository.getOne(executionRequest.getInputFile1().getId()));
        validatedExecutionRequest.setInputFile2(
                fileStructureRepository.getOne(executionRequest.getInputFile2().getId()));
        validatedExecutionRequest.setInputFile3(
                fileStructureRepository.getOne(executionRequest.getInputFile3().getId()));

        FileStructure userRootFolder = fileStructureRepository.findRootByUserID(user.getId()).get(0);

        FileStructure outputFile = new FileStructure();
        outputFile.setName(executionRequest.getOutputFile().getName());
        outputFile.setFileLocation(executionRequest.getOutputFile().getName());
        outputFile.setUser(user);
        outputFile.setFolder(false);
        FileStructure savedOutputFile = fileStructureRepository.save(outputFile);

        List<FileStructure> userRootFolderChildren = userRootFolder.getChildFileStructures();
        userRootFolderChildren.add(savedOutputFile);
        userRootFolder.setChildFileStructures(userRootFolderChildren);
        fileStructureRepository.save(userRootFolder);

        validatedExecutionRequest.setOutputFile(outputFile);
        return validatedExecutionRequest;
    }
}
