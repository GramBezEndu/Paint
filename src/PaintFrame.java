import javax.swing.*;
import java.awt.*;

class PaintFrame extends JFrame {
    ShapesPanel shapesPanel = new ShapesPanel();
    ShapeInfoPanel shapeInfoPanel = new ShapeInfoPanel();
    JPanel mainPanel = new JPanel();
    GridLayout layout = new GridLayout(2, 2);

    PaintFrame(){
        super("PAINT");
        setLayout(layout);
        mainPanel.add(shapesPanel);
        mainPanel.add(new DrawingPanel(shapesPanel, shapeInfoPanel));
        mainPanel.add(shapeInfoPanel);
        setContentPane(mainPanel);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String args[]) {
        new PaintFrame();
    }
}