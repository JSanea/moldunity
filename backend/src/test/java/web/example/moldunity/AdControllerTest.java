package web.example.moldunity;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import web.app.moldunity.MoldunityApplication;
import web.app.moldunity.model.dto.ad.AdDetails;
import web.app.moldunity.model.entity.postgres.ad.Ad;
import web.app.moldunity.model.entity.postgres.transport.Car;
import web.app.moldunity.security.AuthRequest;

@SpringBootTest(classes = MoldunityApplication.class)
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    private static String tokenQiwi;
    private static String tokenAlx;
    private static AdDetails newAd;

    @Test
    @Order(1)
    void performLogin(){
        webTestClient.post().uri("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AuthRequest("Qiwi", "1234"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.token").exists()
                .jsonPath("$.token").value(String.class, t -> tokenQiwi = t);
    }

    @Test
    @Order(2)
    void login(){
        webTestClient.post().uri("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new AuthRequest("alx", "1234"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.token").exists()
                .jsonPath("$.token").value(String.class, t -> tokenAlx = t);
    }

    @Test
    @Order(3)
    void createTest(){
        Ad ad = Ad.builder()
                .username("Qiwi")
                .offerType("Vand").title("BMW M5").categoryName("Transport")
                .subcategoryName("Car").description("The Best").price(11000)
                .build();

        Car car = Car.builder().body("Sedan").build();
        car.setBrand("BMW");
        car.setModel("M5");
        car.setYear(2015);
        car.setMileage(157000);
        car.setFuel("Benzin");
        car.setGearBox("Manual");
        car.setEngineCapacity(2500);
        car.setPower(180);
        car.setColor("Negru");
        car.setSteeringWheel("Stanga");

        AdDetails adDetails = new AdDetails(ad, car);

        webTestClient.post().uri("/api/ads")
                .header("Authorization", "Bearer " + tokenQiwi)
                .bodyValue(adDetails)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.ad.id").exists()
                .jsonPath("$").value(AdDetails.class, a -> newAd = a);
    }

    @Test
    @Order(4)
    void getNewAdByIdTest(){
        Long id = newAd.ad().getId();
        Assertions.assertNotNull(id, "Id should be initialized");
        webTestClient.get().uri("/api/ads/" + id + "?type=Car")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.ad.ad.id").isEqualTo(id);
    }

    @Test
    @Order(5)
    void getBySubcategoryTest(){
        webTestClient.post().uri("/api/ads/car?page=1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.page").isEqualTo(1);
    }

    @Test
    @Order(6)
    void getSubcategoryByAdIdTest(){
        Long id = newAd.ad().getId();
        Assertions.assertNotNull(id, "Id should be initialized");
        webTestClient.get().uri("/api/ads/" + id + "/subcategory?type=Car")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.adId").isEqualTo(id);
    }

    @Test
    @Order(7)
    void getSubcategoryCountByAuthNameTest(){
        webTestClient.get().uri("/api/count/Car")
                .header("Authorization", "Bearer " + tokenQiwi)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @Order(8)
    void updateTest(){
        Long id = newAd.ad().getId();
        Assertions.assertNotNull(id, "Id should be initialized");

        newAd.ad().setPrice(10500);
        Car car = (Car)newAd.subcategory();
        car.setMileage(173000);
        AdDetails updatedAd = new AdDetails(newAd.ad(), car);

        webTestClient.put().uri("/api/edit/ads/" + id)
                .header("Authorization", "Bearer " + tokenQiwi)
                .bodyValue(updatedAd)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.ad.id").isEqualTo(id)
                .jsonPath("$.ad.price").isEqualTo(10500)
                .jsonPath("$.subcategory.mileage").isEqualTo(173000);
    }

    @Test
    @Order(9)
    void republishTest(){
        Long id = newAd.ad().getId();
        Assertions.assertNotNull(id, "Id should be initialized");

        webTestClient.put().uri("/api/republish/ads/" + id)
                .header("Authorization", "Bearer " + tokenQiwi)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Order(10)
    void deleteByAnotherUserTest(){
        Long id = newAd.ad().getId();
        Assertions.assertNotNull(id, "Id should be initialized");

        webTestClient.delete().uri("/api/edit/ads/" + id)
                .header("Authorization", "Bearer " + tokenAlx)
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    @Order(11)
    void deleteTest(){
        Long id = newAd.ad().getId();
        Assertions.assertNotNull(id, "Id should be initialized");

        webTestClient.delete().uri("/api/edit/ads/" + id)
                .header("Authorization", "Bearer " + tokenQiwi)
                .exchange()
                .expectStatus().isOk();
    }
}













