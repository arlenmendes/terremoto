/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import java.io.IOException;
import java.util.List;
import models.Ambiente;
import models.Item;
import models.Paciente;
import models.Saida;
import views.Alerta;
import views.JogoView;
import persistencias.Persistencia;

/**
 * Classe que ira gerenciar todas as açoes do jogo.
 * @author arlen
 */
public class JogoServico {
    private JogoView jogoView;
    private Paciente paciente;
    private Analisador analisador;
    private boolean gameOver;
    private int contador;
    //Variavel para guardar o ambiente atual
    private Ambiente ambienteAtual;
    //Controller para trabalhar os ambientes
    private AmbienteServico ambienteServico;
    
    /**
     * Metodo que inicia um jogo do Zero.
     * Inicia todos os dados que serão utilizados nas iterações com o jogador.
     */
    public void novoJogo(){
        contador = 0;
        gameOver = false;
        paciente = new Paciente();
        analisador = new Analisador();
        ambienteServico = new AmbienteServico(paciente);
        ambienteAtual = ambienteServico.getAmbienteAtual();
    }
    /**
     * Metodo que inicia um jogo a partir de dados salvos.
     * Inicia todos os dados que serão utilizados nas iterações com o jogador.
     */
    public void carregarJogoSalvo() {
//        contador = 0;
        gameOver = false;
        analisador = new Analisador();
        //ambienteServico = new AmbienteServico(paciente);
        try {
//            paciente = Persistencia.carregarPaciente();
//            ambienteServico = Persistencia.carregarAmbienteServico();
            DadosDinamicos dd = Persistencia.carregarDados();
            paciente = dd.getPaciente();
            ambienteServico = dd.getAmbienteServico();
            contador = dd.getContador();
        } catch (Exception e) {
            Alerta.mensagem("Erro ao carregar um jogo salvo, talvez você ainda"
                    + " não tenha um. Será iniciado um novo jogo. Boa sorte!!");
            System.out.println(e.getMessage());
            paciente = new Paciente();
            ambienteServico = new AmbienteServico(paciente);
        }
        ambienteAtual = ambienteServico.getAmbienteAtual();
    }
    
    public void salvarJogo() {
        try {
//            Persistencia.salvarAmbienteServico(ambienteServico);
//            Persistencia.salvarPaciente(paciente);

            DadosDinamicos dd = new DadosDinamicos(ambienteServico, paciente, contador);
            Persistencia.salvarDados(dd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @return o ambiente atual do jogo.
     */
    
    public Ambiente getAmbienteAtual() {
        return this.ambienteAtual;
    }
    /**
     * Realiza todas as verificações necessárias para que possa realizar, ou não,
     * a mudança de ambiente. Umas da pricipais funções do jogo.
     */
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
            if(saida.getStatusSaida().equals(ambienteServico.getStatusLiberada())){
                //verifica se o gerador está ligado
                //a cada movimentação entre ambientes, o gerador "consome combustivel"
                if(ambienteServico.getGerador().ligado())
                    ambienteServico.getGerador().passarTempo();
                ambienteAtual = saida.getAmbiente();
                ambienteServico.setAmbienteAtual(ambienteAtual);
                this.contador++;
            } else if(saida.getStatusSaida().equals(ambienteServico.getStatusObstruida())) {
                
                Alerta.mensagem("Ops! Você não pode passar por aqui " + saida.getDescricaoStatusSaida());
                
            } else if(saida.getStatusSaida().equals(ambienteServico.getStatusTrancada())) {
                //verifica se o jogador possui o token necessario para abrir a porta
                if(saida.liberarSaida(paciente.getItem(saida.getNomeToken()))) {
                    saida.mudarStatusDaSaida(ambienteServico.getStatusLiberada(), ambienteServico.getLiberadaDescricao());
                    Alerta.mensagem("Você desbloqueou esta saida... mudando de  ambiente.");
                    ambienteAtual = saida.getAmbiente();
                    ambienteServico.setAmbienteAtual(ambienteAtual);

                    //verifica se o gerador está ligado
                    //a cada movimentação entre ambientes, o gerador "consome combustivel"
                    if(ambienteServico.getGerador().ligado())
                        ambienteServico.getGerador().passarTempo();
                    this.contador++;
                } else {
                    Alerta.mensagem("Porta trancada. Você precisa do(s) item(ns)  " + saida.getNomeToken() + " para abrir.");
                }
                
            }
            
        } else {
            Alerta.mensagem("Direção invalida ");
        }
    }
    
    /**
     * Executa os comandos passados pela interface.
     */
    
    public void executarComando(String dados) {
        Comando comando = analisador.pegarComando(dados);
        processarComando(comando);
    }
    
    /**
     * @param Comando.
     * Processa o comando passado.
     */
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
                System.out.println(ambienteServico.getGerador().ligado());
                break;
            case "sair":
                this.gameOver = true;
                Alerta.mensagem("O jogo será encerrado!!");
                break;
            default:
                break;
        }
        
        if(ambienteAtual.getDescricao().equals("exterior do hospital")){
            Alerta.mensagem("Parabéns, você conseguiu sair do Hospital\nVocê venceu!!!!!\n\n Sua pontuação foi: " + this.getContador());
        } else if(ambienteServico.getGerador().ligado()){
            if(!ambienteServico.getGerador().haTempoDisponivel()) {
                Alerta.mensagem("FIM DO JOGO!! \nO gerador Acabou a Energia e você não pode mais\nsair do Hospital.");
                gameOver = true;
            }
        }
    }
    
    /**
     * Dá uma breve descrição do que está acontecendo no jogo.
     * Também informa os comandos disponíveis para o usuário.
     */
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
                ambienteAtual.adicionarItem(item);
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
            if(ambienteAtual.getItem(comando.getSegundaPalavra()).getNome().equals(ambienteServico.getGerador().getNome())){
                if(!ambienteServico.getGerador().ligado()){
                    ambienteServico.getGerador().ligar();
                    Alerta.mensagem("Gerador ligado. Fique atento, pois se demorar muito para sair, o combustivel do gerador pode acabar.");
                } else {
                    Alerta.mensagem("Gerador já esta ligado!");
                }
                
                
            } else {
                Alerta.mensagem("Este item nao contem a funcao ligar");
            }
        } else {
            Alerta.mensagem("Ligar o que???");
        }
    }
    
    /**
     * Verifica se o jogo acabou.
     * @return Boolean para informar se o jogo acabou
     */
    public boolean getGameOver() {
        return this.gameOver;
    }
    
    /**
     * Retorna o contador que indica o número de movimentos realizados.
     */
    
    public int getContador(){
        return this.contador;
    }
    
    /**
     * Retorna uma lista de strings com os itens contidos no invetário do jogador.
     * @return Lista de Itens.
     * 
     */
    
    public List<String> getItensPaciente() {
        return paciente.listaItens();
    }
}
