import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Shape {
    protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    protected int x;
    protected int y;
    protected CharacteristicPoint[] characteristicPoints;
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    public abstract void reposition(int newX, int newY);
    public abstract Color getColor();
    public abstract void setColor(Color c);
    public abstract void draw(Graphics g);
    public abstract Rectangle getBounds();
    public CharacteristicPoint[] getCharacteristicPoints() {
        return characteristicPoints;
    }
    public abstract void changeCharacteristicPoint(CharacteristicPoint point);
    protected abstract void updatePoints();
}
