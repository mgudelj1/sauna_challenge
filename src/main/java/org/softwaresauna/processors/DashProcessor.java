package org.softwaresauna.processors;

import org.softwaresauna.Coordinates;
import org.softwaresauna.TraversePosition;

import java.util.List;
import java.util.Set;

public class DashProcessor extends StepProcessor {

    public static final Set<Character> HANDLES_CHARACTERS = Set.of('|', '-');

    public DashProcessor() {
    }

    @Override
    public TraversePosition next(TraversePosition current, char[][] map) {
        return getValidPosition(current, nextCoordinate(current), map);
    }

    @Override
    public boolean handles(char character) {
        return HANDLES_CHARACTERS.contains(character);
    }


    private List<Coordinates> nextCoordinate(TraversePosition current) {
        return List.of(getHeadingCoordinates(current.getPreviousCoordinate(), current.getCoordinates()));
    }

    private Coordinates getHeadingCoordinates(Coordinates previous, Coordinates current) {
        if (current.rightOf(previous)) return current.stepRight();
        if (current.leftOf(previous)) return current.stepLeft();
        if (current.upOf(previous)) return current.stepUp();
        return current.stepDown();
    }
}
