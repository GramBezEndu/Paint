import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class LineInfoPanel extends InfoPanel {
    JTextField length = new JTextField();
    LineInfoPanel(){
    }

    @Override
    protected void addExtraProperties() {
        add(new JLabel("Length"));
        add(length);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        super.propertyChange(evt);
    }
}
