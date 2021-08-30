package main.exceptions;

public class UnknownDirectionException extends RuntimeException {

	public UnknownDirectionException(char instruction) {
		super("The direction '" + instruction + "' was not recognized. Insert R(ight), M(ove) or L(eft), please.");
	}
}
