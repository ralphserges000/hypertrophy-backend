package com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.apimodel.CompletedExercisesInRequest;
import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.apimodel.CompletedWorkoutSetInRequest;
import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.apimodel.SummaryReportResponse;
import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.repository.CompletedExerciseInAWorkoutSession;
import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.repository.CompletedExercisesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddCompletedWorkoutSessionService 
{
	private final CompletedExercisesRepository repository;
	
	
	public SummaryReportResponse persistCompletedWorkoutSession(CompletedExercisesInRequest request) 
	{
		saveCompletedExerciseInAWorkoutSessions(request);
		
		Map<Long, List<CompleteWorkoutSet>> consolidatedWorkoutSetPerExercise = 
				consolidateCompleteWorkoutSetPerExercise(request.getCompletedWorkoutSets());
		
		log.info("Generating Summary report...");
		
		SummaryReportResponse response = 
				SummaryReportResponse.builder()
				.username(request.getUsername())
				.dateCompletion(request.getDateCompletion())
				.currentBodyWeight(request.getCurrentBodyWeight())
				.sessionPeriod(request.getSessionPeriod())
				.intensityReport(generatExerciseIntensityReport(consolidatedWorkoutSetPerExercise))
				.build();
		
		log.info("Summary report generated: {}", response.toString());
		
		return response;
	}
	
	@Transactional 
	private List<CompletedExerciseInAWorkoutSession> saveCompletedExerciseInAWorkoutSessions(CompletedExercisesInRequest request) 
	{
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
		LocalDate dateCompletion = LocalDate.parse(request.getDateCompletion(),dateTimeFormatter);
		Date sqlDate = Date.valueOf(dateCompletion);
		
		List<CompletedExerciseInAWorkoutSession> completedExerciseInAWorkoutSessions = new ArrayList<>();
		
		request.getCompletedWorkoutSets().forEach(workoutSet -> 
		{
			CompletedExerciseInAWorkoutSession completedExercise = 
					CompletedExerciseInAWorkoutSession.builder()
					.username(request.getUsername())
					.dateCompleted(sqlDate)
					.sessionPeriod(request.getSessionPeriod())
					.currentBodyWeight(request.getCurrentBodyWeight())
					.exerciseId(workoutSet.getExerciseId())
					.totalReps(workoutSet.getTotalReps())
					.totalWeightInKG(workoutSet.getTotalWeightInKG())
					.build();
			
			CompletedExerciseInAWorkoutSession savedEntry = repository.save(completedExercise);
			completedExerciseInAWorkoutSessions.add(savedEntry);
			
			log.info("Saved completed exercise: {}", savedEntry.toString());
		});
		log.info("Successfully saved all the completed exercises in a workout session.");
		return completedExerciseInAWorkoutSessions;
	}
	
	private Map<Long, List<CompleteWorkoutSet>> consolidateCompleteWorkoutSetPerExercise(List<CompletedWorkoutSetInRequest> completedExercises)   
	{
		Map<Long, List<CompleteWorkoutSet>> consolidatedResultMap = new HashMap<>();
		
		for(CompletedWorkoutSetInRequest completedExercise : completedExercises) 
		{
			if(!consolidatedResultMap.containsKey(completedExercise.getExerciseId())) 
			{
				consolidatedResultMap.put(
						completedExercise.getExerciseId(), 
						new ArrayList<CompleteWorkoutSet>());
							
				consolidatedResultMap.get(completedExercise.getExerciseId())
					.add(new CompleteWorkoutSet(completedExercise.getTotalReps(), completedExercise.getTotalWeightInKG()));
			}
			else 
			{
				consolidatedResultMap
				.get(completedExercise.getExerciseId())
				.add(new CompleteWorkoutSet(completedExercise.getTotalReps(), completedExercise.getTotalWeightInKG()));		
			}
		}
		return consolidatedResultMap;
	}
	
	private List<ExerciseIntensityReport> generatExerciseIntensityReport(Map<Long, List<CompleteWorkoutSet>> consolidatedWorkoutSets) 
	{
		log.info("Calculating intensity for each exercise");
		
		List<ExerciseIntensityReport> intensityReport = new ArrayList<>();
		
		for(Map.Entry<Long, List<CompleteWorkoutSet>> consolidatedWorkoutSet : consolidatedWorkoutSets.entrySet()) 
		{
			Long exerciseId = consolidatedWorkoutSet.getKey();
			List<CompleteWorkoutSet> completedWorkoutSet = consolidatedWorkoutSet.getValue();
			
			double intensity = completedWorkoutSet
								.stream()
								.mapToDouble(workoutSet -> workoutSet.getTotalReps() * workoutSet.getTotalWeightInKG())
								.sum();
			
			ExerciseIntensityReport exerciseTotalIntensity = 
					ExerciseIntensityReport
					.builder()
					.exerciseId(exerciseId)
					.completeWorkoutSet(completedWorkoutSet)
					.totalIntensity(intensity)
					.build();
			
			intensityReport.add(exerciseTotalIntensity);
		}
		
		log.info("All exercises intensity calculated.");
		return intensityReport;
	}
}
