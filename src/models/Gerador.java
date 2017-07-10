

/**
 * Esta classe representa um gerador de energia, que extende a item.
 * @author arlen
 */
public class Gerador extends Item {
    
    private boolean ligado;
    
    private int tempoLigado = 0;
    private int maximoTempoLigado = 6;
    
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
    
    public void passarTempo() {
        this.tempoLigado++;
    }
    
    public boolean haTempoDisponivel() {
        if(this.tempoLigado <= maximoTempoLigado)
            return true;
        return false;
    }
}
