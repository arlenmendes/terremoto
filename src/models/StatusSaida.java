package models;

import java.io.Serializable;



/**
 * Esta classe representa o Status das saidas dos ambientes
 * @author arlen
 */
public class StatusSaida implements Serializable {
    private String descricao;
    private Item token;
    private int status;
    
    
    public StatusSaida(Item token, int status, String descricao) {
        this.descricao = descricao;
        this.token = token;
        this.status = status;
    }
    
    /**
     * 
     * @return token da saida
     */
    public Item getToken(){
        return this.token;
    }
    
    /**
     * @return descrição da saida
     */
    
    public String getDescricao() {
        return this.descricao;
    }
    
    /**
     * 
     * @return status da saida
     */
    public int getStatus() {
        return this.status;
    }
    /**
     * Altera o status da saida.
     * @param status
     * @param descricao 
     */
    public void mudarStatus(int status, String descricao) {
        this.status = status;
        this.descricao = descricao;
    }
}
