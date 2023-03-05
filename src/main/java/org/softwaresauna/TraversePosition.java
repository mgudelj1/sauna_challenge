package org.softwaresauna;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TraversePosition {

    Coordinates coordinates;

    @ToString.Exclude
    TraversePosition previous;
    char character;

    public TraversePosition(Coordinates coordinates) {
        this(coordinates, null, '@');
    }

    public TraversePosition(Coordinates coordinates, TraversePosition previous, char character) {
        this.coordinates = coordinates;
        this.previous = previous;
        this.character = character;
    }

    public Coordinates getPreviousCoordinate() {
        return previous.getCoordinates();
    }

    public boolean isEmpty() {
        return coordinates == null || charIsNull();
    }

    private boolean charIsNull() {
        return character == '\u0000';
    }

    @Override
    public String toString() {
        return "TraversePosition{coordinates=" + coordinates + ", character=" + character + '}';
    }
}
