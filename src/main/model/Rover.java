package main.model;

import main.exceptions.NotLaunchedException;
import main.exceptions.PositionNotExistException;
import main.exceptions.PositionOccupiedException;
import main.exceptions.UnknownHeadingException;
import main.model.enums.Direction;
import main.model.enums.Heading;

public class Rover {

    private final String name;
    private RoverCoordinates roverCoordinates;
    private Heading heading;
    private Plateau plateau;

    public Rover(String name) {
        this.name = name;
    }

    private static Heading ToHeading(char heading) throws UnknownHeadingException {
        switch (heading) {
            case 'N': return Heading.NORTH;
            case 'W': return Heading.WEST;
            case 'S': return Heading.SOUTH;
            case 'E': return Heading.EAST;
            default: throw new UnknownHeadingException(heading);
        }
    }

    private static char FromHeading(Heading heading) {
        switch (heading) {
            case NORTH: return 'N';
            case WEST: return 'W';
            case SOUTH: return 'S';
            case EAST: return 'E';
            default: throw new RuntimeException("Invalid heading");
        }
    }

    public void dropRover(Plateau plateau, int posX, int posY, char heading) {
        dropRover(plateau, new RoverCoordinates(posX, posY), ToHeading(heading));
    }

    public void dropRover(Plateau plateau, String args) {
        String[] parts = args.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Heading heading = ToHeading(parts[2].toCharArray()[0]);
        dropRover(plateau, new RoverCoordinates(x, y), heading);
    }

    public void dropRover(Plateau plateau, RoverCoordinates roverCoordinates, Heading heading) {
        if (!roverCoordinates.positionExists(plateau)) {
            throw new PositionNotExistException();
        }

        if (plateau.coordinatesOccupied(roverCoordinates)) {
            throw new RuntimeException("Already occupied by a rover!");
        }

        this.roverCoordinates = roverCoordinates;
        this.heading = heading;
        this.plateau = plateau;

        plateau.deployRover(this);
    }

    public boolean hasPosition(RoverCoordinates pos) {
        return roverCoordinates.CoordinatesAreEqual(pos);
    }

    public String reportStatus() {
        return name + " at position " +
                reportPosition();
    }

    public String reportPosition() {
        if (this.roverCoordinates == null || this.heading == null) {
            return new NotLaunchedException().toString();
        }
        return roverCoordinates + " " + FromHeading(heading);
    }

    public void processInstructions(Direction[] directions) {
        for (Direction i : directions) {
            processInstruction(i);
        }
    }

    private void processInstruction(Direction direction) {
        if (this.roverCoordinates == null || this.heading == null) {
            throw new NotLaunchedException();
        }

        switch (direction) {
            case LEFT: turnLeft(); break;
            case MOVE: moveForward(); break;
            case RIGHT: turnRight(); break;
            default: throw new RuntimeException("Invalid direction");
        }
    }

    private void turnLeft() {
        switch (heading) {
            case EAST: heading = Heading.NORTH; break;
            case NORTH: heading = Heading.WEST; break;
            case SOUTH: heading = Heading.EAST; break;
            case WEST: heading = Heading.SOUTH; break;
            default: throw new RuntimeException("Invalid heading");
        }
    }

    private void turnRight() {
        switch (heading) {
            case EAST: heading = Heading.SOUTH; break;
            case NORTH: heading = Heading.EAST; break;
            case SOUTH: heading = Heading.WEST; break;
            case WEST: heading = Heading.NORTH; break;
            default: throw new RuntimeException("Invalid heading");
        }
    }

    private void moveForward() {
        RoverCoordinates newCoordinates = roverCoordinates.moveForward(heading);
        if (plateau.coordinatesOccupied(newCoordinates)) throw new PositionOccupiedException();
        if (newCoordinates.positionExists(plateau)) roverCoordinates = newCoordinates;
        else {
            throw new PositionNotExistException();
        }
    }

}
