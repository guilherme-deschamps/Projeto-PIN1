package Backend.FileStorage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String localizacaoUploads;

    public String getlocalizacaoUploads() {
        return localizacaoUploads;
    }

    public void setUploadDir(String localizacaoUploads) {
        this.localizacaoUploads = localizacaoUploads;
    }
}
