import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeFrame extends JFrame {

    public HomeFrame(ResourceBundle bundle_text, ArrayList<String> roomNames, List<String[]> objectConfigurations) {
        super("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 500);
        setLayout(new BorderLayout());  // Usar BorderLayout para una distribución más flexible

        this.setTitle(bundle_text.getString("Titulo_Home"));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.decode("#E8FAFF"));

        // Cargar y añadir el icono en la esquina superior izquierda
        ImageIcon icon = resizeImage("interfaz/iconos/logo.png", 60, 60);
        JLabel iconLabel = new JLabel(icon);
        topPanel.add(iconLabel, BorderLayout.WEST);

        ImageIcon configure = resizeImage("interfaz/iconos/configureIcon.png", 30, 30);
        JButton configureButton = new JButton(configure);
        configureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SettingsFrame(bundle_text, roomNames, objectConfigurations);
            }
        });
        configureButton.setOpaque(false);
        configureButton.setContentAreaFilled(false);
        configureButton.setBorderPainted(false);
        configureButton.setFocusPainted(false);
        configureButton.setBorder(null);

        // Crear un JPanel para añadir el margen
        JPanel configureButtonPanel = new JPanel(new BorderLayout());
        configureButtonPanel.setOpaque(false); // Hacer que el panel sea transparente
        configureButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Añadir margen a la derecha
        configureButtonPanel.add(configureButton, BorderLayout.EAST);

        topPanel.add(configureButtonPanel, BorderLayout.EAST);

        // Preparar el panel principal para futuros elementos
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#E8FAFF"));
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuraciones generales para todos los botones
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);  // Espacio entre botones
        gbc.ipadx = 40;  // Ancho interno para hacer que los botones sean más anchos
        gbc.ipady = 40;  // Altura interna para hacer que los botones sean más altos

        // Crear botones con la imagen del rectángulo y el símbolo "+"
        ImageIcon rectangleIcon = resizeImage("interfaz/iconos/rectangle.png", 100, 100);

        for (int i = 0; i < 4; i++) {
            JButton button = new JButton(roomNames.get(i), rectangleIcon);
            adjustFontSizeToFit(button);
            configureButton(button);
            final int index = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton sourceButton = (JButton) e.getSource();
                    if (sourceButton.getText().equals("+")) {
                        dispose();
                        new ConfigureRoom(bundle_text, roomNames, objectConfigurations);
                    } else {
                        dispose();
                        // Aquí se pasa el nombre de la habitación al constructor de RoomFrame
                        if(objectConfigurations.isEmpty()){
                            new ConfigureGadget(bundle_text, roomNames, objectConfigurations, index);
                        }else{
                            String currentObject = objectConfigurations.get(0)[1];
                            new RoomFrame(bundle_text, roomNames, objectConfigurations, currentObject, index);
                        }
                    }
                }
            });
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            mainPanel.add(button, gbc);
        }

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.decode("#E8FAFF"));
        
        ImageIcon valoration = resizeImage("interfaz/iconos/valoration.png", 30, 30);
        JButton valorationButton = new JButton(valoration);
        valorationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            dispose();
            new ValorationFrame(bundle_text, roomNames, objectConfigurations);
            }
        });

        valorationButton.setOpaque(false);
        valorationButton.setContentAreaFilled(false);
        valorationButton.setBorderPainted(false);
        valorationButton.setFocusPainted(false);
        valorationButton.setBorder(null);

        bottomPanel.add(valorationButton, BorderLayout.WEST);

        // Añade paneles al JFrame
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void configureButton(JButton button) {
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setOpaque(false);  // Hacer el fondo del botón transparente
        button.setContentAreaFilled(false);  // No rellenar el área del contenido
        button.setBorderPainted(false);  // No pintar el borde
        button.setFocusPainted(false);  // No pintar el foco del botón
        button.setBorder(null);  // Eliminar el borde
    }

    private void adjustFontSizeToFit(JButton button) {
        Font font = button.getFont();
        String text = button.getText();
        int stringWidth = button.getFontMetrics(font).stringWidth(text);
        int componentWidth = button.getWidth();

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(font.getSize() * widthRatio);
        int componentHeight = button.getHeight();

        // Pick a new font size so it will not be larger than the height of button.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the button's font size to the newly determined size.
        button.setFont(new Font(font.getName(), Font.BOLD, fontSizeToUse));
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
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Locale currentLocale = new Locale.Builder().setLanguage("en").setRegion("GB").build();
                ResourceBundle bundle_text = ResourceBundle.getBundle("Bundle", currentLocale);
                ArrayList<String> roomNames = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    roomNames.add("+");
                }
                List<String[]> objectConfigurations = new ArrayList<>();
                new HomeFrame(bundle_text, roomNames, objectConfigurations);
            }
        });
    }
}
