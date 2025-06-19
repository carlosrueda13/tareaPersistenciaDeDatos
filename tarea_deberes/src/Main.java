import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaGrafica app = new VistaGrafica();
            app.setVisible(true);
        });
    }
}
