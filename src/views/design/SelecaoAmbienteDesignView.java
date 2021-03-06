/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.design;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import servicos.design.DesignService;

/**
 *
 * @author arlen
 */
public class SelecaoAmbienteDesignView {
    //janela da classe
    private JDialog janela;
    //Servico 
    private DesignService designService;
    //Lista de ambientes
    private JComboBox ambientes;
    //Botoes da janela
    private JButton btnEditar;
    private JButton btnNovo;
    private JButton btnRemover;
    private AmbienteDesignView ambienteDesinView;
    //Area de texto de informações
    private JTextArea informacoes;
    
    public SelecaoAmbienteDesignView() {
        this.designService = new DesignService();
        construirJanela();
        ambienteDesinView = new AmbienteDesignView();
    }
    
    private void construirJanela() {
        janela = new JDialog();
        janela.setTitle("Seleção de Ambiente");
        criarComponentes();
        montarJanela();
        janela.setVisible(true);
    }
    
    private void criarComponentes() {
        ambientes = new JComboBox();
        preparaComboAmbientes();
        //adiciona acao ao clicar no combobox de ambientes
        ambientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificaAmbienteEstatico();
            }
        });
        //
        String info = "Os ambientes: \n";
        
        for(String s : designService.getNomeAmbientesEstaticos()){
            info += s + ";\n";
        }
        //area de informações
        info += "não podem ser removidos, devido a terem tratamentos especiais "
                + "dentro do jogo. Mas podem ser alterados, como o nome por exemplo.";
        
        //area de informações
        informacoes = new JTextArea();
        informacoes.setText(info);
        
        //botoes
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        btnNovo = new JButton("Novo Ambiente");
        
        //adiciona acoes para os botoes
        
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ambienteDesinView.construirJanelaNovoAmbiente();
            }
        });
        
        
    }
    
    private void montarJanela() {
        janela.setLayout(new GridLayout(4,1));
        janela.setSize(350, 250);
        janela.setLocationRelativeTo(null);
        
        janela.add(ambientes);
        
        JPanel painelAcoesAmbienteSelecionado = new JPanel(new GridLayout(1, 2));
        painelAcoesAmbienteSelecionado.add(btnEditar);
        painelAcoesAmbienteSelecionado.add(btnRemover);
        
        janela.add(painelAcoesAmbienteSelecionado);
        janela.add(btnNovo);
        //area de informações
        informacoes.setLineWrap(true);
        JScrollPane sp = new JScrollPane(informacoes);
        
        janela.add(sp);
    }
    /**
     * Esta função prepara o combobox de ambientes.
     */
    private void preparaComboAmbientes() {
        ambientes.removeAllItems();
        ambientes.addItem("Selecione:");
        for(String s : designService.getNomeTodosAmbientes()){
            ambientes.addItem(s);
        }
    }
    /**
     * Esta função verifica se o ambiente selecionado pode ser excluido.
     * Caso não, ela desabilita o botão de remoção, pois há ambientes que não podem ser
     * removidos dos sistema.
     */
    private void verificaAmbienteEstatico() {
        String dado = ambientes.getSelectedItem().toString();
        if(designService.getNomeAmbientesEstaticos().contains(dado)){
            btnRemover.setEnabled(false);
        } else {
            btnRemover.setEnabled(true);
        }
    }
}
