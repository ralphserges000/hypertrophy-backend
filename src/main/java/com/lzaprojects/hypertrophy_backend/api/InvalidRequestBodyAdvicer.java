package com.lzaprojects.hypertrophy_backend.api;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class InvalidRequestBodyAdvicer 
{
	private static final String UNIQUE_VIOLATION_CODE = "23505";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<InvalidRequestBodyResponse> adviceInvalidRequestBody(MethodArgumentNotValidException exception) 
	{
		Set<String> errorReasons = new HashSet<>();
		exception.getBindingResult().getAllErrors().forEach(error -> 
		{
            errorReasons.add(error.getDefaultMessage());
        });
		
		return new ResponseEntity<InvalidRequestBodyResponse>(
				InvalidRequestBodyResponse.builder()
				.apiOperationStatusMsg(ApiOperationStatus.FAIL.getStatusMsg())
				.errorReasons(errorReasons)
				.build(), 
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<DuplicateExerciseErrorResponse> adviceDuplicateExerciseError(SQLException exception) 
	{
		String sqlErrorCode = exception.getSQLState();
		DuplicateExerciseErrorResponse response = new DuplicateExerciseErrorResponse();
		
		switch(sqlErrorCode) 
		{
			case UNIQUE_VIOLATION_CODE:
				response.setApiOperationStatusMsg(ApiOperationStatus.FAIL.getStatusMsg());
				response.setDuplicateErrorMsg("Exercise already exist.");
				break;
		}
		return new ResponseEntity<DuplicateExerciseErrorResponse>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<InvalidSessionPeriodInputResponse> adviceInvalidSessionPeriodInput(HttpMessageNotReadableException exception) 
	{
		return new ResponseEntity<InvalidSessionPeriodInputResponse>(
				InvalidSessionPeriodInputResponse.builder()
				.apiOperationStatusMsg(ApiOperationStatus.FAIL.getStatusMsg())
				.errorReason("SessionPeriod only accepts values in [NIGHT, MORNING, AFTERNOON]")
				.build(), 
				HttpStatus.BAD_REQUEST);
	} 
}
