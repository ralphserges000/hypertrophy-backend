package com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString

public class ExerciseIntensityReport 
{
	private final Long exerciseId;
	private final List<CompleteWorkoutSet> completeWorkoutSet;
	
	@JsonProperty("totalIntensity")
	private final double totalIntensity;
}
