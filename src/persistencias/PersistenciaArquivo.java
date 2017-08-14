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
     * @return AmbientesItens
     */
    
    public AmbientesItens buscarAmbientes() {
        
        AmbientesItens ambientesEItens = new AmbientesItens();
        
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/arquivos/ambientes-itens.txt"));
            
            // ler todos os ambientes ate encontrar o ponto de parada
            String linha = br.readLine();
            while (!linha.equals("#ambientes")) {
                String[] ambiente = linha.split(";");
                
                ambientesEItens.setAmbiente(ambiente[0], new Ambiente(ambiente[1], ambiente[2], Boolean.getBoolean(ambiente[3])));
                
                linha = br.readLine();
            }
            
            //ler o ambiente inicial
            ambientesEItens.setNomeAmbienteInicial(br.readLine());
            br.readLine();
            //ler todos os itens ate encontrar o ponto de parada
            linha = br.readLine();
            while (!linha.equals("#itens")) {
                String[] item = linha.split(";");
                
                ambientesEItens.setItem(new Item(item[0], item[1], Integer.parseInt(item[2])));
                linha = br.readLine();
            }
            
            //ler todas as saidas ate encontrar o ponto de parada
            linha = br.readLine();
            while (!linha.equals("#saidas")) {                
                String[] saida = linha.split(";");
                
                ambientesEItens.getAmbientes().get(saida[0]).setSaida(saida[1], new Saida(ambientesEItens.getAmbientes().get(saida[2]), Integer.parseInt(saida[3]), ambientesEItens.getItens().get(saida[4]), saida[5]));
                
                linha = br.readLine();
            }
            
            //ler todos itens a serem adicionados em ambientes ate encontrar o ponto de parada
            linha = br.readLine();
            while (!linha.equals("#itens-ambientes")) {                
                String[] item = linha.split(";");
                ambientesEItens.getAmbientes().get(item[0]).adicionarItem(ambientesEItens.getItens().get(item[1]));
                linha = br.readLine();
            }
            
            
        } catch (Exception ex) {
            System.out.println("erro ao ler aquivo.\n" + ex.getMessage());
        }
        
        
        return ambientesEItens;
    }
}
