package main.exceptions;

public class UnknownHeadingException extends RuntimeException {

	public UnknownHeadingException(char heading) {
		super("The character '" + heading + "' is not supported. Insert N(orth), W(est), S(outh) or E(ast).");
	}
}
