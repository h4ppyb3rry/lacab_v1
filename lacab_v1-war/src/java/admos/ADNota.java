/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.inject.Inject;
import manipuladatos.MDNota;
import manipuladatos.MDReserva;
import modelo.DetalleReservacion;
import modelo.Habitacion;
import modelo.Nota;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Named(value = "aDNota")
@SessionScoped
public class ADNota implements Serializable {

    @EJB
    private MDNota mDNota;
    
    private Reservacion reserva;
    private Nota nota;

    @Inject
    private ADReserva aDReserva;

    @Inject
    private ADDetalle aDDetalle;

    public void creaNota() {
        nota = new Nota();
    }

    public List<Nota> getNotas() {
        return mDNota.notas();
    }

    public void registroNota(Reservacion reserva) {
        // reserva = aDReserva.getReserva(); 
        if (reserva == null) {
            System.out.println("Error: No hay una reservación activa para generar la nota.");
            return;
        }

        nota.setNumReserva(reserva);
        nota.setFechaEmision(new Date());
        

       
        LocalDate fechaLlegada = reserva.getFechaLlegada().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaSalida = reserva.getFechaSalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        
        long diasEstancia = ChronoUnit.DAYS.between(fechaLlegada, fechaSalida);
        System.out.println("dias"+diasEstancia);

        List<DetalleReservacion> detalles = aDDetalle.getDetalles(reserva);
        long importeTotal = 0;

        for (DetalleReservacion detalle : detalles) {
            Habitacion habitacion = detalle.getNumHab();
            if (habitacion != null) {
                importeTotal += habitacion.getTarifa() * diasEstancia;
            }
        }

        nota.setImporteTotal(importeTotal);

        mDNota.generarNota(nota);

        System.out.println("Nota generada correctamente para la reservación: " + reserva.getNumReserva());
        //  creaNota(); 
    }

    /**
     * Creates a new instance of ADNota
     */
    public ADNota() {
        creaNota();
    }

}
