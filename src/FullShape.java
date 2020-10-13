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
}
