import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OperationPanel extends JPanel {
    JLabel select = new JLabel("Management");
    JToggleButton selectBtn;
    JLabel label = new JLabel("New shape");
    ButtonGroup shapesGroup;
    JToggleButton rectangle;
    JToggleButton circle;
    JToggleButton line;
    BoxLayout layout;
    Operations.Operation currentOperation = Operations.Operation.Line;

    OperationPanel() {
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        shapesGroup = new ButtonGroup();
        createSelectBtn();
        createLineBtn();
        createRectBtn();
        createCircleBtn();
        shapesGroup.add(selectBtn);
        shapesGroup.add(line);
        shapesGroup.add(rectangle);
        shapesGroup.add(circle);
        selectLine();
        setLayout(layout);
        add(select);
        add(selectBtn);
        add(label);
        add(line);
        add(rectangle);
        add(circle);
        setVisible(true);
    }

    private void createSelectBtn(){
        selectBtn = new JToggleButton("Select");
        ActionListener actionListener = e -> currentOperation = Operations.Operation.Select;
        selectBtn.addActionListener(actionListener);
    }

    private void createLineBtn(){
        line = new JToggleButton("Line");
        ActionListener actionListener = e -> currentOperation = Operations.Operation.Line;
        line.addActionListener(actionListener);
    }

    private void createRectBtn(){
        rectangle = new JToggleButton("Rectangle");
        ActionListener actionListener = e -> currentOperation = Operations.Operation.Rectangle;
        rectangle.addActionListener(actionListener);
    }

    private void createCircleBtn(){
        circle = new JToggleButton("Circle");
        ActionListener actionListener = e -> currentOperation = Operations.Operation.Circle;
        circle.addActionListener(actionListener);
    }

    private void selectLine(){
        line.setSelected(true);
    }

    public Operations.Operation getCurrentOperation(){
        return currentOperation;
    }

    public void setCurrentOperation(Operations.Operation o){
        currentOperation = o;
        switch (currentOperation){
            case Line:
                selectLine();
                break;
            case Circle:
                circle.setSelected(true);
                break;
            case Select:
                selectBtn.setSelected(true);
                break;
            case Rectangle:
                rectangle.setSelected(true);
                break;
        }
    }
}
