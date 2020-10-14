import java.awt.*;

public abstract class FullShape extends Shape {
    protected int width;
    protected int height;

    protected void createPoints() {
        characteristicPoints = new CharacteristicPoint[4];
        characteristicPoints[0] = new CharacteristicPoint(x, y, CharacteristicPoint.PointLocation.TOP_LEFT);
        characteristicPoints[1] = new CharacteristicPoint(x + width, y, CharacteristicPoint.PointLocation.TOP_RIGHT);
        characteristicPoints[2] = new CharacteristicPoint(x, y + height, CharacteristicPoint.PointLocation.BOTTOM_LEFT);
        characteristicPoints[3] = new CharacteristicPoint(x + width, y + height, CharacteristicPoint.PointLocation.BOTTOM_RIGHT);
    }

    public void changeHeight(int newHeight){
        propertyChangeSupport.firePropertyChange("height", height, newHeight);
        this.height = newHeight;
        updatePoints();
    }

    public void changeWidth(int newWidth){
        propertyChangeSupport.firePropertyChange("width", width, newWidth);
        this.width = newWidth;
        updatePoints();
    }

    @Override
    public void changeCharacteristicPoint(CharacteristicPoint point){
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void updatePoints(){
        characteristicPoints[0].point.x = x;
        characteristicPoints[0].point.y = y;
        characteristicPoints[0].pointLocation = CharacteristicPoint.PointLocation.TOP_LEFT;
        characteristicPoints[1].point.x = x + width;
        characteristicPoints[1].point.y = y;
        characteristicPoints[1].pointLocation = CharacteristicPoint.PointLocation.TOP_RIGHT;
        characteristicPoints[2].point.x = x;
        characteristicPoints[2].point.y = y + height;
        characteristicPoints[2].pointLocation = CharacteristicPoint.PointLocation.BOTTOM_LEFT;
        characteristicPoints[3].point.x = x + width;
        characteristicPoints[3].point.y = y + height;
        characteristicPoints[3].pointLocation = CharacteristicPoint.PointLocation.BOTTOM_RIGHT;
    }
}
