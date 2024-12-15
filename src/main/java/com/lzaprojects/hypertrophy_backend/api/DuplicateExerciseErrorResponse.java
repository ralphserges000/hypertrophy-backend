package com.lzaprojects.hypertrophy_backend.api;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DuplicateExerciseErrorResponse {

	private String apiOperationStatusMsg;
	private String duplicateErrorMsg;
}
