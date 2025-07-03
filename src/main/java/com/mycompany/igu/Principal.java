package com.mycompany.igu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame{
    private JPanel contentPane;
    private JPanel iconPanel;
    private JButton btnAlta;
    private JButton btnConsulta;
    private JButton btnSalir;
    private JPanel titlePanel;
    private JPanel btnPanel;
    private JLabel iconImage;

    public Principal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setTitle("Principal");
        eventoAlta();
        eventoSalir();
        eventoConsulta();
    }


    private void eventoAlta() {
        btnAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaAutomovil alta = new AltaAutomovil();
                alta.setVisible(true);
                dispose();

            }
        });
    }

    private void eventoConsulta() {
        btnConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultaAutomovil consulta = new ConsultaAutomovil();
                consulta.setVisible(true);
                dispose();

            }
        });
    }

    private void eventoSalir() {
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
