package com.lzaprojects.hypertrophy_backend.api.exercises.getoradd;

import org.springframework.stereotype.Service;

import com.lzaprojects.hypertrophy_backend.api.ApiOperationStatus;
import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel.AddNewExerciseRequest;
import com.lzaprojects.hypertrophy_backend.api.exercises.getoradd.apimodel.NewExerciseAddedResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddNewExerciseService {
	
	private final ExerciseRepository repository;

	public NewExerciseAddedResponse persistNewExercise(AddNewExerciseRequest request) {
		
		Exercise exerciseEntry = new Exercise();
		exerciseEntry.setEntryUsername(request.getEntryUsername());
		exerciseEntry.setEquipmentUsed(request.getEquipmentUsed());
		exerciseEntry.setExerciseName(request.getNewExerciseName());
		exerciseEntry.setTargetedBodypart(request.getTargetedBodypart());
		
		Exercise savedExerciseEntry = repository.save(exerciseEntry);
		log.info("Successfully saved new exercise: {}", savedExerciseEntry.toString());
		
		return NewExerciseAddedResponse.builder()
				.exerciseEntry(savedExerciseEntry)
				.apiOperationStatusMsg(ApiOperationStatus.SUCCESS.getStatusMsg())
				.build();
	}
}