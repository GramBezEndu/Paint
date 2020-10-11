import java.awt.*;

public class Circle extends Shape {
    int x;
    int y;
    int width;
    int height;

    Circle(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }
    @Override
    public void draw(Graphics g) {
        g.drawOval(x, y, width, height);
    }
}
