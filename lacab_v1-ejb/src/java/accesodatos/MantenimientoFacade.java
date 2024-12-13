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
import modelo.Mantenimiento;

/**
 *
 * @author Paula
 */
@Stateless
public class MantenimientoFacade extends AbstractFacade<Mantenimiento> {

    @PersistenceContext(unitName = "lacab_v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MantenimientoFacade() {
        super(Mantenimiento.class);
    }
    
     public List<Mantenimiento> reportesActivos() {
        List<Mantenimiento> reportesAct = null;
        try {
            Query consultaID = em.createNamedQuery("Mantenimiento.findByEstado");
            consultaID.setParameter("estado", "ACTIVO");
            reportesAct = (List<Mantenimiento>) consultaID.getResultList();
        } catch (Exception e) {
            return null;
        }
        return reportesAct;
    }
    
}
