/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.HuespedFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Huesped;

/**
 *
 * @author Paula
 */
@Stateless
@LocalBean
public class MDHuesped {

    @EJB
    private HuespedFacade huespedF;

    public void insertarHuesped(Huesped h) {
        huespedF.create(h);
    }

    public List<Huesped> huespedes() {
        return huespedF.findAll();
    }

    public Huesped buscarHuespedPorId(Integer id) {
        return huespedF.find(id);
    }

}
