import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.softwaresauna.*;
import org.softwaresauna.exceptions.ChallengeException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TraverserIntTest {

    MapLoader mapLoader = new MapLoader();
    Traverser traverser = new Traverser();

    @ParameterizedTest(name = "Throws error for {0}")
    @MethodSource("invalidMaps")
    void throwsErrorsForInvalidMaps(String mapName, String expectedErrorMessage) {
        char[][] map = mapLoader.loadMap(mapName);

        assertThatThrownBy(() -> traverser.traverse(map))
                .isInstanceOf(ChallengeException.class)
                .hasMessage(expectedErrorMessage);
    }

    static Stream<Arguments> invalidMaps() {
        return Stream.of(
                Arguments.of("missing_start", "Map does not contain starting point!"),
                Arguments.of("empty", "Loaded map is empty!"),
                Arguments.of("multiple_starts_1", "Map can have only one starting point!"),
                Arguments.of("multiple_starts_2", "Map can have only one starting point!"),
                Arguments.of("multiple_starts_3", "Map can have only one starting point!"),
                Arguments.of("missing_start", "Map does not contain starting point!")
        );
    }

    @ParameterizedTest(name = "Throws error for {0}")
    @MethodSource("invalidPaths")
    void throwsErrorsForInvalidPaths(String mapName, String errorMessage, TraversePosition position) {
        char[][] map = mapLoader.loadMap(mapName);
        String expectedErrorMessage = errorMessage + position;
        assertThatThrownBy(() -> traverser.traverse(map))
                .isInstanceOf(ChallengeException.class)
                .hasMessage(expectedErrorMessage);
    }

    static Stream<Arguments> invalidPaths() {
        return Stream.of(
                Arguments.of("fork_in_path", "Multiple steps found for: ", new TraversePosition(new Coordinates(2, 10), null, '+')),
                Arguments.of("out_of_bounds_broken_path", "No possible steps found for: ", new TraversePosition(new Coordinates(0, 9), null, '-')),
                Arguments.of("broken_path", "No possible steps found for: ", new TraversePosition(new Coordinates(1, 7), null, '|')),
                Arguments.of("multiple_starting_paths_1", "Multiple steps found for: ", new TraversePosition(new Coordinates(0, 6), null, '@')),
                Arguments.of("multiple_starting_paths_1", "Multiple steps found for: ", new TraversePosition(new Coordinates(0, 6), null, '@')),
                Arguments.of("fake_turn", "No possible steps found for: ", new TraversePosition(new Coordinates(0, 4), null, '+'))
        );
    }

}
