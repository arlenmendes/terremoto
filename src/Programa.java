
import configuracao.Textos;
import servicos.JogoServico;
import views.JogoView;



/**
 * Classe principal do Programa. Apenas inicia o jogo.
 * 
 * @author Arlen
 */
public class Programa {

    /**
     * Método principal. Cria um objeto da classe Jogo e inicia o jogo.
     * 
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        JogoView jogoView = new JogoView(Textos.nome, Textos.textoBoasVindas);
    }

}
