package com.mycompany.logica;

import com.mycompany.persistencia.ControladorPersistencia;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
    ControladorPersistencia controlPersis;

    public Controlador() {
        controlPersis = new ControladorPersistencia();
    }
    public void guardar(String modelo, String marca, String color, String motor, int cantPuertas, String patente) {
        validarDatos(modelo,marca,color,motor,cantPuertas,patente);
        verificaDuplicado(modelo,marca,color,motor,cantPuertas,patente);
        Automovil automovil = new Automovil(modelo,marca,motor,color,patente,cantPuertas);
        controlPersis.crearAutomovil(automovil);
    }

    private void validarDatos(String modelo, String marca, String color, String motor, int cantPuertas, String patente) {
        //Evita campos vacios
        if(modelo.equals("") || marca.equals("") || motor.equals("")|| cantPuertas<=0 || patente.equals("")||color.equals("")){
            throw new IllegalArgumentException("Debe rellenar todos los campos para poder continuar");
        }
        //Formatea los datos ingresados por el usuario
        modelo = modelo.toUpperCase();
        marca = marca.toUpperCase();
        color = color.toUpperCase();
        motor = motor.toUpperCase();
        patente = patente.toUpperCase();
    }
    private void verificaDuplicado(String modelo, String marca, String color, String motor, int cantPuertas, String patente) {
        //Busca auto duplicado
        ArrayList<Automovil>listaAutos=buscarAutos();
        if(listaAutos!=null){
            for(Automovil automovil:listaAutos){
                if(automovil.getModelo().equals(modelo)&&
                        automovil.getMarca().equals(marca)&&
                        automovil.getPatente().equals(patente)&&
                        automovil.getColor().equals(color)&&
                        automovil.getMotor().equals(motor)){
                    throw new AutoDuplicadoException("Ya existe un auto con estas características.", automovil);
                }
            }
        }
    }
    public ArrayList<Automovil> buscarAutos() {
        List<Automovil> listita=controlPersis.buscarAutos();
        ArrayList<Automovil> listaAutos=new ArrayList<>(listita);
        return listaAutos;
    }

    public void eliminarAuto(int idAuto) {
        controlPersis.eliminarAuto(idAuto);
    }

    public Automovil buscarAuto(int idAuto) {
        return controlPersis.buscarAuto(idAuto);
    }

    public void actualizarDatos(Automovil automovil, String modelo, String marca, String motor, String color, int cantPuertas, String patente) {
        validarDatos(modelo,marca,color,motor,cantPuertas,patente);
        if(automovil!=null){
            automovil.setModelo(modelo);
            automovil.setMarca(marca);
            automovil.setMotor(motor);
            automovil.setColor(color);
            automovil.setCantPuertas(cantPuertas);
            automovil.setPatente(patente);
            controlPersis.actualizarDatos(automovil);
        }
    }

}
