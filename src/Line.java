import java.awt.*;
import java.awt.geom.Point2D;

public class Line extends Shape {
    Color color = Color.BLACK;
    int x2;
    int y2;

    Line(int x, int y, int x2, int y2){
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color c) {
        color = c;
    }

    @Override
    public void draw(Graphics g) {
        Color previousColor = g.getColor();
        g.setColor(color);
        g.drawLine(x, y, x2, y2);
        g.setColor(previousColor);
    }

    public void reposition(int newX, int newY){
        propertyChangeSupport.firePropertyChange("x", x, newX);
        propertyChangeSupport.firePropertyChange("y", y, newY);
        int differenceX = newX - x;
        int differenceY = newY - y;
        this.x = newX;
        this.y = newY;
        this.x2 += differenceX;
        this.y2 += differenceY;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(Math.min(x, x2), Math.min(y, y2), Math.abs(x - x2), Math.abs(y - y2));
    }

    @Override
    public CharacteristicPoint[] getCharacteristicPoints() {
        var points = new CharacteristicPoint[2];
        if (x <= x2){
            if (y >= y2){
                points[0] = new CharacteristicPoint(x, y, CharacteristicPoint.PointLocation.BOTTOM_LEFT);
                points[1] = new CharacteristicPoint(x2, y2, CharacteristicPoint.PointLocation.TOP_RIGHT);
            } else {
                points[0] = new CharacteristicPoint(x, y, CharacteristicPoint.PointLocation.TOP_LEFT);
                points[1] = new CharacteristicPoint(x2, y2, CharacteristicPoint.PointLocation.BOTTOM_RIGHT);
            }
        } else {
            if (y >= y2){
                points[0] = new CharacteristicPoint(x, y, CharacteristicPoint.PointLocation.BOTTOM_RIGHT);
                points[1] = new CharacteristicPoint(x2, y2, CharacteristicPoint.PointLocation.TOP_LEFT);
            } else {
                points[0] = new CharacteristicPoint(x, y, CharacteristicPoint.PointLocation.TOP_RIGHT);
                points[1] = new CharacteristicPoint(x2, y2, CharacteristicPoint.PointLocation.BOTTOM_LEFT);
            }
        }
        return points;
    }
}
