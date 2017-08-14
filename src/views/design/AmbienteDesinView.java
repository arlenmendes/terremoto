/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.design;



import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author arlen
 */
public class AmbienteDesinView {
    private JDialog janela;
    private JTextField txtNome;
    private JTextField txtImagem;
    private JTextArea txtDescricao;
    //botoes das saidas
    private JButton btnSaidaNorte;
    private JButton btnSaidaSul;
    private JButton btnSaidaOeste;
    private JButton btnSaidaLeste;
    
    private JButton btnSalvar;
    
    public AmbienteDesinView() {
        
    }
    /**
     * Esta função prepara a tela para cadastrar um novo Ambiente
     */
    public void construirJanelaNovoAmbiente() {
        janela = new JDialog();
        criarComponentesNovoAmbiente();
        montarJanela();
        janela.setVisible(true);
    }
    
    private void criarComponentesNovoAmbiente(){
        txtNome = new JTextField();
        txtDescricao = new JTextArea();
        txtImagem = new JTextField();
        
        //inicia botoes saidas
        btnSaidaNorte = new JButton("Saida Norte");
        btnSaidaSul = new JButton("Saida Sul");
        btnSaidaLeste = new JButton("Saida Leste");
        btnSaidaOeste = new JButton("Saida Oeste");
        
        btnSalvar = new JButton("Salvar");
    }
    
    private void montarJanela(){
        janela.setSize(600, 500);
        janela.setLocationRelativeTo(null);
        janela.setLayout(new GridLayout(10,1));
        janela.setTitle("Gerenciar Ambiente.");
        
        janela.add(new JLabel("Nome do Ambiente:"));
        janela.add(txtNome);
        
        janela.add(new JLabel("Descrição do Ambiente"));
        janela.add(txtDescricao);
        janela.add(new JLabel("Imagem: (Ex: jogo.png). Add a imagem no diretorio: \'src/views/imagens\'"));
        janela.add(txtImagem);
        
        janela.add(new JLabel("Adicionar saidas para as direções:"));
        JPanel painelBtnSaidas = new JPanel(new GridLayout(1, 4));
        painelBtnSaidas.add(btnSaidaNorte);
        painelBtnSaidas.add(btnSaidaSul);
        painelBtnSaidas.add(btnSaidaLeste);
        painelBtnSaidas.add(btnSaidaOeste);
        
        janela.add(painelBtnSaidas);
        janela.add(new JLabel("Salvar dados."));
        janela.add(btnSalvar);
    }
}
