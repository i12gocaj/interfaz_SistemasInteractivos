import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HomeFrame extends JFrame {

    public HomeFrame() {
        super("Home Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 500);
        setLayout(new BorderLayout());  // Usar BorderLayout para una distribución más flexible

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.decode("#E8FAFF"));
        
        // Cargar y añadir el icono en la esquina superior izquierda
        ImageIcon icon = resizeImage("/Users/nodo/Desktop/Sistemas Interactivos/proyecto/interfaz/iconos/logo.png", 60, 60);
        JLabel iconLabel = new JLabel(icon);
        topPanel.add(iconLabel);

        // Preparar el panel principal para futuros elementos
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#E8FAFF"));
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuraciones generales para todos los botones
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);  // Espacio entre botones
        gbc.ipadx = 40;  // Ancho interno para hacer que los botones sean más anchos
        gbc.ipady = 40;  // Altura interna para hacer que los botones sean más altos

        // Crear botones con la imagen del rectángulo y el símbolo "+"
        ImageIcon rectangleIcon = resizeImage("/Users/nodo/Desktop/Sistemas Interactivos/proyecto/interfaz/iconos/rectangle.png", 100, 100);
        
        // Botón 1
        JButton button1 = new JButton("+", rectangleIcon);
        configureButton(button1);
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(button1, gbc);

        // Botón 2
        JButton button2 = new JButton("+", rectangleIcon);
        configureButton(button2);
        gbc.gridx = 1; gbc.gridy = 0;
        mainPanel.add(button2, gbc);

        // Botón 3
        JButton button3 = new JButton("+", rectangleIcon);
        configureButton(button3);
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(button3, gbc);

        // Botón 4
        JButton button4 = new JButton("+", rectangleIcon);
        configureButton(button4);
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(button4, gbc);

        // Añade paneles al JFrame
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void configureButton(JButton button) {
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setOpaque(false);  // Hacer el fondo del botón transparente
        button.setContentAreaFilled(false);  // No rellenar el área del contenido
        button.setBorderPainted(false);  // No pintar el borde
        button.setFocusPainted(false);  // No pintar el foco del botón
        button.setBorder(null);  // Eliminar el borde
    }

    private ImageIcon resizeImage(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomeFrame();
            }
        });
    }
}
