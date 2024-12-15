package com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.repository;

import java.sql.Date;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.SessionPeriod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@Entity
@Table(name = "completedExercises")
@ToString
public class CompletedExerciseInAWorkoutSession 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long completedExerciseId;
	
	@Column private String username;
	@Column private Date dateCompleted;
	@Column private double currentBodyWeight;
	@Column private Long exerciseId;
	@Column private int totalReps;
	@Column private double totalWeightInKG;
	
	@Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
	private SessionPeriod sessionPeriod;
}
