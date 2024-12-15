package com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.apimodel.CompletedExercisesInRequest;
import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.apimodel.SummaryReportResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CompleteWorkoutSessionController 
{
	private final AddCompletedWorkoutSessionService service; 
	
	@Tag(name="Submit Completed Workout Session")
	@PostMapping("/api/workoutsession/complete")
	public SummaryReportResponse addCompletedWorkoutSession(@Valid @RequestBody CompletedExercisesInRequest request) 
	{
		return service.persistCompletedWorkoutSession(request);
	}
}