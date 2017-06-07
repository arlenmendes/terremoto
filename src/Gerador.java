/**
 * Esta classe representa um gerador de energia, que extende a item.
 * @author arlen
 */
public class Gerador extends Item {
    
    private boolean ligado;
    
    public Gerador(String nome, String descricao, int peso) {
        super(nome, descricao, peso);
        this.ligado = false;
    }
    
    /**
     * Funcão que liga o gerador
     */
    public void ligar() {
        this.ligado = true;
    }
    
    /**
     * 
     * @return estado do Gerador
     */
    public boolean ligado() {
        return this.ligado;
    }
    
}
