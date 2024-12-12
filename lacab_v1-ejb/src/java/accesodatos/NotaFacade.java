/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Nota;
import modelo.Reservacion;

/**
 *
 * @author Paula
 */
@Stateless
public class NotaFacade extends AbstractFacade<Nota> {

    @PersistenceContext(unitName = "lacab_v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotaFacade() {
        super(Nota.class);
    }

    public Nota notaReserva(Reservacion r) {
        Nota nota = null;
        try {
            Query consulta = em.createQuery("SELECT n FROM Nota n WHERE n.numReserva.numReserva = :numReserva");
            consulta.setParameter("numReserva", r.getNumReserva());
            nota = (Nota) consulta.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nota;
    }

}
