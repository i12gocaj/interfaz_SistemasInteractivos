import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {
    public SettingsFrame(){
        super("Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 500);
        setLayout(new BorderLayout());

        /*
         * Top Panel
         */
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.decode("#E8FAFF"));
        
        // Cargar y añadir el icono en la esquina superior izquierda
        ImageIcon icon = resizeImage("iconos/logo.png", 60, 60);
        JButton iconButton = new JButton(icon);
        configureButton(iconButton);
        iconButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomeFrame();
            }
        });
        topPanel.add(iconButton, BorderLayout.WEST);

        /*
         * Middle Panel
         */
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBackground(Color.decode("#E8FAFF"));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel notificationLabel = new JLabel("Notifications");
        notificationLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 5, 0);
        middlePanel.add(notificationLabel, gbc);
        
        JLabel brighnessLabel = new JLabel("Brightness");
        brighnessLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 5, 0);
        middlePanel.add(brighnessLabel, gbc);

        JLabel languageLabel = new JLabel("Language");
        languageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 2;
        middlePanel.add(languageLabel, gbc);

        ImageIcon notifications = resizeImage("iconos/notifications.png", 30, 30);
        JLabel notificationsLabel = new JLabel(notifications);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 5, 0);
        middlePanel.add(notificationsLabel, gbc);

        ImageIcon brightness = resizeImage("iconos/brightness.png", 30, 30);
        JLabel brightnessIcon = new JLabel(brightness);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 5, 0);
        middlePanel.add(brightnessIcon, gbc);

        ImageIcon language = resizeImage("iconos/language.png", 30, 30);
        JLabel languageIcon = new JLabel(language);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 0, 5, 0);
        middlePanel.add(languageIcon, gbc);

        /*
         * Bottom Panel
         */
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(Color.decode("#E8FAFF"));

        //JLabel para el mensaje de error
        JLabel errorMessageLabel = new JLabel("Error password...");
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 0, 20, 0);
        bottomPanel.add(errorMessageLabel, gbc);
        errorMessageLabel.setVisible(false);

        Border boldBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        JPasswordField oldPasswordField = new JPasswordField("password");
        oldPasswordField.setForeground(Color.GRAY);
        oldPasswordField.setHorizontalAlignment(JTextField.CENTER);
        oldPasswordField.setFont(new Font("Arial", Font.PLAIN, 12));
        oldPasswordField.setPreferredSize(new Dimension(180, 30));
        oldPasswordField.setBorder(boldBorder);
        oldPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @SuppressWarnings("deprecation")
            public void focusGained(java.awt.event.FocusEvent evt) {
            if (oldPasswordField.getText().equals("password")) {
                oldPasswordField.setText("");
                oldPasswordField.setForeground(Color.BLACK);
            }
            }
            @SuppressWarnings("deprecation")
            public void focusLost(java.awt.event.FocusEvent evt) {
            if (oldPasswordField.getText().isEmpty()) {
                oldPasswordField.setForeground(Color.GRAY);
                oldPasswordField.setText("password");
            }
            }
        });

        JPasswordField passwordField = new JPasswordField("password");
        passwordField.setForeground(Color.GRAY);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordField.setPreferredSize(new Dimension(180, 30));
        passwordField.setBorder(boldBorder);
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @SuppressWarnings("deprecation")
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (passwordField.getText().equals("password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }
            @SuppressWarnings("deprecation")
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (passwordField.getText().isEmpty()) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText("password");
                }
            }
        });

        JPasswordField confirmPasswordField = new JPasswordField("confirm password");
        confirmPasswordField.setForeground(Color.GRAY);
        confirmPasswordField.setHorizontalAlignment(JTextField.CENTER);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 12));
        confirmPasswordField.setPreferredSize(new Dimension(180, 30));
        confirmPasswordField.setBorder(boldBorder);
        confirmPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @SuppressWarnings("deprecation")
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (confirmPasswordField.getText().equals("confirm password")) {
                    confirmPasswordField.setText("");
                    confirmPasswordField.setForeground(Color.BLACK);
                }
            }
            @SuppressWarnings("deprecation")
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (confirmPasswordField.getText().isEmpty()) {
                    confirmPasswordField.setForeground(Color.GRAY);
                    confirmPasswordField.setText("confirm password");
                }
            }
        });

        RoundedButton btnRegisterBottom = new RoundedButton(resizeImage("iconos/changePassword.png", 120, 30));
        gbc.gridy = 4;
        bottomPanel.add(btnRegisterBottom, gbc);

        // Añadimos action listener para cambiar a las otras páginas o mostrar un mensaje de error
        btnRegisterBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = new String(oldPasswordField.getPassword());
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Verificar si algún campo está vacío o las contraseñas no coinciden
                if (oldPassword.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    errorMessageLabel.setVisible(true);
                    hideErrorMessageAfterDelay(errorMessageLabel, 3000);
                } else if (!password.equals(confirmPassword)) {
                    errorMessageLabel.setVisible(true);
                    hideErrorMessageAfterDelay(errorMessageLabel, 3000);
                } else {
                    // Passwords match and all fields are filled
                    errorMessageLabel.setVisible(false);
                    dispose(); // Cerrar el marco de inicio de sesión
                    new HomeFrame(); // Abrir la nueva pantalla
                }
            }
        });

        JLabel changePasswordLabel = new JLabel("Change Password");

        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 5, 0);
        bottomPanel.add(changePasswordLabel, gbc);


        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 5, 0);
        bottomPanel.add(oldPasswordField, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 5, 0);
        bottomPanel.add(passwordField, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 5, 0);
        bottomPanel.add(confirmPasswordField, gbc);


        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
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

    private void hideErrorMessageAfterDelay(JLabel label, int delay) {
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
            }
        });
        timer.setRepeats(false);  // Ensure the timer only runs once
        timer.start();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SettingsFrame();
            }
        });
    }
}
