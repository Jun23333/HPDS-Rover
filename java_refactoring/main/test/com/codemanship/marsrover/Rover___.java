package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.ViewPoint;
import refactoring.Rover;
import refactoring.Rover.Order;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static refactoring.Rover.Order.*;

public class Rover___ {

    @Test
    public void should_stay() {
        ViewPoint viewPoint = mock(ViewPoint.class);
        Rover rover = new Rover(viewPoint);
        rover.go(Forward);
        assertThat(rover.viewPoint()).isEqualTo(viewPoint);
        verify(viewPoint,times(1)).forward();
        verify(viewPoint,never()).forward();
        verify(viewPoint,never()).backward();
        verify(viewPoint,never()).turnLeft();
        verify(viewPoint,never()).turnRight();
    }

    @Test
    public void should() {
        ViewPoint viewPoint = create(Forward,Left,Forward);
        Rover rover = new Rover(viewPoint);
        List<ViewPoint> points = new ArrayList<>();
        points.add(viewPoint);
        for (Order order : orders(Forward,Forward)) {
            rover.go(order);
            points.add(rover.viewPoint());
        }
        assertThat(points.get(0)).isNotEqualTo(points.get(1));
        assertThat(points.get(1)).isEqualTo(points.get(2));
    }

    private ViewPoint createMap(int [][] ints) {
        return null;
    }

    private Order[] orders(Order... orders) {
        return orders;
    }

    private ViewPoint create(Order... orders) {
        ViewPoint viewPoint = mock(ViewPoint.class);
        ViewPoint current = viewPoint;
        for (Order order: orders) {
            ViewPoint next = mock(ViewPoint.class);
            switch (order) {
                case Forward: when(current.forward()).thenReturn(next); break;
                case Backward: when(current.backward()).thenReturn(next); break;
                case Left: when(current.turnLeft()).thenReturn(next); break;
                case Right: when(current.turnRight()).thenReturn(next); break;
            }
            current = next;
        }
        return viewPoint;
    }
}
