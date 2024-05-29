import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigureRoom extends JFrame {

    public ConfigureRoom(ResourceBundle bundle_text) {
        super("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 500);
        Color backgroundColor = Color.decode("#E8FAFF");
        getContentPane().setBackground(backgroundColor);
        setLayout(new BorderLayout());

        this.setTitle(bundle_text.getString("Titulo_ConfigureRoom"));

        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(backgroundColor);

        ImageIcon homeIcon = resizeImage("interfaz/iconos/logo.png", 60, 60);
        JButton homeButton = new JButton(homeIcon);
        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setBorder(null);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                String[] places = {"+", "+", "+", "+"};
                new HomeFrame(bundle_text, places);
            }
        });
        topPanel.add(homeButton, BorderLayout.WEST);

        ImageIcon configureIcon = resizeImage("interfaz/iconos/configureIcon.png", 30, 30);
        JButton configureButton = new JButton(configureIcon);
        configureButton.setOpaque(false);
        configureButton.setContentAreaFilled(false);
        configureButton.setBorderPainted(false);
        configureButton.setFocusPainted(false);
        configureButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Añadir margen a la derecha
        configureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción al pulsar el botón de configuración
            }
        });
        topPanel.add(configureButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); // Añadir margen superior
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Configure room");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el título y el campo de texto

        JTextField nameField = new JTextField("Introduce name");
        nameField.setForeground(Color.GRAY);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setFont(new Font("Arial", Font.PLAIN, 12));
        nameField.setMaximumSize(new Dimension(180, 30));
        nameField.setPreferredSize(new Dimension(180, 30));
        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (nameField.getText().equals("Introduce name")) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (nameField.getText().isEmpty()) {
                    nameField.setForeground(Color.GRAY);
                    nameField.setText("Introduce name");
                }
            }
        });
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(nameField);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el campo de texto y el botón

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.setMaximumSize(new Dimension(80, 30));
        addButton.setPreferredSize(new Dimension(80, 30));
        addButton.setEnabled(true); // Puedes cambiar a false si quieres que el botón esté deshabilitado por defecto
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción al pulsar el botón de añadir
            }
        });
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(addButton);

        add(mainPanel, BorderLayout.CENTER);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
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
                new ConfigureRoom(bundle_text);
            }
        });
    }
}
