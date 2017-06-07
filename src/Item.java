

/**
 * Essa é a classe dos itens do nosso jogo.
 * Nesta classe, descrevemos itens que estaram disponiveis nos ambientes para o
 * personagem interagir.
 * @author arlen
 */
public class Item {
    private String descricao;
    private int peso;
    
    public Item (String descricao, int peso) {
        this.descricao = descricao;
        this.peso = peso;
    }
    
    /**
     * @return peso do item 
     */
    public String getDescricao() {
        return this.descricao;
    }
    /**
     * @return peso do item 
     */
    public int getPeso() {
        return this.peso;
    }
}
