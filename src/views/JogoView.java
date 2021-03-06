/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author arlen
 */
public class JogoView {
    //cria a tela
    private JFrame janela;
    //Layout da tela
    private BorderLayout layout;
    //botoes da tela
    private JButton btnNovoJogo;
    private JButton btnCarregarJogo;
    private JButton btnRanking;
    //label da tela
    private JLabel lbBoasVindas;
    //textos da tela
    private JTextPane taBoasVindas;
    private String titulo;
    private String textoBoasVindas;
    private String nomeUsuario;
    private JTextField txtNomeUsuario;
    
    public JogoView(String titulo, String textoBoasVindas, String nomeUsuario) {
        this.titulo = titulo;
        this.nomeUsuario = nomeUsuario;
        this.textoBoasVindas = textoBoasVindas;
        construirJanela();
    }
    
    private void construirJanela() {
        janela = new JFrame(this.titulo);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        criarComponentes();
        montarJanela();
    }
    /**
     * Inicializa os componentes das telas
     */
    public void criarComponentes() {
        layout = new BorderLayout();
        btnNovoJogo = new JButton("Novo Jogo");
        btnCarregarJogo = new JButton("Carregar Jogo Salvo");
        btnRanking = new JButton("Ranking de Jogadores");
        taBoasVindas = new JTextPane();
        taBoasVindas.setText(textoBoasVindas);
        taBoasVindas.setEditable(false);
        txtNomeUsuario = new JTextField(nomeUsuario);
        txtNomeUsuario.setEditable(false);
        
        // adiciona o método que tratará o evento de clique no botão Novo Jogo
        btnNovoJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AmbienteView ambienteView = new AmbienteView(true, nomeUsuario);
            }
        });
        // adiciona o método que tratará o evento de clique no botão Carregar Jogo
        btnCarregarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AmbienteView ambienteView = new AmbienteView(false, nomeUsuario);
            }
        });
        //adiciona o método que tratará o evento de clique no botão Ranking
        btnRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RankingView ranking = new RankingView();
            }
        });
    }
    /**
     * Dispoe os componentes na tela
     */
    private void montarJanela() {
        janela.setSize(500, 230);
        janela.setLayout(layout);
        janela.setLocationRelativeTo(null);
        //adicionar os componentes da tela
        
        JPanel painelSuperior = new JPanel(new GridLayout(2, 1));
        
        painelSuperior.add(new JLabel("Usuário:"));
        painelSuperior.add(txtNomeUsuario);
        
        janela.add(painelSuperior, BorderLayout.NORTH);
        janela.add(taBoasVindas, BorderLayout.CENTER);
        
        JPanel painelDireito = new JPanel(new GridLayout(3,1));
        
        painelDireito.add(btnNovoJogo);
        painelDireito.add(btnCarregarJogo);
        painelDireito.add(btnRanking);
        janela.add(painelDireito, BorderLayout.EAST);
        janela.setVisible(true);
    }
}
