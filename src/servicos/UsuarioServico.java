/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import models.Usuario;
import persistencias.UsuarioDAO;
import views.Alerta;

/**
 *
 * @author arlen
 */
public class UsuarioServico {
    
    private UsuarioDAO dao;
    
    public UsuarioServico() {
        dao = new UsuarioDAO();
    }
    /**
     * Esta função retornar um vetor de Strings que contem todos os nomes
     * de todos os usuarios.
     * @return String[]
     */
    
    public String[] getNomesUsuarios() {
        
        HashMap<String, Usuario> usuariosHash = dao.getUsuarios();
        
        String[] usuarios = new String[usuariosHash.size()];
        
        Set<String> chaves = usuariosHash.keySet();
        int i = 0;
        for(String chave : chaves){
            usuarios[i] = chave;
            i++;
        }
        
        return usuarios;
    }
    /**
     * Esta função adiciona um novo usuario.
     * @param String
     * @return boolean
     */
    public String novoUsuario(String nome) {
        for(String s : getNomesUsuarios()){
            if(s.equals(nome)){
                return "Não pode cadastrar um usuário com mesmo nome de um já existente.";
            }
        }
        boolean insert = dao.adicionaUsuario(new Usuario(nome));
        
        if(insert) {
            return "cadastrado com sucesso";
        } else {
            return "Erro ao cadastrar";
        }
    }
}
