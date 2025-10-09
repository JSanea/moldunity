package web.app.moldunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "web.app.moldunity")
@EnableWebFlux
@EnableScheduling
public class MoldunityApplication {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Chisinau"));
		SpringApplication.run(MoldunityApplication.class, args);
	}
}
