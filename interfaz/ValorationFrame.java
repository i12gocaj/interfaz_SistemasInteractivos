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


public class ValorationFrame extends JFrame {
    
    public ValorationFrame(ResourceBundle bundle_text, ArrayList<String> roomNames, List<String[]> objectConfigurations) {
        // Set frame properties (size, layout, etc.)
        setSize(320, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle(bundle_text.getString("Titulo_Valoration"));
        
        // Add your components to the frame
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.decode("#E8FAFF"));

        // Cargar y añadir el icono en la esquina superior izquierda
        ImageIcon icon = resizeImage("interfaz/iconos/logo.png", 60, 60);
        JButton iconButton = new JButton(icon);
        configureButton(iconButton);
        iconButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomeFrame(bundle_text, roomNames, objectConfigurations);
            }
        });
        topPanel.add(iconButton, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);

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

        JPanel configureButtonPanel = new JPanel(new BorderLayout());
        configureButtonPanel.setOpaque(false); // Hacer que el panel sea transparente
        configureButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Añadir margen a la derecha
        configureButtonPanel.add(configureButton, BorderLayout.EAST);

        topPanel.add(configureButtonPanel, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#E8FAFF"));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel rate = new JLabel(bundle_text.getString("Rate"));
        rate.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(rate, gbc);

        JPanel ratePanel = new JPanel(new GridLayout(1, 5));
        ratePanel.setBackground(Color.decode("#E8FAFF"));

        ButtonGroup rateButtonGroup = new ButtonGroup();
        for (int i = 1; i <= 5; i++) {
            JRadioButton rateButton = new JRadioButton(String.valueOf(i));
            rateButton.setFont(new Font("Arial", Font.BOLD, 20));
            rateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle rate button click event
            }
            });
            rateButtonGroup.add(rateButton);
            ratePanel.add(rateButton);
        }

        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(ratePanel, gbc);

        JLabel comment = new JLabel(bundle_text.getString("Comment"));
        comment.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(comment, gbc);

        JTextArea commentArea = new JTextArea();
        commentArea.setFont(new Font("Arial", Font.PLAIN, 15));
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        commentArea.setPreferredSize(new Dimension(250, 100));

        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(commentArea, gbc);

        JButton sendButton = new JButton(bundle_text.getString("Send"));
        sendButton.setFont(new Font("Arial", Font.BOLD, 20));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomeFrame(bundle_text, roomNames, objectConfigurations);
            }
        });

        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(sendButton, gbc);
        
        // Add components to the main panel
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // Pack and display the frame
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
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
                new ValorationFrame(bundle_text, roomNames, objectConfigurations);
            }
        });
    }
}
