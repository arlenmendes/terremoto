/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JOptionPane;

/**
 * Esta clazse serve para informar alertas para o usuario a partir de um JOptionPane.
 * @author arlen
 */
public class Alerta {
    /**
     * Funcao para gerar uma alerta a partir de uma mensagem passada por parametro.
     * 
     * @param mensagem
     */
    public static void mensagem(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
