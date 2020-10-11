import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    ShapesPanel shapesPanel;
    ShapeInfoPanel shapeInfoPanel;

    BoxLayout layout;
    Point firstClickPoint;
    Point secondClickPoint;
    ArrayList<Shape> shapes = new ArrayList<Shape>();

    void setSelectedShape(Shape s){
        selectedShape = s;
        shapeInfoPanel.setCurrentShape(s);
    }

    Rectangle getSelectedShapeBounds(){
        if (selectedShape != null){
            return selectedShape.getBounds();
        }
        else {
            return  null;
        }
    }

    Shape selectedShape;

    DrawingPanel(ShapesPanel shapesPanel, ShapeInfoPanel shapeInfoPanel) {
        this.shapesPanel = shapesPanel;
        this.shapeInfoPanel = shapeInfoPanel;
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        setBackground(new Color(222, 222, 222, 255));
        setPreferredSize(new Dimension(700, 500));
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
            Shape newShape;
            switch (shapesPanel.getShape()){
                case Line:
                    newShape = new Line(firstClickPoint.x, firstClickPoint.y, secondClickPoint.x, secondClickPoint.y);
                    shapes.add(newShape);
                    setSelectedShape(newShape);
                    break;
                case Circle:
                    startingPoint = calculateStartingPoint();
                    size = calculateSize();
                    newShape = new Circle(startingPoint.x, startingPoint.y, size.width, size.height);
                    shapes.add(newShape);
                    setSelectedShape(newShape);
                    break;
                case Rectangle:
                    startingPoint = calculateStartingPoint();
                    size = calculateSize();
                    newShape = new Rectangle(startingPoint.x, startingPoint.y, size.width, size.height);
                    shapes.add(newShape);
                    setSelectedShape(newShape);
                    break;
            }
        }
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2F));
        super.paintComponent(g);
        for (var shape : shapes){
            shape.draw(g);
        }
        Rectangle selectedBounds = getSelectedShapeBounds();
        if (selectedBounds != null){
            g.setColor(Color.red);
            float[] dash = { 10F, 10F };
            Stroke dashedStroke = new BasicStroke( 2F, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 3F, dash, 0F );
            ((Graphics2D) g).fill( dashedStroke.createStrokedShape(new java.awt.Rectangle(selectedBounds.x, selectedBounds.y, selectedBounds.width, selectedBounds.height)));
        }
    }

    protected Point calculateStartingPoint(){
        return new Point(Math.min(firstClickPoint.x, secondClickPoint.x), Math.min(firstClickPoint.y, secondClickPoint.y));
    }

    protected Dimension calculateSize(){
        return new Dimension(Math.abs(secondClickPoint.x - firstClickPoint.x), Math.abs(secondClickPoint.y - firstClickPoint.y));
    }
}
