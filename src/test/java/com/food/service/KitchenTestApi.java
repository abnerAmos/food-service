package com.food.service;

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
import org.springframework.test.context.TestPropertySource;

// Sobe um servidor em uma porta aleatória para efetuar os Testes de API.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource("/application-test.properties")
class KitchenTestApi {

    // Captura a porta gerada em uma variável.
    @LocalServerPort
    private int port;

    // Instancia e executa o flyway em BeforeEach antes de cada teste.
    @Autowired
    private Flyway flyway;

    @BeforeEach
    private void setUp() {
        System.out.println("----- Antes de cada teste -----");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/api/kitchen";
        flyway.migrate();
    }

    @Test
    void mustReturnStatus200_WhenQueryKitchen() {
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured
            .given()
//                .basePath("/api/kitchen")
                .queryParam("name", "")
//                .port(port)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void mustReturnStatus200_WhenQueryKitchenById() {
        RestAssured
            .given()
                .pathParam("id", 3)
                .accept(ContentType.JSON)
            .when()
                .get("/{id}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("Italiana"));
    }

    @Test
    void mustReturnStatus404_WhenQueryKitchenNonExistent() {
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
    void mustReturn4Kitchens_WhenQueryKitchen() {
        // Exibe no console o log da requisição
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured
            .given()
                .queryParam("name", "")
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("", Matchers.hasSize(3))
                .body("name", Matchers.hasItems("Brasileira",  "Japonesa"));
    }

    @Test
    void mustReturnStatus201_WhenRegisterKitchen() {
        RestAssured
            .given()
                .body("{ \"name\": \"Chinesa\" }")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
