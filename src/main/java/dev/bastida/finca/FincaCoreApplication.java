package dev.bastida.finca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("dev.bastida.finca.config")
public class FincaCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FincaCoreApplication.class, args);
	}

}
