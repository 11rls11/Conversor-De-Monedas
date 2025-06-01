package rls.conversorDeMonedas.principal;

import rls.conversorDeMonedas.modelos.ConversorDeMonedas;

import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConversorDeMonedas conversor = new ConversorDeMonedas("d596b20b6908a5904a115b07");
        conversor.ejecutarConversion();
    }
}
