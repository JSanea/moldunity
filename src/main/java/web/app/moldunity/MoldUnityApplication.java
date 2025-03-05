package web.app.moldunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoldUnityApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoldUnityApplication.class, args);
	}
}
