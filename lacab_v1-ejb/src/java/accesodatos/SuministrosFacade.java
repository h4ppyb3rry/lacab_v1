/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Suministros;

/**
 *
 * @author Paula
 */
@Stateless
public class SuministrosFacade extends AbstractFacade<Suministros> {

    @PersistenceContext(unitName = "lacab_v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuministrosFacade() {
        super(Suministros.class);
    }
    
}
