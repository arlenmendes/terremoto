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
    private JPanel painelNavegacao;
    //Painel de comandos de Navegação
    // botoes das janelas;
    private JButton btnSaidaNorte;
    private JButton btnSaidaSul;
    private JButton btnSaidaLeste;
    private JButton btnSaidaOeste;
    private JButton btnExecutar;
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
    }
    
    private void criarComponentes() {
        
        try{
            url = getClass().getResource(ambiente.getImagem());
            file = new File(url.toURI());
            
            imagem = new ImageIcon(file.getPath());
        } catch (URISyntaxException | NullPointerException ex){
            Alerta.mensagem("Imagem: " + ambiente.getImagem()
                    + " Nao encontrada");
            
        }
        
        tpDescricaoLonga = new JTextPane();
        tpDescricaoLonga.setText(ambiente.getDescricaoLonga());
        tpDescricaoLonga.setEditable(false);
        
        btnExecutar = new JButton("Executar");
        btnSaidaNorte = new JButton("Saida Norte");
        btnSaidaSul = new JButton("Saida Sul");
        btnSaidaLeste = new JButton("Saida Leste");
        btnSaidaOeste = new JButton("Saida Oeste");
        
        txtComando = new JTextField(20);
        txtComando.setText("");
        
        painelNavegacao = new JPanel(new GridLayout(8, 1));
        
        painelNavegacao.add(new JLabel("Navegação por botao"));
        painelNavegacao.add(btnSaidaNorte);
        painelNavegacao.add(btnSaidaSul);
        painelNavegacao.add(btnSaidaLeste);
        painelNavegacao.add(btnSaidaOeste);
        painelNavegacao.add(new JLabel("Comandos Por Texto"));
        painelNavegacao.add(txtComando);
        painelNavegacao.add(btnExecutar);
        
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
        // Verifica as saidas do ambiente. Caso nao existam, desabilita o botao.
        // Caso existam, habilita ações para este botao
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
        janela.setSize(500, 400);
        janela.setLayout(new BorderLayout());
        janela.setLocationRelativeTo(null);
        //adicionar os componentes da tela
        janela.add(tpDescricaoLonga, BorderLayout.NORTH);
        janela.add(new JLabel(imagem), BorderLayout.EAST);
        janela.add(painelNavegacao, BorderLayout.WEST);
        janela.setVisible(true);
    }
    
    /**
     * Atualiza os componentes da tela, quando o Paciente troca de ambiente
     */
    
    private void mudarAmbiente() {
        ambiente = jogoController.getAmbienteAtual();
        janela.dispose();
        construirJanela();
    }
    
    
}
