package org.softwaresauna;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Stream;

import static org.softwaresauna.exceptions.ChallengeExceptions.missingArguments;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Main {

    static MapLoader mapLoader = new MapLoader();
    static Traverser traverser = new Traverser();

    /**
     * @param args Map names
     */
    public static void main(String[] args) {
        // run with null
        if (args.length < 1) {
            throw missingArguments();
        }

        // check map for wild characters
        Stream.of(args).forEach(mapName -> {
            char[][] map = mapLoader.loadMap(mapName);
            List<TraversePosition> traverse = traverser.traverse(map);
            String header = "*********     " + mapName + "     *********";
            System.out.println(header);
            System.out.println(traverser.getPath(traverse));
            System.out.println(traverser.collectLetters(traverse));
            System.out.println(StringUtils.repeat('*', header.length()));
        });

    }
}