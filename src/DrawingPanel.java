import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    OperationPanel operationPanel;
    ShapeInfoPanel shapeInfoPanel;

    MouseAdapter mouseAdapter;

    BoxLayout layout;
    Point clickPoint;
    Point releasePoint;
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    Point relative;

    void setSelectedShape(Shape s){
        selectedShape = s;
        shapeInfoPanel.setCurrentShape(s);
        if (s != null){
            relative = new Point(clickPoint.x - getSelectedShapeBounds().x, clickPoint.y - getSelectedShapeBounds().y);
        }
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

    DrawingPanel(OperationPanel operationPanel, ShapeInfoPanel shapeInfoPanel) {
        this.operationPanel = operationPanel;
        this.shapeInfoPanel = shapeInfoPanel;
        shapeInfoPanel.drawingPanel = this;
        mouseAdapter = createMouseAdapter();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(900, 700));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setVisible(true);
    }

    private Shape findShape(Point mousePoint) {
        for (var shape: shapes){
            if (shape.getBounds().contains(mousePoint)){
                return shape;
            }
        }
        return null;
    }

    private void addShape(){
        Point startingPoint;
        Dimension size;
        Shape newShape;
        switch (operationPanel.getCurrentOperation()){
            case Line:
                newShape = new Line(clickPoint.x, clickPoint.y, releasePoint.x, releasePoint.y);
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
            default:
                break;
        }
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1.5F));
        super.paintComponent(g);
        for (var shape : shapes){
            shape.draw(g);
        }
        Rectangle selectedBounds = getSelectedShapeBounds();
        if (selectedBounds != null){
            g.setColor(Color.red);
            float[] dash = { 15F, 15F };
            Stroke dashedStroke = new BasicStroke( 3F, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 3F, dash, 0F );
            ((Graphics2D) g).fill(dashedStroke.createStrokedShape(new java.awt.Rectangle(selectedBounds.x, selectedBounds.y, selectedBounds.width, selectedBounds.height)));
        }
    }

    protected Point calculateStartingPoint(){
        return new Point(Math.min(clickPoint.x, releasePoint.x), Math.min(clickPoint.y, releasePoint.y));
    }

    protected Dimension calculateSize(){
        return new Dimension(Math.abs(releasePoint.x - clickPoint.x), Math.abs(releasePoint.y - clickPoint.y));
    }

    private MouseAdapter createMouseAdapter(){
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                if (getMousePosition() != null){
                    clickPoint = getMousePosition();
                    if(operationPanel.getCurrentOperation() == Operations.Operation.Select || (getSelectedShapeBounds() != null && getSelectedShapeBounds().contains(clickPoint))){
                        //Note: will set null if clicked outside of any shape
                        operationPanel.setCurrentOperation(Operations.Operation.Select);
                        setSelectedShape(findShape(clickPoint));
                        repaint();
                    }
                }
            }
            public void mouseDragged(MouseEvent e){
                if (selectedShape != null && operationPanel.getCurrentOperation() == Operations.Operation.Select){
                    //TODO: Add resize or drag shape
                    Rectangle selector = getSelectedShapeBounds();
                    Point mouseLoc = getMousePosition();
                    selectedShape.reposition(mouseLoc.x - relative.x, mouseLoc.y - relative.y);
                    repaint();
                }
            }
            public void mouseReleased(MouseEvent e){
                if (clickPoint != null && getMousePosition() != null){
                    releasePoint = getMousePosition();
                    addShape();
                    repaint();
                }
            }
        };
    }
}
