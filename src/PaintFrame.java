import javax.swing.*;

class PaintFrame extends JFrame {
    ShapesPanel shapesPanel = new ShapesPanel();
    JPanel mainPanel = new JPanel();

    PaintFrame(){
        super("PAINT");
        mainPanel.add(shapesPanel);
        mainPanel.add(new DrawingPanel(shapesPanel));
        setContentPane(mainPanel);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String args[]) {
        new PaintFrame();
    }
}