import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShapeInfoPanel extends JPanel implements PropertyChangeListener {
    Shape currentShape;
    JTextField posX = new JTextField();
    JTextField posY = new JTextField();
    JButton apply = new JButton("Apply");
    public DrawingPanel drawingPanel;

    ShapeInfoPanel(){
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

    private boolean reposition(){
        int newX, newY;
        if(tryParseInt(posX.getText()) && tryParseInt(posY.getText())){
            newX = Integer.parseInt(posX.getText());
            newY = Integer.parseInt(posY.getText());
            currentShape.reposition(newX, newY);
            //TODO: Refactor
            drawingPanel.repaint();
            return true;
        }
        else {
            System.out.println("not a valid number");
            return false;
        }
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
        String propertyName = evt.getPropertyName();
        if ("x".equals(propertyName)){
            posX.setText(Integer.toString((Integer)evt.getNewValue()));
        } else if ("y".equals(propertyName)){
            posY.setText(Integer.toString((Integer)evt.getNewValue()));
        }
    }

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
