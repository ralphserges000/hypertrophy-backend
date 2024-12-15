CREATE TYPE SESSION_PERIOD AS ENUM ('MORNING', 'AFTERNOON', 'NIGHT');

CREATE TABLE IF NOT EXISTS completedExercises 
(
	workoutSessionId	SERIAL			PRIMARY KEY,
	username			VARCHAR(200) 	NOT NULL,
	dateCompleted		DATE 			NOT NULL,
	sessionPeriod		SESSION_PERIOD 	NOT NULL,
	currentBodyWeight 	NUMERIC			NOT NULL,
	exerciseId			INTEGER 		REFERENCES  exercise,
	totalReps			INTEGER			NOT NULL,
	totalWeightInKG		NUMERIC			NOT NULL
);
