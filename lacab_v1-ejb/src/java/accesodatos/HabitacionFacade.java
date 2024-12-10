/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Habitacion;

/**
 *
 * @author Paula
 */
@Stateless
public class HabitacionFacade extends AbstractFacade<Habitacion> {

    @PersistenceContext(unitName = "lacab_v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HabitacionFacade() {
        super(Habitacion.class);
    }
    

    public List<Habitacion> habitacionesDisponibles() {
        List<Habitacion> habitaciones = null;
        try {
            Query consultaID = em.createNamedQuery("Habitacion.findByEstado");
            consultaID.setParameter("estado", "DISPONIBLE");
            habitaciones = (List<Habitacion>) consultaID.getResultList();
        } catch (Exception e) {
            return null;
        }
        return habitaciones;
    }

}
