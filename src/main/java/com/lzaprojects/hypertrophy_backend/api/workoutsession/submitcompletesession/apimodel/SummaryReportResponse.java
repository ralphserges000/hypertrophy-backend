package com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.apimodel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.ExerciseIntensityReport;
import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.SessionPeriod;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SummaryReportResponse 
{
	private final String username;
	private final String dateCompletion;
	private final double currentBodyWeight;
	private final SessionPeriod sessionPeriod;
	
	@JsonProperty("intensityReport")
	private final List<ExerciseIntensityReport> intensityReport;
}
