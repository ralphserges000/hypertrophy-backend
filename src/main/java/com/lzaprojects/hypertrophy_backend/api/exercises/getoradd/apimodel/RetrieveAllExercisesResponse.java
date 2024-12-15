package com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel;

import java.util.List;

import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.Exercise;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RetrieveAllExercisesResponse {
	
	private List<Exercise> allExercises;
	private String apiOperationStatus;
}