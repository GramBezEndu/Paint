import java.awt.*;

public class Circle extends Shape {
    Color color = Color.BLACK;
    int width;
    int height;

    Circle(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }

    @Override
    public void reposition(int newX, int newY) {
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
        g.drawOval(x, y, width, height);
        g.setColor(previousColor);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
