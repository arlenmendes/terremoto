

/**
 * Esta classe representa o Status das saidas dos ambientes
 * @author arlen
 */
public class StatusSaida {
    private String descricao;
    private Item token;
    private String status;
    
    
    public StatusSaida(Item token, String status, String descricao) {
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
    
    public String getDescricao() {
        return this.descricao;
    }
    
    /**
     * 
     * @return status da saida
     */
    public String getLiberada() {
        return this.status;
    }
    /**
     * Altera o status da saida.
     * @param status
     * @param descricao 
     */
    public void mudarStatus(String status, String descricao) {
        this.status = status;
        this.descricao = descricao;
    }
}
