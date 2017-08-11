/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
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
    private JButton btnJogar;
    //label da tela
    private JLabel lbBoasVindas;
    //textos da tela
    private JTextPane taBoasVindas;
    private String titulo;
    private String textoBoasVindas;
    
    public JogoView(String titulo, String textoBoasVindas) {
        this.titulo = titulo;
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
        btnJogar = new JButton("Iniciar");
        taBoasVindas = new JTextPane();
        taBoasVindas.setText(textoBoasVindas);
        taBoasVindas.setEditable(false);
//        lbBoasVindas = new JLabel(this.textoBoasVindas);
        // adiciona o método que tratará o evento de clique no botão Jogar
        btnJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AmbienteView ambienteView = new AmbienteView();
            }
        });
    }
    /**
     * Dispoe os componentes na tela
     */
    private void montarJanela() {
        janela.setSize(500, 170);
        janela.setLayout(layout);
        janela.setLocationRelativeTo(null);
        //adicionar os componentes da tela
        janela.add(taBoasVindas, BorderLayout.NORTH);
        janela.add(btnJogar, BorderLayout.CENTER);
        janela.setVisible(true);
    }
}
