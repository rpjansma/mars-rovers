package main.model;

import main.model.enums.Heading;

public class RoverCoordinates {
    private final int x;
    private final int y;

    public RoverCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public RoverCoordinates moveForward(Heading heading) {
        switch (heading) {
            case EAST: return new RoverCoordinates(x + 1, y);
            case NORTH: return new RoverCoordinates(x, y + 1);
            case SOUTH: return new RoverCoordinates(x, y - 1);
            case WEST: return new RoverCoordinates(x - 1, y);
            default: throw new RuntimeException("Invalid");
        }
    }

    public boolean positionExists(Plateau plateau) {
        if (x < 0 || x > plateau.getDimX()) {
            return false;
        }

        return y >= 0 && y <= plateau.getDimY();
    }

    public boolean CoordinatesAreEqual(RoverCoordinates other) {
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}


