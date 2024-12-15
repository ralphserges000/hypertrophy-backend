package com.lzaprojects.hypertrophy_backend.api.exercises.getoradd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

	@Query(value = "SELECT * FROM exercise WHERE entryUsername IN ('DEFAULT', :username)", nativeQuery = true)
	List<Exercise> getAllExercises(@Param("username") String username);

}
