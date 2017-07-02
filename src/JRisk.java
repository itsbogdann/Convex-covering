
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JRisk {

    private JFrame mainMap;
    private Polygon poly1;
    private Polygon poly2;
    private Polygon poly3;

    public JRisk(int []xp1, int []yp1, int []xp2, int []yp2, int []xI, int []yI) {

        initComponents(xp1, yp1, xp2, yp2, xI, yI);

    }

    private void initComponents(int []xPoly1, int []yPoly1, int []xPoly2, int []yPoly2, int []xIn, int []yIn) {

        mainMap = new JFrame();
        mainMap.setResizable(true);

        mainMap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        poly1 = new Polygon(xPoly1, yPoly1, xPoly1.length);
        poly2 = new Polygon(xPoly2, yPoly2, xPoly2.length);
        poly3 = new Polygon(xIn, yIn, xIn.length);
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.drawPolygon(poly1);
                g.setColor(Color.BLUE);
                g.drawPolygon(poly2);
                g.setColor(Color.BLACK);
                g.drawPolygon(poly3);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800, 700);
            }
        };
        mainMap.add(p);
        mainMap.pack();
        mainMap.setVisible(true);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JRisk(null, null, null, null, null, null);
            }
        });
    }
}