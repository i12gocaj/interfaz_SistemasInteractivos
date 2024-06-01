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
import java.util.List;
import java.util.ArrayList;

public class RoomFrame extends JFrame {

    public RoomFrame(ResourceBundle bundle_text, ArrayList<String> roomNames, List<String[]> objectConfigurations, String currentObject, Integer roomNumber) {
        super(roomNames.get(roomNumber));  // Usar el nombre de la habitación como título
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 500);
        Color backgroundColor = Color.decode("#E8FAFF");
        getContentPane().setBackground(backgroundColor);

        // Actualizar el título de la ventana
        if (roomNames.get(roomNumber) != null && !roomNames.get(roomNumber).isEmpty()) {
            this.setTitle(bundle_text.getString("Titulo_Room") + ": " + roomNames.get(roomNumber));
        } else {
            this.setTitle(bundle_text.getString("Titulo_Room"));
        }

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
        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setBackground(backgroundColor);

        // Labels
        JLabel roomLabel = new JLabel(bundle_text.getString("Configuration"));
        roomLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel objectsLabel = new JLabel(bundle_text.getString("Objects"));
        objectsLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel addGadgetLabel = new JLabel(bundle_text.getString("Add_Gadget"));
        addGadgetLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Room panel
        JPanel roomPanel = new JPanel(new GridBagLayout());

        List<String[]> actualObjectConfigurations = new ArrayList<>();
        if (objectConfigurations != null) {
            for (int i = 0; i < objectConfigurations.size(); i++) {
                String[] objectConfiguration = objectConfigurations.get(i);
                if (objectConfiguration[1].equals(currentObject)) {
                    actualObjectConfigurations.add(objectConfiguration);
                }
            }
        }

        for (int i = 0; i < actualObjectConfigurations.size(); i++) {
            String[] objectConfiguration = actualObjectConfigurations.get(i);
            String type = type(objectConfiguration);
            if(Integer.parseInt(objectConfiguration[0]) != roomNumber) {
                continue;
            }
            JLabel label = new JLabel(objectConfiguration[2]);

            if (type.equals("Switch")) {
                ImageIcon switchIconOn = resizeImage("interfaz/iconos/switch_on.png", 40, 30);
                ImageIcon switchIconOff = resizeImage("interfaz/iconos/switch_off.png", 40, 30);

                JButton Switch = new JButton(switchIconOn);
                configureButton(Switch);
                Switch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Switch.getIcon().equals(switchIconOn)) {
                            Switch.setIcon(switchIconOff);
                        } else {
                            Switch.setIcon(switchIconOn);
                        }
                    }
                });
                gbc.gridx = 0;
                gbc.gridy = i;
                gbc.insets = new Insets(0, 0, 10, 0);
                roomPanel.add(label, gbc);

                gbc.gridx = 1;
                gbc.gridy = i;
                gbc.insets = new Insets(0, 0, 10, 0);
                roomPanel.add(Switch, gbc);

            } else if (type.equals("Slider")) {
                JSlider brightnessSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
                brightnessSlider.setPreferredSize(new Dimension(100, brightnessSlider.getPreferredSize().height));
                brightnessSlider.setUI(new javax.swing.plaf.metal.MetalSliderUI() {
                    public void paintTrack(Graphics g) {
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        Rectangle trackBounds = trackRect;
                        g2d.setColor(Color.decode("#E8FAFF"));
                        g2d.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                    }

                    public void paintThumb(Graphics g) {
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        Rectangle thumbBounds = thumbRect;
                        g2d.setColor(Color.decode("#007BFF"));
                        g2d.fillOval(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
                    }
                });
                brightnessSlider.setMajorTickSpacing(10);
                brightnessSlider.setMinorTickSpacing(1);

                gbc.gridx = 0;
                gbc.gridy = i;
                gbc.insets = new Insets(0, 0, 10, 0);
                roomPanel.add(label, gbc);

                gbc.gridx = 1;
                gbc.gridy = i;
                gbc.insets = new Insets(0, 0, 10, 0);
                roomPanel.add(brightnessSlider, gbc);
            } else {
                JComboBox<String> comboBox = new JComboBox<>();
                for (int j = 3; j < objectConfiguration.length; j++) {
                    comboBox.addItem(objectConfiguration[j]);
                }
                gbc.gridx = 0;
                gbc.gridy = i;
                gbc.insets = new Insets(0, 0, 10, 0);
                roomPanel.add(label, gbc);

                gbc.gridx = 1;
                gbc.gridy = i;
                gbc.insets = new Insets(0, 0, 10, 0);
                roomPanel.add(comboBox, gbc);
            }
        }

        // Gadget selector
        String[] allGadgets = {};
        if (objectConfigurations != null) {
            for (int i = 0; i < objectConfigurations.size(); i++) {
                String[] objectConfiguration = objectConfigurations.get(i);
                if (!java.util.Arrays.asList(allGadgets).contains(objectConfiguration[1]) && Integer.parseInt(objectConfiguration[0]) == roomNumber) {
                    allGadgets = java.util.Arrays.copyOf(allGadgets, allGadgets.length + 1);
                    allGadgets[allGadgets.length - 1] = objectConfiguration[1];
                }
            }
        }

        JComboBox<String> gadgetSelector = new JComboBox<>();
        for (int i = 0; i < allGadgets.length; i++) {
            gadgetSelector.addItem(allGadgets[i]);
        }
        gadgetSelector.setSelectedItem(currentObject);

        gadgetSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RoomFrame(bundle_text, roomNames, objectConfigurations, (String) gadgetSelector.getSelectedItem(), roomNumber);
            }
        });

        ImageIcon addIcon = resizeImage("interfaz/iconos/big_plus.png", 30, 30);
        JButton addButton = new JButton(addIcon);
        configureButton(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ConfigureGadget(bundle_text, roomNames, objectConfigurations, roomNumber);
            }
        });

        // Add components to the main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(roomLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(roomPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(objectsLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(gadgetSelector, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(addGadgetLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 10, 0);
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

    private String type(String[] objectConfigurations) {
        switch (objectConfigurations[3]) {
            case "Switch":
                return "Switch";
            case "Slider":
                return "Slider";
            default:
                return "Box";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Locale currentLocale = new Locale.Builder().setLanguage("en").setRegion("GB").build();
                ResourceBundle bundle_text = ResourceBundle.getBundle("Bundle", currentLocale);
                ArrayList<String> roomNames = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    roomNames.add("+");
                }

                List<String[]> objectConfigurations = new ArrayList<>();
                objectConfigurations.add(new String[]{"0", "Air conditioner", "Start", "Switch"});
                objectConfigurations.add(new String[]{"0", "Air conditioner", "Fan", "Low", "Medium", "High"});
                objectConfigurations.add(new String[]{"0", "Lights", "Living room", "Switch"});
                objectConfigurations.add(new String[]{"0", "Lights", "Kitchen", "Switch"});
                objectConfigurations.add(new String[]{"1", "Lights", "Type", "Static", "Dynamic"});
                objectConfigurations.add(new String[]{"1", "Lights", "Color", "Red", "Green", "Blue"});
                objectConfigurations.add(new String[]{"1", "Lights", "Brightness", "Slider"});

                String currentObject = "Lights";

                Integer roomNumber = 0;

                new RoomFrame(bundle_text, roomNames, objectConfigurations, currentObject, roomNumber);
            }
        });
    }
}
