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
    public CharacteristicPoint[] getCharacteristicPoints() {
        var points = new CharacteristicPoint[4];
        points[0] = new CharacteristicPoint(x, y, CharacteristicPoint.PointLocation.TOP_LEFT);
        points[1] = new CharacteristicPoint(x + width, y, CharacteristicPoint.PointLocation.TOP_RIGHT);
        points[2] = new CharacteristicPoint(x, y + height, CharacteristicPoint.PointLocation.BOTTOM_LEFT);
        points[3] = new CharacteristicPoint(x + width, y + height, CharacteristicPoint.PointLocation.BOTTOM_RIGHT);
        return points;
    }
}
