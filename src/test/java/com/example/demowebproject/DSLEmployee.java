package com.example.demowebproject;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

/**
 *
 */
public class DSLEmployee
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void addEmployee(Employee employee) throws JsonProcessingException
    {
        given()
                .contentType("application/json")
                .body(OBJECT_MAPPER.writeValueAsString(employee))
            .when()
                .post("/addEmployee")
            .then()
                .statusCode(200);
    }


    public static List<Employee> getAllEmployees() throws JsonProcessingException
    {
        final String jsonResponse = given()
                .contentType("application/json")
                .get("/allEmployee")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        final CollectionType type = OBJECT_MAPPER.getTypeFactory()
                .constructCollectionType(List.class, Employee.class);
        return OBJECT_MAPPER.readValue(jsonResponse, type);
    }

    public static List<Employee> getEmployeesByName(String name) throws JsonProcessingException
    {
        final String jsonResponse = given()
                .contentType("application/json")
                .queryParam("name")
                .get("/getEmployeesByName")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        final CollectionType type = OBJECT_MAPPER.getTypeFactory()
                .constructCollectionType(List.class, Employee.class);
        return OBJECT_MAPPER.readValue(jsonResponse, type);
    }
}