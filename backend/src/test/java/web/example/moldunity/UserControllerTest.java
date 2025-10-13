package web.example.moldunity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import web.app.moldunity.MoldunityApplication;

@SpringBootTest(classes = MoldunityApplication.class)
@AutoConfigureWebTestClient
public class UserControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getProfileTest(){
        webTestClient.get().uri("/api/profile/Qiwi")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.profile.username").isEqualTo("Qiwi");
    }
}
