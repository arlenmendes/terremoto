



/**
 * Essa é a classe dos itens do nosso jogo.
 * Nesta classe, descrevemos itens que estaram disponiveis nos ambientes para o
 * personagem interagir.
 * @author arlen
 */
public class Item {
    private String nome;
    private String descricao;
    private int peso;
    
    public Item (String nome, String descricao, int peso) {
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
    }
    
    /**
     * @return nome do item
     */
    public String getNome() {
        return this.nome;
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
