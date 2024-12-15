/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admos;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import manipuladatos.MDNota;
import manipuladatos.MDReserva;
import modelo.DetalleReservacion;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Nota;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Named(value = "aDReserva")
@SessionScoped
public class ADReserva implements Serializable {

    @EJB
    private MDNota mDNota;

    @EJB
    private MDReserva mDReserva;

    private Reservacion reserva;
    private Huesped numHuesped;
    private List<DetalleReservacion> listaDetalles;
    private Nota nota;
    private Habitacion habSelec;
    private String gestion[] = {"true", "false", "false"};
    private Date fechaHoy = new Date();

    @Inject
    private ADHabitacion aDHabitacion;

    @Inject
    private ADDetalle aDDetalle;
    
    
        private List<Reservacion> filteredReservas;
        public List<Reservacion> getFilteredReservas() {
        return filteredReservas;
    }

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

    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }
    
    

    public String registroReserva() {
        reserva.setEstado("ACTIVA");
        //reserva.setFechaSalida(null);
        mDReserva.insertarReserva(reserva);
        // creaReserva();
        return "reserva_detalles.xhtml?faces-redirect=true";
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

    /*
    public String finalizarReserva(Reservacion reserva) {
        if (reserva != null) {
            reserva.setEstado("FINALIZADA");

            // reserva.setFechaSalida(new Date());
           // registroNota(reserva);
            List<DetalleReservacion> detalles = aDDetalle.getDetalles(reserva);
            // cambiar el estado 
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
             return "nota.xhtml?faces-redirect=true"; 
        } else {
            System.out.println("Error");
             return null;
        }
    }
    

    public void registroNota(Reservacion reserva) {
        //reserva = aDReserva.getReserva(); 
        if (reserva == null) {
            System.out.println("Error: No hay una reservación activa para generar la nota.");
            return;
        }

        nota = new Nota();
        nota.setNumReserva(reserva);
        nota.setFechaEmision(new Date());

        LocalDate fechaLlegada = reserva.getFechaLlegada().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaSalida = reserva.getFechaSalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long diasEstancia = ChronoUnit.DAYS.between(fechaLlegada, fechaSalida);
        System.out.println("dias" + diasEstancia);

        List<DetalleReservacion> detalles2 = aDDetalle.getDetalles(reserva);
        long importeTotal = 0;

        for (DetalleReservacion detalle : detalles2) {
            Habitacion habitacion = detalle.getNumHab();
            if (habitacion != null) {
                importeTotal += habitacion.getTarifa() * diasEstancia;
            }
        }

        nota.setImporteTotal(Math.abs(importeTotal));

        mDNota.generarNota(nota);

        System.out.println("Nota generada correctamente para la reservación: " + reserva.getNumReserva());
        //  creaNota(); 
    }*/

    public void finalizarReserva(Reservacion reserva) {
        if (reserva != null) {
            reserva.setEstado("FINALIZADA");

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
            FacesMessage mensaje = new FacesMessage(
                    "Reservación finalizada correctamente");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            
            System.err.println("Error al finalizar la reservación: reserva nula.");
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

    public String nuevaReserva() {
        creaReserva();
        return "reserva_nueva.xhtml?faces-redirect=true";
    }

    public List<Habitacion> hDisponibles() {
        return aDHabitacion.getDisponibles();
    }
    

    public int visitasTotales(Huesped h) {
        return mDReserva.visitasTotales(h);

    }

    //validaciones
    public void vHab(FacesContext contexto, UIComponent obp, Object valorc) {
        int datovc = (int) valorc;
        UIInput ciu = (UIInput) obp;
        int hdispo = hDisponibles().size();

        if (datovc > hdispo) {
            ciu.setValid(false);
            FacesMessage msj = new FacesMessage("Solo hay " + hdispo + " habitaciones disponibles");
            contexto.addMessage(obp.getClientId(contexto), msj);
        }
    }

    public void vFechas(FacesContext contexto, UIComponent obp, Object valorc) {
        try {

            Date datovc = (Date) valorc;
            LocalDate fechaL = datovc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            UIInput fechaSalida = (UIInput) contexto.getViewRoot().findComponent("formId:fechaSalida");
            Object valorF = fechaSalida.getSubmittedValue();

            if (valorF != null) {
                String valorFString = valorF.toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaS = sdf.parse(valorFString);

                LocalDate fechaS1 = fechaS.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if (fechaL.isAfter(fechaS1)) {
                    ((UIInput) obp).setValid(false);
                    FacesMessage msj = new FacesMessage("La fecha de llegada no puede ser posterior a la de salida.");
                    contexto.addMessage(obp.getClientId(contexto), msj);
                }
            }
        } catch (Exception e) {

            FacesMessage errorMsg = new FacesMessage("Error al validar las fechas: " + e.getMessage());
            contexto.addMessage(obp.getClientId(contexto), errorMsg);
        }
    }

    //gestión de páginas
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

    public ADReserva() {
        creaReserva();
    }

}
