/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import manipuladatos.MDIncidencias;
import modelo.Huesped;
import modelo.Reporteinci;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Named(value = "aDIncidencia")
@SessionScoped
public class ADIncidencia implements Serializable {

    @EJB
    private MDIncidencias mDIncidencias;
    
    private Reporteinci reporteI;

    public Reporteinci getReporteI() {
        return reporteI;
    }

    public void setReporteI(Reporteinci reporteI) {
        this.reporteI = reporteI;
    }

     public List<Reporteinci> getReportesI() {
        return mDIncidencias.reportesInc();
    }
    
     public void creaReporte() {
        reporteI = new Reporteinci();
    }
     
     public String nuevoReporte() {
      
       reporteI.setFecha(new Date());
        
        mDIncidencias.insertarReporte(reporteI);
        return "gestion_reserva.xhtml?faces-redirect=true"; 
    }
     
     public String nReporte() {
        creaReporte();
        return "reporteIn_nuevo.xhtml?faces-redirect=true";
    }

    public int incidencias(Huesped h) {
        return mDIncidencias.incidenciasXHuesped(h);

    }
    public ADIncidencia() {
        creaReporte();
    }
    
}
