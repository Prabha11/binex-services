package pdn.ce.outlierhandler.modules.coremodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pdn.ce.outlierhandler.modules.coremodule.model.FileStructure;
import pdn.ce.outlierhandler.modules.coremodule.model.User;
import pdn.ce.outlierhandler.modules.coremodule.repository.FileStructureRepository;
import pdn.ce.outlierhandler.modules.coremodule.repository.UserRepository;
import pdn.ce.outlierhandler.modules.coremodule.tempmodel.FileContainer;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UploadController {
    @Autowired
    UserRepository ur;
    @Autowired
    FileStructureRepository fileStructureRepository;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = ".//files//";

    @GetMapping("/file")
    public String index() {
        return "upload";
    }

    @PostMapping("/file/upload") // //new annotation since 4.3
    public boolean singleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("parentFolderId") long parentFolderId,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return false;
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            return false;
        }

        User user = ur.getOne((long) 1);

        FileStructure fileStructure = new FileStructure();
        fileStructure.setUser(user);
        fileStructure.setName(file.getOriginalFilename());
        fileStructure.setFolder(false);
        fileStructure.setFileLocation(file.getOriginalFilename());

        FileStructure parentFolder = fileStructureRepository.getOne(parentFolderId);
        List<FileStructure> childFolders = parentFolder.getChildFileStructures();
        childFolders.add(fileStructure);
        parentFolder.setChildFileStructures(childFolders);

        fileStructureRepository.save(parentFolder);

        return true;
    }

    @GetMapping("/file/download/{fileID}")
    public StreamingResponseBody getSteamingFile(HttpServletResponse response,
                                                 @PathVariable long fileID) throws IOException {
        FileStructure file = fileStructureRepository.getOne(fileID);
        response.setContentType("application/png");
        response.setHeader("Content-Disposition", "attachment; filename=\"download.png\"");
        InputStream inputStream = new FileInputStream(new File("E:\\temp\\download.png"));
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                System.out.println("Writing some bytes..");
                outputStream.write(data, 0, nRead);
            }
        };
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
