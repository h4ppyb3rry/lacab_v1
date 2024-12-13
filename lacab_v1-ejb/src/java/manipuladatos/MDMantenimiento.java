/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.MantenimientoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Mantenimiento;

/**
 *
 * @author Paula
 */
@Stateless
@LocalBean
public class MDMantenimiento {

    @EJB
    private MantenimientoFacade mantenimientoF;

    public void insertarReporte(Mantenimiento m) {
        mantenimientoF.create(m);
    }

    public List<Mantenimiento> reportesMant() {
        return mantenimientoF.findAll();
    }
    
     public Mantenimiento buscarReporte(Integer id) {
        return mantenimientoF.find(id);
    }
     
     public void editarReporte(Mantenimiento m) {
        mantenimientoF.edit(m);
    }
     
     public List<Mantenimiento> reportesActivos() {
        return mantenimientoF.reportesActivos();
    }
}
