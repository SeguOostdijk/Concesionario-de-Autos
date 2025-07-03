package com.mycompany.igu;

import com.mycompany.logica.Automovil;
import com.mycompany.logica.Controlador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarAutomovil extends JFrame {
    private JPanel contentPane;
    private JPanel txtPanel;
    private JTextField txtModelo;
    private JTextField txtMarca;
    private JTextField txtMotor;
    private JTextField txtColor;
    private JTextField txtPatente;
    private JTextField txtCantPuertas;
    private JPanel btnPanel;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    Controlador controlador=null;
    Automovil automovil=null;

    public EditarAutomovil(int idAuto) {
        controlador=new Controlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        cargarDatos(idAuto);
        eventoLimpiar();
        eventoGuardarCambios();
    }


    private void cargarDatos(int idAuto) {
        this.automovil = controlador.buscarAuto(idAuto);
        if(automovil!=null) {
            txtModelo.setText(automovil.getModelo());
            txtMarca.setText(automovil.getMarca());
            txtMotor.setText(automovil.getMotor());
            txtColor.setText(automovil.getColor());
            txtPatente.setText(automovil.getPatente());
            txtCantPuertas.setText(String.valueOf(automovil.getCantPuertas()));
        }
    }
    private void eventoLimpiar() {
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtModelo.setText("");
                txtMarca.setText("");
                txtMotor.setText("");
                txtColor.setText("");
                txtCantPuertas.setText("");
                txtPatente.setText("");
            }
        });
    }

    private void eventoGuardarCambios() {
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String modelo = txtModelo.getText();
                String marca = txtMarca.getText();
                String color = txtColor.getText();
                String motor = txtMotor.getText();
                int cantPuertas = Integer.parseInt(txtCantPuertas.getText());
                String patente = txtPatente.getText();
                try {
                    controlador.actualizarDatos(automovil,modelo,marca,motor,color,cantPuertas,patente);
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);
                    ConsultaAutomovil consulta=new ConsultaAutomovil();
                    consulta.setVisible(true);
                    dispose();
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

}
