/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import java.io.IOException;
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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    private boolean notaGenerada = false;

    public boolean isNotaGenerada() {
        return notaGenerada;
    }

    public void setNotaGenerada(boolean notaGenerada) {
        this.notaGenerada = notaGenerada;
    }

    public void creaNota() {
        nota = new Nota();
    }

    public List<Nota> getNotas() {
        return mDNota.notas();
    }

    /*

    public void registroNota(Reservacion reserva) {
        FacesContext contexto = FacesContext.getCurrentInstance();
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
        System.out.println("dias" + diasEstancia);

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
        notaGenerada = true;
        FacesMessage msj = new FacesMessage("Puedes acceder a la nota después de finalizar la reservación.");
        contexto.addMessage(null, msj);
        System.out.println("Nota generada correctamente para la reservación: " + reserva.getNumReserva());
        //  creaNota(); 
    }
     */
    public void registroNota(Reservacion reserva) {
        FacesContext contexto = FacesContext.getCurrentInstance();

        if (reserva == null) {
            System.out.println("Error: No hay una reservación activa para generar la nota.");
            return;
        }

        Nota notaExistente = mDNota.buscarNota(reserva);

        if (notaExistente != null) {
            // si ya hay entonces vamos a verla
            try {
                contexto.getExternalContext().redirect("nota.xhtml?reserva=" + reserva.getNumReserva());
            } catch (IOException e) {
                System.err.println("Error al redirigir a la página de visualización: " + e.getMessage());
            }
            return;
        }

        // si no existe, crear y guardar una nueva nota
        nota = new Nota();
        nota.setNumReserva(reserva);
        nota.setFechaEmision(new Date());

        LocalDate fechaLlegada = reserva.getFechaLlegada().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaSalida = reserva.getFechaSalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long diasEstancia = ChronoUnit.DAYS.between(fechaLlegada, fechaSalida);
        System.out.println("Días de estancia: " + diasEstancia);

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
        notaGenerada = true;
        FacesMessage msj = new FacesMessage("Nota generada correctamente.");
        contexto.addMessage(null, msj);
        System.out.println("Nota generada para la reservación: " + reserva.getNumReserva());
    }

    public void cargarNota(Reservacion reservacion) {
        nota = mDNota.buscarNota(reservacion);
        if (nota == null) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "No se encontró una nota para esta reservación", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }

    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public boolean isNotaGenerada(Reservacion item) {
        return mDNota.buscarNota(item) != null;
    }

    public ADNota() {
        creaNota();
    }

}
