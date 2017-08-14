/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import servicos.RankingServico;

/**
 *
 * @author arlen
 */
public class RankingView {
    private JDialog janela;
    //Caixa de texto para exibir o ranking
    private JTextArea txtRanking;
    private RankingServico rankingServico;
    
    public RankingView() {
        rankingServico = new RankingServico();
        construirJanela();
    }
    
    private void construirJanela(){
        janela = new JDialog();
        janela.setTitle("Ranking de Jogadores");
        criarComponentes();
        montarJanela();
        janela.setVisible(true);
    }
    /**
     * Inicia e os componentes da tela.
     */
    private void criarComponentes() {
        txtRanking = new JTextArea();
        txtRanking.setText(rankingServico.getConteudo());
        txtRanking.setEditable(false);
    }
    /**
     * Posiciona os componentes na tela.
     */
    private void montarJanela() {
        janela.setSize(250, 400);
        janela.setLayout(new GridLayout(1,1));
        janela.setLocationRelativeTo(null);
        
        janela.add(txtRanking);
    }
}
