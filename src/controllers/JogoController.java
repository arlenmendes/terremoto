/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import configuracao.Textos;
import models.Ambiente;
import models.Item;
import models.Paciente;
import models.PortaoGaragem;
import models.Saida;
import views.Alerta;
import views.AmbienteView;
import views.JogoView;

/**
 * Classe que ira gerenciar todas as açoes do jogo.
 * @author arlen
 */
public class JogoController {
    private JogoView jogoView;
    private Paciente paciente;
    private Analisador analisador;
    private boolean gameOver;
    //Variavel para guardar o ambiente atual
    private Ambiente ambienteAtual;
    //Controller para trabalhar os ambientes
    private AmbienteController ambienteController;
    
    
    public JogoController() {
        gameOver = false;
        paciente = new Paciente();
        analisador = new Analisador();
        ambienteController = new AmbienteController();
        ambienteAtual = ambienteController.prepararAmbientes();
    }
    
    public Ambiente getAmbienteAtual() {
        return this.ambienteAtual;
    }
    
    public void mudarAmbiente(Comando comando) {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            Alerta.mensagem("Ir pra onde?");
            return;
        }
        
        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Saida saida = ambienteAtual.getSaida(direcao);
        //verifica se a direção informada existe
        if(saida != null){
            //verifica se a saida esta liberada, obstruida ou trancada
            if(saida.getStatusSaida().getStatus().equals(ambienteController.getStatusLiberada())){
                //verifica se o gerador está ligado
                //a cada movimentação entre ambientes, o gerador "consome combustivel"
                if(ambienteController.getGerador().ligado())
                    ambienteController.getGerador().passarTempo();
                ambienteAtual = saida.getAmbiente();
            } else if(saida.getStatusSaida().getStatus().equals(ambienteController.getStatusObstruida())) {
                
                Alerta.mensagem("Ops! Você não pode passar por aqui " + saida.getStatusSaida().getDescricao());
                
            } else if(saida.getStatusSaida().getStatus().equals(ambienteController.getStatusTrancada())) {
                //verifica se a saida informada é o portao da garagem
                if(ambienteAtual.getSaida(direcao) instanceof PortaoGaragem){
                    //verifica se ainda há combustivel no gerador
                    if(ambienteController.getGerador().haTempoDisponivel()) {
                        //verifica se o jogador possui o token necessario para abrir a porta
                        if(paciente.getItem(saida.getStatusSaida().getToken().getNome()) != null) {
                            saida.getStatusSaida().mudarStatus(ambienteController.getStatusLiberada(), ambienteController.getLiberadaDescricao());
                            Alerta.mensagem("Você desbloqueu esta saida");
                            ambienteAtual = saida.getAmbiente();
                            //verifica se o gerador está ligado
                            //a cada movimentação entre ambientes, o gerador "consome combustivel"
                            if(ambienteController.getGerador().ligado())
                                ambienteController.getGerador().passarTempo();
                        } else {
                            Alerta.mensagem(saida.getStatusSaida().getDescricao() + " Você precisa de um(a) " + saida.getStatusSaida().getToken().getNome() + " para abrir");
                        }
                    }
                } else {
                    //verifica se o jogador possui o token necessario para abrir a porta
                    if(paciente.getItem(saida.getStatusSaida().getToken().getNome()) != null) {
                        saida.getStatusSaida().mudarStatus(ambienteController.getStatusLiberada(), ambienteController.getLiberadaDescricao());
                        Alerta.mensagem("Você desbloqueou esta saida... mudando de  ambiente.");
                        ambienteAtual = saida.getAmbiente();
                        
                        //verifica se o gerador está ligado
                        //a cada movimentação entre ambientes, o gerador "consome combustivel"
                        if(ambienteController.getGerador().ligado())
                            ambienteController.getGerador().passarTempo();
                    } else {
                        Alerta.mensagem("Porta emperrada. Você precisa de " + saida.getStatusSaida().getToken().getNome() + " para abrir.");
                    }
                }
            }
            
        } else {
            Alerta.mensagem("Direção invalida ");
        }
    }
    
    public void executarComando(String dados) {
        Comando comando = analisador.pegarComando(dados);
        processarComando(comando);
    }
    
    private void processarComando(Comando comando) {
        if(comando.ehDesconhecido()) {
            Alerta.mensagem("Eu nao entendi o que voce disse...");
            return;
        }
        
        String palavraDeComando = comando.getPalavraDeComando();
        switch (palavraDeComando) {
            case "ajuda":
                ajuda();
                break;
            case "ir":
                mudarAmbiente(comando);
                break;
            case "coletar":
                coletarItem(comando);
                break;
            case "desfazer":
                desfazerItem(comando);
                break;
            case "meus-itens":
                Alerta.mensagem(paciente.ListarMeusItens());
                break;
            case "ligar":
                ligarGerador(comando);
                break;
            default:
                break;
        }
        
        if(ambienteAtual.getDescricao().equals("exterior do hospital")){
            Alerta.mensagem("Parabéns, você conseguiu sair do Hospital\nVocê venceu!!!!!");
        } else if(ambienteController.getGerador().ligado()){
            if(!ambienteController.getGerador().haTempoDisponivel()) {
                Alerta.mensagem("GAME OVER\nO gerador Acabou a Energia e você não pode mais\nsair do Hospital.");
                gameOver = true;
            }
            
                
        }
    }
    
    private void ajuda() {
        Alerta.mensagem("Você está em um hospital. Você precisa encontrar uma maneira de sair.");
        String comandos = "Comandos disponíveis: \n";
        for(String c : analisador.comandosDisponiveis()){
            comandos += c + ", ";
        }
        Alerta.mensagem(comandos);
    }
    
    /**
     * funcao que remove o item do ambiente e coloca no inventario do paciente
     * @param comando
     */
    private void coletarItem(Comando comando) {
        if(comando.temSegundaPalavra()){
            Item item = ambienteAtual.getItem(comando.getSegundaPalavra());
            if(item != null){
                if(paciente.pesoItemDisponivel() >= item.getPeso()){
                    ambienteAtual.removeItem(comando.getSegundaPalavra());
                    paciente.setItem(item);
                    Alerta.mensagem("Item adicionado ao seu inventario.");
                } else {
                    Alerta.mensagem("Espaço insuficiente no seu inventário");
                }
            } else {
                Alerta.mensagem("Não existe este item no ambiente");
            }
        } else {
            Alerta.mensagem("coletar o que?");
        }
    }
    
    /**
     * Funcao que tira um item do seu inventario e o coloca no ambiente.
     * @param comando 
     */
    private void desfazerItem(Comando comando) {
        if(comando.temSegundaPalavra()){
            Item item = paciente.getItem(comando.getSegundaPalavra());
            if(item != null){
                paciente.removeItem(comando.getSegundaPalavra());
                ambienteAtual.setItem(item.getNome(), item);
                Alerta.mensagem("Item deixado no ambiente");
            } else {
                Alerta.mensagem("Você nao tem este item em seu inventario");
            }
        } else {
            Alerta.mensagem("desfazer do quê?");
        }
    }
    /**
     * Funcao que liga o gerador. Parte fundamental para conclusão do jogo
     * @param comando
     */
    private void ligarGerador(Comando comando) {
        if(ambienteAtual.getItem(comando.getSegundaPalavra()) != null){
            if(ambienteAtual.getItem(comando.getSegundaPalavra()).getNome().equals(ambienteController.getGerador().getNome())){
                ambienteController.getGerador().ligar();
                Alerta.mensagem("Gerador ligado. Fique atento, pois se demorar muito para sair, o combustivel do gerador pode acabar.");
                
            } else {
                Alerta.mensagem("Este item nao contem a funcao ligar");
            }
        } else {
            Alerta.mensagem("Não existe "+ comando.getSegundaPalavra() +" neste ambiente");
        }
    }
    
    public boolean getGameOver() {
        return this.gameOver;
    }
}
