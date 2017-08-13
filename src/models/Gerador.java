package models;


import java.io.Serializable;
import models.Item;



/**
 * Esta classe representa um gerador de energia, que extende a item.
 * @author arlen
 */
public class Gerador extends Item  implements Serializable{
    
    private boolean ligado;
    
    private int tempoLigado;
    private int maximoTempoLigado = 6;
    
    public Gerador(String nome, String descricao, int peso) {
        super(nome, descricao, peso);
        this.ligado = false;
    }
    
    /**
     * Funcão que liga o gerador
     */
    public void ligar() {
        this.tempoLigado = 0;
        this.ligado = true;
    }
    
    /**
     * 
     * @return estado do Gerador
     */
    public boolean ligado() {
        return this.ligado;
    }
    
    /**
     * Função que passa o tempo do gerador ligado.
     */
    
    public void passarTempo() {
        this.tempoLigado++;
    }
    
    public boolean haTempoDisponivel() {
        if(this.tempoLigado <= maximoTempoLigado)
            return true;
        return false;
    }
}
