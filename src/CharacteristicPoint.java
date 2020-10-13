import java.awt.*;

public class CharacteristicPoint {
    enum PointLocation {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
    };

    Point point;
    PointLocation pointLocation;

    CharacteristicPoint(int x, int y, PointLocation pointLocation){
        point = new Point(x, y);
        this.pointLocation = pointLocation;
    }
}
