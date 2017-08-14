/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import configuracao.Textos;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import servicos.UsuarioServico;

/**
 * Esta classe é responsável pela tela de Login do sistema do jogo.
 * @author arlen
 */
public class SelecaoUsuarioView {
    private JFrame janela;
    private GridLayout layout;
    // Areas de texto da tela
    private JComboBox cbUsuarios;
    //botoes do da tela
    private JButton btnEntrar;
    private JButton btnNovoUsuario;
    private UsuarioServico usuarioServico;
    
    
    /**
     * Construtor da classe.
     */
    public SelecaoUsuarioView() {
        usuarioServico = new UsuarioServico();
        construirJanela();
    }
    /**
     * Esta função inicia o Jframe e chama as funções que criam e adicionam os componentes
     * na tela.
     */
    private void construirJanela() {
        janela = new JFrame("Seleção de Usuário");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        criarComponentes();
        montarJanela();
        janela.setVisible(true);
    }
    /**
     * Esta função inicia os componentes da tela.
     */
    private void criarComponentes() {
        layout = new GridLayout(3, 2);
        
        cbUsuarios = new JComboBox();
        preparaListaUsuarios();
        btnEntrar = new JButton("Entrar");
        btnNovoUsuario = new JButton("Novo Usuário");
        
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbUsuarios.getSelectedItem().equals("Selecione")){
                    JOptionPane.showMessageDialog(null, "Selecione um usuário!");
                } else {
                    JogoView jogoView = new JogoView(Textos.nome, Textos.textoBoasVindas, cbUsuarios.getSelectedItem().toString());
                }
            }
        });
        
        btnNovoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog("Informe o nome do novo Usuario");
                
                if(nome != null) {
                    if(nome.length() > 3 && nome.length() < 21) {
                        String insert = usuarioServico.novoUsuario(nome);
                        JOptionPane.showMessageDialog(null, insert);
                        preparaListaUsuarios();
                    } else {
                        JOptionPane.showMessageDialog(janela, "O nome deve ter no mínimo 4 caracteres e no máxiomo 20.");
                    }
                }
            }
        });
    }
    //Esta função prepara o combobox de usuarios para serem selecionados
    private void preparaListaUsuarios() {
        cbUsuarios.removeAllItems();
        cbUsuarios.addItem("Selecione");
        for(String s : usuarioServico.getNomesUsuarios()){
            cbUsuarios.addItem(s);
        }
    }
    /**
     * Esta função adiciona os componentes a tela, define sua dimensão e seta seu
     * layout.
     */
    private void montarJanela() {
        janela.setSize(350, 130);
        janela.setLayout(layout);
        janela.setLocationRelativeTo(null);
        
        //adiciona componentes na tela
        janela.add(cbUsuarios);
        janela.add(btnEntrar);
        janela.add(btnNovoUsuario);
        
        
    }
}
