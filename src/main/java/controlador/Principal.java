package controlador;

import javax.swing.UIManager;
import vistacontrolador.UICRUD;

public class Principal {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new UICRUD().setVisible(true);
    }
}
