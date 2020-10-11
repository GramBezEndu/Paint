import javax.swing.*;
import java.awt.event.ActionListener;

public class ShapesPanel extends JPanel {
    JLabel label = new JLabel("Shapes");
    ButtonGroup shapesGroup;
    JToggleButton rectangle;
    JToggleButton circle;
    JToggleButton line;
    BoxLayout layout;
    Shapes.AllShapes currentlySelected = Shapes.AllShapes.Line;

    ShapesPanel() {
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        shapesGroup = new ButtonGroup();
        createLineBtn();
        createRectBtn();
        createCircleBtn();
        shapesGroup.add(line);
        shapesGroup.add(rectangle);
        shapesGroup.add(circle);
        selectLine();
        setLayout(layout);
        add(label);
        add(line);
        add(rectangle);
        add(circle);
        setVisible(true);
    }

    private void createLineBtn(){
        line = new JToggleButton("Line");
        ActionListener actionListener = e -> currentlySelected = Shapes.AllShapes.Line;
        line.addActionListener(actionListener);
    }

    private void createRectBtn(){
        rectangle = new JToggleButton("Rectangle");
        ActionListener actionListener = e -> currentlySelected = Shapes.AllShapes.Rectangle;
        rectangle.addActionListener(actionListener);
    }

    private void createCircleBtn(){
        circle = new JToggleButton("Circle");
        ActionListener actionListener = e -> currentlySelected = Shapes.AllShapes.Circle;
        circle.addActionListener(actionListener);
    }

    private void selectLine(){
        line.setSelected(true);
    }

    public Shapes.AllShapes getShape(){
        return currentlySelected;
    }
}
