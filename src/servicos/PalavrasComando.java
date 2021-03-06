package servicos;



/**
 * Classe PalavrasComando - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "Terremoto".
 * "Terremoto" eh um jogo de aventura muito simples, baseado em texto.  
 * 
 * Essa classe guarda uma enumeracao de todos os comandos conhecidos do
 * jogo. Ela eh usada no reconhecimento de comandos como eles sao digitados.
 *
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2017.05.16)
 */

public class PalavrasComando {
    // um vetor constante que guarda todas as palavras de comandos validas
    private static final String[] comandosValidos = {
        "ir", "sair", "ajuda", "observar", "coletar", "desfazer", "meus-itens", "ligar"
    };

    /**
     * Construtor - inicializa as palavras de comando.
     */
    public PalavrasComando() {
        // nada a fazer no momento...
    }

    /**
     * Verifica se uma dada String eh uma palavra de comando valida. 
     * @param umaString String a ser verificada
     * @return true se a string dada eh um comando valido,
     * false se nao eh.
     */
    public boolean ehComando(String umaString) {
        for(int i = 0; i < comandosValidos.length; i++) {
            if(comandosValidos[i].equals(umaString))
                return true;
        }
        // se chegamos aqui, a string nao foi encontrada nos comandos.
        return false;
    }
    
    /**
     * Retorna um vetor com os comandos válidos e aceitos pelo sistema do jogo.
     * @return Vetor de Strings
     */
    
    public String[] comandos() {
        return this.comandosValidos;
    }
}
