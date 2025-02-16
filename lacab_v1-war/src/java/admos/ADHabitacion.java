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
    private String gestion[] = {"true", "false", "false"};

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
        if (habitacion != null) {
            mDHabitacion.editarHabitacion(habitacion);
        } else {
            System.out.println("Error: No hay habitación para actualizar.");
        }
    }

    public List<Habitacion> getDisponibles() {
        return mDHabitacion.habitacionesDisponibles();
    }

    public List<Habitacion> getOcupadas() {
        return mDHabitacion.habitacionesOcupadas();
    }

    public List<Habitacion> getMantenimiento() {
        return mDHabitacion.habitacionesMantenimiento();
    }

    public void gestion(int ng) {

        for (int n = 0; n < 3; n++) {
            if (n == ng) {
                gestion[ng] = "true";
            } else {
                gestion[n] = "false";
            }

        }
    }

    public String getGestion(int ng) {
        return gestion[ng];
    }

    public ADHabitacion() {
    }

}
