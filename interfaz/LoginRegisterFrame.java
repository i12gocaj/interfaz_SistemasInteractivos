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

public class LoginRegisterFrame extends JFrame {

    // Constructor de la clase
    public LoginRegisterFrame() {
        // Llamamos al constructor de la superclase JFrame y establecemos el título de la ventana
        super("");

        // Configuramos el comportamiento de cierre de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establecemos el tamaño de la ventana a 320x480 píxeles
        setSize(320, 500);

        Locale currentLocale = new Locale.Builder().setLanguage("en").setRegion("GB").build();
        ResourceBundle bundle_text = ResourceBundle.getBundle("Bundle", currentLocale);

        this.setTitle(bundle_text.getString("Titulo_Login_Register"));

        // Configuramos el color de fondo de la ventana usando el valor hexadecimal
        Color backgroundColor = Color.decode("#E8FAFF");
        getContentPane().setBackground(backgroundColor);

        // Creamos e inicializamos un panel con un GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(backgroundColor); // Asignamos el color de fondo al panel también
        GridBagConstraints gbc = new GridBagConstraints();

        // Cargamos la imagen del icono y la redimensionamos
        ImageIcon icon = resizeImage("interfaz/iconos/logo.png", 60, 60);
        JLabel iconLabel = new JLabel(icon);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0); // Ajusta los márgenes para dejar más espacio
        panel.add(iconLabel, gbc);

        // Creamos e inicializamos los botones con imágenes redimensionadas
        RoundedButton btnLogin = new RoundedButton(resizeImage("interfaz/iconos/login.png", 120, 60));
        RoundedButton btnRegister = new RoundedButton(resizeImage("interfaz/iconos/register.png", 120, 60));

        // Añadimos action listener para cambiar a las otras paginas

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame(bundle_text);
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegisterFrame(bundle_text);
            }
        });


        // Posicionamos el botón de inicio de sesión debajo del icono
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Restablecemos esto para los botones
        gbc.insets = new Insets(10, 0, 10, 0); // Ajusta los márgenes para dejar más espacio
        panel.add(btnLogin, gbc);

        // Posicionamos el botón de registro debajo del botón de inicio de sesión
        gbc.gridy = 2;
        panel.add(btnRegister, gbc);

        // Añadimos el panel al frame
        add(panel);

        // Evitamos que el usuario pueda cambiar el tamaño de la ventana
        setResizable(false);

        // Centramos la ventana en la pantalla
        setLocationRelativeTo(null);

        // Hacemos visible la ventana
        setVisible(true);
    }

    // Método para redimensionar la imagen
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

    // Método main para ejecutar la aplicación


public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new LoginRegisterFrame();
        }
    });
}
}