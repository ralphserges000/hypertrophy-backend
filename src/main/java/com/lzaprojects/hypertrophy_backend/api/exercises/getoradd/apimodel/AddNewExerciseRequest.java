package com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddNewExerciseRequest {
	
	@NotBlank(message = "newExerciseName must not be empty") 
	private final String newExerciseName;
	
	@NotBlank(message = "equipmentUsed must not be empty")  
	private final String equipmentUsed;
	
	@NotBlank(message = "targetedBodypart must not be empty") 
	private final String targetedBodypart;
	
	@NotBlank(message = "entryUsername must not be empty") 
	private final String entryUsername;
	
}
