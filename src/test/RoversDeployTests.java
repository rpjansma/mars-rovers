package test;

import main.exceptions.NotLaunchedException;
import main.exceptions.PositionNotExistException;
import main.exceptions.UnknownDirectionException;
import main.services.Instructions;
import main.model.Plateau;
import main.model.Rover;
import main.model.enums.Direction;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoversDeployTests {

	private final Plateau plateau = new Plateau("50 50");
	
	@Test
	public void curiosity_explore_with_success() {
		Rover rover = new Rover("Curiosity");
		rover.dropRover(plateau, 1, 2, 'N');

		rover.processInstructions(convertToArray("LMLMLMLMM"));
		
		String report = rover.reportPosition();		
		assertEquals("1 3 N", report);
	}
	
	private static Direction[] convertToArray(String instructions) {
		return new Instructions(instructions).getInstructions();
	}
	
	@Test
	public void perseverance_explore_with_success() {
		Rover rover = new Rover("Perseverance");
		rover.dropRover(plateau, 3, 3, 'E');

		rover.processInstructions(convertToArray("MMRMMRMRRM"));

		String report = rover.reportPosition();
		assertEquals("5 1 E", report);
	}

	@Test
	public void rover_outside_plateu() {
		Rover rover = new Rover("Opportunity");
		try {
			rover.dropRover(plateau, 60, -5, 'N');
			fail("Dropping should have thrown before!");
		}
		catch (PositionNotExistException ex) {
			assertEquals("Position not exist in the plateau!", ex.getMessage());
		}
	}

	@Test
	public void position_not_exist_while_moving_rover() {
		Rover rover = new Rover("Opportunity");
		rover.dropRover(plateau, 50, 50, 'N');

		try {
			rover.processInstructions(convertToArray("MMMMM"));
			fail("Moving should have thrown before!");
		}
		catch (PositionNotExistException ex) {
			assertEquals("Position not exist in the plateau!", ex.getMessage());
		}
	}

	@Test
	public void unknown_instruction_exception() {
		Rover rover = new Rover("Opportunity");
		try {
			rover.processInstructions(convertToArray("ABCD"));
			fail("Should have thrown before!");

		}
		catch (UnknownDirectionException ignored) { }
	}
	
	@Test
	public void two_rovers_in_one_position_exception() {
		Plateau giantPlateau = new Plateau("500 500");
		Rover rover1 = new Rover("Curiosity");
		Rover rover2 = new Rover("Opportunity");
		
		rover1.dropRover(giantPlateau, "230 400 E");
		
		try {
			rover2.dropRover(giantPlateau, "230 400 N");
			fail("Should have thrown before!");
		}
		catch (RuntimeException ignored) {
			
		}
	}
	
	@Test
	public void rover_reported_where_was_dropped() {

		Plateau plat = new Plateau("20 15");
		
		Rover rover1 = new Rover("Perseverance");

		rover1.dropRover(plat, "12 10 N");
		
		String report = rover1.reportPosition();

		assertEquals("12 10 N", report);
	}

	@Test
	public void not_dropped_rover_reported() {
		Rover rover = new Rover("Opportunity");
		String report = rover.reportPosition();
		assertEquals(new NotLaunchedException().toString(), report);
	}
}