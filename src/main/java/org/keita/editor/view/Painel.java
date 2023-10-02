package org.keita.editor.view;

import org.keita.editor.model.*;
import org.keita.editor.util.CriadoraDeMenu;
import org.keita.editor.util.Utilidades;

import javax.swing.*;
import java.awt.*;

public class Painel extends JPanel {

    private CriadoraDeMenu criadoraDeMenu;
    private final ItemDoMenu itemDoMenu;
    //private final JTabbedPane abaTPane;
    private final MenuEmergente menuEmergente;
    private JPanel painelExtra;
    //private final ArrayList<JTextPane> listaDeAreasDeTexto;
    //private final ArrayList<File> listaDeArquivos;
    //private final ArrayList<JScrollPane> listaDeScrolls;
    private JMenuBar barraDeMenu;
    private BotaoArquivo botaoArquivo;

    BotaoVisualizar botaoVisualizar;
    BotaoEditar botaoEditar;
    BotaoSelecionar botaoSelecionar;

    //    private int contadorDeJanelasDeTexto = 0;
    //private int contadorDeJanelasDeTexto;
    private boolean existeJanelaDeTexto = false;
    //private final ArrayList<UndoManager> listaUndoManager;
    private static final boolean NUMERACAO = false;
//    private final String normal = TipoDeFundo.NORMAL.getNome();
//    private final String dark = TipoDeFundo.DARK.getNome();
    private static final String TIPO_DE_FUNDO = TipoDeFundo.NORMAL.getNome();
    private JToolBar barraDeFerramentas;


    //private boolean estadoAlfinete;
    //private JSlider barraDeAumentoDeFonte;
    private BarraDeAumentoDeFonte barraDeAumentoDeFonte;
    //private final JMenuItem[] itens;
    private final String novoArquivo = Acoes.NOVO_ARQUIVO.getNome();
//    private final String abrirArquivo = Acoes.ABRIR_ARQUIVO.getNome();
//    private final String salvar = Acoes.SALVAR.getNome();
//    private final String salvarComo = Acoes.SALVAR_COMO.getNome();
//    private final String selecionarTudo = Acoes.SELECIONAR_TUDO.getNome();


    //private final BotaoAparencia botaoAparencia = new BotaoAparencia(ItemDoMenu.APARENCIA);
    BotaoAparencia botaoAparencia;

    public Painel(JanelaPrincipal janelaPrincipal) {
        setLayout(new BorderLayout());

        //----- Menu -----
        JPanel painelMenu = new JPanel();
        //itens = new JMenuItem[8];
        painelMenu.setLayout(new BorderLayout());

        //criarBarraDeMenu();
        itemDoMenu = new ItemDoMenu();
        barraDeAumentoDeFonte = new BarraDeAumentoDeFonte(8, 36, 14, itemDoMenu.getListaDeAreasDeTexto());
        botaoAparencia = new BotaoAparencia(ItemDoMenu.APARENCIA, barraDeAumentoDeFonte);
        //criadoraDeMenu = new CriadoraDeMenu(botaoSelecionar, botaoAparencia, botaoArquivo, botaoVisualizar, botaoEditar);
        criadoraDeMenu = new CriadoraDeMenu();
        barraDeMenu = criadoraDeMenu.criarTodosOsMenus();
        //criadoraDeItens = new CriadoraDeItens();

        //-----Painel Extra ------
        criarPainelExtra(janelaPrincipal);


        //------Adicionando Barra de Menu
        painelMenu.add(barraDeMenu, BorderLayout.NORTH);

        //----- Area de Texto-----
        //this.abaTPane = new JTabbedPane();

//        listaDeArquivos = new ArrayList<>();
//        listaDeAreasDeTexto = new ArrayList<>();
//        listaDeScrolls = new ArrayList<>();
//        listaUndoManager = new ArrayList<>();
        //contadorDeJanelasDeTexto = itemDoMenu.getContadorDeJanelasDeTexto();

        Utilidades.desativaItens(itemDoMenu.getListaDeItens());

        //----Barra de Ferramentas------
        criarBarraDeFerramentas();


//        //-----Painel Extra ------
//        criarPainelExtra(janelaPrincipal);

        //------Menu Emergente------
        menuEmergente = new MenuEmergente();


        //----- Adicionando ao Painel ------
        add(painelMenu, BorderLayout.NORTH);
        add(itemDoMenu.getAbaTPane(), BorderLayout.CENTER);
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
        JLabel labelAlfinete;
        painelExtra = new JPanel();
        painelExtra.setLayout(new BorderLayout());
        JPanel painelEsquerdo = new JPanel();

        labelAlfinete = Alfinete.inserirAlfineteParaFixarTela(janelaPrincipal);
        painelEsquerdo.add(labelAlfinete);

        JPanel painelCentral = new JPanel();
        barraDeAumentoDeFonte = new BarraDeAumentoDeFonte(8, 36, 14, itemDoMenu.getListaDeAreasDeTexto());
        //barraDeAumentoDeFonte.criarBarraDeAumentoDeFonte(itemDoMenu.getListaDeAreasDeTexto());

        painelCentral.add(barraDeAumentoDeFonte);

        painelExtra.add(painelEsquerdo, BorderLayout.WEST);
        painelExtra.add(painelCentral, BorderLayout.CENTER);
    }

//    private JSlider criarBarraDeAumentoDeFonte() {
//        barraDeAumentoDeFonte = new JSlider(8, 36, 14);
//        barraDeAumentoDeFonte.setMajorTickSpacing(4);
//        barraDeAumentoDeFonte.setMinorTickSpacing(2);
//        barraDeAumentoDeFonte.setPaintTicks(true);
//        barraDeAumentoDeFonte.setPaintLabels(true);
//        barraDeAumentoDeFonte.addChangeListener(evento -> Utilidades.mudarTamanhoDoTexto(barraDeAumentoDeFonte.getValue(), itemDoMenu.getListaDeAreasDeTexto()));
//        return barraDeAumentoDeFonte;
//    }


    private void criarBarraDeFerramentas() {
        String url;
        barraDeFerramentas = new JToolBar(SwingConstants.VERTICAL);
        url = "src/main/java/org/keita/editor/img/botao_de_fechar.png";
        Utilidades.adicionarBotao(url, barraDeFerramentas, "fechar janela atual").addActionListener(e -> {
            int selecao = itemDoMenu.getAbaTPane().getSelectedIndex();
            if (selecao != -1) {
                itemDoMenu.getListaDeScrolls().get(itemDoMenu.getAbaTPane().getSelectedIndex()).setRowHeaderView(null);
                itemDoMenu.getAbaTPane().remove(selecao);
                itemDoMenu.getListaDeAreasDeTexto().remove(selecao);
                itemDoMenu.getListaDeScrolls().remove(selecao);
                itemDoMenu.getListaUndoManager().remove(selecao);
                itemDoMenu.getListaDeArquivos().remove(selecao);
                if (itemDoMenu.getAbaTPane().getSelectedIndex() == -1) {
                    existeJanelaDeTexto = false;
                    Utilidades.desativaItens(itemDoMenu.getListaDeItens());
                }
            }
        });

        url = "src/main/java/org/keita/editor/img/mais.png";
        Utilidades.adicionarBotao(url, barraDeFerramentas, novoArquivo).addActionListener(e -> {
            botaoArquivo.criarJanelaDeTexto();
            if (existeJanelaDeTexto) {
                Utilidades.ativaItens(itemDoMenu.getListaDeItens());
            }
        });
    }

//    private void criarBarraDeMenu() {
//
//        barraDeMenu = new JMenuBar();
//        botaoArquivo = new BotaoArquivo(ItemDoMenu.ARQUIVO);
//        botaoEditar = new BotaoEditar(ItemDoMenu.EDITAR);
//        botaoSelecionar = new BotaoSelecionar(ItemDoMenu.SELECIONAR);
//        botaoVisualizar = new BotaoVisualizar(ItemDoMenu.VISUALIZAR);
//        //aparencia = new BotaoAparencia(ItemDoMenu.APARENCIA, barraDeAumentoDeFonte);
//
//        //botaoAparencia = new BotaoAparencia(ItemDoMenu.APARENCIA);
//
//        barraDeMenu.add(botaoArquivo);
//        barraDeMenu.add(botaoEditar);
//        barraDeMenu.add(botaoSelecionar);
//        barraDeMenu.add(botaoVisualizar);
//    }


//    private void criarJanelaDeTexto() {
//
//        JPanel janela = new JPanel();
//        janela.setLayout(new BorderLayout());
//        itemDoMenu.getListaDeArquivos().add(new File(""));
//        itemDoMenu.getListaDeAreasDeTexto().add(new JTextPane());
//        itemDoMenu.getListaDeScrolls().add(new JScrollPane(itemDoMenu.getListaDeAreasDeTexto().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1)));
//        itemDoMenu.getListaUndoManager().add(new UndoManager());
//
//        itemDoMenu.getListaDeAreasDeTexto().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1)
//                .getDocument()
//                .addUndoableEditListener(itemDoMenu.getListaUndoManager().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1));
//
//        itemDoMenu.getListaDeAreasDeTexto().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1)
//                .setComponentPopupMenu(menuEmergente.criarMenuEmergente());
//        janela.add(itemDoMenu.getListaDeScrolls().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1), BorderLayout.CENTER);
//        itemDoMenu.getAbaTPane().addTab(novoArquivo, janela);
//
//        Utilidades.mostrarNumeracaoInicio(NUMERACAO, itemDoMenu.getListaDeAreasDeTexto().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1), itemDoMenu.getListaDeScrolls().get(itemDoMenu.getListaDeAreasDeTexto().size() - 1));
//        itemDoMenu.getAbaTPane().setSelectedIndex(itemDoMenu.getListaDeAreasDeTexto().size() - 1);
//
////        botaoAparencia.aplicarFundo(TIPO_DE_FUNDO, barraDeAumentoDeFonte.getValue(), itemDoMenu.getListaDeAreasDeTexto());
//        //botaoAparencia.aplicarFundo(TIPO_DE_FUNDO, itemDoMenu.getListaDeAreasDeTexto());
//        aparencia.aplicarFundo(TIPO_DE_FUNDO, itemDoMenu.getListaDeAreasDeTexto());
//        existeJanelaDeTexto = true;
//
//        Utilidades.ativaItens(itemDoMenu.getListaDeItens());
//
//    }



//    public int getTamanhoDaFonte(){
//        return barraDeAumentoDeFonte.getValue();
//    }

    public static String getTipoDeFundo(){
        return TIPO_DE_FUNDO;
    }

    public static boolean getNumeracao(){
        return NUMERACAO;
    }
}
