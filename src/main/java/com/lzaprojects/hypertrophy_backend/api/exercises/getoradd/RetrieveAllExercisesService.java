package com.lzaprojects.hypertrophy_backend.api.exercises.getoradd;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lzaprojects.hypertrophy_backend.api.ApiOperationStatus;
import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel.RetrieveAllExercisesResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RetrieveAllExercisesService {
	
	private final ExerciseRepository repository;
	
	public RetrieveAllExercisesResponse getAllExercises(String username) {
		
		List<Exercise> allExercises = repository.getAllExercises(username);
		return RetrieveAllExercisesResponse.builder()
										.allExercises(allExercises)
										.apiOperationStatus(ApiOperationStatus.SUCCESS.getStatusMsg())
										.build();
	} 
}