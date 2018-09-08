package sw3.kartoteka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import sw3.kartoteka.model.InitBean;
import sw3.kartoteka.storage.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})

@ComponentScan
public class KartotekaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KartotekaApplication.class, args);
		 
		 context.getBean(InitBean.class).fillData();
	}
}
