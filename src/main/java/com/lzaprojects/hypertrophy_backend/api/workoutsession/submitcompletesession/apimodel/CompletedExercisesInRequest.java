package com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.apimodel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.SessionPeriod;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CompletedExercisesInRequest 
{
	@NotBlank(message = "username should not be blank.")
	@JsonProperty("username")
	private final String username;

	@NotBlank(message = "dateCompletion should not be blank.")
	@Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", message = "dateCompletion format should be YYYY-MM-DD. e.g. 2024-11-23")
	@JsonProperty("dateCompletion")
	private final String dateCompletion;
	
	@NotNull(message = "sessionPeriod should not be blank.")
	@JsonProperty("sessionPeriod")
	private final SessionPeriod sessionPeriod;

	@NotNull(message = "currentBodyWeight should not be blank.")
	@JsonProperty("currentBodyWeight")
	private final double currentBodyWeight;

	@Valid
	@NotNull(message = "completeWorkoutSets should not be blank.")
	@JsonProperty("completedWorkoutSets")
	private final List<CompletedWorkoutSetInRequest> completedWorkoutSets;
}
