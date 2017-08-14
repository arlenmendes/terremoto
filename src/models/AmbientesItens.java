/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Map;

/**
 * Esta classe representa a estruta de dados do sistema do jogo
 * @author arlen
 */
public class AmbientesItens {

    
    private Map<String, Ambiente> ambientes;
    private Map<String, Item> itens;
    private String ambienteInicial;
    
    public AmbientesItens(
            Map<String, Ambiente> ambientes,
            Map<String, Item> itens,
            String ambienteInicial
    ) {
        this.ambientes = ambientes;
        this.itens = itens;
        this.ambienteInicial = ambienteInicial;
    }
    /**
     * @return the ambientes
     */
    public Map<String, Ambiente> getAmbientes() {
        return ambientes;
    }

    /**
     * @param ambientes the ambientes to set
     */
    public void setAmbientes(Map<String, Ambiente> ambientes) {
        this.ambientes = ambientes;
    }

    /**
     * @return the itens
     */
    public Map<String, Item> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(Map<String, Item> itens) {
        this.itens = itens;
    }

    /**
     * @return the ambienteInicial
     */
    public String getAmbienteInicial() {
        return ambienteInicial;
    }
    
    
    
}
