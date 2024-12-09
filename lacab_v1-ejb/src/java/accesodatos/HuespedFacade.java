/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Huesped;

/**
 *
 * @author Paula
 */
@Stateless
public class HuespedFacade extends AbstractFacade<Huesped> {

    @PersistenceContext(unitName = "lacab_v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HuespedFacade() {
        super(Huesped.class);
    }
    
}
