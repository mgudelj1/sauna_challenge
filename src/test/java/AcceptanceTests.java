import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.softwaresauna.MapLoader;
import org.softwaresauna.TraversePosition;
import org.softwaresauna.Traverser;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AcceptanceTests {

    MapLoader mapLoader = new MapLoader();
    Traverser traverser = new Traverser();

    @ParameterizedTest(name = "calculates path for {0}")
    @MethodSource("maps")
    void throwsErrorsForInvalidMaps(String letters, String traversePath, String mapName) {
        char[][] map = mapLoader.loadMap(mapName);

        List<TraversePosition> positions = traverser.traverse(map);

        assertThat(traverser.collectLetters(positions)).isEqualTo(letters);
        assertThat(traverser.getPath(positions)).isEqualTo(traversePath);
    }

    static Stream<Arguments> maps() {
        return Stream.of(
                Arguments.of("ACB", "@---A---+|C|+---+|+-B-x", "basic_example"),
                Arguments.of("ABCD", "@|A+---B--+|+--C-+|-||+---D--+|x", "straight_through_intersections"),
                Arguments.of("ACB", "@---A---+|||C---+|+-B-x", "letters_on_turns"),
                Arguments.of("GOONIES", "@-G-O-+|+-+|O||+-O-N-+|I|+-+|+-I-+|ES|x", "letters_on_same_location"),
                Arguments.of("BLAH", "@B+++B|+-L-+A+++A-+Hx", "compact_space"),
                Arguments.of("AB", "@-A--+|+-B--x", "characters_after_end")
        );
    }
}
