package org.softwaresauna;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.softwaresauna.exceptions.ChallengeExceptions.*;

public class MapLoader {

    public char[][] loadMap(String mapName) {
        if (StringUtils.isBlank(mapName)) {
            throw mapNameShouldNotBeBlank();
        }

        try {
            URL systemResource = ClassLoader.getSystemResource("maps/" + mapName);
            if (systemResource == null) {
                throw noMapWithGivenName();
            }
            Path pathToMap = Paths.get(systemResource.toURI());
            List<String> lines = Files.readAllLines(pathToMap);
            return convertToArray(lines);
        } catch (IOException | URISyntaxException e) {
            // log exception
            throw fileCanNotBeOpened();
        }
    }

    private char[][] convertToArray(List<String> lines) {
        int rows = lines.size();
        char[][] matrix = new char[rows][];

        for (int i = 0; i < rows; i++) {
            matrix[i] = lines.get(i).toCharArray();
        }

        return matrix;
    }

}
