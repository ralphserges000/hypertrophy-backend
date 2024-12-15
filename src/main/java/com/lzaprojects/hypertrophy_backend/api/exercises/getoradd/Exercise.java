package com.lzaprojects.hypertrophy_backend.api.exercises.getoradd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "exercise")
public class Exercise {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long exerciseId;
	
	@Column private String exerciseName;
	@Column private String equipmentUsed;
	@Column private String targetedBodypart;
	@Column private String entryUsername;
}
