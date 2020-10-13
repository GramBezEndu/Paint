import java.awt.*;

public class Selector {
    private int rectWidth = 8;
    private int rectHeight = 8;
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
        CharacteristicPoint[] characteristicPoints = shape.getCharacteristicPoints();
        resizeRectangles = new Rectangle[characteristicPoints.length];
        for (int i = 0; i < characteristicPoints.length; i++) {
            CharacteristicPoint p = characteristicPoints[i];
            switch (p.pointLocation){
                case TOP_LEFT:
                    resizeRectangles[i] = new Rectangle(p.point.x, p.point.y, rectWidth, rectHeight);
                    break;
                case TOP_RIGHT:
                    resizeRectangles[i] = new Rectangle(p.point.x - rectWidth, p.point.y, rectWidth, rectHeight);
                    break;
                case BOTTOM_LEFT:
                    resizeRectangles[i] = new Rectangle(p.point.x, p.point.y - rectHeight, rectWidth, rectHeight);
                    break;
                case BOTTOM_RIGHT:
                    resizeRectangles[i] = new Rectangle(p.point.x - rectWidth, p.point.y - rectHeight, rectWidth, rectHeight);
                    break;
            }
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
