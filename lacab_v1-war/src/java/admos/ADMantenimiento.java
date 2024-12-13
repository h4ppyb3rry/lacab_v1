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
import javax.inject.Inject;
import manipuladatos.MDMantenimiento;
import modelo.Habitacion;
import modelo.Mantenimiento;

/**
 *
 * @author Paula
 */
@Named(value = "aDMantenimiento")
@SessionScoped
public class ADMantenimiento implements Serializable {

    @Inject
    private ADHabitacion aDHabitacion;

    @EJB
    private MDMantenimiento mDMantenimiento;

    private Mantenimiento reporteM;

    public String nuevoReporte() {
        reporteM.setEstado("ACTIVO");
        reporteM.setFecha(new Date());// fecha actual

        Habitacion habitacion = reporteM.getNumHab();
        if (habitacion != null) {
            habitacion.setEstado("MANTENIMIENTO");
            aDHabitacion.setHabitacion(habitacion);
            aDHabitacion.actualizarValor();
        } else {
            System.out.println("Error: No se seleccionó una habitación para el reporte.");
        }
        mDMantenimiento.insertarReporte(reporteM);
        return "gestion_habitacion.xhtml?faces-redirect=true"; 
    }
    
    public String cerrarReporte(Mantenimiento m){
        Habitacion habitacion = m.getNumHab();
        if (habitacion != null) {
            habitacion.setEstado("DISPONIBLE");
            aDHabitacion.setHabitacion(habitacion);
            aDHabitacion.actualizarValor();
        }
        
        m.setEstado("CERRADO");
        mDMantenimiento.editarReporte(m);
        return "gestion_habitacion.xhtml?faces-redirect=true"; 
    } 
            
            
    public List<Mantenimiento> getReportesM() {
        return mDMantenimiento.reportesMant();
    }
    
     public List<Mantenimiento> getActivos() {
        return mDMantenimiento.reportesActivos();
    }

    public void creaReporte() {
        reporteM = new Mantenimiento();
    }

    public Mantenimiento getReporteM() {
        return reporteM;
    }

    public void setReporteM(Mantenimiento reporteM) {
        this.reporteM = reporteM;
    }
    
    public String nReporte() {
        creaReporte();
        return "reportemant_nuevo.xhtml?faces-redirect=true";
    }

    public ADMantenimiento() {
        creaReporte();
    }

}
