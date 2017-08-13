/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe representa um Usuário do sistema do Jogo.
 * Ela contém o Nome do Usuário, sua senha e sua pontuações;
 * @author arlen
 */
public class Usuario implements Serializable{
    
    private String nome;
    private List<Integer> pontuacoes;
    
    public Usuario(String nome){
        this.nome = nome;
        this.pontuacoes = new ArrayList<>();
    }
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the pontuacoes
     */
    public List<Integer> getPontuacoes() {
        return pontuacoes;
    }
    /**
     * Adiciona uma nova pontuação.
     */
    public void addPontuacao(int pontos) {
        this.pontuacoes.add(pontos);
    }
}
