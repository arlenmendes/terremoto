package controllers;

import models.Ambiente;
import models.Gerador;
import models.Item;
import models.PortaoGaragem;
import models.Saida;

/**
 *
 * @author arlen
 */
public class AmbienteController {
    
    //cria itens
    private Item chave, bisturi, avental, controlePortao, siringa, soro, peDeCabra;
    private Gerador gerador;
    
    //status das portas
    private final String liberada = "liberada";
    private final String trancada = "trancada";
    private final String obstruida = "obstruida";
    
    //descriçoes das saidas
    private final String liberadaDescricao = "Porta Liberada.";
    private final String trancadaDescricao = "Porta Trancada.";
    private final String obstruidaDescricao = "Porta Obstruida Por Destroços Do Predio.";
    /**
     * Prepara os ambientes, com suas saidas e seus itens, e retorna o ambiente inicial.
     * @return Ambiente
     */
    public Ambiente prepararAmbientes() {
        Ambiente exterior, saguao, garagem, recepicaoGeral, triagem, corredor, recepcaoUti, uti, salaEsperaUti, recepcaoCti, cti, salaEsperaCti, salaFuncionarios, salaLimpeza, almoxerifado, salaSeguranca, salaMaquinas;
        
        //cria itens
        chave = new Item("chave", "chave da porta da recepção para a garagem", 2);
        bisturi = new Item("bisturi","Bisturi para cirurgias", 2);
        gerador = new Gerador("gerador","Gerador de energia", 50);
        controlePortao = new Item("controle-portao","Controle do Portao da Garagem", 2);
        peDeCabra = new Item("pe-de-cabra","Pe de Cabra de ferro", 6);
        
        
        // cria os ambientes
        exterior = new Ambiente("exterior do hospital", "/views/imagens/hospital.jpg");
        saguao = new Ambiente("saguao do Hospital", "/views/imagens/hospital.jpg");
        garagem = new Ambiente("Garagem do hospital", "/views/imagens/hospital.jpg");
        recepicaoGeral = new Ambiente("Recepição Geral do Hospital", "/views/imagens/hospital.jpg");
        triagem = new Ambiente("triagem de pacientes do hospital", "/views/imagens/hospital.jpg");
        corredor = new Ambiente("Coredor Geral", "/views/imagens/hospital.jpg");
        recepcaoUti = new Ambiente("Recepição da UTI.", "/views/imagens/hospital.jpg");
        recepcaoCti = new Ambiente("Recepição da CTI.", "/views/imagens/hospital.jpg");
        uti = new Ambiente("Sala da UTI", "/views/imagens/hospital.jpg");
        cti = new Ambiente("Sala da CTI", "/views/imagens/hospital.jpg");
        salaEsperaUti = new Ambiente("Sala de espera da UTI.", "/views/imagens/hospital.jpg");
        salaEsperaCti = new Ambiente("Sala de espera da CTI.", "/views/imagens/hospital.jpg");
        salaFuncionarios = new Ambiente("Sala exclusiva para funcionarios.", "/views/imagens/hospital.jpg");
        salaLimpeza = new Ambiente("Sala de limpeza.", "/views/imagens/hospital.jpg");
        almoxerifado = new Ambiente("Almoxerifado", "/views/imagens/hospital.jpg");
        salaSeguranca = new Ambiente("Sala da Segurança", "/views/imagens/hospital.jpg");
        salaMaquinas = new Ambiente("Sala das maquinas do Hospital.", "/views/imagens/hospital.jpg");
        
        
        // inicializa as saidas dos ambientes
        saguao.setSaida("sul", new Saida(exterior, obstruida, null, obstruidaDescricao));
        saguao.setSaida("norte", new Saida(recepicaoGeral, liberada, null, liberadaDescricao));
        
        recepicaoGeral.setSaida("sul", new Saida(saguao, liberada, null, liberadaDescricao));
        recepicaoGeral.setSaida("oeste", new Saida(garagem, trancada, chave, trancadaDescricao));
        recepicaoGeral.setSaida("norte", new Saida(triagem, liberada, null, liberadaDescricao));
        
        garagem.setSaida("leste", new Saida(recepicaoGeral, trancada, chave, trancadaDescricao));
        garagem.setSaida("oeste", new PortaoGaragem(exterior, trancada, controlePortao, trancadaDescricao));
        
        triagem.setSaida("sul", new Saida(recepicaoGeral, liberada, null, liberadaDescricao));
        triagem.setSaida("norte", new Saida(corredor, liberada, null, liberadaDescricao));
        
        corredor.setSaida("norte", new Saida(recepcaoCti, liberada, null, liberadaDescricao));
        corredor.setSaida("leste", new Saida(salaFuncionarios, liberada, null, liberadaDescricao));
        corredor.setSaida("sul", new Saida(triagem, trancada, peDeCabra, trancadaDescricao));
        corredor.setSaida("oeste", new Saida(recepcaoUti, liberada, null, liberadaDescricao));
        
        salaFuncionarios.setSaida("norte", new Saida(salaLimpeza, liberada, null, liberadaDescricao));
        salaFuncionarios.setSaida("leste", new Saida(almoxerifado, liberada, null, liberadaDescricao));
        salaFuncionarios.setSaida("sul", new Saida(salaSeguranca, liberada, null, liberadaDescricao));
        salaFuncionarios.setSaida("oeste", new Saida(corredor, liberada, null, liberadaDescricao));
        
        salaLimpeza.setSaida("sul", new Saida(salaFuncionarios, liberada, null, liberadaDescricao));
        
        almoxerifado.setSaida("oeste", new Saida(salaFuncionarios, liberada, null, liberadaDescricao));
        
        salaSeguranca.setSaida("norte", new Saida(salaFuncionarios, liberada, null, liberadaDescricao));
        salaSeguranca.setSaida("sul", new Saida(salaMaquinas, liberada, null, liberadaDescricao));
        
        salaMaquinas.setSaida("norte", new Saida(salaSeguranca, liberada, null, liberadaDescricao));
        
        recepcaoCti.setSaida("norte", new Saida(salaEsperaCti, liberada, null, liberadaDescricao));
        recepcaoCti.setSaida("leste", new Saida(cti, liberada, null, liberadaDescricao));
        recepcaoCti.setSaida("sul", new Saida(corredor, liberada, null, liberadaDescricao));
        
        cti.setSaida("oeste", new Saida(recepcaoCti, liberada, null, liberadaDescricao));
        
        salaEsperaCti.setSaida("sul", new Saida(recepcaoCti, liberada, null, liberadaDescricao));
        
        recepcaoUti.setSaida("norte", new Saida(salaEsperaUti, liberada, null, liberadaDescricao));
        recepcaoUti.setSaida("leste", new Saida(corredor, liberada, null, liberadaDescricao));
        recepcaoUti.setSaida("oeste", new Saida(uti, liberada, null, liberadaDescricao));
        
        uti.setSaida("leste", new Saida(recepcaoUti, liberada, null, liberadaDescricao));
        
        salaEsperaUti.setSaida("sul", new Saida(recepcaoUti, liberada, null, liberadaDescricao));
        
        // adiciona itens aos ambientes
        salaSeguranca.setItem(chave.getNome(), chave);
        salaSeguranca.setItem(controlePortao.getNome(), controlePortao);
        
        almoxerifado.setItem(peDeCabra.getNome(), peDeCabra);
        
        salaMaquinas.setItem(gerador.getNome(), gerador);
        

        return uti;  // o jogo comeca na uti
    }
    /**
     * Retorna o gerador de energia do jogo.
     * 
     * @Return Gerador
     * 
     */
    
    public Gerador getGerador() {
        
        return this.gerador;
    }
    
    /**
     * Liga o gerador de energia do jogo.
     * 
     */
    public void ligarGerador() {
        this.gerador.ligar();
    }
    
    /**
     * Retorna o status de Obstruida.
     * 
     * @Return String
     * 
     */
    public String getStatusObstruida() {
        return this.liberada;
    }
    
    /**
     * Retorna o status de Liberada.
     * 
     * @Return String
     * 
     */
    public String getStatusLiberada() {
        return this.liberada;
    }
    
    /**
     * Retorna o status de Trancada.
     * 
     * @Return String
     * 
     */
    public String getStatusTrancada() {
        return this.trancada;
    }
    
    /**
     * Retorna a descricao do status de Liberada.
     * 
     * @Return String
     * 
     */
    public String getLiberadaDescricao() {
        return this.liberadaDescricao;
    }
    
    /**
     * Retorna a descricao do status de Obstruida.
     * 
     * @Return String
     * 
     */
    public String getObstruidaDescricao() {
        return this.obstruidaDescricao;
    }
    
    /**
     * Retorna a descricao do status de Trancada.
     * 
     * @Return String
     * 
     */
    public String getTrancadaDescricao() {
        return this.trancadaDescricao;
    }
}
