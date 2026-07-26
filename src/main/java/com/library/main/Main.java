package com.library.main;

import com.library.database.DatabaseInitializer;
import com.library.view.LoginFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            new LoginFrame().setVisible(true);
        });
    }
}
