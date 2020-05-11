package pdn.ce.outlierhandler.modules.coremodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdn.ce.outlierhandler.modules.coremodule.model.FileStructure;
import pdn.ce.outlierhandler.modules.coremodule.sevice.FileReadService;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class FileController {
    @Autowired
    FileReadService fileReadService;

    @GetMapping("/files")
    public List<FileStructure> getAllFiles() throws IOException {
        return fileReadService.getRootFileStructureByUserID(1);
    }
}
