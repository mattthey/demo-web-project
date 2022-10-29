package com.example.demowebproject;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import io.restassured.RestAssured;

/**
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RestTest
{
    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp()
    {
        RestAssured.port = port;
    }
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void test() throws JsonProcessingException
    {
        final Employee employee = new Employee("Nik", "Cap");

        DSLEmployee.addEmployee(employee);
        final List<Employee> employees = DSLEmployee.getAllEmployees();

        Assertions.assertEquals(1, employees.size());
        Assertions.assertEquals(employee.getName(), employees.get(0).getName());
        Assertions.assertEquals(employee.getRang(), employees.get(0).getRang());
    }
}