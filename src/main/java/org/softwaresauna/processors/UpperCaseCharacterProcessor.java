package org.softwaresauna.processors;

import org.softwaresauna.TraversePosition;
import org.softwaresauna.exceptions.ChallengeException;

public class UpperCaseCharacterProcessor extends StepProcessor {

    DashProcessor dashProcessor;
    TurnProcessor turnProcessor;

    public UpperCaseCharacterProcessor() {
        this.dashProcessor = new DashProcessor();
        this.turnProcessor = new TurnProcessor();
    }

    @Override
    public TraversePosition next(TraversePosition current, char[][] map) {
        try {
            return dashProcessor.next(current, map);
        } catch (ChallengeException e) {
            return turnProcessor.next(current, map);
        }
    }

    @Override
    public boolean handles(char character) {
        return Character.isUpperCase(character);
    }

}
