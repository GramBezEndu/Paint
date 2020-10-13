import java.awt.*;

public class Selector {
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    Rectangle selector;
    Rectangle[] resizeRectangles;
    private Shape shape;
    private float[] dash = { 15F, 15F };

    Selector(Shape shape) {
        this.shape = shape;
        selector = new Rectangle(shape.getBounds().x, shape.getBounds().y, shape.getBounds().width, shape.getBounds().height);
        createResizeRectangles();
    }

    private void createResizeRectangles(){
        Point[] characteristicPoints = shape.getCharacteristicPoints();
        resizeRectangles = new Rectangle[characteristicPoints.length];
        for (int i = 0; i < characteristicPoints.length; i++) {
            Point p = characteristicPoints[i];
            resizeRectangles[i] = new Rectangle(p.x, p.y, WIDTH, HEIGHT);
            resizeRectangles[i].setColor(Color.red);
            resizeRectangles[i].filled = true;
        }
    }

    public void draw(Graphics g){
        selector = new Rectangle(shape.getBounds().x, shape.getBounds().y, shape.getBounds().width, shape.getBounds().height);
        createResizeRectangles();
        Color previousColor = g.getColor();
        g.setColor(Color.black);
        Stroke dashedStroke = new BasicStroke( 1F, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 3F, dash, 0F );
        ((Graphics2D) g).fill(dashedStroke.createStrokedShape(new java.awt.Rectangle(selector.x, selector.y, selector.width, selector.height)));
        for (var rect : resizeRectangles){
            rect.draw(g);
        }
        g.setColor(previousColor);
    }
}
