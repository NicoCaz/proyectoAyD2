package org.grupo10.main;

import org.grupo10.controlador.ControladorBox;
import org.grupo10.vista.VistaBox;

import javax.swing.*;

public class MainBox {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VistaBox().setVisible(true);
            }
        });
    }
}