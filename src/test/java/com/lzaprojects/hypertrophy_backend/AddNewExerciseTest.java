package com.lzaprojects.hypertrophy_backend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.ExerciseRepository;
import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel.AddNewExerciseRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class AddNewExerciseTest {

	@Autowired private ExerciseRepository repository;
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");
	
	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}
	
	@AfterAll
	static void afterAll() {
		postgres.stop();
	}
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
	    registry.add("spring.datasource.url", postgres::getJdbcUrl);
	    registry.add("spring.datasource.username", postgres::getUsername);
	    registry.add("spring.datasource.password", postgres::getPassword);
	}
	
	@Test
	@Order(1)
	void isTestcontainerSetupSuccessfully() {
		assertThat(postgres.isCreated()).isTrue();
		assertThat(postgres.isRunning()).isTrue();
	}
	
	@Test
	@Order(2)
	void isExerciseTableLoaded() {
		assertThat(repository.count()).isGreaterThan(10);
	}
	
	@Test
	@Order(3)
	void persistNewExercise_withValidExerciseInput_shouldReturnOkAsResponse() throws Exception {
		
		AddNewExerciseRequest mockRequest = new AddNewExerciseRequest("INCLINE FLY","DUMBBELL","CHEST","ZIANLIU");
		String requestJson = objectMapper.writeValueAsString(mockRequest);
		
		mockMvc.perform(MockMvcRequestBuilders
							.post("/api/exercises/new")
							.accept(MediaType.APPLICATION_JSON_VALUE)
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(requestJson))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.exerciseEntry.exerciseId").isNotEmpty());
	}
	
	@Test
	@Order(4)
	void persistNewExercise_withAlreadyExistingExerciseInput_shouldReturnBadRequestAsResponse() throws Exception {
		
		AddNewExerciseRequest mockRequest = new AddNewExerciseRequest("INCLINE FLY","DUMBBELL","CHEST","ZIANLIU");
		String requestJson = objectMapper.writeValueAsString(mockRequest);
		
		mockMvc.perform(MockMvcRequestBuilders
							.post("/api/exercises/new")
							.accept(MediaType.APPLICATION_JSON_VALUE)
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(requestJson))
					.andExpect(status().isBadRequest());
			
	}
}
