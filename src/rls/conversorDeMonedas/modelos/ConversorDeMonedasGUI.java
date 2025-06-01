package rls.conversorDeMonedas.modelos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ConversorDeMonedasGUI extends JFrame {
    private final ConversorDeMonedas conversor;
    private JComboBox<String> comboMonedaBase;
    private JComboBox<String> comboMonedaDestino;
    private JTextField textCantidad;
    private JLabel labelResultado;
    private JButton btnConvertir;
    private JButton btnHistorial;
    private JButton btnIntercambiar;
    private final List<Conversion> historial = new ArrayList<>();

    // Array con todas las monedas disponibles
    private final String[] monedas = {
            "AED - Dirham de los Emiratos Árabes Unidos",
            "AFN - Afgani Afgano",
            "ALL - Lek Albanés",
            "AMD - Dram Armenio",
            "ANG - Florín de las Antillas Neerlandesas",
            "AOA - Kwanza Angoleño",
            "ARS - Peso Argentino",
            "AUD - Dólar Australiano",
            "AWG - Florín de Aruba",
            "AZN - Manat Azerbaiyano",
            "BAM - Marco de Bosnia y Herzegovina",
            "BBD - Dólar de Barbados",
            "BDT - Taka de Bangladés",
            "BGN - Lev Búlgaro",
            "BHD - Dinar de Baréin",
            "BIF - Franco de Burundi",
            "BMD - Dólar de las Bermudas",
            "BND - Dólar de Brunéi",
            "BOB - Boliviano Boliviano",
            "BRL - Real Brasileño",
            "BSD - Dólar de las Bahamas",
            "BTN - Ngultrum de Bután",
            "BWP - Pula de Botsuana",
            "BYN - Rublo Bielorruso",
            "BZD - Dólar de Belice",
            "CAD - Dólar Canadiense",
            "CDF - Franco Congoleño",
            "CHF - Franco Suizo",
            "CLP - Peso Chileno",
            "CNY - Renminbi Chino",
            "COP - Peso Colombiano",
            "CRC - Colón Costarricense",
            "CUP - Peso Cubano",
            "CVE - Escudo de Cabo Verde",
            "CZK - Corona Checa",
            "DJF - Franco de Yibuti",
            "DKK - Corona Danesa",
            "DOP - Peso Dominicano",
            "DZD - Dinar Argelino",
            "EGP - Libra Egipcia",
            "ERN - Nakfa de Eritrea",
            "ETB - Birr Etíope",
            "EUR - Euro",
            "FJD - Dólar de Fiyi",
            "FKP - Libra de las Islas Malvinas",
            "FOK - Corona de las Islas Feroe",
            "GBP - Libra Esterlina",
            "GEL - Lari Georgiano",
            "GGP - Libra de Guernsey",
            "GHS - Cedi de Ghana",
            "GIP - Libra de Gibraltar",
            "GMD - Dalasi de Gambia",
            "GNF - Franco de Guinea",
            "GTQ - Quetzal Guatemalteco",
            "GYD - Dólar de Guyana",
            "HKD - Dólar de Hong Kong",
            "HNL - Lempira Hondureño",
            "HRK - Kuna Croata",
            "HTG - Gourde Haitiano",
            "HUF - Forinto Húngaro",
            "IDR - Rupia Indonesia",
            "ILS - Nuevo Séquel Israelí",
            "IMP - Libra de la Isla de Man",
            "INR - Rupia India",
            "IQD - Dinar Iraquí",
            "IRR - Rial Iraní",
            "ISK - Corona Islandesa",
            "JEP - Libra de Jersey",
            "JMD - Dólar Jamaicano",
            "JOD - Dinar Jordano",
            "JPY - Yen Japonés",
            "KES - Chelín Keniano",
            "KGS - Som Kirguís",
            "KHR - Riel Camboyano",
            "KID - Dólar de Kiribati",
            "KMF - Franco de las Comoras",
            "KRW - Won Surcoreano",
            "KWD - Dinar Kuwaití",
            "KYD - Dólar de las Islas Caimán",
            "KZT - Tenge Kazajo",
            "LAK - Kip Laosiano",
            "LBP - Libra Libanesa",
            "LKR - Rupia de Sri Lanka",
            "LRD - Dólar Liberiano",
            "LSL - Loti de Lesoto",
            "LYD - Dinar Libio",
            "MAD - Dirham Marroquí",
            "MDL - Leu Moldavo",
            "MGA - Ariary Malgache",
            "MKD - Denar de Macedonia del Norte",
            "MMK - Kyat de Myanmar",
            "MNT - Tugrik Mongol",
            "MOP - Pataca de Macao",
            "MRU - Ouguiya Mauritano",
            "MUR - Rupia de Mauricio",
            "MVR - Rufiyaa de las Maldivas",
            "MWK - Kwacha de Malaui",
            "MXN - Peso Mexicano",
            "MYR - Ringgit Malayo",
            "MZN - Metical de Mozambique",
            "NAD - Dólar Namibio",
            "NGN - Naira Nigeriano",
            "NIO - Córdoba Nicaragüense",
            "NOK - Corona Noruega",
            "NPR - Rupia Nepalí",
            "NZD - Dólar Neozelandés",
            "OMR - Rial Omaní",
            "PAB - Balboa Panameño",
            "PEN - Sol Peruano",
            "PGK - Kina de Papúa Nueva Guinea",
            "PHP - Peso Filipino",
            "PKR - Rupia Pakistaní",
            "PLN - Zloty Polaco",
            "PYG - Guaraní Paraguayo",
            "QAR - Riyal Catarí",
            "RON - Leu Rumano",
            "RSD - Dinar Serbio",
            "RUB - Rublo Ruso",
            "RWF - Franco Ruandés",
            "SAR - Riyal Saudí",
            "SBD - Dólar de las Islas Salomón",
            "SCR - Rupia de Seychelles",
            "SDG - Libra Sudanesa",
            "SEK - Corona Sueca",
            "SGD - Dólar de Singapur",
            "SHP - Libra de Santa Elena",
            "SLE - Leone de Sierra Leona",
            "SOS - Chelín Somalí",
            "SRD - Dólar Surinamés",
            "SSP - Libra de Sudán del Sur",
            "STN - Dobra de Santo Tomé y Príncipe",
            "SYP - Libra Siria",
            "SZL - Lilangeni de Esuatini",
            "THB - Baht Tailandés",
            "TJS - Somoni Tayiko",
            "TMT - Manat Turkmeno",
            "TND - Dinar Tunecino",
            "TOP - Paanga de Tonga",
            "TRY - Lira Turca",
            "TTD - Dólar de Trinidad y Tobago",
            "TVD - Dólar de Tuvalu",
            "TWD - Nuevo Dólar de Taiwán",
            "TZS - Chelín Tanzano",
            "UAH - Grivna Ucraniana",
            "UGX - Chelín Ugandés",
            "USD - Dólar Estadounidense",
            "UYU - Peso Uruguayo",
            "UZS - Som Uzbeko",
            "VES - Bolívar Soberano Venezolano",
            "VND - Dong Vietnamita",
            "VUV - Vatu de Vanuatu",
            "WST - Tala Samoano",
            "XAF - Franco CFA de África Central",
            "XCD - Dólar del Caribe Oriental",
            "XDR - Derechos Especiales de Giro",
            "XOF - Franco CFA de África Occidental",
            "XPF - Franco CFP",
            "YER - Rial Yemení",
            "ZAR - Rand Sudafricano",
            "ZMW - Kwacha Zambiano",
            "ZWL - Dólar Zimbabuense"
    };

    public ConversorDeMonedasGUI(String apiKey) {
        this.conversor = new ConversorDeMonedas(apiKey);
        initializeComponents();
        setupLayout();
        setupEventListeners();
    }

    private void initializeComponents() {
        // Configuración de la ventana principal
        setTitle("Conversor De Monedas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        // Inicializar componentes
        comboMonedaBase = new JComboBox<>(monedas);
        comboMonedaDestino = new JComboBox<>(monedas);
        textCantidad = new JTextField("1");
        labelResultado = new JLabel("", SwingConstants.CENTER);
        btnConvertir = new JButton("Convertir");
        btnHistorial = new JButton("Ver Historial");
        btnIntercambiar = new JButton("⇄");
        btnIntercambiar.setFont(new Font("Arial Unicode MS", Font.BOLD, 18));
        btnIntercambiar.setPreferredSize(new Dimension(50, 30));
        btnIntercambiar.setToolTipText("Intercambiar monedas");

        // Configurar estilos
        Font titleFont = new Font("Arial", Font.BOLD, 16);

        labelResultado.setFont(titleFont);

        textCantidad.setColumns(8);
        textCantidad.setHorizontalAlignment(JTextField.CENTER);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel titulo = new JLabel("Conversor De Monedas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titulo, gbc);

        // Etiqueta y campo de cantidad
        gbc.gridwidth = 1;
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel labelCantidad = new JLabel("Cantidad: ");
        labelCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
        mainPanel.add(labelCantidad, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        textCantidad.setPreferredSize(new Dimension(100, 25));
        textCantidad.setMaximumSize(new Dimension(100, 25));
        mainPanel.add(textCantidad, gbc);

        //Etiquetas de monedas
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel labelMonedaBase = new JLabel("Moneda Base");
        labelMonedaBase.setFont(new Font("Arial", Font.PLAIN, 12));
        mainPanel.add(labelMonedaBase, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel labelMonedaDestino = new JLabel("Moneda Destino");
        labelMonedaDestino.setFont(new Font("Arial", Font.PLAIN, 12));
        mainPanel.add(labelMonedaDestino, gbc);

        // Combos de monedas
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        comboMonedaBase.setPreferredSize(new Dimension(200, 25));
        mainPanel.add(comboMonedaBase, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        btnIntercambiar.setBackground(new Color(255, 165, 0)); // Color naranja
        btnIntercambiar.setForeground(Color.BLACK);
        mainPanel.add(btnIntercambiar, gbc);

        gbc.gridx = 2; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        comboMonedaDestino.setPreferredSize(new Dimension(200, 25));
        mainPanel.add(comboMonedaDestino, gbc);

        // Resultado
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        labelResultado.setBorder(BorderFactory.createEtchedBorder());
        labelResultado.setPreferredSize(new Dimension(400, 40));
        mainPanel.add(labelResultado, gbc);

        // Panel para botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        btnConvertir.setPreferredSize(new Dimension(120, 35));
        btnConvertir.setBackground(new Color(70, 130, 180));
        btnConvertir.setForeground(Color.BLACK); // Texto negro
        btnConvertir.setFont(new Font("Arial", Font.BOLD, 14));

        btnHistorial.setPreferredSize(new Dimension(120, 35));
        btnHistorial.setBackground(new Color(60, 179, 113));
        btnHistorial.setForeground(Color.BLACK); // Texto negro
        btnHistorial.setFont(new Font("Arial", Font.BOLD, 14));

        panelBotones.add(btnConvertir);
        panelBotones.add(btnHistorial);

        // Agregar panel de botones
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(panelBotones, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void setupEventListeners() {
        btnConvertir.addActionListener(e -> realizarConversion());

        btnHistorial.addActionListener(e -> mostrarHistorial());

        btnIntercambiar.addActionListener(e -> intercambiarMonedas());
    }

    private void intercambiarMonedas() {
        // Obtener los índices seleccionados actualmente
        int indiceBase = comboMonedaBase.getSelectedIndex();
        int indiceDestino = comboMonedaDestino.getSelectedIndex();

        // Intercambiar las selecciones
        comboMonedaBase.setSelectedIndex(indiceDestino);
        comboMonedaDestino.setSelectedIndex(indiceBase);

        if (!textCantidad.getText().trim().isEmpty()) {
            realizarConversion();
        }
    }

    private void realizarConversion() {
        try {
            // Obtener cantidad
            double cantidad = Double.parseDouble(textCantidad.getText());

            // Obtener códigos de moneda
            String monedaBase = obtenerCodigoMoneda((String) comboMonedaBase.getSelectedItem());
            String monedaDestino = obtenerCodigoMoneda((String) comboMonedaDestino.getSelectedItem());

            if (monedaBase == null || monedaDestino == null) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione monedas válidas",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Realizar conversión usando la API
            Conversion conversion = conversor.obtenerConversion(monedaBase, monedaDestino, cantidad);

            if (conversion != null) {
                // Mostrar resultado
                labelResultado.setText(conversion.toString());

                // Agregar al historial
                historial.add(conversion);
            } else {
                JOptionPane.showMessageDialog(this, "Error al realizar la conversión",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una cantidad válida",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerCodigoMoneda(String monedaCompleta) {
        if (monedaCompleta != null && monedaCompleta.length() >= 3) {
            return monedaCompleta.substring(0, 3);
        }
        return null;
    }

    private void mostrarHistorial() {
        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay conversiones en el historial",
                    "Historial", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Historial de Conversiones:\n\n");
        for (int i = 0; i < historial.size(); i++) {
            sb.append((i + 1)).append(". ").append(historial.get(i).toString()).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Historial de Conversiones",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
