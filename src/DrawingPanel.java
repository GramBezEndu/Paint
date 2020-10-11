import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    ShapesPanel shapesPanel;
    BoxLayout layout;
    Point firstClickPoint;
    Point secondClickPoint;
    ArrayList<Shape> shapes = new ArrayList<Shape>();

    DrawingPanel(ShapesPanel shapesPanel) {
        this.shapesPanel = shapesPanel;
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                if (getMousePosition() != null){
                    firstClickPoint = getMousePosition();
                }
            }
            public void mouseReleased(MouseEvent e){
                if (firstClickPoint != null && getMousePosition() != null){
                    secondClickPoint = getMousePosition();
                    addShape();
                    repaint();
                }
            }
        });
        setVisible(true);
    }

    private void addShape(){
        if (firstClickPoint != null){
            Point startingPoint;
            Dimension size;
            switch (shapesPanel.getShape()){
                case Line:
                    shapes.add(new Line(firstClickPoint.x, firstClickPoint.y, secondClickPoint.x, secondClickPoint.y));
                    break;
                case Circle:
                    startingPoint = calculateStartingPoint();
                    size = calculateSize();
                    shapes.add(new Circle(startingPoint.x, startingPoint.y, size.width, size.height));
                    break;
                case Rectangle:
                    startingPoint = calculateStartingPoint();
                    size = calculateSize();
                    shapes.add(new Rectangle(startingPoint.x, startingPoint.y, size.width, size.height));
                    break;
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (var shape : shapes){
            shape.draw(g);
        }
    }

    protected Point calculateStartingPoint(){
        return new Point(Math.min(firstClickPoint.x, secondClickPoint.x), Math.min(firstClickPoint.y, secondClickPoint.y));
    }

    protected Dimension calculateSize(){
        return new Dimension(Math.abs(secondClickPoint.x - firstClickPoint.x), Math.abs(secondClickPoint.y - firstClickPoint.y));
    }
}
