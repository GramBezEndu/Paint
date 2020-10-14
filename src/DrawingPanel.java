import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class DrawingPanel extends JPanel {
    OperationPanel operationPanel;
    InfoPanel infoPanel;

    MouseAdapter mouseAdapter;

    LayoutManager layout;
    Point clickPoint;
    Point releasePoint;
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    Point relative;
    Rectangle resizeRectangle;
    int resizeIndex = -1;
    Shape selectedShape;
    Selector selector;

    void setSelectedShape(Shape s){
        selectedShape = s;
        if (s != null){
            if (selectedShape.getClass() == Line.class){
                relative = new Point(clickPoint.x - selectedShape.x, clickPoint.y - selectedShape.y);
                removeInfoPanels();
                infoPanel = new LineInfoPanel();
            } else {
                relative = new Point(clickPoint.x - selectedShape.getBounds().x, clickPoint.y - selectedShape.getBounds().y);
                removeInfoPanels();
                infoPanel = new ShapeInfoPanel();
            }
            infoPanel.drawingPanel = this;
            infoPanel.setCurrentShape(selectedShape);
            selector = new Selector(selectedShape);
            resizeRectangle = checkIfResizeRequested();
            resizeIndex = Arrays.asList(selector.resizeRectangles).indexOf(resizeRectangle);
            add(infoPanel);
            validate();
            repaint();
        } else {
            removeInfoPanels();
            infoPanel = null;
            selector = null;
            resizeRectangle = null;
            resizeIndex = -1;
        }
    }

    private Rectangle checkIfResizeRequested(){
        for (var resizeRect : selector.resizeRectangles){
            if (resizeRect.contains(clickPoint)){
                return resizeRect;
            }
        }
        return null;
    }

    private void removeInfoPanels() {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof InfoPanel)
                remove(component);
        }
    }

    Rectangle getSelectedShapeBounds(){
        if (selectedShape != null){
            return selectedShape.getBounds();
        }
        else {
            return null;
        }
    }

    DrawingPanel(OperationPanel operationPanel) {
        this.operationPanel = operationPanel;
        mouseAdapter = createMouseAdapter();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        layout = new FlowLayout(FlowLayout.RIGHT);
        setLayout(layout);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(900, 700));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setVisible(true);
    }

    private Shape findShape(Point mousePoint) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.getBounds().contains(mousePoint)) {
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
        super.paintComponent(g);
        for (var shape : shapes){
            shape.draw(g);
        }
        if (selector != null){
            selector.draw(g);
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
                    Point mouseLoc = getMousePosition();
                    if (mouseLoc != null){
                        if (resizeRectangle != null){
                            //TODO: Add resize
                            selectedShape.changeCharacteristicPoint(resizeIndex, mouseLoc.x, mouseLoc.y);
                        } else {
                            selectedShape.reposition(mouseLoc.x - relative.x, mouseLoc.y - relative.y);
                        }
                        repaint();
                    }
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
