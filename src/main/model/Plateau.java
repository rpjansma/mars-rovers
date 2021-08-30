package main.model;

import java.util.ArrayList;
import java.util.List;

public class Plateau {

    private final int dimX;
    private final int dimY;

    private final List<Rover> rovers = new ArrayList<>();

    public boolean coordinatesOccupied(RoverCoordinates position) {
        for (Rover rover : rovers) {
            if (rover.hasPosition(position)) {
                return true;
            }
        }
        return false;
    }

    public Plateau(String dimensions) {
        String[] parts = dimensions.split(" ");
        int dimX = Integer.parseInt(parts[0]);
        int dimY = Integer.parseInt(parts[1]);

        this.dimX = dimX;
        this.dimY = dimY;
    }

    public void deployRover(Rover rover) {
        rovers.add(rover);
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }
}
