/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import modelo.Huesped;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Stateless
public class ReservacionFacade extends AbstractFacade<Reservacion> {

    @PersistenceContext(unitName = "lacab_v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservacionFacade() {
        super(Reservacion.class);
    }

    public List<Reservacion> reservasActivas() {
        List<Reservacion> habitaciones = null;
        try {
            Query consultaID = em.createNamedQuery("Reservacion.findByEstado");
            consultaID.setParameter("estado", "ACTIVA");
            habitaciones = (List<Reservacion>) consultaID.getResultList();
        } catch (Exception e) {
            return null;
        }
        return habitaciones;
    }

    public int totalVisitas(Huesped huesped) {
        try {
            Query consulta = em.createQuery("SELECT COUNT(r) FROM Reservacion r WHERE r.numHuesped.numHuesped = :numHuesped");
            consulta.setParameter("numHuesped", huesped.getNumHuesped());
            Long visitas = (Long) consulta.getSingleResult(); // Cambiar a Long

            return visitas.intValue();
        } catch (NoResultException e) {
            return 0;
        } 
    }
    
   /*
    public List<Reservacion> obtenerReservasDelDia(Date fechaHoy) {
    TypedQuery<Reservacion> query = em.createNamedQuery("Reservacion.findByFechaHoy", Reservacion.class);
    query.setParameter("fechaHoy", fechaHoy);
    return query.getResultList();
}*/


}
