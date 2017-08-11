package models;




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
 * guarda uma referencia para o ambiente vizinho.
 * 
 * @author  Arlen Mendes
 * @version 2011.07.31 (2017.05.16)
 */
public class Ambiente  {
    private String descricao;
    private HashMap<String, Saida> saidas;
    private HashMap<String, Item> itens;
    private String imagem;

    /**
     * Cria um ambiente com a "descricao" passada e o caminho da imagem que 
     * será mostrada para o usuário. Inicialmente, ele nao tem saidas e nem
     * Itens. "descricao" eh algo como "Sala de Espera" ou
     * "Recepção"
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao, String imagem)  {
        this.descricao = descricao;
        saidas = new HashMap<String, Saida>();
        itens = new HashMap<String, Item>();
        this.imagem = imagem;
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
     * Esta função adiciona uma referência do item ao ambiente.
     * @param nome informa o nome do item
     * @param item a ser adicionado ao ambiente
     */
    public void adicionarItem(Item item) {
        itens.put(item.getNome(), item);
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
     * Retorna uma String com os nomes dos itens disponiveis.
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
     * Função que retorna uma Lista com o nome dos itens contidos no ambiente.
     * @return ArrayList de Itens
     */
    
    public List<String> getListaItens() {
        List<String> listaItens = new ArrayList<String>();
        
        for(String key : this.itens.keySet()){
            listaItens.add(key);
        }
        
        return listaItens;
    }

    /**
     * @return the imagem
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
