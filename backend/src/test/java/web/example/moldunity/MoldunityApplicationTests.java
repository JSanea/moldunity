package web.example.moldunity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class MoldunityApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	@Test
	void contextLoads() {
	}

}
