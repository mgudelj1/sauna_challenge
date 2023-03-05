package org.softwaresauna.processors;

import org.softwaresauna.Coordinates;
import org.softwaresauna.TraversePosition;

import java.util.List;
import java.util.stream.Collectors;

import static org.softwaresauna.exceptions.ChallengeExceptions.multipleStepsAvailableFor;
import static org.softwaresauna.exceptions.ChallengeExceptions.noPossibleSteps;

public abstract class StepProcessor {


    public abstract TraversePosition next(TraversePosition current, char[][] map);

    public abstract boolean handles(char character);

    TraversePosition getValidPosition(TraversePosition current, List<Coordinates> possibleCoordinates, char[][] map) {
        List<TraversePosition> validPositions = possibleCoordinates.stream()
                .map(cord -> new TraversePosition(cord, current, mapValue(cord, map)))
                .filter(this::emptyCharacter)
                .collect(Collectors.toList());

        if (validPositions.size() == 0) {
            throw noPossibleSteps(current);
        }

        if (validPositions.size() > 1) {
            throw multipleStepsAvailableFor(current);
        }

        return validPositions.get(0);
    }

    char mapValue(Coordinates current, char[][] map) {
        if (!inBoundPosition(current, map)) {
            return ' ';
        }
        return map[current.getX()][current.getY()];
    }

    boolean inBoundPosition(Coordinates current, char[][] map) {
        int x = current.getX();
        int y = current.getY();
        if (x < 0 || y < 0 || x >= map.length) {
            return false;
        }
        return y < map[x].length;
    }

    boolean emptyCharacter(TraversePosition position) {
        return position.getCharacter() != ' ';
    }

}
