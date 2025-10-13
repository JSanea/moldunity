package web.example.moldunity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import web.app.moldunity.MoldunityApplication;
import web.app.moldunity.security.AuthRequest;

@SpringBootTest(classes = MoldunityApplication.class)
@AutoConfigureWebTestClient
public class AuthControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void loginTest(){
        webTestClient.post().uri("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AuthRequest("Qiwi", "1234"))
                .exchange()
                .expectStatus().isOk();
    }

}
