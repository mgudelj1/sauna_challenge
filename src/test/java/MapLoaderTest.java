import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.softwaresauna.MapLoader;
import org.softwaresauna.exceptions.ChallengeException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MapLoaderTest {

    private static final String MAP_NAME_BLANK_MESSAGE = "Map name can not be blank";
    public static final String NO_FILE_MESSAGE = "File with given name could not be found";
    private final MapLoader mapLoader = new MapLoader();

    @ParameterizedTest(name = "Throws error for \"{1}\"")
    @MethodSource("invalidMaps")
    void throwsErrorsForInvalidMaps(String mapName, String expectedErrorMessage) {
        assertThatThrownBy(() -> mapLoader.loadMap(mapName))
                .isInstanceOf(ChallengeException.class)
                .hasMessage(expectedErrorMessage);
    }

    static Stream<Arguments> invalidMaps() {
        return Stream.of(
                Arguments.of("   ", MAP_NAME_BLANK_MESSAGE),
                Arguments.of(null, MAP_NAME_BLANK_MESSAGE),
                Arguments.of("MapName", NO_FILE_MESSAGE)
        );
    }

    @Test
    void readMapFile() {
        char[][] actualMap = new char[][]{
                {' ', ' ', '@', '-', '-', 'A', '-', '+'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', '-', 'x'}
        };
        char[][] loadedMap = mapLoader.loadMap("broken_path");
        assertThat(loadedMap).isEqualTo(actualMap);
    }

}
