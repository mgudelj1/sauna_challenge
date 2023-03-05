package org.softwaresauna.processors;

import org.softwaresauna.Coordinates;
import org.softwaresauna.TraversePosition;

import java.util.List;

public class TurnProcessor extends StepProcessor {

    public static final char HANDLES_CHAR = '+';

    public TurnProcessor() {
    }

    @Override
    public TraversePosition next(TraversePosition current, char[][] map) {
        return getValidPosition(current, getTurningCoordinates(current.getPreviousCoordinate(), current.getCoordinates()), map);
    }

    @Override
    public boolean handles(char character) {
        return HANDLES_CHAR == character;
    }


    private List<Coordinates> getTurningCoordinates(Coordinates previous, Coordinates current) {
        if (current.rightOf(previous) || current.leftOf(previous)) return current.verticalSteps();
        return current.horizontalSteps();
    }
}
