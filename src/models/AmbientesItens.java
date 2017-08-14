/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;
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
    ) {
        this.ambientes = new HashMap<>();
        this.itens = new HashMap<>();
        adicionaDadosEstaticos();
    }
    /**
     * Esta função adiciona os dados estaticos do sistema. Este dados são primordiais para
     * o funcionamento do sistema do jogo a partir da organzação Original.
     */
    private void adicionaDadosEstaticos() {
        //dados estaticos do sistema
        //ambientes
        Ambiente garagem = new Ambiente("Garagem do hospital", "/views/imagens/hospital.jpg", false);
        Ambiente exterior = new Ambiente("exterior do hospital", "/views/imagens/hospital.jpg", true);
        Ambiente salaMaquinas = new Ambiente("Sala das maquinas do Hospital.", "/views/imagens/hospital.jpg", false);
        //itens
        Gerador gerador = new Gerador("gerador","Gerador de energia", 50);
        Item controlePortao = new Item("controle-portao","Controle do Portao da Garagem", 2);
        
        salaMaquinas.adicionarItem(gerador);
        salaMaquinas.adicionarItem(controlePortao);
        
        ambientes.put("garagem", garagem);
        ambientes.put("exterior", exterior);
        ambientes.put("sala de maquinas", salaMaquinas);
        //saidas estaticas
        garagem.setSaida("oeste", new PortaoGaragem(exterior, 2, controlePortao, "Saida Trancada.",  gerador));
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
    public void setAmbiente(String nome, Ambiente ambiente) {
        this.ambientes.put(nome, ambiente);
    }

    /**
     * @return the itens
     */
    public Map<String, Item> getItens() {
        return itens;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.itens.put(item.getNome(), item);
    }

    /**
     * @return the ambienteInicial
     */
    public String getAmbienteInicial() {
        return ambienteInicial;
    }
    
    /**
     * 
     */
    public void setNomeAmbienteInicial(String ai){
        this.ambienteInicial = ai;
    }
    
    
    
}
