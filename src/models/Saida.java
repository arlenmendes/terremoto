package models;


import models.Ambiente;



/**
 * Esta classe representa as saidas dos Ambientes do hospital.
 * @author arlen
 */
public class Saida {
    private Ambiente ambiente;
    private StatusSaida status;
    
    public Saida(Ambiente ambiente, String status, Item token, String descricao) {
        this.ambiente = ambiente;
        this.status = new StatusSaida(token, status, descricao);
    }
    /**
     * 
     * @return ambinte da saida especificada
     */
    public Ambiente getAmbiente() {
        return this.ambiente;
    }
    
    /**
     * 
     * @return status da saida
     */
    public StatusSaida getStatusSaida() {
        return this.status;
    }
    
    public void abrir() {
        
    }
}
