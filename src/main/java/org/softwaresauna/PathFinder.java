package org.softwaresauna;


import org.softwaresauna.exceptions.ChallengeExceptions;
import org.softwaresauna.processors.*;

import java.util.List;
import java.util.Optional;

import static org.softwaresauna.exceptions.ChallengeExceptions.*;

public class PathFinder {

    List<StepProcessor> processors;

    public PathFinder() {
        this.processors = ProcessorFactory.getProcessors();
    }

    public TraversePosition start(char[][] map) {
        return findStartingPosition(map);
    }

    public TraversePosition nextStep(TraversePosition current, char[][] map) {
        if (current == null || current.isEmpty()) {
            throw currentPositionEmpty();
        }

        return processors.stream()
                .filter(e -> e.handles(current.getCharacter()))
                .findFirst()
                .map(e -> e.next(current, map))
                .orElseThrow(() -> characterNotProcessable(current));
    }

    private TraversePosition findStartingPosition(char[][] map) {
        Coordinates start = null;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '@') {
                    if (start != null) {
                        throw multipleStartingPointsFound();
                    }
                    else {
                        start = new Coordinates(i, j);
                    }
                }
            }
        }
        return Optional.ofNullable(start)
                .map(TraversePosition::new)
                .orElseThrow(ChallengeExceptions::noStartingPointFound);
    }
}
