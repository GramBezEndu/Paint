import java.awt.*;

public abstract class Shape {
    protected int x;
    protected  int y;
    public abstract Color getColor();
    public abstract void setColor(Color c);
    public abstract void draw(Graphics g);
    public abstract Rectangle getBounds();
}
