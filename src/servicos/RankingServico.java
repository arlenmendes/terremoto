/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import java.util.Map;
import java.util.Set;
import models.Usuario;
import persistencias.UsuarioDAO;

/**
 *
 * @author arlen
 */
public class RankingServico {
    private UsuarioDAO dao;
    
    public RankingServico() {
        dao = new UsuarioDAO();
    }
    
    public String getConteudo() {
        String conteudo = "Melhores Pontuações de cada Usuário\n";
        
        Map<String, Usuario> usuarios = dao.getUsuarios();
        
        Set<String> keys = usuarios.keySet();
        
        for(String key : keys){
            Usuario usuario = usuarios.get(key);
            if (usuario.getPontuacoes().size() > 0){
                int pontuação = usuario.getPontuacoes().get(0);
                for(int i : usuario.getPontuacoes()) {
                    if(i < pontuação)
                        pontuação = i;
                }
                conteudo += "\n" + key + ": " + pontuação;
            } else {
                conteudo += "\n" + key + ": 0";
            }
        }
        
        return conteudo;
    }
}
