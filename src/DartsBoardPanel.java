import javax.swing.*;
import java.awt.*;

// Класс для отображения мишени дартс на панели
public class DartsBoardPanel extends JPanel {
    private final int CENTER_X = 690, CENTER_Y = 400;

    private final int BULLSEYE_RADIUS = 20;

    public DartsBoardPanel() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(1380, 800));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Отрисовка мишени
        for (int i = 10; i > 0; i--) {
            if (i % 2 == 0) {
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(Color.WHITE);
            }
            int radius = (i - 1) * 20;
            g2.drawOval(CENTER_X - radius, CENTER_Y - radius, radius * 2, radius * 2);
            g2.fillOval(CENTER_X - radius, CENTER_Y - radius, radius * 2, radius * 2);
        }

        // Отрисовка буллсай
        g2.setColor(Color.RED);
        g2.fillOval(CENTER_X - BULLSEYE_RADIUS, CENTER_Y - BULLSEYE_RADIUS, BULLSEYE_RADIUS * 2, BULLSEYE_RADIUS * 2);
    }
}