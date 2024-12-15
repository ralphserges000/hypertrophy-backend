package com.lzaprojects.hypertrophy_backend.sampleprovider;

import java.util.List;

import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.SessionPeriod;
import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.apimodel.CompletedExercisesInRequest;
import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.apimodel.CompletedWorkoutSetInRequest;

public class CompletedWorkoutSessionSample {

	public static CompletedExercisesInRequest getSampleRequest( ) 
	{
		List<CompletedWorkoutSetInRequest> completedWorkoutSets = 
				List.of(
						new CompletedWorkoutSetInRequest((long) 39, 10, 55.5),
						new CompletedWorkoutSetInRequest((long) 39, 10, 45.0),
						new CompletedWorkoutSetInRequest((long) 39, 10, 55.0));
		
		CompletedExercisesInRequest request = 
				CompletedExercisesInRequest.builder()
				.username("johndoe")
				.dateCompletion("2024-11-22")
				.sessionPeriod(SessionPeriod.NIGHT)
				.currentBodyWeight(85.5)
				.completedWorkoutSets(completedWorkoutSets)
				.build();
		
		return request;
	}
	
	public static double getIntensity(List<CompletedWorkoutSetInRequest> completedWorkoutSets) 
	{
		return completedWorkoutSets
				.stream()
				.mapToDouble(workoutset -> workoutset.getTotalWeightInKG() * workoutset.getTotalReps())
				.sum();
	}
}
