package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;

import org.acme.quickstart.responses.BaseResponse;
import org.acme.quickstart.responses.BaseResult;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;

import javax.json.bind.JsonbBuilder;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given().when().get("/hello").then().statusCode(200).body(is("hello"));
    }

    @Test
    public void testGreetingPoint() {
        final String uuid = UUID.randomUUID().toString();
        given().pathParam("name", uuid).when().get("/hello/greeting/{name}").then().statusCode(200)
                .body(is("hello 2 " + uuid));
    }

    @Test
    public void testJsonSyncPoint() {
        final BaseResponse expected = new BaseResponse(BaseResult.OK, "Everything is fine");
        final String expString = JsonbBuilder.create().toJson(expected);
        given().when().get("/hello/json").then().statusCode(200).body(is(expString));
    }

    @Test
    public void testJsonAsyncPoint() {
        final BaseResponse expected = new BaseResponse(BaseResult.OK, "Everything is fine");
        final String expString = JsonbBuilder.create().toJson(expected);
        given().when().get("/hello/json/async").then().statusCode(200).body(is(expString));
    }

    @Test
    public void testTextAsyncPoint() {
        given()
            .when().get("/hello/text/async")
            .then()
                .statusCode(200)
                .body(is("example"));
    }

}