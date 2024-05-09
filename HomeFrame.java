import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;


class RoundedRectangle extends JPanel {
    private Color color;
    private int arcWidth;
    private int arcHeight;

    public RoundedRectangle(Color color, int arcWidth, int arcHeight) {
        this.color = color;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.setPreferredSize(new Dimension(150, 500)); // Adjust the preferred size as needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
    }
}
public class HomeFrame extends JFrame {

    public HomeFrame() {
        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Home Frame");
        setSize(320, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding around components
        gbc.anchor = GridBagConstraints.CENTER;

        int rectangleSize = 90;

        // Create and add rounded rectangles with GridBagConstraints
        addRoundedRectangle(mainPanel, gbc, Color.BLUE, 20, 20, rectangleSize, rectangleSize);
        addRoundedRectangle(mainPanel, gbc, Color.RED, 20, 20, rectangleSize, rectangleSize);
        addRoundedRectangle(mainPanel, gbc, Color.GREEN, 20, 20, rectangleSize, rectangleSize);
        addRoundedRectangle(mainPanel, gbc, Color.ORANGE, 20, 20, rectangleSize, rectangleSize);
        addRoundedRectangle(mainPanel, gbc, Color.MAGENTA, 20, 20, rectangleSize, rectangleSize);
        addRoundedRectangle(mainPanel, gbc, Color.CYAN, 20, 20, rectangleSize, rectangleSize);

        // Add the main panel to the frame
        mainPanel.setSize(500, 700);
        add(mainPanel);

        pack(); // Pack components together
        setResizable(false);
        setLocationRelativeTo(null); // Center the frame on screen
        setVisible(true);
    }

    // Helper method to add a rounded rectangle with specified constraints
    private void addRoundedRectangle(JPanel panel, GridBagConstraints gbc, Color color, int arcWidth, int arcHeight, int width, int height) {
        JPanel rectanglePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(color);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width, height); // Use specified width and height
            }
        };

        // Set GridBagConstraints for the current rectangle
        if (gbc.gridx > 1) {
            gbc.gridx = 0;
            gbc.gridy++;
        }
        gbc.gridx++;
        panel.add(rectanglePanel, gbc);
    }

    public static void main(String[] args) {
        new HomeFrame();
    }
}