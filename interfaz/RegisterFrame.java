import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase para botones redondeados
class RoundedButton extends JButton {
    public RoundedButton(Icon icon) {
        super(icon);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        }
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No pintar borde
    }
}

public class RegisterFrame extends JFrame {

    public RegisterFrame() {
        super("Register Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 500);
        Color backgroundColor = Color.decode("#E8FAFF");
        getContentPane().setBackground(backgroundColor);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();

        ImageIcon icon = resizeImage("/Users/nodo/Desktop/Sistemas Interactivos/proyecto/interfaz/iconos/logo.png", 60, 60);
        JLabel iconLabel = new JLabel(icon);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(iconLabel, gbc);

        // Crear un JLabel para el mensaje de error
        JLabel errorMessageLabel = new JLabel("Error password...");
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 0, 20, 0);
        panel.add(errorMessageLabel, gbc);
        errorMessageLabel.setVisible(false);

        Border boldBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        JTextField emailField = new JTextField("email");
        emailField.setForeground(Color.GRAY);
        emailField.setHorizontalAlignment(JTextField.CENTER);
        emailField.setFont(new Font("Arial", Font.PLAIN, 12));
        emailField.setPreferredSize(new Dimension(180, 30));
        emailField.setBorder(boldBorder);
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (emailField.getText().equals("email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (emailField.getText().isEmpty()) {
                    emailField.setForeground(Color.GRAY);
                    emailField.setText("email");
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

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 5, 0);
        panel.add(emailField, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(20, 0, 5, 0);
        panel.add(passwordField, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 5, 0);
        panel.add(confirmPasswordField, gbc);

        // Create a rounded image label for register
        RoundedButton btnRegisterBottom = new RoundedButton(resizeImage("/Users/nodo/Desktop/Sistemas Interactivos/proyecto/interfaz/iconos/registerBottom.png", 120, 60));
        gbc.gridy = 4;
        panel.add(btnRegisterBottom, gbc);

        // Añadimos action listener para cambiar a las otras páginas o mostrar un mensaje de error
        btnRegisterBottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Verificar si algún campo está vacío o las contraseñas no coinciden
                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                   
                    errorMessageLabel.setVisible(true);
                } else if (!password.equals(confirmPassword)) {
                    
                    errorMessageLabel.setVisible(true);
                } else {
                    // Passwords match and all fields are filled
                    errorMessageLabel.setVisible(false);
                    dispose(); // Cerrar el marco de inicio de sesión
                    new HomeFrame(); // Abrir la nueva pantalla
                }
            }
        });

        add(panel);
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
                new RegisterFrame();
            }
        });
    }
}
