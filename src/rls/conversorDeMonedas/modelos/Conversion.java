package rls.conversorDeMonedas.modelos;

public class Conversion {
    private final String monedaBase;
    private final String monedaDestino;
    private final double cantidad;
    private final double resultado;

    public Conversion(ConversionExRateAPI conversionExRateAPI, double cantidad) {
        this.monedaBase = conversionExRateAPI.base_code();
        this.monedaDestino = conversionExRateAPI.target_code();
        this.cantidad = cantidad;
        this.resultado = Double.parseDouble(conversionExRateAPI.conversion_result());
    }

    @Override
    public String toString() {
        return String.format("%.2f", cantidad) + " " + monedaBase + " = " + String.format("%.2f", resultado) + " " + monedaDestino;
    }
}
