package refactoring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Rover {
	private Heading heading;
	private Position position;
	private Map<Order, Action> actions = new HashMap<>();

	public Rover(String facing, int x, int y) {
		this(Heading.of(facing),new Position(x,y));
	}

	public Rover(Heading heading, int x, int y) {
		this(heading,new Position(x,y));
	}

	public Rover(Heading heading, Position position) {
		this.heading = heading;
		this.position = position;
		this.actions.put(Order.Left, ()-> this.heading = this.heading.turnLeft());
		this.actions.put(Order.Right, ()-> this.heading = this.heading.turnRight());
		this.actions.put(Order.Forward, ()-> this.position = this.position.forward(this.heading));
		this.actions.put(Order.Backward, ()-> this.position = this.position.backward(this.heading));
		this.actions.put(null, ()-> this.heading = this.heading);
	}

	public Heading heading() {
		return this.heading;
	}

	public Position position() {
		return this.position;
	}

	public void go(Order... orders){
		go(Arrays.stream(orders));
	}

	public void go(String instructions) {
		go(Arrays.stream(instructions.split("")).map(Order::of));
	}

	private void go(Stream<Order> orders) {
		orders.forEach(o->actions.get(o).execute());
	}

	public interface Action {
		void execute();
	}

	public static class Position {
		private final int x;
		private final int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Position forward(Heading heading) {
			return move(heading,1);
		}

		public Position backward(Heading heading) {
			return move(heading,-1);
		}

		public Position move(Heading heading, int i) {
			if(heading.equals(Heading.North)) return new Position(this.x,this.y+i);
			if(heading.equals(Heading.East)) return new Position(this.x+i,this.y);
			if(heading.equals(Heading.South)) return new Position(this.x,this.y-i);
			if(heading.equals(Heading.West)) return new Position(this.x-i,this.y);
			return null;
		}

		@Override
		public boolean equals(Object object) {
			return isSameClass(object) && equals((Position) object);
		}

		private boolean equals(Position position) {
			return position == this || (x == position.x && y == position.y);
		}

		private boolean isSameClass(Object object) {
			return object != null && object.getClass() == Position.class;
		}

	}

	public enum Order {
		Forward, Backward, Left, Right;

		public static Order of(char label) {
			if (label == 'L') return Left;
			if (label == 'R') return Right;
			if (label == 'F') return Forward;
			if (label == 'B') return Backward;
			return null;
		}

		public static Order of(String value) {
			return of(value.charAt(0));
		}
	}



	public enum Heading {
		North, East, South, West;

		public static Heading of(String label) {
			return of(label.charAt(0));
		}

		public static Heading of(char label) {
			if (label == 'N') return North;
			if (label == 'S') return South;
			if (label == 'W') return West;
			if (label == 'E') return East;
			return null;
		}

		public Heading turnRight() {
			return values()[add(+1)];
		}

		public Heading turnLeft() {
			return values()[add(-1)];
		}

		private int add(int offset) {
			return (this.ordinal() + offset + values().length) % values().length;
		}

	}


}
