import view.BukuView;

import javax.swing.*;

import static utils.ConsoleColors.printError;

public class Responsi2_PPBO_L0123068_SC {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                printError(e.getMessage());
            }

            BukuView app = new BukuView();
            app.setVisible(true);
        });
    }
}
