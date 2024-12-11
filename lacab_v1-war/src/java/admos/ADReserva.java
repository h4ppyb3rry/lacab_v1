/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import manipuladatos.MDReserva;
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

    public ADReserva() {
        creaReserva();
    }

}
