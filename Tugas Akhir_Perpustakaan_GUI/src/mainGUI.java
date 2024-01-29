
import viewsGUI.MenuGUI;

import javax.swing.*;

public class mainGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuGUI();
            }
        });
    }
}

