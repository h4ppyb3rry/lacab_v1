/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.DetalleReservacion;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Stateless
public class DetalleReservacionFacade extends AbstractFacade<DetalleReservacion> {

    @PersistenceContext(unitName = "lacab_v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleReservacionFacade() {
        super(DetalleReservacion.class);
    }
    
    
    public List<DetalleReservacion> obtenerDetalles(Reservacion reserva) {
        return em.createQuery("SELECT d FROM DetalleReservacion d WHERE d.numReserva = :reserva", DetalleReservacion.class)
                .setParameter("reserva", reserva)
                .getResultList();
    }
    
}
