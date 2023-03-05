package org.softwaresauna;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.List;

@EqualsAndHashCode
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Coordinates {

    int x;
    int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean rightOf(Coordinates coordinates) {
        return (y - 1) == coordinates.getY() && x == coordinates.getX();
    }

    public boolean leftOf(Coordinates coordinates) {
        return (y + 1) == coordinates.getY() && x == coordinates.getX();
    }

    public boolean downOf(Coordinates coordinates) {
        return y == coordinates.getY() && (x - 1) == coordinates.getX();
    }

    public boolean upOf(Coordinates coordinates) {
        return y == coordinates.getY() && (x + 1) == coordinates.getX();
    }

    public Coordinates stepRight() {
        return new Coordinates(x, y + 1);
    }

    public Coordinates stepLeft() {
        return new Coordinates(x, y - 1);
    }

    public Coordinates stepUp() {
        return new Coordinates(x - 1, y);
    }

    public Coordinates stepDown() {
        return new Coordinates(x + 1, y);
    }

    public List<Coordinates> verticalSteps() {
        return List.of(stepUp(), stepDown());
    }

    public List<Coordinates> horizontalSteps() {
        return List.of(stepRight(), stepLeft());
    }



}
