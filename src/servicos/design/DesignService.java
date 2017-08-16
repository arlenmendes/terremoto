/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos.design;

import java.util.List;
import java.util.Set;
import models.Ambiente;
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
    
    public Set<String> getNomeTodosItens() {
        return this.ambientesItensDAO.getAmbientesEItens().getItens().keySet();
    }
    
    public Ambiente getAmbientePorNome(String nome) {
        return this.ambientesItensDAO.getAmbientesEItens().getAmbientes().get(nome);
    }
    
    public void salvarAmbiente(String nome, Ambiente ambiente) {
        System.out.println("tudo OK");
    }
}
