import java.awt.*;

public class Rectangle extends Shape {
    int x;
    int y;
    int width;
    int height;
    Rectangle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x, y, width, height);
    }
}
