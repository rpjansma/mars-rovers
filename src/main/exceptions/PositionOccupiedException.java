package main.exceptions;

public class PositionOccupiedException extends RuntimeException {
	public PositionOccupiedException() {
		super("Sorry, the path has an Rover, change your directions to keep exploring.");
	}
}
