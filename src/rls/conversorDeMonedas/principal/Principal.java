package rls.conversorDeMonedas.principal;

import rls.conversorDeMonedas.modelos.ConversorDeMonedasGUI;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Crear y mostrar la GUI
            ConversorDeMonedasGUI gui = new ConversorDeMonedasGUI("");
            gui.setVisible(true);
        });
    }
}
