package org.grupo10.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaBox extends JFrame implements IVista {

    private JLabel numeroAtendidoLabel, personasEnEsperaLabel;
    private ActionListener controlador;
    private JButton llamarSiguienteButton, finalizarTurnoButton;
    private int numeroAtendido = 0;
    private int personasEnEspera = 25;

    public VistaBox(ActionListener controlador) {
        setTitle("Box N°01");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
        this.controlador = controlador;

        // Crear los componentes de la interfaz
        numeroAtendidoLabel = new JLabel("Numero atendido: " + numeroAtendido);
        personasEnEsperaLabel = new JLabel("Personas en espera: " + personasEnEspera);
        llamarSiguienteButton = new JButton("Llamar siguiente");
        finalizarTurnoButton = new JButton("Finalizar turno");

        // Establecer el tamaño de fuente responsivo
        int fontSize = (int) (getHeight() * 0.08);
        Font font = new Font("Arial", Font.PLAIN, fontSize);
        numeroAtendidoLabel.setFont(font);
        personasEnEsperaLabel.setFont(font);
        llamarSiguienteButton.setFont(font);
        finalizarTurnoButton.setFont(font);

        llamarSiguienteButton.addActionListener(controlador);
        finalizarTurnoButton.addActionListener(controlador);

        // Agregar los componentes al panel principal
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.add(numeroAtendidoLabel);
        panel.add(personasEnEsperaLabel);
        panel.add(llamarSiguienteButton);
        panel.add(finalizarTurnoButton);

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void cerrar() {
    }

    @Override
    public void mostrar() {
        setVisible(true);
    }

    @Override
    public void actualizar() {
    }

    @Override
    public void ventanaConfirmacion(String msg) {
    }

    @Override
    public void ventanaError(String msg) {
    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        this.controlador = actionListener;
    }

    @Override
    public JLabel getDisplayLabel() {
        return null;
    }

    @Override
    public StringBuilder getInputBuffer() {
        return null;
    }
}