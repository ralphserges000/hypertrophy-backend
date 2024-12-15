package com.lzaprojects.hypertrophy_backend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.AddNewExerciseService;
import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.Exercise;
import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.ExerciseRepository;
import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel.AddNewExerciseRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RetrieveAllExercisesTest {
	
	@Autowired private ExerciseRepository repository;
	@Autowired private AddNewExerciseService addNewExerciseService;
	
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
	void getAllExercises_addedNewCustomExerciseSuccessfully_shouldIncludeMyExerciseWithDefault() {
		
		AddNewExerciseRequest mockRequest = new AddNewExerciseRequest("INCLINE FLY","DUMBBELL","CHEST","ZIANLIU");
		addNewExerciseService.persistNewExercise(mockRequest);
		
		Exercise exerciseCreatedByNonDefault = 
				repository.getAllExercises("ZIANLIU")
				.stream()
				.filter(exercise -> exercise.getEntryUsername().equalsIgnoreCase("ZIANLIU"))
				.findFirst()
				.orElse(null);
		
		assertThat(exerciseCreatedByNonDefault).isNotNull();
	}
}
