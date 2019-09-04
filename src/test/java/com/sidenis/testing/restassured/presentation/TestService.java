package com.sidenis.testing.restassured.presentation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Eg
 * @since 5/11/2016.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestService {

    @Test
    public void testGet() {
        given().that().baseUri("http://localhost").port(8080).and().basePath("/presentation").when().get("/call/without/authorization").then().statusCode(200);
    }

    @Test
    public void testPost() {
        given().that().baseUri("http://localhost").port(8080).and().basePath("/presentation").when().post("/call/without/authorization").then().statusCode(201);
    }

    @Test
    public void testPut() {
        given().that().baseUri("http://localhost").port(8080).and().basePath("/presentation").when().put("/call/without/authorization").then().statusCode(200);
    }

    @Test
    public void testDelete() {
        given().that().baseUri("http://localhost").port(8080).and().basePath("/presentation").when().delete("/call/without/authorization").then().statusCode(200);
    }

    @Test
    public void testGetWithJsonInResponse() {
        given().that().baseUri("http://localhost").port(8080).and().basePath("/presentation").when().get("/call/without/authorization1").then().assertThat().body("lotto.lottoId", equalTo(5)).and().body("lotto.winning-numbers", hasItem(45)).and().body("lotto.winners[1].numbers[5]", equalTo(22)).and().body("lotto.winners[0].numbers", hasItems(2, 23, 3));
    }

    @Test
    public void testGetWithXmlInResponse() {
        given().that().baseUri("http://localhost").port(8080).and().basePath("/presentation").when().get("/call/without/authorization2").then().assertThat().body("persones.person[1].firstname", equalTo("Petja")).body("persones.person[1].lastname", equalTo("Chopikow")).and().statusCode(200);
    }

    @Test
    public void testGetWithBasicPreemptiveAuthorization() {
        given().that().baseUri("http://localhost").port(8080).and().basePath("/presentation").auth().preemptive().basic("thisDude", "canPass").log().all().when().get("/call/with/authorization").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void testGetWithBasicChallengeAuthorization() {
        given().that().auth().basic("thisDude", "canPass").baseUri("http://localhost").port(8080).and().basePath("/presentation").when().get("/call/with/authorization").then().assertThat().statusCode(200);
    }

    @Test
    public void testPostWithHeaders0() {
        given().that().baseUri("http://localhost").port(8080).basePath("/presentation").header("Content-Type", "text/xml").log().all().when().post("/call/with/headers").then().log().all().and().assertThat().statusCode(200);
    }

    @Test
    public void testPostWithHeaders1() {
        given().that().baseUri("http://localhost").port(8080).basePath("/presentation").header("Content-Type", "text/plain").header("Accept", "text/.sfsdfsf").log().all().when().post("/call/with/headers").then().log().all().and().assertThat().statusCode(201);
    }

    @Test
    public void testPostWithHeaders2() {
        given().that().baseUri("http://localhost").port(8080).basePath("/presentation").header("Content-Type", "application/json").header("Accept", "text/.rasdrsa").header("etag", "252452525").log().all().when().post("/call/with/headers").then().log().all().and().assertThat().statusCode(202);
    }

    @Test
    public void testPostWithHeaders3() {
        given().that().baseUri("http://localhost").port(8080).basePath("/presentation").header("Content-Type", "text/html").header("Accept", "text/.rasdrsa").header("etag", "252452525").header("X-Custom-Header", "2134_aDFahdrAJDFIYa").log().all().when().post("/call/with/headers").then().log().all().and().assertThat().statusCode(203);
    }

    @Test
    public void testPutWithQueryParameters() {
        given().that().baseUri("http://localhost").and().port(8080).and().basePath("/presentation").param("search", "Some_text").log().all().when().put("/call/with/query/parameters").then().log().all().and().assertThat().statusCode(223);
    }
}
