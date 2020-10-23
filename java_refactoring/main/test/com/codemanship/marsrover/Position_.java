package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.Rover;
import refactoring.Rover.Position;

import static org.junit.Assert.assertEquals;
import static refactoring.Rover.Heading.*;

public class Position_ {

	@Test
	public void should_calculate_forward_position() {
		assertEquals(new Position(-1,0), new Position(0,0).forward(North).forward(West).forward(South));
		assertEquals(new Position(0,3), new Position(0,0).forward(North).forward(North).forward(North));
		assertEquals(new Position(0,1), new Position(0,0).forward(North).forward(West).forward(East));
		assertEquals(new Position(0,-1), new Position(0,0).forward(North).forward(South).forward(South));
		assertEquals(new Position(-2,-1), new Position(0,0).forward(West).forward(West).forward(South));
	}

	@Test
	public void should_calculate_backward_position() {
		assertEquals(new Position(1,0), new Position(0,0).backward(North).backward(West).backward(South));
		assertEquals(new Position(0,-3), new Position(0,0).backward(North).backward(North).backward(North));
		assertEquals(new Position(0,-1), new Position(0,0).backward(North).backward(West).backward(East));
		assertEquals(new Position(0,1), new Position(0,0).backward(North).backward(South).backward(South));
		assertEquals(new Position(2,1), new Position(0,0).backward(West).backward(West).backward(South));
	}
}
