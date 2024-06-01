import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigureGadget extends JFrame {

    public ConfigureGadget(ResourceBundle bundle_text, ArrayList<String> roomNames, List<String[]> objectConfigurations, Integer numberRoom) {
        super("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 500);
        Color backgroundColor = Color.decode("#E8FAFF");
        getContentPane().setBackground(backgroundColor);

        this.setTitle(bundle_text.getString("Titulo_ConfigureGadget"));

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

        JLabel title = new JLabel(bundle_text.getString("Configure_Gadget"));
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(title, gbc);

        JTextField nameField = new JTextField(bundle_text.getString("Name_Room"));
        nameField.setForeground(Color.GRAY);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setFont(new Font("Arial", Font.PLAIN, 12));
        nameField.setMaximumSize(new Dimension(180, 30));
        nameField.setPreferredSize(new Dimension(180, 30));
        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (nameField.getText().equals(bundle_text.getString("Name_Room"))) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (nameField.getText().isEmpty()) {
                    nameField.setForeground(Color.GRAY);
                    nameField.setText(bundle_text.getString("Name_Room"));
                }
            }
        });

        // Limitar el número de caracteres a 10
        ((AbstractDocument) nameField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= 10) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) <= 10) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(nameField, gbc);

        ImageIcon bluetooth = resizeImage("interfaz/iconos/bluetooth.png", 30, 30);
        JLabel bluetoothLabel = new JLabel(bluetooth);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(bluetoothLabel, gbc);

        ImageIcon wifi = resizeImage("interfaz/iconos/wifi.png", 30, 30);
        JLabel wifiLabel = new JLabel(wifi);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(wifiLabel, gbc);

        ImageIcon pairBluetooth = resizeImage("interfaz/iconos/pair_bluetooth.png", 80, 20);
        JButton pairBluetoothButton = new JButton(pairBluetooth);
        pairBluetoothButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Pairing complete, press Add");
            }
        });
        pairBluetoothButton.setOpaque(false);
        pairBluetoothButton.setContentAreaFilled(false);
        pairBluetoothButton.setBorderPainted(false);
        pairBluetoothButton.setFocusPainted(false);
        pairBluetoothButton.setBorder(null);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(pairBluetoothButton, gbc);

        ImageIcon pairWifi = resizeImage("interfaz/iconos/pair_wifi.png", 80, 20);
        JButton pairWifiButton = new JButton(pairWifi);
        pairWifiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Pairing complete, press Add");
            }
        });
        pairWifiButton.setOpaque(false);
        pairWifiButton.setContentAreaFilled(false);
        pairWifiButton.setBorderPainted(false);
        pairWifiButton.setFocusPainted(false);
        pairWifiButton.setBorder(null);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(pairWifiButton, gbc);


        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.setMaximumSize(new Dimension(80, 30));
        addButton.setPreferredSize(new Dimension(80, 30));
        addButton.setEnabled(true); // Puedes cambiar a false si quieres que el botón esté deshabilitado por defecto
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String place = randomPlace();
                String type = randomType();
                if(type.isBlank()){
                    type = randomCharacteristic();
                    switch (type) {
                        case "Red":
                            if (!name.equals(bundle_text.getString("Name_Gadget")) && !name.isEmpty()) {
                                objectConfigurations.add(new String[]{numberRoom.toString(), name, place, type, "Green", "Blue"});
                            }
                            break;

                        case "Low":
                            if (!name.equals(bundle_text.getString("Name_Gadget")) && !name.isEmpty()) {
                                objectConfigurations.add(new String[]{numberRoom.toString(), name, place, type, "Medium", "High"});
                            }
                            break;  

                        case "Static":
                            if (!name.equals(bundle_text.getString("Name_Gadget")) && !name.isEmpty()) {
                                objectConfigurations.add(new String[]{numberRoom.toString(), name, place, type, "Dynamic", "Random"});
                            }
                            break;
                        default:
                            break;
                    }
                }else{
                    if (!name.equals(bundle_text.getString("Name_Gadget")) && !name.isEmpty()) {
                        objectConfigurations.add(new String[]{numberRoom.toString(), name, place, type});
                    }
                }
                dispose();
                new HomeFrame(bundle_text, roomNames, objectConfigurations);
            }
        });
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(addButton, gbc);

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

    private String randomType(){
        String randomType = "";

        int random = (int) (Math.random() * 3) + 1;
        switch (random) {
            case 1:
                randomType = "Switch";
                break;
            case 2:
                randomType = "Slider";
                break;
            case 3:
                randomType = "";
                break;
        }

        return randomType;
    }

    private String randomPlace(){
        String randomPlace = "";

        int random = (int) (Math.random() * 3) + 1;
        switch (random) {
            case 1:
                randomPlace = "Next to the door";
                break;
            case 2:
                randomPlace = "Next to the window";
                break;
            case 3:
                randomPlace = "Middle";
                break;
        }

        return randomPlace;
    }

    private String randomCharacteristic(){
        String randomCharacteristic = "";

        int random = (int) (Math.random() * 3) + 1;
        switch (random) {
            case 1:
                randomCharacteristic = "Red";
                break;
            case 2:
                randomCharacteristic = "Low";
                break;
            case 3:
                randomCharacteristic = "Static";
                break;
        }

        return randomCharacteristic;
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

                Integer numberRoom = 0;

                new ConfigureGadget(bundle_text, roomNames, objectConfigurations, numberRoom);
            }
        });
    }
}
