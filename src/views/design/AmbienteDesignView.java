/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.design;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import models.Ambiente;
import servicos.design.DesignService;
import views.Alerta;

/**
 *
 * @author arlen
 */
public class AmbienteDesignView {
    private JDialog janela;
    private JTextField txtNome;
    private JTextField txtImagem;
    private JTextArea txtDescricao;
    //ComboBox das saidas
    private JComboBox cbSaidaNorte;
    private JComboBox cbSaidaSul;
    private JComboBox cbSaidaOeste;
    private JComboBox cbSaidaLeste;
    //ComboBox dos tokens das saidas 
    private JComboBox cbTokenSaidaNorte;
    private JComboBox cbTokenSaidaSul;
    private JComboBox cbTokenSaidaOeste;
    private JComboBox cbTokenSaidaLeste;
    //Tokens saidas
    private String tokenSaidaNorte;
    private String tokenSaidaSul;
    private String tokenSaidaLeste;
    private String tokenSaidaOeste;
    private DesignService designService;
    private JButton btnSalvar;
    //Strings de nomes
    private Set<String> nomesAmbientes;
    private Set<String> nomesItens;
    //radio para verificar se este pode ser um ambiente de vitoria
    private JRadioButton ambienteVitoria;
    /**
     * Esta classe é responsável por renderizar a interface responsável pelo pesign dos ambientes.
     * A partir dela, podemos criar novos ambientes para o jogo, e atualizar ambientes existentes.
     */
    public AmbienteDesignView() {
        
    }
    /**
     * Esta função prepara a tela para cadastrar um novo Ambiente
     */
    public void construirJanelaNovoAmbiente() {
        janela = new JDialog();
        this.designService =  new DesignService();
        nomesAmbientes = designService.getNomeTodosAmbientes();
        nomesItens = designService.getNomeTodosItens();
        criarComponentesNovoAmbiente();
        montarJanela();
        janela.setVisible(true);
    }
    
    private void criarComponentesNovoAmbiente(){
        txtNome = new JTextField();
        txtDescricao = new JTextArea();
        txtImagem = new JTextField();
        
        
        //inicia ComboBox das direções das saidas
        cbSaidaNorte = new JComboBox();
        cbSaidaNorte.addItem("Saida Norte:");
        cbSaidaSul = new JComboBox();
        cbSaidaSul.addItem("Saida Sul:");
        cbSaidaLeste = new JComboBox();
        cbSaidaLeste.addItem("Saida Leste:");
        cbSaidaOeste = new JComboBox();
        cbSaidaOeste.addItem("Saida Oeste:");
        
        for(String saida : nomesAmbientes){
            cbSaidaNorte.addItem(saida);
            cbSaidaSul.addItem(saida);
            cbSaidaLeste.addItem(saida);
            cbSaidaOeste.addItem(saida);
        }
        
        //inicia comboBox dos tokens das saidas
        cbTokenSaidaNorte = new JComboBox();
        cbTokenSaidaNorte.addItem("Selecione Token");
        cbTokenSaidaNorte.setEnabled(false);
        
        cbTokenSaidaSul = new JComboBox();
        cbTokenSaidaSul.addItem("Selecione Token");
        cbTokenSaidaSul.setEnabled(false);
        
        cbTokenSaidaLeste = new JComboBox();
        cbTokenSaidaLeste.addItem("Selecione Token");
        cbTokenSaidaLeste.setEnabled(false);
        
        cbTokenSaidaOeste = new JComboBox();
        cbTokenSaidaOeste.addItem("Selecione Token");
        cbTokenSaidaOeste.setEnabled(false);
        
        
        for(String item : nomesItens) {
            cbTokenSaidaNorte.addItem(item);
            cbTokenSaidaSul.addItem(item);
            cbTokenSaidaLeste.addItem(item);
            cbTokenSaidaOeste.addItem(item);
        }
        
        //inicializa Radio
        ambienteVitoria =  new JRadioButton("Ambiente de vitória");
        
        
        //chama funções que adicionam eventos aos combobox Saidas
        adicionaActionListenerComboBoxSaidas();
        
        //inicia botao salvar
        btnSalvar = new JButton("Salvar");
        
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDados();
            }
        });
    }
    
    private void montarJanela(){
        janela.setSize(600, 500);
        janela.setLocationRelativeTo(null);
        janela.setLayout(new GridLayout(12,1));
        janela.setTitle("Gerenciar Ambiente.");
        
        janela.add(new JLabel("Nome do Ambiente:"));
        janela.add(txtNome);
        
        janela.add(new JLabel("Descrição do Ambiente"));
        janela.add(txtDescricao);
        janela.add(new JLabel("Imagem: (Ex: jogo.png). Add a imagem no diretorio: \'src/views/imagens\'"));
        janela.add(txtImagem);
        
        janela.add(new JLabel("Adicionar saidas para as direções:"));
        //painel saidas
        JPanel painelComboSaidas = new JPanel(new GridLayout(1, 4));
        
        //painel ComboBox's saida Norte
        JPanel painelSaidaNorte = new JPanel(new GridLayout(2,1));
        painelSaidaNorte.add(cbSaidaNorte);
        painelSaidaNorte.add(cbTokenSaidaNorte);
        
        //painel ComboBox's saida Sul
        JPanel painelSaidaSul = new JPanel(new GridLayout(2,1));
        painelSaidaSul.add(cbSaidaSul);
        painelSaidaSul.add(cbTokenSaidaSul);
        
        //painel ComboBox's saida Leste
        JPanel painelSaidaLeste = new JPanel(new GridLayout(2,1));
        painelSaidaLeste.add(cbSaidaLeste);
        painelSaidaLeste.add(cbTokenSaidaLeste);
        
        //painel ComboBox's saida Oeste
        JPanel painelSaidaOeste = new JPanel(new GridLayout(2,1));
        painelSaidaOeste.add(cbSaidaOeste);
        painelSaidaOeste.add(cbTokenSaidaOeste);
        
        painelComboSaidas.add(painelSaidaNorte);
        painelComboSaidas.add(painelSaidaSul);
        painelComboSaidas.add(painelSaidaLeste);
        painelComboSaidas.add(painelSaidaOeste);
        
        janela.add(painelComboSaidas);
        
        janela.add(new JLabel("Marque abaixo se este for um Ambiente de Vitória:"));
        janela.add(ambienteVitoria);
        janela.add(new JLabel("Salvar dados."));
        janela.add(btnSalvar);
    }
    
    private void adicionaActionListenerComboBoxSaidas(){
        cbSaidaNorte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbSaidaNorte.getSelectedIndex() > 0)
                    cbTokenSaidaNorte.setEnabled(true);
                else
                    cbTokenSaidaNorte.setEnabled(false);
            }
        });
        
        cbSaidaSul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbSaidaSul.getSelectedIndex() > 0)
                    cbTokenSaidaSul.setEnabled(true);
                else
                    cbTokenSaidaSul.setEnabled(false);
            }
        });
        
        cbSaidaLeste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbSaidaLeste.getSelectedIndex() > 0)
                    cbTokenSaidaLeste.setEnabled(true);
                else
                    cbTokenSaidaLeste.setEnabled(false);
            }
        });
        
        cbSaidaOeste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbSaidaOeste.getSelectedIndex() > 0)
                    cbTokenSaidaOeste.setEnabled(true);
                else
                    cbTokenSaidaOeste.setEnabled(false);
            }
        });
    }
    /**
     * Esta função  é responsável por validar os dados e chamar o serviço de salvar.
     */
    private void salvarDados(){
        //verifica se informou 
        if(!txtNome.getText().equals("")){
            //verifica se já existe um ambiente com este nome
            if(!nomesAmbientes.contains(txtNome.getText())){
                //booleans para verificar as saidas.
                boolean norte = false, sul = false, leste = false,  oeste = false;
                //validar a saida Norte
                //verifica se foi selecionado algum ambiente
                if(cbSaidaNorte.getSelectedIndex() > 0){
                    //verifica se a saida sul do ambiente selecionado, está livre.
                    if(designService.getAmbientePorNome(cbSaidaNorte.getSelectedItem().toString()).getSaida("sul") != null){
                        Alerta.mensagem("A Norte aponta para um ambiente que já  contém uma saida para a sua saida sul.");
                    } else {
                        norte = true;
                    }
                } else {
                    norte = true;
                }
                //valida saida Sul
                //verifica se foi selecionado algum ambiente
                if(cbSaidaSul.getSelectedIndex() > 0){
                    //verifica se a saida norte do ambiente selecionado, está livre.
                    if(designService.getAmbientePorNome(cbSaidaSul.getSelectedItem().toString()).getSaida("norte") != null){
                        Alerta.mensagem("A Saida Sul aponta para um ambiente que já  contém uma saida para a sua saida norte.");
                    } else {
                        sul = true;
                    }
                } else {
                    sul = true;
                }
                //valida saida Leste
                //verifica se foi selecionado algum ambiente
                if(cbSaidaLeste.getSelectedIndex() > 0){
                    //verifica se a saida oeste do ambiente selecionado, está livre.
                    if(designService.getAmbientePorNome(cbSaidaLeste.getSelectedItem().toString()).getSaida("oeste") != null){
                        Alerta.mensagem("A Saida Leste aponta para um ambiente que já  contém uma saida para a sua saida oeste.");
                    } else {
                        leste = true;
                    }
                } else {
                    leste = true;
                }
                //valida saida Oeste
                //verifica se foi selecionado algum ambiente
                if(cbSaidaOeste.getSelectedIndex() > 0){
                    //verifica se a saida leste do ambiente selecionado, está livre.
                    if(designService.getAmbientePorNome(cbSaidaOeste.getSelectedItem().toString()).getSaida("leste") != null){
                        Alerta.mensagem("A Saida Oeste aponta para um ambiente que já  contém uma saida para a sua saida leste.");
                    } else {
                        oeste = true;
                    }
                } else {
                    oeste = true;
                }
                
                if(norte && sul &&  leste && oeste) {
                    boolean ambVitoria;
                    if(ambienteVitoria.isSelected())
                        ambVitoria = true;
                    else
                        ambVitoria = false;
                    Ambiente ambiente = new Ambiente(txtDescricao.getText(), "src/views/imagens/" + txtImagem.getText(), ambVitoria);
                    designService.salvarAmbiente(txtNome.getText(), ambiente);
                }
            } else {
                Alerta.mensagem("Já contem um Ambiente com este nome.");
            }
        } else {
            Alerta.mensagem("Informe um nome para o ambiente.");
        }
    }
}
