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
    public void changeCharacteristicPoint(int index, int newX, int newY){
        CharacteristicPoint.PointLocation pl = characteristicPoints[index].pointLocation;
        switch (pl){
            case TOP_LEFT:
                if (width - (newX - x) >= 4 && height - (newY - y) >= 4){
                    propertyChangeSupport.firePropertyChange("x", x, newX);
                    propertyChangeSupport.firePropertyChange("y", y, newY);
                    propertyChangeSupport.firePropertyChange("width", width, width - (newX - x));
                    propertyChangeSupport.firePropertyChange("height", height, height - (newY - y));
                    width -= newX - x;
                    height -= newY - y;
                    x = newX;
                    y = newY;
                    updatePoints();
                }
                break;
            case TOP_RIGHT:
                if (width + (newX - (x + width)) >= 4 && height - (newY - y) >= 4){
                    propertyChangeSupport.firePropertyChange("y", y, newY);
                    propertyChangeSupport.firePropertyChange("width", width, width + (newX - (x + width)));
                    propertyChangeSupport.firePropertyChange("height", height, height - (newY - y));
                    width += newX - (x + width);
                    height -= newY - y;
                    y = newY;
                    updatePoints();
                }
                break;
            case BOTTOM_LEFT:
                if (width - (newX - x) >= 4 && height + (newY - (y + height)) >= 4){
                    propertyChangeSupport.firePropertyChange("x", x, newX);
                    propertyChangeSupport.firePropertyChange("width", width, width - (newX - x));
                    propertyChangeSupport.firePropertyChange("height", height, height + (newY - (y + height)));
                    width -= (newX - x);
                    height += (newY - (y + height));
                    x = newX;
                    updatePoints();
                }
                break;
            case BOTTOM_RIGHT:
                break;
        }
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
