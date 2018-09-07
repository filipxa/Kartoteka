package sw3.kartoteka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import sw3.kartoteka.storage.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})


public class KartotekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KartotekaApplication.class, args);
	}
}
