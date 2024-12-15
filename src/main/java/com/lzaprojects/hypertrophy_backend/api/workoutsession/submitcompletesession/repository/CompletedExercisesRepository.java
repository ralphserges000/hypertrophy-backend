package com.lzaprojects.hypertrophy_backend.api.workoutsession.submitcompletesession.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedExercisesRepository extends JpaRepository<CompletedExerciseInAWorkoutSession, Long> {

}
