/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import manipuladatos.MDHabitacion;
import modelo.Habitacion;

/**
 *
 * @author Paula
 */
@Named(value = "aDHabitacion")
@SessionScoped
public class ADHabitacion implements Serializable {

    @EJB
    private MDHabitacion mDHabitacion;
    
    private Habitacion habitacion;
    
    
    public List<Habitacion> getHabitaciones() {
        return mDHabitacion.habitaciones();
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    
    public void actualizarValor() {
            mDHabitacion.editarHabitacion(habitacion);
    }

    
    public List<Habitacion> getDisponibles() {
        return mDHabitacion.habitacionesDisponibles();
    } 

    
    public ADHabitacion() {
    }
    
}
