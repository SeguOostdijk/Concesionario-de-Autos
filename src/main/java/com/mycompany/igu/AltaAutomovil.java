package com.mycompany.igu;

import com.mycompany.logica.AutoDuplicadoException;
import com.mycompany.logica.Controlador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltaAutomovil extends JFrame {
    private JPanel contentPane;
    private JTextField txtModelo;
    private JTextField txtMarca;
    private JTextField txtColor;
    private JTextField txtMotor;
    private JTextField txtCantPuertas;
    private JPanel txtPanel;
    private JPanel iconPanel;
    private JLabel iconImage;
    private JPanel titlePanel;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JPanel btnPanel;
    private JTextField txtPatente;
    Controlador controlador;

    public AltaAutomovil() {
        controlador=new Controlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setTitle("Agregar Automóvil");
        eventoLimpiar();
        eventoGuardar();
    }


    private void eventoLimpiar() {
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtModelo.setText("");
                txtMarca.setText("");
                txtColor.setText("");
                txtMotor.setText("");
                txtCantPuertas.setText("");
                txtPatente.setText("");
            }
        });
    }

    private void eventoGuardar() {
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
                    controlador.guardar(modelo,marca,motor,color,cantPuertas,patente);
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
                catch (AutoDuplicadoException ex){
                    int opcion = JOptionPane.showOptionDialog(
                            null,
                            ex.getMessage(),
                            "Auto Duplicado",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null,
                            new Object[]{"Ver Datos", "Agregar de todas formas"},
                            "Ver Datos"
                    );
                    if (opcion == 0){
                        ConsultaAutomovil consulta=new ConsultaAutomovil(ex.getAutoDuplicado());
                        consulta.setVisible(true);
                        dispose();
                    }
                    else if (opcion == 1){
                        controlador.guardar(modelo,marca,motor,color,cantPuertas,patente);
                        JOptionPane.showMessageDialog(null, "Datos guardados correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

}
