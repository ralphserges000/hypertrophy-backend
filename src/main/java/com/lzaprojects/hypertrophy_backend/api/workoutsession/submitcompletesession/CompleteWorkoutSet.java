package com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CompleteWorkoutSet 
{
	private final int totalReps;
	private final double totalWeightInKG;
}
