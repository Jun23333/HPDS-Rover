package refactoring;

import javafx.geometry.Pos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Rover {
	private ViewPoint viewPoint;

	public ViewPoint viewPoint() {
		return viewPoint;
	}

	public Rover(ViewPoint viewPoint) {
		this.viewPoint = viewPoint;
	}

	public void go(Order... orders){
		go(Arrays.stream(orders));
	}

	public void go(String instructions) {
		go(Arrays.stream(instructions.split("")).map(Order::of));
	}

	private void go(Stream<Order> orders) {
		orders.filter(Objects::nonNull).forEach(this::execute);
	}

	private void execute(Order order) {
		actions.get(order).execute();
	}

	private Map<Order,Action> actions = new HashMap<>();
	{
		actions.put(Order.Left, this::turnLeft);
		actions.put(Order.Right, this::turnRight);
		actions.put(Order.Forward, this::forward);
		actions.put(Order.Backward, this::backward);
	}

	private void turnLeft() {
		viewPoint = viewPoint.turnLeft();
	}

	private void turnRight() {
		viewPoint = viewPoint.turnRight();
	}

	private void forward() {
		viewPoint = viewPoint.forward();
	}

	private void backward() {
		viewPoint = viewPoint.backward();
	}

	public interface Action {
		void execute();
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
}

