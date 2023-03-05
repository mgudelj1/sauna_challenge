package org.softwaresauna;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.softwaresauna.exceptions.ChallengeExceptions.mapCanNotBeEmpty;

public class Traverser {

    public static final Set<Character> END_OF_PATH_CHAR = Set.of('x', 'X');

    public List<TraversePosition> traverse(char[][] map) {
        validateMap(map);

        ArrayList<TraversePosition> visited = new ArrayList<>();
        PathFinder pathFinder = new PathFinder();

        TraversePosition current = pathFinder.start(map);
        visited.add(current);
        while (!END_OF_PATH_CHAR.contains(current.getCharacter())) {
            current = pathFinder.nextStep(current, map);
            visited.add(current);
        }

        return visited;
    }

    public String collectLetters(List<TraversePosition> positions) {
        Set<TraversePosition> letterPositions = positions.stream()
                .filter(pos -> Character.isUpperCase(pos.getCharacter()))
                .map(e -> new TraversePosition(e.getCoordinates(), null, e.getCharacter()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return getPath(letterPositions);
    }

    public String getPath(Iterable<TraversePosition> positions) {
        StringBuilder sb = new StringBuilder();
        for (TraversePosition position : positions) {
            sb.append(position.getCharacter());
        }
        return sb.toString();
    }

    private void validateMap(char[][] map) {
        if (map == null || map.length < 1) {
            throw mapCanNotBeEmpty();
        }
    }

}
