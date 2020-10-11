import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

//    private void changePosX() {
//        String textContent = posX.getText();
//        int value = Integer.parseInt(textContent);
//        if (value <= 0){
//            JOptionPane.showMessageDialog(null,
//                    "Error: Please enter number bigger than 0", "Error Message",
//                    JOptionPane.ERROR_MESSAGE);
//        } else {
//            currentShape.x = value;
//        }
//    }

    public Shape getCurrentShape(){
        return currentShape;
    }

    public void setCurrentShape(Shape s){
        currentShape = s;
        if (currentShape != null){
            posX.setText(Integer.toString(currentShape.x));
            posY.setText(Integer.toString(currentShape.y));
        }
        else{
            posX.setText("");
            posY.setText("");
        }
    }
}
