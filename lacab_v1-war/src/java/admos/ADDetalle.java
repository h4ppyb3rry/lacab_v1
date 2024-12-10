/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import manipuladatos.MDDetalles;
import modelo.DetalleReservacion;
import modelo.Habitacion;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Named(value = "aDDetalle")
@SessionScoped
public class ADDetalle implements Serializable {

    private List<Habitacion> habitacionesDisponibles;
    private List<DetalleReservacion> listaDetalles;
    private DetalleReservacion detalle;
    private Reservacion reserva;

    @EJB
    private MDDetalles mDDetalles;

    @Inject
    private ADHabitacion aDHabitacion;

    @Inject
    private ADReserva aDReserva;

    @PostConstruct
    public void init() {

        habitacionesDisponibles = hDisponibles();
        listaDetalles = new ArrayList<>();
        nuevoDetalle();
    }

    public void nuevoDetalle() {

        detalle = new DetalleReservacion();
    }

    public void agregarHabitacion() {
    if (detalle.getNumHab() != null) { 
        // Verificar que el detalle con la misma habitación no esté ya en la lista
        boolean habitacionYaAñadida = listaDetalles.stream()
                .anyMatch(d -> d.getNumHab().equals(detalle.getNumHab())); // Comparar por habitación
        
        if (!habitacionYaAñadida) {
            listaDetalles.add(detalle); // Agrega el detalle a la lista
            nuevoDetalle(); // Reinicia el detalle para una nueva habitación
        } else {
            System.out.println("Error: La habitación ya ha sido añadida.");
        }
    } else {
        System.out.println("Error: No se ha seleccionado una habitación.");
    }
}


    public void eliminarDetalle(DetalleReservacion detalle) {
        listaDetalles.remove(detalle);
    }

    public void registroReserva() {
        reserva = aDReserva.getReserva();
        if (reserva != null) {
            for (DetalleReservacion d : listaDetalles) {
                d.setNumReserva(reserva);
                mDDetalles.insertarDetalle(d);
            }
            listaDetalles.clear();
            nuevoDetalle();
        } else {
            System.out.println("Error: No se puede registrar detalles sin una reserva activa.");
        }
    }

    public List<Habitacion> hDisponibles() {
        return aDHabitacion.getDisponibles();
    }

    public List<Habitacion> getHabitacionesDisponibles() {
        return habitacionesDisponibles;
    }

    public void setHabitacionesDisponibles(List<Habitacion> habitacionesDisponibles) {
        this.habitacionesDisponibles = habitacionesDisponibles;
    }

    public List<DetalleReservacion> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleReservacion> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public DetalleReservacion getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleReservacion detalle) {
        this.detalle = detalle;
    }

}
