package Backend;

import Backend.FileStorage.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
@PropertySources(@PropertySource("classpath:application.properties"))
public class DeliveryMercadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryMercadoApplication.class, args);
    }

}
