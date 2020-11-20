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
		set(go(Arrays.stream(orders)));
	}

	public void set(ViewPoint viewPoint) {
	    if (viewPoint == null) return;
	    this.viewPoint = viewPoint;
    }

	public void go(String instructions) {
		set(go(Arrays.stream(instructions.split("")).map(Order::of)));
	}

	private ViewPoint go(Stream<Order> orders) {
		return orders.filter(Objects::nonNull).reduce(this.viewPoint, this::execute, (v1,v2)->null);
	}

	private ViewPoint execute(ViewPoint v, Order o) {
        return v != null ? actions.get(o).execute(v) : null;
	}

	private Map<Order,Action> actions = new HashMap<>();
	{
		actions.put(Order.Left, ViewPoint::turnLeft);
		actions.put(Order.Right, ViewPoint::turnRight);
		actions.put(Order.Forward, ViewPoint::forward);
		actions.put(Order.Backward, ViewPoint::backward);
	}

	public interface Action {
		ViewPoint execute(ViewPoint viewPoint);
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

