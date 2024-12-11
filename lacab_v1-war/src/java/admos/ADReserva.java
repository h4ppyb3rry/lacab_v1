/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import manipuladatos.MDReserva;
import modelo.DetalleReservacion;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Named(value = "aDReserva")
@SessionScoped
public class ADReserva implements Serializable {

    @EJB
    private MDReserva mDReserva;
    private Reservacion reserva;
    private Huesped numHuesped;
    private List<DetalleReservacion> listaDetalles;

    @Inject
    private ADHabitacion aDHabitacion;

    @Inject
    private ADDetalle aDDetalle;
    
    @Inject
    private ADNota aDNota;

    public Huesped getNumHuesped() {
        return numHuesped;
    }

    public void setNumHuesped(Huesped numHuesped) {
        this.numHuesped = numHuesped;
    }

    public void creaReserva() {
        reserva = new Reservacion();
    }

    public List<Reservacion> getReservaciones() {
        return mDReserva.reservaciones();
    }

    public List<DetalleReservacion> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleReservacion> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public String registroReserva() {
        reserva.setEstado("ACTIVA");
        //reserva.setFechaSalida(null);
        mDReserva.insertarReserva(reserva);
        // creaReserva();
        return "detalle_reserva.xhtml?faces-redirect=true";
    }

    public Reservacion getReserva() {
        return reserva;
    }

    public void setReserva(Reservacion reserva) {
        this.reserva = reserva;
    }

    public List<Reservacion> getActivas() {
        return mDReserva.reservasActivas();
    }

    
    
    public void finalizarReserva(Reservacion reserva) {
        if (reserva != null) {
            reserva.setEstado("FINALIZADA");
            
           // reserva.setFechaSalida(new Date());

            // cambiar el estado 
            List<DetalleReservacion> detalles = aDDetalle.getDetalles(reserva);
            for (DetalleReservacion detalle : detalles) {
                Habitacion habitacion = detalle.getNumHab();
                if (habitacion != null) {
                    habitacion.setEstado("DISPONIBLE");
                    aDHabitacion.setHabitacion(habitacion);
                    aDHabitacion.actualizarValor();
                }
            }
            mDReserva.actualizarReserva(reserva);
            
            System.out.println("Reservación " + reserva.getNumReserva() + " ha sido finalizada.");
        } else {
            System.out.println("Error");
        }
    }
    
    public void actualizarReserva() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        if (reserva != null) {
            mDReserva.actualizarReserva(reserva);
             FacesMessage msj = new FacesMessage("¡Valor actualizado!");
            contexto.addMessage(null, msj);
        }
    }
    



    public ADReserva() {
        creaReserva();
    }

}
