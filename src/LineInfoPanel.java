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
    }
}
