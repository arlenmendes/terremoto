/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencias;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import servicos.DadosDinamicos;

/**
 *
 * @author arlen
 */
public class Persistencia {
    private static final String nomeArquivo = "dados.bat";
    
    
    /**
     * Esta função salva um binario da Classe DadosDinamicos.
     */
    public static void salvarDados(DadosDinamicos  dados) throws Exception{
        
        ObjectOutputStream oos
                = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
        oos.writeObject(dados);
        oos.close();
    }
    /**
     * Esta função retorna uma instancia de objeto da classe DadosDinamicos, caso haja
     * uma instância serializada.
     * @return JogoServico
     */
    public static DadosDinamicos carregarDados() throws Exception{
        DadosDinamicos dd = null;
        
        ObjectInputStream ois = new ObjectInputStream(
                                            new FileInputStream(nomeArquivo)
                                        );
        dd = (DadosDinamicos)ois.readObject();
        ois.close();
        return dd;
    }
}
