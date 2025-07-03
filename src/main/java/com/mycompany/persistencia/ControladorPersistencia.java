package com.mycompany.persistencia;

import com.mycompany.logica.Automovil;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ControladorPersistencia {
    EntityManagerFactory emf;
    AutomovilJPAController autoJPA;

    public ControladorPersistencia() {
        emf = Persistence.createEntityManagerFactory("concesionario");
        autoJPA = new AutomovilJPAController(emf);
    }

    public void crearAutomovil(Automovil automovil) {
        autoJPA.create(automovil);
    }

    public List<Automovil> buscarAutos() {
       return autoJPA.findAutomoviles();
    }

    public void eliminarAuto(int idAuto) {
        autoJPA.destroy(idAuto);
    }

    public Automovil buscarAuto(int idAuto) {
        return autoJPA.findAutomovil(idAuto);
    }

    public void actualizarDatos(Automovil automovil) {
        autoJPA.edit(automovil);
    }
}
