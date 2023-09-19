package org.keita.editor.view;

import org.keita.editor.model.*;
import org.keita.editor.util.Utilidades;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Painel extends JPanel {
    private ItemDoMenu itemDoMenu;
    private final JTabbedPane abaTPane;
    private JPanel painelExtra;
    //private final ArrayList<JTextPane> listaDeAreasDeTexto;
    private final ArrayList<File> listaDeArquivos;
    private final ArrayList<JScrollPane> listaDeScrolls;
    private JMenuBar barraDeMenu;
    private JMenu arquivo;
    private JMenu editar;
    //private JMenu selecionar;
    private BotaoSelecionar selecionar;
    private BotaoEditar botaoEditar;
    private JMenu visualizar;
    private JMenu aparencia;
//    private int contadorDeJanelasDeTexto = 0;
    //private int contadorDeJanelasDeTexto;
    private boolean existeJanelaDeTexto = false;
    private final ArrayList<UndoManager> listaUndoManager;
    private static boolean numeracao = false;
    private final String normal = TipoDeFundo.NORMAL.getNome();
    private final String dark = TipoDeFundo.DARK.getNome();
    private static String tipoDeFundo = TipoDeFundo.NORMAL.getNome();
    private JToolBar barraDeFerramentas;
    private String url;
    private JLabel labelAlfinete;
    private boolean estadoAlfinete;
    private static JSlider barraDeAumentoDeFonte;
    private final JMenuItem[] itens;
    private final String novoArquivo = Acoes.NOVO_ARQUIVO.getNome();
    private final String abrirArquivo = Acoes.ABRIR_ARQUIVO.getNome();
    private final String salvar = Acoes.SALVAR.getNome();
    private final String salvarComo = Acoes.SALVAR_COMO.getNome();
    private final String selecionarTudo = Acoes.SELECIONAR_TUDO.getNome();

    private BotaoAparencia botaoAparencia;


    public Painel(JanelaPrincipal janelaPrincipal) {
        setLayout(new BorderLayout());

        //----- Menu -----
        JPanel painelMenu = new JPanel();
        itens = new JMenuItem[8];
        painelMenu.setLayout(new BorderLayout());

        criarBarraDeMenu();
        itemDoMenu = new ItemDoMenu();

        //-----Painel Extra ------
        criarPainelExtra(janelaPrincipal);




        //------Adicionando Barra de Menu
        painelMenu.add(barraDeMenu, BorderLayout.NORTH);

        //----- Area de Texto-----
        this.abaTPane = new JTabbedPane();

//        listaDeArquivos = new ArrayList<>();
//        listaDeAreasDeTexto = new ArrayList<>();
//        listaDeScrolls = new ArrayList<>();
//        listaUndoManager = new ArrayList<>();
        //contadorDeJanelasDeTexto = itemDoMenu.getContadorDeJanelasDeTexto();

        Utilidades.desativaItens(itens);

        //----Barra de Ferramentas------
        criarBarraDeFerramentas();


//        //-----Painel Extra ------
//        criarPainelExtra(janelaPrincipal);

        //------Menu Emergente------
        this.botaoEditar.criarMenuEmergente();


        //----- Adicionando ao Painel ------
        add(painelMenu, BorderLayout.NORTH);
        add(abaTPane, BorderLayout.CENTER);
        add(barraDeFerramentas, BorderLayout.WEST);
        add(painelExtra, BorderLayout.SOUTH);
    }

//    private void criarMenuEmergente() {
//        menuEmergente = new JPopupMenu();
//        JMenuItem cortar = new JMenuItem(this.cortarSt);
//        cortar.addActionListener(cortar());
//        JMenuItem copiar = new JMenuItem(this.copiarSt);
//        copiar.addActionListener(copiar());
//        JMenuItem colar = new JMenuItem(this.colarSt);
//        colar.addActionListener(colar());
//        menuEmergente.add(cortar);
//        menuEmergente.add(copiar);
//        menuEmergente.add(colar);
//    }

    private void criarPainelExtra(JanelaPrincipal janelaPrincipal) {
        painelExtra = new JPanel();
        painelExtra.setLayout(new BorderLayout());
        JPanel painelEsquerdo = new JPanel();

        labelAlfinete = Alfinete.inserirAlfineteParaFixarTela(janelaPrincipal);
        painelEsquerdo.add(labelAlfinete);

        JPanel painelCentral = new JPanel();
        barraDeAumentoDeFonte = criarBarraDeAumentoDeFonte();

        painelCentral.add(barraDeAumentoDeFonte);

        painelExtra.add(painelEsquerdo, BorderLayout.WEST);
        painelExtra.add(painelCentral, BorderLayout.CENTER);
    }

    private JSlider criarBarraDeAumentoDeFonte() {
        barraDeAumentoDeFonte = new JSlider(8, 36, 14);
        barraDeAumentoDeFonte.setMajorTickSpacing(4);
        barraDeAumentoDeFonte.setMinorTickSpacing(2);
        barraDeAumentoDeFonte.setPaintTicks(true);
        barraDeAumentoDeFonte.setPaintLabels(true);
        barraDeAumentoDeFonte.addChangeListener(evento -> Utilidades.mudarTamanhoDoTexto(barraDeAumentoDeFonte.getValue(), itemDoMenu.getListaDeAreasDeTexto()));
        return barraDeAumentoDeFonte;
    }


    private void criarBarraDeFerramentas() {
        barraDeFerramentas = new JToolBar(SwingConstants.VERTICAL);
        url = "src/main/java/org/keita/editor/img/botao_de_fechar.png";
        Utilidades.adicionarBotao(url, barraDeFerramentas, "fechar janela atual").addActionListener(e -> {
            int selecao = abaTPane.getSelectedIndex();
            if (selecao != -1) {
                listaDeScrolls.get(abaTPane.getSelectedIndex()).setRowHeaderView(null);
                abaTPane.remove(selecao);
                itemDoMenu.getListaDeAreasDeTexto().remove(selecao);
                listaDeScrolls.remove(selecao);
                listaUndoManager.remove(selecao);
                listaDeArquivos.remove(selecao);
                if (abaTPane.getSelectedIndex() == -1) {
                    existeJanelaDeTexto = false;
                    Utilidades.desativaItens(itens);
                }
            }
        });

        url = "src/main/java/org/keita/editor/img/mais.png";
        Utilidades.adicionarBotao(url, barraDeFerramentas, novoArquivo).addActionListener(e -> {
            criarJanelaDeTexto();
            if (existeJanelaDeTexto) {
                Utilidades.ativaItens(itens);
            }
        });
    }

    private void criarBarraDeMenu() {
        barraDeMenu = new JMenuBar();
        arquivo = new JMenu(ItemDoMenu.ARQUIVO);
        botaoEditar = new BotaoEditar(ItemDoMenu.EDITAR);
        selecionar = new BotaoSelecionar(ItemDoMenu.SELECIONAR);
        visualizar = new JMenu(ItemDoMenu.VISUALIZAR);
        aparencia = new JMenu(ItemDoMenu.APARENCIA);
        //botaoAparencia = new BotaoAparencia(ItemDoMenu.APARENCIA);

        barraDeMenu.add(arquivo);
        barraDeMenu.add(editar);
        barraDeMenu.add(selecionar);
        barraDeMenu.add(visualizar);
    }


    private void criarJanelaDeTexto() {

        JPanel janela = new JPanel();
        janela.setLayout(new BorderLayout());
        listaDeArquivos.add(new File(""));
        itemDoMenu.getListaDeAreasDeTexto().add(new JTextPane());
        listaDeScrolls.add(new JScrollPane(itemDoMenu.getListaDeAreasDeTexto().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1)));
        listaUndoManager.add(new UndoManager());

        itemDoMenu.getListaDeAreasDeTexto().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1)
                .getDocument()
                .addUndoableEditListener(listaUndoManager.get(itemDoMenu.getListaDeAreasDeTexto().size() - 1));

        itemDoMenu.getListaDeAreasDeTexto().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1)
                .setComponentPopupMenu(menuEmergente);
        janela.add(listaDeScrolls.get(itemDoMenu.getListaDeAreasDeTexto().size() - 1), BorderLayout.CENTER);
        abaTPane.addTab(novoArquivo, janela);

        Utilidades.mostrarNumeracaoInicio(numeracao, itemDoMenu.getListaDeAreasDeTexto().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1), listaDeScrolls.get(listaDeAreasDeTexto.size() - 1));
        abaTPane.setSelectedIndex(itemDoMenu.getListaDeAreasDeTexto().size() - 1);

        botaoAparencia.aplicarFundo(tipoDeFundo, barraDeAumentoDeFonte.getValue(), itemDoMenu.getListaDeAreasDeTexto());
        existeJanelaDeTexto = true;

        Utilidades.ativaItens(itens);

    }



    public static int getTamanhoDaFonte(){
        return barraDeAumentoDeFonte.getValue();
    }

    public static String getTipoDeFundo(){
        return tipoDeFundo;
    }

    public static boolean getNumeracao(){
        return numeracao;
    }
}
