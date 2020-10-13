import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class ShapeInfoPanel extends InfoPanel {
    JTextField width;
    JTextField height;
    ShapeInfoPanel(){
    }

    @Override
    protected void addExtraProperties() {
        width = new JTextField();
        height = new JTextField();
        add(new JLabel("Width"));
        add(width);
        add(new JLabel("Height"));
        add(height);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        super.propertyChange(evt);
        String propertyName = evt.getPropertyName();
        if ("width".equals(propertyName)){
            width.setText(Integer.toString((Integer)evt.getNewValue()));
        } if ("height".equals(propertyName)){
            height.setText(Integer.toString((Integer)evt.getNewValue()));
        }
    }

    @Override
    protected void setTextFieldsValues(){
        super.setTextFieldsValues();
        FullShape shape = (FullShape)currentShape;
        width.setText(Integer.toString(shape.width));
        height.setText(Integer.toString(shape.height));
    }

    @Override
    protected boolean validateProperties(){
        if (super.validateProperties()){
            return (validateProperty(width.getText()) && validateProperty(height.getText()));
        } else {
            return false;
        }
    }

    @Override
    protected void changeAttributes(){
        super.changeAttributes();
        int newWidth = Integer.parseInt(width.getText());
        int newHeight = Integer.parseInt(height.getText());
        FullShape shape = (FullShape)currentShape;
        shape.changeWidth(newWidth);
        shape.changeHeight(newHeight);
    }
}
