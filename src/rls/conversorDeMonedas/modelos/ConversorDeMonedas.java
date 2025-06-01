package rls.conversorDeMonedas.modelos;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorDeMonedas {
    private final String APIKey;
    private Scanner consola;
    private final List<Conversion> historial = new ArrayList<>();

    public ConversorDeMonedas(String APIKey) {
        this.APIKey = APIKey;

        if (APIKey == null) {
            System.out.println("API Key inválida");
        } else {
            System.out.println("API Key: " +  APIKey);
            this.consola = new Scanner(System.in);
        }
    }

    private void imprimirMenu() {
        System.out.println("""
                Conversor de Monedas
                
                0. Salir
                1. AED - Dirham de los Emiratos Árabes Unidos  2. AFN - Afgani Afgano  3. ALL - Lek Albanés  4. AMD - Dram Armenio  5. ANG - Florín de las Antillas Neerlandesas \s
                6. AOA - Kwanza Angoleño  7. ARS - Peso Argentino  8. AUD - Dólar Australiano  9. AWG - Florín de Aruba  10. AZN - Manat Azerbaiyano \s
                11. BAM - Marco de Bosnia y Herzegovina  12. BBD - Dólar de Barbados  13. BDT - Taka de Bangladés  14. BGN - Lev Búlgaro  15. BHD - Dinar de Baréin \s
                16. BIF - Franco de Burundi  17. BMD - Dólar de las Bermudas  18. BND - Dólar de Brunéi  19. BOB - Boliviano Boliviano  20. BRL - Real Brasileño \s
                21. BSD - Dólar de las Bahamas  22. BTN - Ngultrum de Bután  23. BWP - Pula de Botsuana  24. BYN - Rublo Bielorruso  25. BZD - Dólar de Belice \s
                26. CAD - Dólar Canadiense  27. CDF - Franco Congoleño  28. CHF - Franco Suizo  29. CLP - Peso Chileno  30. CNY - Renminbi Chino \s
                31. COP - Peso Colombiano  32. CRC - Colón Costarricense  33. CUP - Peso Cubano  34. CVE - Escudo de Cabo Verde  35. CZK - Corona Checa \s
                36. DJF - Franco de Yibuti  37. DKK - Corona Danesa  38. DOP - Peso Dominicano  39. DZD - Dinar Argelino  40. EGP - Libra Egipcia \s
                41. ERN - Nakfa de Eritrea  42. ETB - Birr Etíope  43. EUR - Euro  44. FJD - Dólar de Fiyi  45. FKP - Libra de las Islas Malvinas \s
                46. FOK - Corona de las Islas Feroe  47. GBP - Libra Esterlina  48. GEL - Lari Georgiano  49. GGP - Libra de Guernsey  50. GHS - Cedi de Ghana \s
                51. GIP - Libra de Gibraltar  52. GMD - Dalasi de Gambia  53. GNF - Franco de Guinea  54. GTQ - Quetzal Guatemalteco  55. GYD - Dólar de Guyana \s
                56. HKD - Dólar de Hong Kong  57. HNL - Lempira Hondureño  58. HRK - Kuna Croata  59. HTG - Gourde Haitiano  60. HUF - Forinto Húngaro \s
                61. IDR - Rupia Indonesia  62. ILS - Nuevo Séquel Israelí  63. IMP - Libra de la Isla de Man  64. INR - Rupia India  65. IQD - Dinar Iraquí \s
                66. IRR - Rial Iraní  67. ISK - Corona Islandesa  68. JEP - Libra de Jersey  69. JMD - Dólar Jamaicano  70. JOD - Dinar Jordano \s
                71. JPY - Yen Japonés  72. KES - Chelín Keniano  73. KGS - Som Kirguís  74. KHR - Riel Camboyano  75. KID - Dólar de Kiribati \s
                76. KMF - Franco de las Comoras  77. KRW - Won Surcoreano  78. KWD - Dinar Kuwaití  79. KYD - Dólar de las Islas Caimán  80. KZT - Tenge Kazajo \s
                81. LAK - Kip Laosiano  82. LBP - Libra Libanesa  83. LKR - Rupia de Sri Lanka  84. LRD - Dólar Liberiano  85. LSL - Loti de Lesoto \s
                86. LYD - Dinar Libio  87. MAD - Dirham Marroquí  88. MDL - Leu Moldavo  89. MGA - Ariary Malgache  90. MKD - Denar de Macedonia del Norte \s
                91. MMK - Kyat de Myanmar  92. MNT - Tugrik Mongol  93. MOP - Pataca de Macao  94. MRU - Ouguiya Mauritano  95. MUR - Rupia de Mauricio \s
                96. MVR - Rufiyaa de las Maldivas  97. MWK - Kwacha de Malaui  98. MXN - Peso Mexicano  99. MYR - Ringgit Malayo  100. MZN - Metical de Mozambique \s
                101. NAD - Dólar Namibio  102. NGN - Naira Nigeriano  103. NIO - Córdoba Nicaragüense  104. NOK - Corona Noruega  105. NPR - Rupia Nepalí \s
                106. NZD - Dólar Neozelandés  107. OMR - Rial Omaní  108. PAB - Balboa Panameño  109. PEN - Sol Peruano  110. PGK - Kina de Papúa Nueva Guinea \s
                111. PHP - Peso Filipino  112. PKR - Rupia Pakistaní  113. PLN - Zloty Polaco  114. PYG - Guaraní Paraguayo  115. QAR - Riyal Catarí \s
                116. RON - Leu Rumano  117. RSD - Dinar Serbio  118. RUB - Rublo Ruso  119. RWF - Franco Ruandés  120. SAR - Riyal Saudí \s
                121. SBD - Dólar de las Islas Salomón  122. SCR - Rupia de Seychelles  123. SDG - Libra Sudanesa  124. SEK - Corona Sueca  125. SGD - Dólar de Singapur \s
                126. SHP - Libra de Santa Elena  127. SLE - Leone de Sierra Leona  128. SOS - Chelín Somalí  129. SRD - Dólar Surinamés  130. SSP - Libra de Sudán del Sur \s
                131. STN - Dobra de Santo Tomé y Príncipe  132. SYP - Libra Siria  133. SZL - Lilangeni de Esuatini  134. THB - Baht Tailandés  135. TJS - Somoni Tayiko \s
                136. TMT - Manat Turkmeno  137. TND - Dinar Tunecino  138. TOP - Paanga de Tonga  139. TRY - Lira Turca  140. TTD - Dólar de Trinidad y Tobago \s
                141. TVD - Dólar de Tuvalu  142. TWD - Nuevo Dólar de Taiwán  143. TZS - Chelín Tanzano  144. UAH - Grivna Ucraniana  145. UGX - Chelín Ugandés \s
                146. USD - Dólar Estadounidense  147. UYU - Peso Uruguayo  148. UZS - Som Uzbeko  149. VES - Bolívar Soberano Venezolano  150. VND - Dong Vietnamita \s
                151. VUV - Vatu de Vanuatu  152. WST - Tala Samoano  153. XAF - Franco CFA de África Central  154. XCD - Dólar del Caribe Oriental  155. XDR - Derechos Especiales de Giro \s
                156. XOF - Franco CFA de África Occidental  157. XPF - Franco CFP  158. YER - Rial Yemení  159. ZAR - Rand Sudafricano  160. ZMW - Kwacha Zambiano \s
                161. ZWL - Dólar Zimbabuense  162. Mostrar historial de conversiones.
                """);
    }

    private String getCodigoMoneda(int opcion) {
        return switch (opcion) {
            case 1 -> "AED";
            case 2 -> "AFN";
            case 3 -> "ALL";
            case 4 -> "AMD";
            case 5 -> "ANG";
            case 6 -> "AOA";
            case 7 -> "ARS";
            case 8 -> "AUD";
            case 9 -> "AWG";
            case 10 -> "AZN";
            case 11 -> "BAM";
            case 12 -> "BBD";
            case 13 -> "BDT";
            case 14 -> "BGN";
            case 15 -> "BHD";
            case 16 -> "BIF";
            case 17 -> "BMD";
            case 18 -> "BND";
            case 19 -> "BOB";
            case 20 -> "BRL";
            case 21 -> "BSD";
            case 22 -> "BTN";
            case 23 -> "BWP";
            case 24 -> "BYN";
            case 25 -> "BZD";
            case 26 -> "CAD";
            case 27 -> "CDF";
            case 28 -> "CHF";
            case 29 -> "CLP";
            case 30 -> "CNY";
            case 31 -> "COP";
            case 32 -> "CRC";
            case 33 -> "CUP";
            case 34 -> "CVE";
            case 35 -> "CZK";
            case 36 -> "DJF";
            case 37 -> "DKK";
            case 38 -> "DOP";
            case 39 -> "DZD";
            case 40 -> "EGP";
            case 41 -> "ERN";
            case 42 -> "ETB";
            case 43 -> "EUR";
            case 44 -> "FJD";
            case 45 -> "FKP";
            case 46 -> "FOK";
            case 47 -> "GBP";
            case 48 -> "GEL";
            case 49 -> "GGP";
            case 50 -> "GHS";
            case 51 -> "GIP";
            case 52 -> "GMD";
            case 53 -> "GNF";
            case 54 -> "GTQ";
            case 55 -> "GYD";
            case 56 -> "HKD";
            case 57 -> "HNL";
            case 58 -> "HRK";
            case 59 -> "HTG";
            case 60 -> "HUF";
            case 61 -> "IDR";
            case 62 -> "ILS";
            case 63 -> "IMP";
            case 64 -> "INR";
            case 65 -> "IQD";
            case 66 -> "IRR";
            case 67 -> "ISK";
            case 68 -> "JEP";
            case 69 -> "JMD";
            case 70 -> "JOD";
            case 71 -> "JPY";
            case 72 -> "KES";
            case 73 -> "KGS";
            case 74 -> "KHR";
            case 75 -> "KID";
            case 76 -> "KMF";
            case 77 -> "KRW";
            case 78 -> "KWD";
            case 79 -> "KYD";
            case 80 -> "KZT";
            case 81 -> "LAK";
            case 82 -> "LBP";
            case 83 -> "LKR";
            case 84 -> "LRD";
            case 85 -> "LSL";
            case 86 -> "LYD";
            case 87 -> "MAD";
            case 88 -> "MDL";
            case 89 -> "MGA";
            case 90 -> "MKD";
            case 91 -> "MMK";
            case 92 -> "MNT";
            case 93 -> "MOP";
            case 94 -> "MRU";
            case 95 -> "MUR";
            case 96 -> "MVR";
            case 97 -> "MWK";
            case 98 -> "MXN";
            case 99 -> "MYR";
            case 100 -> "MZN";
            case 101 -> "NAD";
            case 102 -> "NGN";
            case 103 -> "NIO";
            case 104 -> "NOK";
            case 105 -> "NPR";
            case 106 -> "NZD";
            case 107 -> "OMR";
            case 108 -> "PAB";
            case 109 -> "PEN";
            case 110 -> "PGK";
            case 111 -> "PHP";
            case 112 -> "PKR";
            case 113 -> "PLN";
            case 114 -> "PYG";
            case 115 -> "QAR";
            case 116 -> "RON";
            case 117 -> "RSD";
            case 118 -> "RUB";
            case 119 -> "RWF";
            case 120 -> "SAR";
            case 121 -> "SBD";
            case 122 -> "SCR";
            case 123 -> "SDG";
            case 124 -> "SEK";
            case 125 -> "SGD";
            case 126 -> "SHP";
            case 127 -> "SLE";
            case 128 -> "SOS";
            case 129 -> "SRD";
            case 130 -> "SSP";
            case 131 -> "STN";
            case 132 -> "SYP";
            case 133 -> "SZL";
            case 134 -> "THB";
            case 135 -> "TJS";
            case 136 -> "TMT";
            case 137 -> "TND";
            case 138 -> "TOP";
            case 139 -> "TRY";
            case 140 -> "TTD";
            case 141 -> "TVD";
            case 142 -> "TWD";
            case 143 -> "TZS";
            case 144 -> "UAH";
            case 145 -> "UGX";
            case 146 -> "USD";
            case 147 -> "UYU";
            case 148 -> "UZS";
            case 149 -> "VES";
            case 150 -> "VND";
            case 151 -> "VUV";
            case 152 -> "WST";
            case 153 -> "XAF";
            case 154 -> "XCD";
            case 155 -> "XDR";
            case 156 -> "XOF";
            case 157 -> "XPF";
            case 158 -> "YER";
            case 159 -> "ZAR";
            case 160 -> "ZMW";
            case 161 -> "ZWL";
            case 162 -> {
                if (!historial.isEmpty()) {
                    mostarHistorial();
                }
                yield null;
            }
            default -> null;
        };
    }

    private void obtenerTasaDeCambio(String codigoMonedaBase, String codigoMonedaDestino) throws IOException, InterruptedException {
        System.out.println("Ingrese la cantidad a conocer su tasa de cambio");

        int cantidad = consola.nextInt();

        String direccion = "https://v6.exchangerate-api.com/v6/" +
                APIKey + "/pair/" + codigoMonedaBase + "/" + codigoMonedaDestino + "/" + cantidad;

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        String json = respuesta.body();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        ConversionExRateAPI miConversionExRateAPI = gson.fromJson(json, ConversionExRateAPI.class);

        Conversion conversion =  new Conversion(miConversionExRateAPI, cantidad);
        System.out.println(conversion);

        this.historial.add(conversion);
    }

    private String obtenerCodigoMonedaValido(String mensaje) {
        System.out.println(mensaje);
        int opcion = consola.nextInt();

        if (opcion == 0) {
            System.out.println("¡Gracias por usar el conversor! ¡Vuelva pronto!");
            return null;
        }

        String codigo = getCodigoMoneda(opcion);
        if (codigo == null) {
            if (opcion != 162 || historial.isEmpty()) {
                System.out.println("Ingresa una opción válida");
            }
            return obtenerCodigoMonedaValido(mensaje);
        }
        return codigo;
    }

    public void ejecutarConversion() throws IOException, InterruptedException {
        boolean continuar = true;

        while (continuar) {
            imprimirMenu();

            String codigoMonedaBase = obtenerCodigoMonedaValido("Elige una opción de moneda base para obtener su tasa de cambio o '162' para mostrar el historial: ");
            if (codigoMonedaBase == null) {
                continuar = false;
                continue;
            }

            String codigoMonedaDestino = obtenerCodigoMonedaValido("Elige la moneda destino: ");
            if (codigoMonedaDestino == null) {
                continuar = false;
                continue;
            }

            obtenerTasaDeCambio(codigoMonedaBase, codigoMonedaDestino);
        }
    }

    private void mostarHistorial() {
        for (int i = 0; i < historial.size(); i++) {
            System.out.println("rls.convesorDeMonedas.modelos.Conversion " + (i+1) + ": " + historial.get(i));
        }
    }
}
