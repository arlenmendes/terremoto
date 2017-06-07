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
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Arlen Mateus Mendes
 * @version 2011.07.31 (2017.05.16)
 */

public class Jogo  {
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Paciente paciente;
    
    //status das portas
    private final String liberada = "liberada";
    private final String trancada = "trancada";
    private final String obstruida = "obstruida";
    
    //descriçoes das saidas
    private final String liberadaDescricao = "Porta Liberada.";
    private final String trancadaDescricao = "Porta Trancada.";
    private final String obstruidaDescricao = "Porta Obstruida Por Destroços Do Predio.";
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        criarAmbientes();
        analisador = new Analisador();
        paciente = new Paciente();
    }

    /**
     * Cria todos os ambientes, liga as saidas deles e seta seus itens
     */
    private void criarAmbientes() {
        Ambiente exterior, saguao, garagem, recepicaoGeral, triagem, corredor, recepcaoUti, uti, salaEsperaUti, recepcaoCti, cti, salaEsperaCti, salaFuncionarios, salaLimpeza, almoxerifado, salaSeguranca, salaMaquinas;
        
        Item chave, bisturi, gerador, avental, controlePortao, siringa, soro, peDeCabra;
        
        //cria itens
        chave = new Item("chave", "chave da porta da recepção para a garagem", 2);
        bisturi = new Item("bisturi","Bisturi para cirurgias", 2);
        gerador = new Gerador("gerador","Gerador de energia", 50);
        controlePortao = new Item("controle-portao","Controle do Portao da Garagem", 2);
        peDeCabra = new Item("pe-de-cabra","Pe de Cabra de ferro", 4);
        
        
        // cria os ambientes
        exterior = new Ambiente("experior do hospital");
        saguao = new Ambiente("saguao do Hospital");
        garagem = new Ambiente("Garagem do hospital");
        recepicaoGeral = new Ambiente("Recepição Geral do Hospital");
        triagem = new Ambiente("triagem de pacientes do hospital");
        corredor = new Ambiente("Coredor Geral");
        recepcaoUti = new Ambiente("Recepição da UTI.");
        recepcaoCti = new Ambiente("Recepição da CTI.");
        uti = new Ambiente("Sala da UTI");
        cti = new Ambiente("Sala da CTI");
        salaEsperaUti = new Ambiente("Sala de espera da UTI.");
        salaEsperaCti = new Ambiente("Sala de espera da CTI.");
        salaFuncionarios = new Ambiente("Sala exclusiva para funcionarios.");
        salaLimpeza = new Ambiente("Sala de limpeza.");
        almoxerifado = new Ambiente("Almoxerifado");
        salaSeguranca = new Ambiente("Sala da Segurança");
        salaMaquinas = new Ambiente("Sala das maquinas do Hospital.");
        
        
        // inicializa as saidas dos ambientes
        saguao.setSaida("sul", new Saida(exterior, obstruida, null, obstruidaDescricao));
        saguao.setSaida("norte", new Saida(recepicaoGeral, liberada, null, liberadaDescricao));
        
        recepicaoGeral.setSaida("sul", new Saida(saguao, liberada, null, liberadaDescricao));
        recepicaoGeral.setSaida("oeste", new Saida(garagem, trancada, chave, trancadaDescricao));
        recepicaoGeral.setSaida("norte", new Saida(triagem, liberada, null, liberadaDescricao));
        
        garagem.setSaida("leste", new Saida(recepicaoGeral, trancada, chave, trancadaDescricao));
        garagem.setSaida("oeste", new Saida(exterior, trancada, controlePortao, trancadaDescricao));
        
        triagem.setSaida("sul", new Saida(recepicaoGeral, liberada, null, liberadaDescricao));
        triagem.setSaida("norte", new Saida(corredor, trancada, peDeCabra, trancadaDescricao));
        
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
        
        cti.setSaida("leste", new Saida(recepcaoCti, liberada, null, liberadaDescricao));
        
        salaEsperaCti.setSaida("sul", new Saida(recepcaoCti, liberada, null, liberadaDescricao));
        
        recepcaoUti.setSaida("norte", new Saida(salaEsperaUti, liberada, null, liberadaDescricao));
        recepcaoUti.setSaida("leste", new Saida(corredor, liberada, null, liberadaDescricao));
        recepcaoUti.setSaida("oeste", new Saida(uti, liberada, null, liberadaDescricao));
        
        uti.setSaida("leste", new Saida(recepcaoUti, liberada, null, liberadaDescricao));
        
        salaEsperaUti.setSaida("sul", new Saida(recepcaoUti, liberada, null, liberadaDescricao));
        
        // adiciona itens aos ambientes
        saguao.setItem(chave.getNome(), chave);
        

        ambienteAtual = saguao;  // o jogo comeca na uti
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() {            
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        System.out.println();
        System.out.println("Bem-vindo ao Terremo!");
        System.out.println("Terremoto eh um novo jogo de aventura, baseado em texto.");
        System.out.println("O jogo consiste em que você é um paciente que estava na UTI do hospital.");
        System.out.println("Mas enquanto você estava desacordado, ocorreu um terremoto e toda a cidade foi evacuada.");
        System.out.println("Você precisa agora, conseguir sair do hospital.");
        System.out.println("Boa sorte.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();
        
        System.out.println("Voce esta " + ambienteAtual.getDescricaoLonga());
    
        System.out.print("Saidas: ");
        System.out.println(ambienteAtual.getSaidas());
        System.out.println();
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
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }
        else if(palavraDeComando.equals("observar")) {
            System.out.println(ambienteAtual.getDescricaoLonga());
        }
        else if(palavraDeComando.equals("coletar")) {
            coletarItem(comando);
        } else if(palavraDeComando.equals("desfazer")){
            desfazerItem(comando);
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
        //refazer
        Saida saida = ambienteAtual.getSaida(direcao);
        if(saida != null){ 
            if(saida.getStatusSaida().getLiberada().equals(liberada)){
                ambienteAtual = saida.getAmbiente();
            } else if(saida.getStatusSaida().getLiberada().equals(obstruida)) {
                System.out.println("Ops! Você não pode passar por aqui " + saida.getStatusSaida().getDescricao());
            } else if(saida.getStatusSaida().getLiberada().equals(trancada)) {
                if(paciente.getItem(saida.getStatusSaida().getToken().getNome()) != null) {
                    saida.getStatusSaida().mudarStatus(liberada, liberadaDescricao);
                    System.out.println("Você desbloqueou esta saida... mudando de  ambiente");
                    System.out.println();
                } else {
                    System.out.println(saida.getStatusSaida().getDescricao() + " Você precisa de um(a) " + saida.getStatusSaida().getToken().getNome() + " para abrir");
                }
            }
//            System.out.println("Voce esta " + ambienteAtual.getDescricaoLonga());
            
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
}
