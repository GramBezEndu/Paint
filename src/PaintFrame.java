import javax.swing.*;
import java.awt.*;

class PaintFrame extends JFrame {
    OperationPanel operationPanel = new OperationPanel();
    ShapeInfoPanel shapeInfoPanel = new ShapeInfoPanel();
    JPanel mainPanel = new JPanel();
    GridLayout layout = new GridLayout(2, 2);

    PaintFrame(){
        super("PAINT");
        setLayout(layout);
        mainPanel.add(operationPanel);
        mainPanel.add(new DrawingPanel(operationPanel, shapeInfoPanel));
        mainPanel.add(shapeInfoPanel);
        setContentPane(mainPanel);
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pack();
        setVisible(true);
    }
    public static void main(String args[]) {
        new PaintFrame();
    }
}