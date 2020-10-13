import java.awt.*;

public class Selector extends Rectangle {
    private Rectangle[] characteristicPoints;
    private float[] dash = { 15F, 15F };

    Selector(int x, int y, int w, int h) {
        super(x, y, w, h);
        createCharectiristicPoints();
    }

    private void createCharectiristicPoints(){
        int pointWidth = 8;
        int pointHeight = 8;
        characteristicPoints = new Rectangle[4];
        characteristicPoints[0] = new Rectangle(x, y, pointWidth, pointHeight);
        characteristicPoints[1] = new Rectangle(x + width - pointWidth, y, pointWidth, pointHeight);
        characteristicPoints[2] = new Rectangle(x, y + height - pointHeight, pointWidth, pointHeight);
        characteristicPoints[3] = new Rectangle(x + width - pointWidth, y + height - pointHeight, pointWidth, pointHeight);
        for(int i = 0; i < characteristicPoints.length; i++){
            characteristicPoints[i].setColor(Color.red);
            characteristicPoints[i].filled = true;
        }
    }

    @Override
    public void draw(Graphics g){
        Color previousColor = g.getColor();
        g.setColor(color);
        Stroke dashedStroke = new BasicStroke( 1F, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 3F, dash, 0F );
        ((Graphics2D) g).fill(dashedStroke.createStrokedShape(new java.awt.Rectangle(x, y, width, height)));
        for (var rect : characteristicPoints){
            rect.draw(g);
        }
        g.setColor(previousColor);
    }
}
