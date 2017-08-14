/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos.design;

import java.util.List;
import java.util.Set;
import models.AmbientesItens;
import persistencias.AmbientesItensDAO;

/**
 *
 * @author arlen
 */
public class DesignService {
    private AmbientesItensDAO ambientesItensDAO;
    
    public DesignService() {
        ambientesItensDAO = new AmbientesItensDAO();
    }
    /**
     * Esta funcao retorna todos os nomes dos ambientes
     * @return Set
     */
    public Set<String> getNomeTodosAmbientes() {
        return this.ambientesItensDAO.getAmbientesEItens().getAmbientes().keySet();
    }
    
    public List<String> getNomeAmbientesEstaticos() {
        return this.ambientesItensDAO.getAmbientesEItens().getNomeAmbientesEstaticos();
    }
}
