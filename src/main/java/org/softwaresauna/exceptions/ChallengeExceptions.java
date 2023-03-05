package org.softwaresauna.exceptions;

import org.softwaresauna.TraversePosition;

public class ChallengeExceptions {

    public static ChallengeException currentPositionEmpty() {
        return new ChallengeException("Current position can not be null or empty!");
    }

    public static ChallengeException missingArguments() {
        return new ChallengeException("Program should be started with at least one argument");
    }

    public static ChallengeException mapNameShouldNotBeBlank() {
        return new ChallengeException("Map name can not be blank");
    }

    public static ChallengeException fileCanNotBeOpened() {
        return new ChallengeException("File with given name can not be opened");
    }

    public static ChallengeException noMapWithGivenName() {
        return new ChallengeException("File with given name could not be found");
    }

    public static ChallengeException mapCanNotBeEmpty() {
        return new ChallengeException("Loaded map is empty!");
    }

    public static ChallengeException multipleStartingPointsFound() {
        return new ChallengeException("Map can have only one starting point!");
    }

    public static ChallengeException noStartingPointFound() {
        return new ChallengeException("Map does not contain starting point!");
    }

    public static ChallengeException noPossibleSteps(TraversePosition position) {
        return new ChallengeException("No possible steps found for: " + position);
    }

    public static ChallengeException multipleStepsAvailableFor(TraversePosition position) {
        return new ChallengeException("Multiple steps found for: " + position);
    }

    public static ChallengeException characterNotProcessable(TraversePosition position) {
        return new ChallengeException("Processor not implemented for: " + position);
    }
}
