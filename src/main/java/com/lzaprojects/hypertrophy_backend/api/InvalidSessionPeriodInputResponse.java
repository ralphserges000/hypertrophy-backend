package com.lzaprojects.hypertrophy_backend.api;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InvalidSessionPeriodInputResponse 
{
	private String apiOperationStatusMsg;
	private String errorReason;
}
