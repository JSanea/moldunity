package web.example.webflux_moldunity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class WebfluxMoldunityApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	@Test
	void contextLoads() {
	}

}
