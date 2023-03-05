import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.softwaresauna.Coordinates;
import org.softwaresauna.MapLoader;
import org.softwaresauna.PathFinder;
import org.softwaresauna.TraversePosition;
import org.softwaresauna.exceptions.ChallengeException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PathFinderTest {

    MapLoader mapLoader = new MapLoader();
    PathFinder pathFinder = new PathFinder();

    @Test
    void findStartingPoint1() {
        char[][] map = mapLoader.loadMap("basic_example");
        TraversePosition actualTraversePosition = new TraversePosition(new Coordinates(0, 2));

        TraversePosition startingPoint = pathFinder.start(map);

        assertThat(startingPoint).isEqualTo(actualTraversePosition);
    }

    @Test
    void findStartingPoint2() {
        char[][] map = mapLoader.loadMap("letters_on_same_location");
        TraversePosition actualTraversePosition = new TraversePosition(new Coordinates(3, 1));

        TraversePosition startingPoint = pathFinder.start(map);

        assertThat(startingPoint).isEqualTo(actualTraversePosition);
    }

    @Test
    void noStartingPointFound() {
        char[][] map = new char[][]{{
                '-', '?', '-', '-', 'x'
        }};

        assertThatThrownBy(() -> pathFinder.start(map))
                .isInstanceOf(ChallengeException.class)
                .hasMessage("Map does not contain starting point!");
    }

    @Test
    void pathFinderNotStartedProperly(){
        assertThatThrownBy(() -> pathFinder.nextStep(new TraversePosition(null), new char[][]{}))
                .isInstanceOf(ChallengeException.class)
                .hasMessage("Current position can not be null or empty!");
    }

    @Test
    void characterNotProcessable() {
        char[][] map = new char[][]{{
                '@', '?', '-', '-', 'x'
        }};
        TraversePosition errorTraversePosition = new TraversePosition(new Coordinates(0, 1), null, '?');


        assertThatThrownBy(() -> pathFinder.nextStep(errorTraversePosition, map))
                .isInstanceOf(ChallengeException.class)
                .hasMessage("Processor not implemented for: " + errorTraversePosition);
    }

}
