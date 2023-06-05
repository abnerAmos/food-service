package com.food.service;

import com.food.service.dto.request.RestaurantAddRequest;
import com.food.service.dto.request.RestaurantUpdateRequest;
import com.food.service.repository.RestaurantRepository;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantTestApi {

    @LocalServerPort
    private int port;

    @Autowired
    private Flyway flyway;

    @Autowired
    private RestaurantRepository repository;

    @BeforeEach
    private void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/api/restaurant";
        flyway.migrate();
    }

    @Test
    void mustReturnStatus200_WhenQueryRestaurantByIdExisting() {
        RestAssured
            .given()
                .pathParam("id", 1)
                .accept(ContentType.JSON)
            .when()
                .get("/{id}")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void mustReturnStatus404_WhenQueryRestaurantByIdNonExistent() {
        RestAssured
            .given()
                .pathParam("id", -1)
                .accept(ContentType.JSON)
            .when()
                .get("/{id}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void mustReturnStatus200_WhenQueryListAllRestaurant() {
        RestAssured
            .given()
                .queryParam("name", "")
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("", Matchers.hasSize(repository.countAllByName("").intValue()));
    }

    @Test
    void mustReturnStatus200_WhenQueryListAllRestaurantByName() {
        RestAssured
            .given()
                .queryParam("name", "Brasileira")
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.hasSize(repository.countAllByName("Brasileira").intValue()));
    }

    @Test
    void mustReturnStatus404_WhenQueryListAllRestaurantByNameNonExistent() {
        RestAssured
            .given()
                .queryParam("name", "Nonexistent")
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void mustReturnStatus201_WhenRegisterRestaurant() {

        RestaurantAddRequest request = new RestaurantAddRequest();
        request.setName("Pizza Hot");
        request.setDeliveryFee(BigDecimal.valueOf(2));
        request.setKitchenId(3L);
        request.setPostalCode("02465300");
        request.setPlaceNumber("157");
        request.setComplementAddress("B");
        request.setTypePaymentId(Arrays.asList(1L, 2L));

        Gson gson = new Gson();
        String json = gson.toJson(request);

        RestAssured
            .given()
                .body(json)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void mustReturnStatus400_WhenRegisterRestaurantInvalidData() {

        RestAssured
            .given()
                .body("")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void mustReturnStatus200_WhenUpdateRestaurant() {

        RestaurantUpdateRequest request = new RestaurantUpdateRequest();
        request.setName("Pizza Hot");
        request.setKitchenId(3L);

        Gson gson = new Gson();
        String json = gson.toJson(request);

        RestAssured
            .given()
                .pathParam("id", 1)
                .body(json)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .patch("/{id}")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void mustReturnStatus404_WhenUpdateRestaurant() {

        RestaurantUpdateRequest request = new RestaurantUpdateRequest();
        request.setName("Pizza Hot");
        request.setKitchenId(3L);

        Gson gson = new Gson();
        String json = gson.toJson(request);

        RestAssured
            .given()
                .pathParam("id", -1)
                .body(json)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .patch("/{id}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void mustReturnStatus400_WhenUpdateRestaurant() {

        RestaurantUpdateRequest request = new RestaurantUpdateRequest();

        Gson gson = new Gson();
        String json = gson.toJson(request);

        RestAssured
                .given()
                .pathParam("id", 1)
                .body(json)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .patch("/{id}")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void mustReturnStatus204_WhenDeleteRestaurantByIdNoLinkedItens() {
        RestAssured
            .given()
                .pathParam("id", 4)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void mustReturnStatus404_WhenDeleteRestaurantByIdNonExistent() {
        RestAssured
            .given()
                .pathParam("id", -1)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void mustReturnStatus400_WhenDeleteRestaurantByIdWithLinkedItens() {
        RestAssured
            .given()
                .pathParam("id", 1)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
