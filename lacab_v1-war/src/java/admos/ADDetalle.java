/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
         listaDetalles.clear();
        nuevoDetalle();
    }

    public void nuevoDetalle() {

        detalle = new DetalleReservacion();
    }

    public void agregarHabitacion() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        reserva = aDReserva.getReserva();

        if (detalle.getNumHab() != null) {
            boolean habitacionAdentro = listaDetalles.stream()
                    .anyMatch(d -> d.getNumHab().equals(detalle.getNumHab())); // comparar por habitación

            if (listaDetalles.size() < reserva.getNumHabitaciones()) {
                if (!habitacionAdentro) {
                    listaDetalles.add(detalle);
                    nuevoDetalle();
                    FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Habitación añadida correctamente.", null);
                    contexto.addMessage(null, msj);
                } else {
                    FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Esta habitación ya ha sido añadida.", null);
                    contexto.addMessage(null, msj);
                }
            } else {
                FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Estás sobrepasando el número de habitaciones designadas en la reservación.", null);
                contexto.addMessage(null, msj);
            }
        } else {
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se ha seleccionado una habitación.", null);
            contexto.addMessage(null, msj);
        }
    }


    /*public void agregarHabitacion() {
         //FacesContext contexto = FacesContext.getCurrentInstance();
        reserva = aDReserva.getReserva();
        if (detalle.getNumHab() != null) {

            boolean habitacionAdentro = listaDetalles.stream()
                    .anyMatch(d -> d.getNumHab().equals(detalle.getNumHab())); // comparar por habitación

            if (listaDetalles.size() < reserva.getNumHabitaciones()) {
                if (!habitacionAdentro) {
                    listaDetalles.add(detalle);

                    nuevoDetalle();
                } else {
                    System.out.println("Error: La habitación ya ha sido añadida.");
                }
            } 
           // FacesMessage msj = new FacesMessage("Estás sobrepasando el número de habitaciones.");
            //contexto.addMessage(null, msj);
            System.out.println("Error: Estás sobrepasando el número de habitaciones");
        } else {
            System.out.println("Error: No se ha seleccionado una habitación.");
        }
    }
    
    
        public void registroReserva() {
        reserva = aDReserva.getReserva();
        if (reserva != null) {
            for (DetalleReservacion d : listaDetalles) {
                d.setNumReserva(reserva);
                mDDetalles.insertarDetalle(d);

                Habitacion habitacion = d.getNumHab();
                if (habitacion != null) {
                    habitacion.setEstado("OCUPADA");
                    aDHabitacion.setHabitacion(habitacion);
                    aDHabitacion.actualizarValor();
                } else {
                    System.out.println("Error: Detalle sin habitación asociada.");
                }
            }
            listaDetalles.clear();
            nuevoDetalle();
        } else {
            System.out.println("Error: No se puede registrar detalles sin una reserva activa.");
        }
    }
    
    
    
     */
    public void eliminarDetalle(DetalleReservacion detalle) {
        listaDetalles.remove(detalle);
    }

    public void registroReserva() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        reserva = aDReserva.getReserva();
        if (listaDetalles.size() == reserva.getNumHabitaciones()) {
            if (reserva != null) {
            for (DetalleReservacion d : listaDetalles) {
                d.setNumReserva(reserva);
                mDDetalles.insertarDetalle(d);

                Habitacion habitacion = d.getNumHab();
                if (habitacion != null) {
                    habitacion.setEstado("OCUPADA");
                    aDHabitacion.setHabitacion(habitacion);
                    aDHabitacion.actualizarValor();
                } else {
                    System.out.println("Error: Detalle sin habitación asociada.");
                }
            }
            listaDetalles.clear();
            nuevoDetalle();

            try {
                contexto.getExternalContext().redirect("confirmacion.xhtml");
            } catch (IOException e) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error al redirigir a la página de confirmación.", null);
                contexto.addMessage(null, mensaje);
            }
        } else {
            System.out.println("Error: No se puede registrar detalles sin una reserva activa.");
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error: No se puede registrar detalles sin una reserva activa.", null);
            contexto.addMessage(null, mensaje);
        } 
        } else{
             FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No puedes registrar menos habitaciones de las designadas.", null);
            contexto.addMessage(null, mensaje);
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
    
    public List<DetalleReservacion> getDetalles(Reservacion r) {
        return mDDetalles.detallesReserva(r);
    }
    

}
