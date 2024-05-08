import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class OverlayButton extends JButton {
    private BufferedImage backgroundImage;
    private BufferedImage overlayImage;

    public OverlayButton(String backgroundImagePath, String overlayImagePath, int width, int height) {
        try {
            this.backgroundImage = ImageIO.read(new File(backgroundImagePath));
            this.overlayImage = ImageIO.read(new File(overlayImagePath));
            setPreferredSize(new Dimension(width, height));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción al hacer clic en el botón (puedes añadir la lógica aquí)
                System.out.println("Button clicked!");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen de fondo
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // Dibujar la imagen de superposición (más)
        if (overlayImage != null) {
            int x = (getWidth() - overlayImage.getWidth()) / 2;
            int y = (getHeight() - overlayImage.getHeight()) / 2;
            g.drawImage(overlayImage, x, y, this);
        }
    }
}

public class HomePageFrame extends JFrame {
    public HomePageFrame() {
        super("Home Page");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 500);

        // Configurar el color de fondo del panel principal
        Color backgroundColor = Color.decode("#E8FAFF");
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBackground(backgroundColor);

        // Rutas de las imágenes para los botones
        String rectangleImagePath = "interfaz_SistemasInteractivos/iconos/rectangle.png";
        String plusImagePath = "interfaz_SistemasInteractivos/iconos/mas.png";

        // Crear botones con imágenes superpuestas
        OverlayButton btn1 = new OverlayButton(rectangleImagePath, plusImagePath, 100, 100);
        OverlayButton btn2 = new OverlayButton(rectangleImagePath, plusImagePath, 100, 100);
        OverlayButton btn3 = new OverlayButton(rectangleImagePath, plusImagePath, 100, 100);
        OverlayButton btn4 = new OverlayButton(rectangleImagePath, plusImagePath, 100, 100);
        OverlayButton btn5 = new OverlayButton(rectangleImagePath, plusImagePath, 100, 100);
        OverlayButton btn6 = new OverlayButton(rectangleImagePath, plusImagePath, 100, 100);

        // Agregar botones al panel principal
        mainPanel.add(btn1);
        mainPanel.add(btn2);
        mainPanel.add(btn3);
        mainPanel.add(btn4);
        mainPanel.add(btn5);
        mainPanel.add(btn6);

        // Agregar el panel principal al frame
        add(mainPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomePageFrame::new);
    }
}
