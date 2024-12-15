package com.lzaprojects.hypertrophy_backend.api.exercises.getoradd;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel.AddNewExerciseRequest;
import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel.NewExerciseAddedResponse;
import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel.RetrieveAllExercisesResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExerciseController {
	
	private final AddNewExerciseService addNewExerciseService;
	private final RetrieveAllExercisesService retrieveAllExercisesService;

	@Tag(name="Add New Exercise")
	@PostMapping(
			value = "/api/exercises/new",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public NewExerciseAddedResponse addNewExercise(@Valid @RequestBody AddNewExerciseRequest request) {
		
		return addNewExerciseService.persistNewExercise(request);
	}
	
	@Tag(name="Get All Exercises List Created By User and Default")
	@GetMapping(
			value = "/api/exercises/{username}/all",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public RetrieveAllExercisesResponse retrieveAllExerciseList(@PathVariable("username") String username) {
		
		return retrieveAllExercisesService.getAllExercises(username);
	}
}