package models;


import models.Ambiente;



/**
 * Esta classe representa as saidas dos Ambientes do hospital.
 * @author arlen
 */
public class Saida {
    private Ambiente ambiente;
    protected StatusSaida status;
    
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
    public String getStatusSaida() {
        return this.status.getStatus();
    }
    
    /**
     * @return Descrição do Status da Saida
     */
    
    public String getDescricaoStatusSaida() {
        return this.status.getDescricao();
    }
    /**
     * Esta função muda os dados do status da saida do ambiente.
     * @param status
     * @param descricao
     */
    public void mudarStatusDaSaida(String status, String descricao) {
        this.status.mudarStatus(status, descricao);
    }
    /**
     * @return nome do token que libera esta saida
     */
    public String getNomeToken() {
        return this.status.getToken().getNome();
    }
    
    public boolean liberarSaida(Item item){
        if(item != null)
            if(item == this.status.getToken())
                return true;
        return false;
        
    }
}
