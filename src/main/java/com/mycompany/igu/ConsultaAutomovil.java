package com.mycompany.igu;

import com.mycompany.logica.Automovil;
import com.mycompany.logica.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showConfirmDialog;

public class ConsultaAutomovil extends JFrame {
    private JPanel contentPane;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JTable tableAutos;
    private JPanel auxiliarPanel;
    private JPanel btnPanel;
    private JPanel titlePanel;
    private JScrollPane tableScrollPane;
    Controlador controlador=null;

    public ConsultaAutomovil() {
        iniciarComponentes();
    }
    //Iniciar consulta con fila seleccionada
    public ConsultaAutomovil(Automovil automovil) {
        iniciarComponentes();
        int idAuto=automovil.getId();
        for (int i = 0; i < tableAutos.getRowCount(); i++) {
            // Obtener el valor de la primera columna (ID) de la fila actual
            int id = (int) tableAutos.getValueAt(i, 0);

            // Verificar si el ID de la fila coincide con el ID del automóvil
            if (id == idAuto) {
                // Seleccionar la fila encontrada
                tableAutos.setRowSelectionInterval(i, i);
                break; // Terminar el ciclo cuando se encuentra la fila
            }
        }
    }
    private void iniciarComponentes() {
        controlador=new Controlador();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        cargarTabla();
        eventoEliminar();
        eventoEditar();
    }

    private void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] titulos = {"Id Auto","Modelo","Marca","Motor","Color","Patente","Cantidad de Puertas"};
        modelo.setColumnIdentifiers(titulos);
        ArrayList<Automovil> listaAutos=controlador.buscarAutos();
        if(listaAutos!=null){
            for (Automovil automovil:listaAutos){
                Object[] row = {automovil.getId(), automovil.getModelo(),automovil.getMarca(),automovil.getMotor(), automovil.getColor(),
                        automovil.getPatente(),automovil.getCantPuertas()};
                modelo.addRow(row);
            }
        }
        tableAutos.setModel(modelo);
    }

    private void eventoEliminar() {
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tableAutos.getSelectedRow();
                if(filaSeleccionada!=1){
                    JOptionPane.showMessageDialog(null,"Para eliminar un automóvil es necesario seleccionar una fila","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int confirmacion=JOptionPane.showConfirmDialog(null,"¿Estas seguro que deseas eliminar este auto?",
                            "Confirmar eliminación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                    if(confirmacion==JOptionPane.OK_OPTION){
                        int idAuto=(Integer) (tableAutos.getValueAt(filaSeleccionada, 0));
                        controlador.eliminarAuto(idAuto);
                        JOptionPane.showMessageDialog(null,"Auto eliminado correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);
                        cargarTabla();
                    }
                }
            }
        });
    }

    private void eventoEditar() {
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tableAutos.getSelectedRow();
                if(filaSeleccionada==-1){
                    JOptionPane.showMessageDialog(null,"Para editar un automóvil es necesario seleccionar una fila","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int idAuto=(Integer) (tableAutos.getValueAt(filaSeleccionada, 0));
                    EditarAutomovil editarAutomovil = new EditarAutomovil(idAuto);
                    editarAutomovil.setVisible(true);
                    dispose();
                }
            }
        });
    }


}
