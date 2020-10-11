import javax.swing.*;

public class ShapeInfoPanel extends JPanel {
    Shape currentShape;
    JTextField posX = new JTextField();
    JTextField posY = new JTextField();

    ShapeInfoPanel(){
        posX.setEditable(false);
        posY.setEditable(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Properties"));
        add(new JLabel("Position:"));
        add(new JLabel("x:"));
        add(posX);
        add(new JLabel("y:"));
        add(posY);
        setVisible(true);
    }

    public Shape getCurrentShape(){
        return currentShape;
    }

    public void setCurrentShape(Shape s){
        currentShape = s;
        posX.setText(Integer.toString(currentShape.x));
        posY.setText(Integer.toString(currentShape.y));
    }
}
