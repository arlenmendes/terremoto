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
import java.util.HashMap;
import models.Usuario;
import servicos.DadosDinamicos;

/**
 * Esta classe é responsável por salvar seus devidos arquivos binários.
 * @author arlen
 */
public class PersistenciaBinaria {
    private static final String arquivoUsuarios = "usuarios.bat";
    
    
    /**
     * Esta função salva um binario da Classe DadosDinamicos.
     */
    public static void salvarDados(DadosDinamicos  dados, String nomeArquivo) throws Exception{
        
        ObjectOutputStream oos
                = new ObjectOutputStream(new FileOutputStream("src/arquivos/" + nomeArquivo + ".bat"));
        oos.writeObject(dados);
        oos.close();
    }
    /**
     * Esta função retorna uma instancia de objeto da classe DadosDinamicos, caso haja
     * uma instância serializada.
     * @return JogoServico
     */
    public static DadosDinamicos carregarDados(String nomeArquivo) throws Exception{
        DadosDinamicos dd = null;
        
        ObjectInputStream ois = new ObjectInputStream(
                                            new FileInputStream("src/arquivos/" + nomeArquivo + ".bat")
                                        );
        dd = (DadosDinamicos)ois.readObject();
        ois.close();
        return dd;
    }
    
    
    /**
     * Esta função salva um binario de HashMap de Usuarios.
     */
    public static void salvarUsuarios(HashMap<String, Usuario> usuarios) throws Exception{
        
        ObjectOutputStream oos
                = new ObjectOutputStream(new FileOutputStream("src/arquivos/" + arquivoUsuarios));
        oos.writeObject(usuarios);
        oos.close();
    }
    
    /**
     * Esta função retorna uma instancia de HashMap de Usuarios, caso haja
     * uma instância serializada.
     * @return List
     */
    public static HashMap<String, Usuario> carregarUsuarios() throws Exception{
        HashMap<String, Usuario> usuarios = null;
        ObjectInputStream ois = new ObjectInputStream(
                                            new FileInputStream("src/arquivos/" + arquivoUsuarios)
                                        );
        usuarios = (HashMap<String, Usuario>)ois.readObject();
        ois.close();
        return usuarios;
    }
    
    
}
