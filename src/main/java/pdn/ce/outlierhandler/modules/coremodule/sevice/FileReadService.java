package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.model.FileStructure;
import pdn.ce.outlierhandler.modules.coremodule.repository.FileStructureRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileReadService {
    @Autowired
    private FileStructureRepository fileStructureRepository;
    private static final String FILE_ROOT = "E:\\Projects\\fyp\\engine\\Model python file\\sample_data";

    public List<String> getListOfAllFiles() throws IOException {
        Stream<Path> walk = Files.walk(Paths.get(FILE_ROOT));
        return walk.filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());
    }

    public List<FileStructure> getFileStructureByUserID(long userID) {
        return fileStructureRepository.findAll();
    }

    public List<FileStructure> getRootFileStructureByUserID(long userID) {
        return fileStructureRepository.findRootByUserID(userID);
    }
}
