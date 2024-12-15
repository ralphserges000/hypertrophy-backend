package com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CompletedWorkoutSetInRequest {

	@NotNull(message = "exerciseId must not be null.")
	@JsonProperty("exerciseId")
	private Long exerciseId;

	@Min(value = 1, message = "totalReps must not be negative.")
	@JsonProperty("totalReps")
	private int totalReps;

	@Min(value = 1, message = "totalWeightInKG must not be negative.")
	@JsonProperty("totalWeightInKG")
	private double totalWeightInKG;
}
