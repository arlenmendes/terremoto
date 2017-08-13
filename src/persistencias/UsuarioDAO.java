/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import models.Usuario;

/**
 * Esta classe representa a persistencia de usuarios do sistema
 * @author arlen
 */
public class UsuarioDAO {
    private HashMap<String, Usuario> usuarios;
    
    public UsuarioDAO() {
        try {
            usuarios = Persistencia.carregarUsuarios();
        } catch (Exception e) {
            usuarios = new HashMap<>();
        }
    }
    
    public HashMap<String, Usuario> getUsuarios() {
        return this.usuarios;
    }
    /**
     * Esta função atualiza um usuario existente.
     * @param usuarios
     * @return boolean
     */
    public boolean atualizarUsuario(Usuario usuario) {
        try {
            this.usuarios.put(usuario.getNome(), usuario);
            Persistencia.salvarUsuarios(usuarios);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    /**
     * Esta função adiciona um novo usuario.
     * @param usuarios
     * @return boolean
     */
    public boolean adicionaUsuario(Usuario usuario){
        try {
            this.usuarios.put(usuario.getNome(), usuario);
            Persistencia.salvarUsuarios(usuarios);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
