package controllers;


import models.PortaoGaragem;
import models.Paciente;
import models.Gerador;
import models.Item;
import models.Saida;
import models.Ambiente;
import views.AmbienteView;



/**
 *  Essa eh a classe principal da aplicacao "Terremoto".
 *  "Terremoto" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria as saídas, cria os itens, cria o analisador e comeca o jogo.
 * Ela tambem avalia e executa os comandos que o analisador retorna.
 * 
 * @author  Arlen Mateus Mendes
 * @version 2011.07.31 (2017.05.16)
 */

public class Jogo  {
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Paciente paciente;
    
    
    
    
    AmbienteView ambienteView;
    
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
//        prepararAmbientes();
        analisador = new Analisador();
        paciente = new Paciente();
    }
    

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public Ambiente ambienteAtual() {            
//        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
//        boolean terminado = false;
//        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
//        }
//        System.out.println("Obrigado por jogar. Ate mais!");
//        ambienteView = new AmbienteView(ambienteAtual);
        return ambienteAtual;
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        switch (palavraDeComando) {
            case "ajuda":
                imprimirAjuda();
                break;
            case "ir":
                irParaAmbiente(comando);
                break;
            case "sair":
                querSair = sair(comando);
                break;
            case "observar":
                System.out.println(ambienteAtual.getDescricaoLonga());
                break;
            case "coletar":
                coletarItem(comando);
                break;
            case "desfazer":
                desfazerItem(comando);
                break;
            case "meus-itens":
                System.out.println(paciente.ListarMeusItens());
                break;
            case "ligar":
                ligarGerador(comando);
                break;
            default:
                break;
        }
        
        if(ambienteAtual.getDescricao().equals("exterior do hospital")){
            System.out.println("Parabéns, você conseguiu sair do Hospital");
            System.out.println("Você venceu!!!!!");
            querSair = true;
        } else if(gerador.ligado()){
            if(!gerador.haTempoDisponivel()) {
                System.out.println("GAME OVER");
                System.out.println("O gerador Acabou a Energia e você não pode mais");
                System.out.println("sair do Hospital.");
                querSair = true;
            }
            
                
        }
        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pelo hospital.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        for(String comandoDisponivel : analisador.comandosDisponiveis()){
            System.out.print(comandoDisponivel + " ");
        }
        System.out.println();
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Saida saida = ambienteAtual.getSaida(direcao);
        //verifica se a direção informada existe
        if(saida != null){
            //verifica se a saida esta liberada, obstruida ou trancada
            if(saida.getStatusSaida().getStatus().equals(liberada)){
                //verifica se o gerador está ligado
                //a cada movimentação entre ambientes, o gerador "consome combustivel"
                if(this.gerador.ligado())
                    gerador.passarTempo();
                ambienteAtual = saida.getAmbiente();
                System.out.println("Voce esta " + ambienteAtual.getDescricaoLonga());
            } else if(saida.getStatusSaida().getStatus().equals(obstruida)) {
                
                System.out.println("Ops! Você não pode passar por aqui " + saida.getStatusSaida().getDescricao());
                
            } else if(saida.getStatusSaida().getStatus().equals(trancada)) {
                //verifica se a saida informada é o portao da garagem
                if(ambienteAtual.getSaida(direcao) instanceof PortaoGaragem){
                    //verifica se ainda há combustivel no gerador
                    if(gerador.haTempoDisponivel()) {
                        //verifica se o jogador possui o token necessario para abrir a porta
                        if(paciente.getItem(saida.getStatusSaida().getToken().getNome()) != null) {
                            saida.getStatusSaida().mudarStatus(liberada, liberadaDescricao);
                            System.out.println("Você desbloqueou esta saida... mudando de  ambiente.");
                            System.out.println();
                            System.out.println("Voce esta " + ambienteAtual.getDescricaoLonga());
                            System.out.println();
                            ambienteAtual = saida.getAmbiente();
                            //verifica se o gerador está ligado
                            //a cada movimentação entre ambientes, o gerador "consome combustivel"
                            if(this.gerador.ligado())
                                gerador.passarTempo();
                        } else {
                            System.out.println(saida.getStatusSaida().getDescricao() + " Você precisa de um(a) " + saida.getStatusSaida().getToken().getNome() + " para abrir");
                        }
                    }
                } else {
                    //verifica se o jogador possui o token necessario para abrir a porta
                    if(paciente.getItem(saida.getStatusSaida().getToken().getNome()) != null) {
                        saida.getStatusSaida().mudarStatus(liberada, liberadaDescricao);
                        System.out.println("Você desbloqueou esta saida... mudando de  ambiente.");
                        System.out.println();
                        ambienteAtual = saida.getAmbiente();
                        System.out.println("Voce esta " + ambienteAtual.getDescricaoLonga());
                        System.out.println();
                    }
                    //verifica se o gerador está ligado
                    //a cada movimentação entre ambientes, o gerador "consome combustivel"
                    if(this.gerador.ligado())
                        gerador.passarTempo();
                }
                
                
            }
            
        } else {
            System.out.println("Direção invalida ");
        }
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
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
                    System.out.println("Item adicionado ao seu inventario.");
                } else {
                    System.out.println("Espaço insuficiente no seu inventário");
                    System.out.println();
                }
            } else {
                System.out.println("Não existe este item no ambiente");
                System.out.println();
            }
        } else {
            System.out.println("coletar o que?");
            System.out.println();
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
                System.out.println("Item deixado no ambiente");
            } else {
                System.out.println("Você nao tem este item em seu inventario");
                System.out.println();
            }
        } else {
            System.out.println("desfazer do quê?");
            System.out.println();
        }
    }
    
    private void ligarGerador(Comando comando) {
        if(ambienteAtual.getItem(comando.getSegundaPalavra()) != null && ambienteAtual.getItem(comando.getSegundaPalavra()).getNome().equals("gerador")){
            gerador.ligar();
        } else {
            System.out.println("Não existe "+ comando.getSegundaPalavra() +" neste ambiente");
        }
    }
}
