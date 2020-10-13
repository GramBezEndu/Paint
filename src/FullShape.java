import java.awt.*;

public abstract class FullShape extends Shape {
    protected int width;
    protected int height;

    public void changeHeight(int newHeight){
        propertyChangeSupport.firePropertyChange("height", height, newHeight);
        this.height = newHeight;
    }

    public void changeWidth(int newWidth){
        propertyChangeSupport.firePropertyChange("width", width, newWidth);
        this.width = newWidth;
    }

    @Override
    public Point[] getCharacteristicPoints() {
        var points = new Point[4];
        points[0] = new Point(x, y);
        points[1] = new Point(x + width - Selector.WIDTH, y);
        points[2] = new Point(x, y + height - Selector.HEIGHT);
        points[3] = new Point(x + width - Selector.WIDTH, y + height - Selector.HEIGHT);
        return points;
    }
}
