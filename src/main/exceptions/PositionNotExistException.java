package main.exceptions;

public class PositionNotExistException extends RuntimeException {
	public PositionNotExistException() {
		super("Position not exist in the plateau!");
	}
}
