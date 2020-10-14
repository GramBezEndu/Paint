import java.awt.*;
import java.awt.geom.Point2D;

public class Line extends Shape {
    Color color = Color.BLACK;
    private int x2;
    private int y2;

    public int getx2(){
        return x2;
    }

    public int gety2(){
        return y2;
    }

    public void setx2(int val){
        x2 = val;
        updatePoints();
    }

    public void sety2(int val){
        y2 = val;
        updatePoints();
    }

    Line(int x, int y, int x2, int y2){
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        createCharacteristicPoint();
    }

    private void createCharacteristicPoint() {
        characteristicPoints = new CharacteristicPoint[2];
        if (x <= x2){
            if (y >= y2){
                characteristicPoints[0] = new CharacteristicPoint(x, y, CharacteristicPoint.PointLocation.BOTTOM_LEFT);
                characteristicPoints[1] = new CharacteristicPoint(x2, y2, CharacteristicPoint.PointLocation.TOP_RIGHT);
            } else {
                characteristicPoints[0] = new CharacteristicPoint(x, y, CharacteristicPoint.PointLocation.TOP_LEFT);
                characteristicPoints[1] = new CharacteristicPoint(x2, y2, CharacteristicPoint.PointLocation.BOTTOM_RIGHT);
            }
        } else {
            if (y >= y2){
                characteristicPoints[0] = new CharacteristicPoint(x, y, CharacteristicPoint.PointLocation.BOTTOM_RIGHT);
                characteristicPoints[1] = new CharacteristicPoint(x2, y2, CharacteristicPoint.PointLocation.TOP_LEFT);
            } else {
                characteristicPoints[0] = new CharacteristicPoint(x, y, CharacteristicPoint.PointLocation.TOP_RIGHT);
                characteristicPoints[1] = new CharacteristicPoint(x2, y2, CharacteristicPoint.PointLocation.BOTTOM_LEFT);
            }
        }
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
        updatePoints();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(Math.min(x, x2), Math.min(y, y2), Math.abs(x - x2), Math.abs(y - y2));
    }

    @Override
    public void changeCharacteristicPoint(CharacteristicPoint point){
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void updatePoints() {
        characteristicPoints[0].point.x = x;
        characteristicPoints[0].point.y = y;
        characteristicPoints[1].point.x = x2;
        characteristicPoints[1].point.y = y2;
        if (x <= x2){
            if (y >= y2){
                characteristicPoints[0].pointLocation = CharacteristicPoint.PointLocation.BOTTOM_LEFT;
                characteristicPoints[1].pointLocation = CharacteristicPoint.PointLocation.TOP_RIGHT;
            } else {
                characteristicPoints[0].pointLocation = CharacteristicPoint.PointLocation.TOP_LEFT;
                characteristicPoints[1].pointLocation = CharacteristicPoint.PointLocation.BOTTOM_RIGHT;
            }
        } else {
            if (y >= y2){
                characteristicPoints[0].pointLocation =  CharacteristicPoint.PointLocation.BOTTOM_RIGHT;
                characteristicPoints[1].pointLocation = CharacteristicPoint.PointLocation.TOP_LEFT;
            } else {
                characteristicPoints[0].pointLocation = CharacteristicPoint.PointLocation.TOP_RIGHT;
                characteristicPoints[1].pointLocation = CharacteristicPoint.PointLocation.BOTTOM_LEFT;
            }
        }
    }
}
