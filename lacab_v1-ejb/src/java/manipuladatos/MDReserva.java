/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.ReservacionFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.DetalleReservacion;
import modelo.Huesped;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Stateless
@LocalBean
public class MDReserva {

    @EJB
    private ReservacionFacade reservacionF;

    public void insertarReserva(Reservacion r) {
        reservacionF.create(r);
    }

    public List<Reservacion> reservaciones() {
        return reservacionF.findAll();
    }

    public void actualizarReserva(Reservacion r) {
        reservacionF.edit(r);
    }

    public List<Reservacion> reservasActivas() {
        return reservacionF.reservasActivas();
    }

    public int visitasTotales(Huesped h) {
        if (h == null || h.getNumHuesped() == null) {
            return 0;
        }
        return reservacionF.totalVisitas(h);
    }
}
