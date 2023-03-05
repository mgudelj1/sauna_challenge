package org.softwaresauna.processors;

import org.softwaresauna.Coordinates;
import org.softwaresauna.TraversePosition;

import java.util.ArrayList;
import java.util.List;

public class StartingPositionProcessor extends StepProcessor {

    public static final char HANDLES_CHAR = '@';

    public StartingPositionProcessor() {
    }

    @Override
    public TraversePosition next(TraversePosition current, char[][] map) {
        return getValidPosition(current, getStartingPositionCoordinates(current.getCoordinates()), map);
    }

    @Override
    public boolean handles(char character) {
        return HANDLES_CHAR == character;
    }

    private List<Coordinates> getStartingPositionCoordinates(Coordinates current) {
        ArrayList<Coordinates> surroundingCoordinates = new ArrayList<>(current.horizontalSteps());
        surroundingCoordinates.addAll(current.verticalSteps());
        return surroundingCoordinates;
    }

}
