package pdn.ce.outlierhandler.modules.coremodule.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pdn.ce.outlierhandler.modules.coremodule.tempmodel.FileContainer;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "E://temp//";

    @GetMapping("/file")
    public String index() {
        return "upload";
    }

    @PostMapping("/file/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("one") String yay,
                                   RedirectAttributes redirectAttributes) {
//        MultipartFile file = fileContainer.getFile();
        System.out.println(file);
        System.out.println(yay);
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}