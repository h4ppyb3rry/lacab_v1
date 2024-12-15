/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.SuministrosFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Suministros;

/**
 *
 * @author Paula
 */
@Stateless
@LocalBean
public class MDSuministros {

    @EJB
    private SuministrosFacade suministrosF;
    
    
     public void agregarSuministro(Suministros s) {
        suministrosF.create(s);
    }

    public List<Suministros> suministros() {
        return suministrosF.findAll();
    }

    public void editarSuministro(Suministros s) {
        suministrosF.edit(s);
    }
    
    public Suministros buscarSumPorId(Integer id) {
        return suministrosF.find(id);
    }

    
}
