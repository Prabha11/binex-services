package pdn.ce.outlierhandler.modules.coremodule.tempmodel;

import org.springframework.web.multipart.MultipartFile;

public class FileContainer {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
