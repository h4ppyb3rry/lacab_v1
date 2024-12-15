/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Huesped;
import modelo.Reporteinci;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Stateless
public class ReporteinciFacade extends AbstractFacade<Reporteinci> {

    @PersistenceContext(unitName = "lacab_v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteinciFacade() {
        super(Reporteinci.class);
    }
    
    
    public int contarIncidenciasPorHuesped(Huesped h) {
    try {
        Query consulta = em.createQuery(
            "SELECT COUNT(ri) " +
            "FROM Reporteinci ri " +
            "JOIN ri.numReserva r " +
            "WHERE r.numHuesped.numHuesped = :numHuesped"
        );
        consulta.setParameter("numHuesped", h.getNumHuesped());
        Long totalIncidencias = (Long) consulta.getSingleResult();

        return totalIncidencias.intValue();
    } catch (NoResultException e) {
        return 0;
    }
}

    
    
}
