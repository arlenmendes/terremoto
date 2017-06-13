


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "Terremoto".
 * "Terremoto" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Arlen Mendes
 * @version 2011.07.31 (2017.05.16)
 */
public class Ambiente  {
    private String descricao;
    private HashMap<String, Saida> saidas;
    private HashMap<String, Item> itens;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "Sala de Espera" ou
     * "Recepção"
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao)  {
        this.descricao = descricao;
        saidas = new HashMap<>();
        itens = new HashMap<>();
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um ambiente
     * @param direcao informa a direção da saida
     * @param saida informa a saida dessa direcao
     */
    public void setSaida(String direcao, Saida saida) {
        saidas.put(direcao, saida);
    }
    
    /**
     * Define os itens do ambiente.
     * @param nome informa o nome do item
     * @param item a ser adicionado ao ambiente
     */
    public void setItem(String nome, Item item) {
        itens.put(nome, item);
    }
    /**
     * Buscao item pelo nome.
     * @param nome nome do item a ser buscado
     * @return item ou null caso o item informado nao exista;
     */
    public Item getItem(String nome) {
        if(itens.containsKey(nome))
            return itens.get(nome);
        return null;
    }
    
    /**
     * Remove item pelo nome
     * @param nome nome do item a ser removido
     */
    public void removeItem(String nome) {
        itens.remove(nome);
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao() {
        return descricao;
    }
    /**
     * @param direcao busca uma saida para determinada direcao
     * @return A saida da direção informada.
     */
    public Saida getSaida(String direcao) {
        if(saidas.containsKey(direcao))
            return saidas.get(direcao);
        return null;
    }
    
    /**
     * @return As direções disponiveis.
     */
    
    public String getSaidas() {
        String resposta = "";
            for(String saida : saidas.keySet()){
                resposta = resposta + saida + " ";
            }
        return resposta;
    }
    
    /**
     * @return Os itens disponiveis no ambiente.
     */
    public String getItens() {
        String resposta = "";
            for(String item : this.itens.keySet()){
                resposta = resposta + item + " ";
            }
            
        if(resposta.equals(""))
            return "nenhum";
        return resposta;
    }
    /**
     * @return A descricao complete do ambiente, com suas saidas e seus itens.
     */
    public String getDescricaoLonga() {
        
        String descricaoLonga = "";
        
        descricaoLonga += this.descricao;
        descricaoLonga += "\n" + "Este ambiente conta com saidas para: ";
        descricaoLonga += "\n" + getSaidas();
        descricaoLonga += "\n" + "Este ambiente conta com os seguintes itens: ";
        descricaoLonga += "\n" + getItens();
        
        return descricaoLonga;
    }

}
