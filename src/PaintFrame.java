import javax.swing.*;
import java.awt.*;

class PaintFrame extends JFrame {
    OperationPanel operationPanel = new OperationPanel();
    JPanel mainPanel = new JPanel();
    GridLayout layout = new GridLayout(2, 2);

    PaintFrame(){
        super("PAINT");
        setLayout(layout);
        mainPanel.add(operationPanel);
        mainPanel.add(new DrawingPanel(operationPanel));
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    public static void main(String args[]) {
        new PaintFrame();
    }
}