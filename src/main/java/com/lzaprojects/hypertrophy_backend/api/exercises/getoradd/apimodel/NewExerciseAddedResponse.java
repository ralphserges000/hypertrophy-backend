package com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel;

import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.Exercise;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewExerciseAddedResponse {
	
	private final Exercise exerciseEntry;
	private final String apiOperationStatusMsg;
}
