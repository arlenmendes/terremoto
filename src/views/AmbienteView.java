package views;

import controllers.Comando;
import controllers.JogoController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import models.Ambiente;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * Classe responsável pela interface dos ambientes.
 * @author arlen
 */
public class AmbienteView {
    //Controller Do Jogo
    JogoController jogoController;
    // cria janela
    private JDialog janela;
    //Imagem da janela
    private File file;
    private ImageIcon imagem;
    private URL url;
    //Layout da tela
    private BorderLayout layout;
    //Painel de informações
    //Painel de comandos de Navegação
    private JPanel painelNavegacao;
    //painel de itens
    private JPanel painelItens;
    // botoes das janelas Direções
    private JButton btnSaidaNorte;
    private JButton btnSaidaSul;
    private JButton btnSaidaLeste;
    private JButton btnSaidaOeste;
    private JButton btnExecutar;
    
    //Botoes itens
    private JButton btnPeDeCabraAmbiente;
    private JButton btnBisturiAmbiente;
    private JButton btnChaveAmbiente;
    private JButton btnControleAmbiente;
    private JButton btnGeradorAmbiente;
    
    // ambiente atual
    private Ambiente ambiente;
    //Labels da tela
    private JLabel lbDescricao;
    //TextsArea da tela
    private JTextPane tpDescricaoLonga;
    //TextField da tela
    private JTextField txtComando;
    
    public AmbienteView() {
        //inicializa o controller do Jogo
        jogoController = new JogoController();
        //informa o ambiente atual
        this.ambiente = jogoController.getAmbienteAtual();
        layout = new BorderLayout();
        construirJanela();
    }
    
    private void construirJanela() {
        janela = new JDialog();
        
        criarComponentes();
        montarJanela();
        janela.setVisible(true);
    }
    
    /**
     * Inicia e os componentes da tela.
     */
    private void criarComponentes() {
        
        try{
            url = getClass().getResource(ambiente.getImagem());
            file = new File(url.toURI());
            
            imagem = new ImageIcon(file.getPath());
        } catch (URISyntaxException  ex){
            Alerta.mensagem("Imagem: " + ambiente.getImagem()
                    + " Nao encontrada");
            
        }
        
        tpDescricaoLonga = new JTextPane();
        tpDescricaoLonga.setText(ambiente.getDescricao()+ "\n\nMovimentos: " + jogoController.getContador());
        tpDescricaoLonga.setEditable(false);
        
        btnExecutar = new JButton("Executar");
        btnSaidaNorte = new JButton("Saida Norte");
        btnSaidaSul = new JButton("Saida Sul");
        btnSaidaLeste = new JButton("Saida Leste");
        btnSaidaOeste = new JButton("Saida Oeste");
        
        txtComando = new JTextField(20);
        txtComando.setText("");
        //cria painel de Direções e adiciona os botoes
        painelNavegacao = new JPanel(new GridLayout(8, 1));
        
        painelNavegacao.add(new JLabel("Navegação por botao"));
        painelNavegacao.add(btnSaidaNorte);
        painelNavegacao.add(btnSaidaSul);
        painelNavegacao.add(btnSaidaLeste);
        painelNavegacao.add(btnSaidaOeste);
        painelNavegacao.add(new JLabel("Comandos Por Texto"));
        painelNavegacao.add(txtComando);
        painelNavegacao.add(btnExecutar);
        //adiciona eventos a area para digitar os comandos
        txtComando.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    jogoController.executarComando(txtComando.getText());
                    mudarAmbiente();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        
        //adiciona evento para o botao executar
        btnExecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtComando.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Informe um comando");
                } else {
                    jogoController.executarComando(txtComando.getText());
                }
            }
        });
        
        verificaItensAmbiente();
        
        verificaDirecoes();
        
        //Cria o painel e adiciona seus repectivos botoes
        painelItens = new JPanel(new GridLayout(11, 1));
        
        painelItens.add(new JLabel("Itens do ambiente"));
        painelItens.add(btnPeDeCabraAmbiente);
        painelItens.add(btnChaveAmbiente);
        painelItens.add(btnBisturiAmbiente);
        painelItens.add(btnControleAmbiente);
        painelItens.add(btnGeradorAmbiente);
        
        if(jogoController.getGameOver()) {
            btnExecutar.setEnabled(false);
            btnSaidaLeste.setEnabled(false);
            btnSaidaOeste.setEnabled(false);
            btnSaidaSul.setEnabled(false);
            btnSaidaNorte.setEnabled(false);
            txtComando.setEditable(false);
            tpDescricaoLonga.setText("Acabou o Jogo!!!");
        }
    }
    
    private void montarJanela() {
        janela.setSize(700, 400);
        janela.setLayout(new BorderLayout());
        janela.setLocationRelativeTo(null);
        //adicionar os componentes da tela
        janela.add(tpDescricaoLonga, BorderLayout.NORTH);
        janela.add(new JLabel(imagem), BorderLayout.CENTER);
        janela.add(painelItens, BorderLayout.EAST);
        janela.add(painelNavegacao, BorderLayout.WEST);
    }
    
    /**
     * Atualiza os componentes da tela, quando o Paciente troca de ambiente
     */
    private void mudarAmbiente() {
        ambiente = jogoController.getAmbienteAtual();
        janela.dispose();
        construirJanela();
//        verificaDirecoes();
//        criarComponentes();
//        janela.revalidate();
//        janela.repaint();
        
    }
    /**
     * Verifica as direções disponiveis no ambiente.
     * Verifica as saidas do ambiente. Caso nao existam, desabilita o botao.
     * Caso existam, habilita ações para este botao.
     */
    private void verificaDirecoes() {
        
        if(ambiente.getSaida("norte") != null){
            btnSaidaNorte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogoController.mudarAmbiente(new Comando("ir", "norte"));
                    mudarAmbiente();
                }
            });
        } else {
            btnSaidaNorte.setEnabled(false);
        }
        if(ambiente.getSaida("sul") != null){
            btnSaidaSul.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogoController.mudarAmbiente(new Comando("ir", "sul"));
                    mudarAmbiente();
                }
            });
        } else {
            btnSaidaSul.setEnabled(false);
        }
        if(ambiente.getSaida("leste") != null){
            btnSaidaLeste.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogoController.mudarAmbiente(new Comando("ir", "leste"));
                    mudarAmbiente();
                }
            });
        } else {
            btnSaidaLeste.setEnabled(false);
        }
        if(ambiente.getSaida("oeste") != null){
            btnSaidaOeste.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogoController.mudarAmbiente(new Comando("ir", "oeste"));
                    mudarAmbiente();
                }
            });
        } else {
            btnSaidaOeste.setEnabled(false);
        }
    }
    /**
     * Verifica os itens disponiveis no ambiente atual.
     * Caso estejam, habilita seu determinados botões.
     */
    private void verificaItensAmbiente() {
        
        List<String> itens = ambiente.getListaItens();
        if(itens.contains("pe-de-cabra")){
            btnPeDeCabraAmbiente = new JButton("Pé de cabra");
            
            btnPeDeCabraAmbiente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogoController.executarComando("coletar pe-de-cabra");
                    mudarAmbiente();
                }
            });
        } else{
            btnPeDeCabraAmbiente = new JButton("");
            btnPeDeCabraAmbiente.setEnabled(false);
        }
        
        if(itens.contains("bisturi")) {
            btnBisturiAmbiente = new JButton("Bisturi");
            
            btnBisturiAmbiente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogoController.executarComando("coletar bisturi");
                    mudarAmbiente();
                }
            });
        } else{
            btnBisturiAmbiente = new JButton("");
            btnBisturiAmbiente.setEnabled(false);
        }
        
        if(itens.contains("chave")) {
            btnChaveAmbiente = new JButton("Chave");
            
            btnChaveAmbiente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogoController.executarComando("coletar chave");
                    mudarAmbiente();
                }
            });
        } else {
            btnChaveAmbiente = new JButton("");
            btnChaveAmbiente.setEnabled(false);
        }
        
        if(itens.contains("controle-portao")) {
            btnControleAmbiente = new JButton("Controle");
            
            btnControleAmbiente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogoController.executarComando("coletar controle-portao");
                    mudarAmbiente();
                }
            });
        } else {
            btnControleAmbiente = new JButton("");
            btnControleAmbiente.setVisible(false);
        }
        
        if(itens.contains("gerador")) {
            btnGeradorAmbiente = new JButton("Gerador");
            
            btnGeradorAmbiente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jogoController.executarComando("ligar gerador");
                    mudarAmbiente();
                }
            });
        } else {
            btnGeradorAmbiente = new JButton("");
            btnGeradorAmbiente.setVisible(false);
        }
    }
}
