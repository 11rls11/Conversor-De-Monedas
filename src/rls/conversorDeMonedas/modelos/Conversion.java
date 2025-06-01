package rls.conversorDeMonedas.modelos;

public class Conversion {
    private final String monedaBase;
    private final String monedaDestino;
    private final int cantidad;
    private final double resultado;

    public Conversion(ConversionExRateAPI conversionExRateAPI, int cantidad) {
        this.monedaBase = conversionExRateAPI.base_code();
        this.monedaDestino = conversionExRateAPI.target_code();
        this.cantidad = cantidad;
        this.resultado = Double.parseDouble(conversionExRateAPI.conversion_result());
    }

    @Override
    public String toString() {
        return cantidad + " " + monedaBase + " = " + resultado + " " + monedaDestino;
    }
}
