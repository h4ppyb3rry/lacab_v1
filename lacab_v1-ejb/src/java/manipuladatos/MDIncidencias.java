/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.ReporteinciFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Huesped;
import modelo.Reporteinci;

/**
 *
 * @author Paula
 */
@Stateless
@LocalBean
public class MDIncidencias {

    @EJB
    private ReporteinciFacade reporteInF;
    
    public void insertarReporte(Reporteinci i) {
        reporteInF.create(i);
    }

    public List<Reporteinci> reportesInc() {
        return reporteInF.findAll();
    }
    
     public Reporteinci buscarReporte(Integer id) {
        return reporteInF.find(id);
    } 
         public int incidenciasXHuesped(Huesped h) {
        if (h == null || h.getNumHuesped() == null) {
            return 0;
        }
        return reporteInF.contarIncidenciasPorHuesped(h);
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
