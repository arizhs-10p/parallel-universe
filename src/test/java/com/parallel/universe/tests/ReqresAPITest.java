package com.parallel.universe.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReqresAPITest {

    String baseURL = "https://reqres.in/api"; // Assuming this is the base URL of the API

    @Test
    public void testPOSTMethod() {
        String requestBody = "{\"name\": \"John\", \"age\": 30}";
        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(baseURL + "/users")
                .then()
                .statusCode(201) // Assuming 201 is the status code for successful creation
                .body("name", equalTo("John"))
                .body("age", equalTo(30));
    }

    @Test
    public void testPUTMethod() {
        String requestBody = "{\"name\": \"John\", \"age\": 35}";
        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(baseURL + "/users/123") // Assuming 123 is the ID of the user to be updated
                .then()
                .statusCode(200) // Assuming 200 is the status code for successful update
                .body("age", equalTo(35));
    }

    @Test
    public void testPATCHMethod() {
        String requestBody = "{\"age\": 40}";
        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch(baseURL + "/users/123") // Assuming 123 is the ID of the user to be updated
                .then()
                .statusCode(200) // Assuming 200 is the status code for successful update
                .body("age", equalTo(40));
    }

    @Test
    public void testDELETEMethod() {
        given()
                .contentType("application/json")
                .when()
                .delete(baseURL + "/users/123") // Assuming 123 is the ID of the user to be deleted
                .then()
                .statusCode(204); // Assuming 204 is the status code for successful deletion
    }
}
