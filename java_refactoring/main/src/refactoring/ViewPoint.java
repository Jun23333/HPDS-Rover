package refactoring;

import java.util.HashMap;
import java.util.Map;

public interface ViewPoint {
    ViewPoint turnLeft();
    ViewPoint turnRight();
    ViewPoint forward();
    ViewPoint backward();
}
