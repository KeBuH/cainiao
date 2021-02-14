package ru.cainiao.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;




import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("API integration tests")
class AppControllerTest {

    @Autowired
    private MockMvc mvc;

    static {
        final JdbcDatabaseContainer<?> container = new PostgreSQLContainer<>("postgres:11.1");
        container.start();
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());

    }

    @Test
    @DisplayName("Positive case. Should just calc delivery from A to B")
    public void shouldCalcDeliveryFromAToB() throws Exception {
        mvc.perform(get("/route")
                .param("fromCity", "A").param("toCity", "B"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Transport", equalTo("SuperMan")))
                .andExpect(jsonPath("$.['First Mile']", equalTo("SmartBoy")))
                .andExpect(jsonPath("$.['Last Mile']", equalTo("OldGranny")))
                .andExpect(jsonPath("$.['Total Cost']", equalTo("$11.5")))
                .andExpect(jsonPath("$.['Total Time']", equalTo("2d 10h")));
    }

    @Test
    @DisplayName("Positive case. Should just calc delivery from A to C")
    public void shouldCalcDeliveryFromAToC() throws Exception {
        mvc.perform(get("/route")
                .param("fromCity", "A").param("toCity", "C"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Transport", equalTo("FlyGirl")))
                .andExpect(jsonPath("$.['First Mile']", equalTo("SmartBoy")))
                .andExpect(jsonPath("$.['Last Mile']", equalTo("BikeOwner")))
                .andExpect(jsonPath("$.['Total Cost']", equalTo("$43.0")))
                .andExpect(jsonPath("$.['Total Time']", equalTo("3d 3h")));
    }

    @Test
    @DisplayName("Negative case. Should throw Exception by missing route part first mile from C to A")
    public void shouldCalcDeliveryFromCToA() throws Exception {
        mvc.perform(get("/route")
                .param("fromCity", "C").param("toCity", "A"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("building route is not possible"));
    }

    @Test
    @DisplayName("Negative case. Should throw exception by missing route from A to D")
    public void shouldThrowExceptionWhenRouteIsMissingFromAToD() throws Exception {
        mvc.perform(get("/route")
                .param("fromCity", "A").param("toCity", "D"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("building route is not possible"));
    }

    @Test
    @DisplayName("Negative case. Should throw exception by missing route from C to B")
    public void shouldThrowExceptionWhenRouteIsMissingFromCToB() throws Exception {
        mvc.perform(get("/route")
                .param("fromCity", "A").param("toCity", "D"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("building route is not possible"));
    }

}