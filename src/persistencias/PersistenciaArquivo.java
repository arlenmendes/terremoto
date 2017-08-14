/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencias;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import models.Ambiente;
import models.AmbientesItens;
import models.Gerador;
import models.Item;
import models.PortaoGaragem;
import models.Saida;

/**
 *
 * @author arlen
 */
public class PersistenciaArquivo implements Serializable{
    
    /**
     * Esta função lê o arquivo que contem os ambiente dos ambientes e dos itens do sistema.
     * Ela é uma das responsáveis por tornar o design do jogo dinâmico.
 Observação: Os ambientes Garagem, Exterior e Sala de Maquinas e os itens controle-potao
 e Gerador não podem ser removidos, pois fazem parte da estrutura original do jogo.
 Ela retorna uma instância da classe AmbientesItens com os ambiente necessários
     * @return AmbientesItens
     */
    
    public AmbientesItens buscarAmbientes() {
        
        HashMap<String, Ambiente> ambientes = new HashMap<>();
        HashMap<String, Item> itens = new HashMap<>();
        String ambienteInicial = "";
        
        //dados estaticos do sistema
        //ambientes
        Ambiente garagem = new Ambiente("Garagem do hospital", "/views/imagens/hospital.jpg", false);
        Ambiente exterior = new Ambiente("exterior do hospital", "/views/imagens/hospital.jpg", true);
        Ambiente salaMaquinas = new Ambiente("Sala das maquinas do Hospital.", "/views/imagens/hospital.jpg", false);
        //itens
        Gerador gerador = new Gerador("gerador","Gerador de energia", 50);
        Item controlePortao = new Item("controle-portao","Controle do Portao da Garagem", 2);
        
        salaMaquinas.adicionarItem(gerador);
        salaMaquinas.adicionarItem(controlePortao);
        
        ambientes.put("garagem", garagem);
        ambientes.put("exterior", exterior);
        ambientes.put("sala de maquinas", salaMaquinas);
        //saidas estaticas
        garagem.setSaida("oeste", new PortaoGaragem(exterior, 2, controlePortao, "Saida Trancada.",  gerador));
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/arquivos/ambientes-itens.txt"));
            
            // ler todos os ambientes ate encontrar o ponto de parada
            String linha = br.readLine();
            while (!linha.equals("#ambientes")) {
                String[] ambiente = linha.split(";");
                
                ambientes.put(ambiente[0], new Ambiente(ambiente[1], ambiente[2], Boolean.getBoolean(ambiente[3])));
                
                linha = br.readLine();
            }
            
            //ler o ambiente inicial
            ambienteInicial = br.readLine();
            br.readLine();
            //ler todos os itens ate encontrar o ponto de parada
            linha = br.readLine();
            while (!linha.equals("#itens")) {
                String[] item = linha.split(";");
                
                itens.put(item[0], new Item(item[0], item[1], Integer.parseInt(item[2])));
                linha = br.readLine();
            }
            
            //ler todas as saidas ate encontrar o ponto de parada
            linha = br.readLine();
            while (!linha.equals("#saidas")) {                
                String[] saida = linha.split(";");
                
                ambientes.get(saida[0]).setSaida(saida[1], new Saida(ambientes.get(saida[2]), Integer.parseInt(saida[3]), itens.get(saida[4]), saida[5]));
                
                linha = br.readLine();
            }
            
            //ler todos itens a serem adicionados em ambientes ate encontrar o ponto de parada
            linha = br.readLine();
            while (!linha.equals("#itens-ambientes")) {                
                String[] item = linha.split(";");
                ambientes.get(item[0]).adicionarItem(itens.get(item[1]));
                linha = br.readLine();
            }
            
            
        } catch (Exception ex) {
            System.out.println("erro ao ler aquivo.\n" + ex.getMessage());
        }
        
        
        return new AmbientesItens(ambientes, itens, ambienteInicial);
    }
}
