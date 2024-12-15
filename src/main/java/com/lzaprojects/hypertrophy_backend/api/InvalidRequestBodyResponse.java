package com.lzaprojects.hypertrophy_backend.api;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InvalidRequestBodyResponse {
	
	private String apiOperationStatusMsg;
	private Set<String> errorReasons;
	private String errorReasonOneLinerOnly;
}
