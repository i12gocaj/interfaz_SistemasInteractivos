import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    public RegisterFrame(ResourceBundle bundle_text, ArrayList<String> roomNames, List<String[]> objectConfigurations) {
        super("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 500);
        Color backgroundColor = Color.decode("#E8FAFF");
        getContentPane().setBackground(backgroundColor);

        this.setTitle(bundle_text.getString("Titulo_Register"));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();

        ImageIcon icon = resizeImage("interfaz/iconos/logo.png", 60, 60);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor para indicar que es clicable
        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginRegisterFrame(roomNames);
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(iconLabel, gbc);

        // Crear un JLabel para el mensaje de error
        JLabel errorMessageLabel = new JLabel(bundle_text.getString("Wrong_Username_Password"));
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 0, 20, 0);
        panel.add(errorMessageLabel, gbc);
        errorMessageLabel.setVisible(false);

        Border boldBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        JTextField emailField = new JTextField(bundle_text.getString("Email"));
        emailField.setForeground(Color.GRAY);
        emailField.setHorizontalAlignment(JTextField.CENTER);
        emailField.setFont(new Font("Arial", Font.PLAIN, 12));
        emailField.setPreferredSize(new Dimension(180, 30));
        emailField.setBorder(boldBorder);
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (emailField.getText().equals(bundle_text.getString("Email"))) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (emailField.getText().isEmpty()) {
                    emailField.setForeground(Color.GRAY);
                    emailField.setText(bundle_text.getString("Email"));
                }
            }
        });

        JPasswordField passwordField = new JPasswordField(bundle_text.getString("Password"));
        configurePasswordField(passwordField, bundle_text.getString("Password"));
        passwordField.setBorder(boldBorder);

        JPasswordField confirmPasswordField = new JPasswordField(bundle_text.getString("Confirm_Password"));
        configurePasswordField(confirmPasswordField, bundle_text.getString("Confirm_Password"));
        confirmPasswordField.setBorder(boldBorder);

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
        RoundedButton btnRegisterBottom = new RoundedButton(resizeImage("interfaz/iconos/registerBottom.png", 120, 60));
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
                    errorMessageLabel.setText(bundle_text.getString("Fields_Cannot_Be_Empty"));
                    errorMessageLabel.setVisible(true); // Mostrar el mensaje de error
                    hideErrorMessageAfterDelay(errorMessageLabel, 3000);
                } else if (!password.equals(confirmPassword)) {
                    errorMessageLabel.setText(bundle_text.getString("Wrong_Username_Password"));
                    errorMessageLabel.setVisible(true); // Mostrar el mensaje de error
                    hideErrorMessageAfterDelay(errorMessageLabel, 3000);
                } else {
                    // Passwords match and all fields are filled
                    errorMessageLabel.setVisible(false);
                    dispose(); // Cerrar el marco de registro
                    new HomeFrame(bundle_text, roomNames, objectConfigurations); // Abrir la nueva pantalla
                }
            }
        });

        add(panel);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void configurePasswordField(JPasswordField passwordField, String placeholder) {
        passwordField.setForeground(Color.GRAY);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordField.setPreferredSize(new Dimension(180, 30));
        passwordField.setEchoChar((char) 0);  // Mostrar texto por defecto

        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('\u2022');  // Cambiar a modo de contraseña
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText(placeholder);
                    passwordField.setEchoChar((char) 0);  // Mostrar texto por defecto
                }
            }
        });
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
        timer.setRepeats(false); // Ensure the timer only runs once
        timer.start();
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
                new RegisterFrame(bundle_text, roomNames, objectConfigurations);
            }
        });
    }
}
