import java.awt.*;

public class Rectangle extends FullShape {
    Color color = Color.BLACK;
    boolean filled = false;

    Rectangle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    @Override
    public void reposition(int newX, int newY) {
        propertyChangeSupport.firePropertyChange("x", x, newX);
        propertyChangeSupport.firePropertyChange("y", y, newY);
        this.x = newX;
        this.y = newY;
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
        if (filled){
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
        g.setColor(previousColor);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean contains(Point p){
        java.awt.Rectangle r = new java.awt.Rectangle(x, y, width, height);
        return  r.contains(p);
    }
}
