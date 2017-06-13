

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
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        prepararAmbientes();
        analisador = new Analisador();
        paciente = new Paciente();
    }

    /**
     * Cria todos os ambientes, liga as saidas deles e seta seus itens
     */
    private void prepararAmbientes() {
        Ambiente exterior, saguao, garagem, recepicaoGeral, triagem, corredor, recepcaoUti, uti, salaEsperaUti, recepcaoCti, cti, salaEsperaCti, salaFuncionarios, salaLimpeza, almoxerifado, salaSeguranca, salaMaquinas;
        
        
        
        //cria itens
        chave = new Item("chave", "chave da porta da recepção para a garagem", 2);
        bisturi = new Item("bisturi","Bisturi para cirurgias", 2);
        gerador = new Gerador("gerador","Gerador de energia", 50);
        controlePortao = new Item("controle-portao","Controle do Portao da Garagem", 2);
        peDeCabra = new Item("pe-de-cabra","Pe de Cabra de ferro", 6);
        
        
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
        
        cti.setSaida("leste", new Saida(recepcaoCti, liberada, null, liberadaDescricao));
        
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
        

        ambienteAtual = salaMaquinas;  // o jogo comeca na uti
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
        
        if(ambienteAtual.getDescricao().equals("experior do hospital")){
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
            if(saida.getStatusSaida().getLiberada().equals(liberada)){
                //verifica se o gerador está ligado
                //a cada movimentação entre ambientes, o gerador "consome combustivel"
                if(this.gerador.ligado())
                    gerador.passarTempo();
                ambienteAtual = saida.getAmbiente();
                System.out.println("Voce esta " + ambienteAtual.getDescricaoLonga());
            } else if(saida.getStatusSaida().getLiberada().equals(obstruida)) {
                
                System.out.println("Ops! Você não pode passar por aqui " + saida.getStatusSaida().getDescricao());
                
            } else if(saida.getStatusSaida().getLiberada().equals(trancada)) {
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
