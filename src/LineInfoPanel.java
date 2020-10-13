import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class LineInfoPanel extends InfoPanel {
    JTextField x2;
    JTextField y2;
    LineInfoPanel(){
    }

    @Override
    protected void addExtraProperties() {
        x2 = new JTextField();
        y2 = new JTextField();
        add(new JLabel("x2"));
        add(x2);
        add(new JLabel("y2"));
        add(y2);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        super.propertyChange(evt);
        String propertyName = evt.getPropertyName();
        if ("x2".equals(propertyName)){
            x2.setText(Integer.toString((Integer)evt.getNewValue()));
        } if ("y2".equals(propertyName)){
            y2.setText(Integer.toString((Integer)evt.getNewValue()));
        }
    }

    @Override
    protected void setTextFieldsValues(){
        super.setTextFieldsValues();
        Line shape = (Line)currentShape;
        x2.setText(Integer.toString(shape.x2));
        y2.setText(Integer.toString(shape.y2));
    }

    @Override
    protected boolean validateProperties(){
        if (super.validateProperties()){
            return (validateProperty(x2.getText()) && validateProperty(y2.getText()));
        } else {
            return false;
        }
    }

    @Override
    protected void changeAttributes(){
        super.changeAttributes();
        int newX2 = Integer.parseInt(x2.getText());
        int newY2 = Integer.parseInt(y2.getText());
        Line shape = (Line)currentShape;
        shape.x2 = newX2;
        shape.y2 = newY2;
    }
}
