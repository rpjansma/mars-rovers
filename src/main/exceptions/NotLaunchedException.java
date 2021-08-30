package main.exceptions;

public class NotLaunchedException extends RuntimeException {

	public NotLaunchedException() {
		super("Rover was not dropped on the plateau!");
	}
}
