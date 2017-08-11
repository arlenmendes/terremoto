package models;




import models.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe que representa o personagem principal do jogo.
 * @author arlen
 */
public class Paciente {
    //Hash que representa os itens coletados pelo jogador
    private HashMap<String, Item> itensColetados;
    //capacidade máxima do jogador. Isso indica quanto, em peso, ele pode
    // carregar de itens.
    private int pesoMaximoItens;
    
    public Paciente() {
        itensColetados = new HashMap<String, Item>();
        pesoMaximoItens = 6;
    }
    /**
     * 
     * @return intens coletados pelo jogador
     */
    public Item getItem(String nome) {
        return this.itensColetados.get(nome);
    }
    /**
     * 
     * @return listagem dos meus itens coletados
     */
    public String ListarMeusItens() {
        String resposta = "";
        
        for(String item : this.itensColetados.keySet())
            resposta += item + " ";
        
        return resposta;
    }
    /**
     * 
     * @return o peso disponivel de itens que o paciente pode carregar
     */
    public int pesoItemDisponivel(){
        int peso = 0;
        
        for(String chave : this.itensColetados.keySet())
            peso += this.itensColetados.get(chave).getPeso();
        return this.pesoMaximoItens - peso;
    }
    
    public List<String> listaItens() {
        List<String> listaItens = new ArrayList<String>();
        
        for(String key : this.itensColetados.keySet()){
            listaItens.add(key);
        }
        
        return listaItens;
    }
    
    /**
     * adiciona um item ao seu inventario
     * @param item 
     */
    public void setItem(Item item) {
        itensColetados.put(item.getNome(), item);
    }
    /**
     * remove o item do inventario do paciente
     * @param nome 
     */
    public void removeItem(String nome) {
        itensColetados.remove(nome);
    }
}
