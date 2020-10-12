import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShapeInfoPanel extends JPanel implements PropertyChangeListener {
    Shape currentShape;
    JTextField posX = new JTextField();
    JTextField posY = new JTextField();
    JButton apply = new JButton("Apply");

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
        add(apply);
        ActionListener actionListener = e -> {
            reposition();
        };
        apply.addActionListener(actionListener);
        setVisible(false);
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

    private void reposition(){
//        currentShape.reposition(0, 0);
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public Shape getCurrentShape(){
        return currentShape;
    }

    public void setCurrentShape(Shape s) {
        if (currentShape != null){
            currentShape.removePropertyChangeListener(this);
        }
        currentShape = s;
        if (currentShape != null) {
            posX.setText(Integer.toString(currentShape.x));
            posY.setText(Integer.toString(currentShape.y));
            currentShape.addPropertyChangeListener(this::propertyChange);
            setVisible(true);
        } else {
            posX.setText("");
            posY.setText("");
            setVisible(false);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        posX.setText(Integer.toString(currentShape.x));
        posY.setText(Integer.toString(currentShape.y));
    }
}
