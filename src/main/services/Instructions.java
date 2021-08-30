package main.services;

import main.exceptions.UnknownDirectionException;
import main.model.enums.Direction;

import java.util.ArrayList;

public class Instructions {
	private final String instructions;
	public Instructions(String instructions) {
		this.instructions = instructions;
	}
	
	public Direction[] getInstructions() {
		ArrayList<Direction> result = new ArrayList<>();
		
		for (char c: instructions.toCharArray()) {
			switch (c) {
				case 'L': result.add(Direction.LEFT); break;
				case 'M': result.add(Direction.MOVE); break;
				case 'R': result.add(Direction.RIGHT); break;
				default: throw new UnknownDirectionException(c);
			}
		}
		
		return result.toArray(new Direction[0]);
	}
}
