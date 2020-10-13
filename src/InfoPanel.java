import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class InfoPanel extends JPanel implements PropertyChangeListener {
    Shape currentShape;
    JTextField posX = new JTextField();
    JTextField posY = new JTextField();
    JButton apply = new JButton("Apply");
    public DrawingPanel drawingPanel;

    InfoPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Properties"));
        add(new JLabel("Position:"));
        add(new JLabel("x:"));
        add(posX);
        add(new JLabel("y:"));
        add(posY);
        addExtraProperties();
        add(apply);
        ActionListener actionListener = e -> {
            if (validateProperties()){
                changeAttributes();
            } else{
            setTextFieldsValues();
            JOptionPane.showMessageDialog(null,
                    "Error: Please enter integer numbers bigger than 0", "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }
        };
        apply.addActionListener(actionListener);
    }

    protected abstract void addExtraProperties();

    protected void changeAttributes(){
        int newX = Integer.parseInt(posX.getText());
        int newY = Integer.parseInt(posY.getText());
        currentShape.reposition(newX, newY);
        //TODO: Refactor
        drawingPanel.repaint();
    }

    protected boolean validateProperties(){
        return (validateProperty(posX.getText()) && validateProperty(posY.getText()));
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
            setTextFieldsValues();
            currentShape.addPropertyChangeListener(this::propertyChange);
            setVisible(true);
        } else {
            posX.setText("");
            posY.setText("");
            setVisible(false);
        }
    }

    protected void setTextFieldsValues(){
        posX.setText(Integer.toString(currentShape.x));
        posY.setText(Integer.toString(currentShape.y));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if ("x".equals(propertyName)){
            posX.setText(Integer.toString((Integer)evt.getNewValue()));
        } if ("y".equals(propertyName)){
            posY.setText(Integer.toString((Integer)evt.getNewValue()));
        }
    }

    protected boolean validateProperty(String value) {
        try {
            int val = Integer.parseInt(value);
            if (val > 0)
                return true;
            else
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
