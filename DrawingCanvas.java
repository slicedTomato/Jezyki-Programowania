import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingCanvas extends JFrame {

    private ShapePanel shapePanel;
    public DrawingCanvas(){
        setTitle("Simple Drawing Canvas [ Wybierz k - kwadrat lub o - koło ]");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        shapePanel = new ShapePanel();
        getContentPane().add(shapePanel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                shapePanel.setMousePosition(e.getPoint());
                shapePanel.repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                switch (e.getKeyChar()){
                    case 'k':
                        shapePanel.setSelectedShape(ShapePanel.Shapes.KWADRAT);
                        break;
                    case 'o':
                        shapePanel.setSelectedShape(ShapePanel.Shapes.OWAL);
                        break;
                }
            }
        });

        setFocusable(true);
        requestFocus();
        setVisible(true);
    }


    static class ShapePanel extends JPanel{

        public enum Shapes{
            OWAL, KWADRAT;
        }

        private Shapes selectedShape;

        Point mousePosition = new Point();

        public void setMousePosition(Point mousePosition){
            this.mousePosition = mousePosition;
        }

        public void setSelectedShape(Shapes selectedShape){
            this.selectedShape = selectedShape;
        }

        @Override
        public void paintComponent(Graphics g){

            if (selectedShape != null) {
                switch (selectedShape) {
                    case KWADRAT -> g.fillRect(mousePosition.x - 60, mousePosition.y - 80, 100, 100);
                    case OWAL -> g.fillOval(mousePosition.x - 60, mousePosition.y - 80, 100, 100);
                }
            }
        }

    }
}

public class Main{
    public static void main(String[] args) {

        DrawingCanvas canvas = new DrawingCanvas();
    }
}
