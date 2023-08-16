package org.keita.editor.view;

import org.keita.editor.model.Acoes;
import org.keita.editor.model.ItemDoMenu;
import org.keita.editor.model.TipoDeFundo;
import org.keita.editor.util.Utilidades;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class Painel extends JPanel {
    private final JTabbedPane abaTPane;
    private JPanel painelExtra;
    private final ArrayList<JTextPane> listaDeAreasDeTexto;
    private final ArrayList<File> listaDeArquivos;
    private final ArrayList<JScrollPane> listaDeScrolls;
    private JMenuBar barraDeMenu;
    private JMenu arquivo;
    private JMenu editar;
    private JMenu selecionar;
    private JMenu visualizar;
    private JMenu aparencia;
    private int contadorDeJanelasDeTexto = 0;
    private boolean existeJanelaDeTexto = false;
    private final ArrayList<UndoManager> listaUndoManager;
    private boolean numeracao = false;
    private final String normal = TipoDeFundo.NORMAL.getNome();
    private final String dark = TipoDeFundo.DARK.getNome();
    private String tipoDeFundo = normal;
    private JToolBar barraDeFerramentas;
    private String url;
    private JLabel labelAlfinete;
    private boolean estadoAlfinete;
    private JSlider barraDeAumentoDeFonte;
    private JPopupMenu menuEmergente;
    private final JMenuItem[] itens;
    private final String cortarSt = Acoes.CORTAR.getNome();
    private final String copiarSt = Acoes.COPIAR.getNome();
    private final String colarSt = Acoes.COLAR.getNome();
    private final String desfazerSt = Acoes.DESFAZER.getNome();
    private final String refazerSt = Acoes.REFAZER.getNome();
    private final String novoArquivo = Acoes.NOVO_ARQUIVO.getNome();
    private final String abrirArquivo = Acoes.ABRIR_ARQUIVO.getNome();
    private final String salvar = Acoes.SALVAR.getNome();
    private final String salvarComo = Acoes.SALVAR_COMO.getNome();
    private final String selecionarTudo = Acoes.SELECIONAR_TUDO.getNome();


    public Painel(JanelaPrincipal janelaPrincipal) {
        setLayout(new BorderLayout());

        //----- Menu -----
        JPanel painelMenu = new JPanel();
        itens = new JMenuItem[8];
        painelMenu.setLayout(new BorderLayout());

        criarBarraDeMenu();

        //-----Agregando Opções ao Menu Arquivo
        criarMenuDeArquivo();

        //-----Agregando opções ao menu Editar
        criarMenuEditar();

        //-----Agregando opções ao menu Selecionar
        criarMenuSelecionar();

        //-----Agregando opções ao menu Visualizar
        criarMenuVisualizar();

        //------Adicionando Barra de Menu
        painelMenu.add(barraDeMenu, BorderLayout.NORTH);

        //----- Area de Texto-----
        this.abaTPane = new JTabbedPane();

        listaDeArquivos = new ArrayList<>();
        listaDeAreasDeTexto = new ArrayList<>();
        listaDeScrolls = new ArrayList<>();
        listaUndoManager = new ArrayList<>();

        Utilidades.desativaItens(itens);

        //----Barra de Ferramentas------
        criarBarraDeFerramentas();


        //-----Painel Extra ------
        criarPainelExtra(janelaPrincipal);

        //------Menu Emergente------
        criarMenuEmergente();


        //----- Adicionando ao Painel ------
        add(painelMenu, BorderLayout.NORTH);
        add(abaTPane, BorderLayout.CENTER);
        add(barraDeFerramentas, BorderLayout.WEST);
        add(painelExtra, BorderLayout.SOUTH);
    }

    private void criarMenuEmergente() {
        menuEmergente = new JPopupMenu();
        JMenuItem cortar = new JMenuItem(this.cortarSt);
        cortar.addActionListener(cortar());
        JMenuItem copiar = new JMenuItem(this.copiarSt);
        copiar.addActionListener(copiar());
        JMenuItem colar = new JMenuItem(this.colarSt);
        colar.addActionListener(colar());
        menuEmergente.add(cortar);
        menuEmergente.add(copiar);
        menuEmergente.add(colar);
    }

    private void criarPainelExtra(JanelaPrincipal janelaPrincipal) {
        painelExtra = new JPanel();
        painelExtra.setLayout(new BorderLayout());
        JPanel painelEsquerdo = new JPanel();

        inserirAlfineteParaFixarTela(janelaPrincipal);
        painelEsquerdo.add(labelAlfinete);

        JPanel painelCentral = new JPanel();
        barraDeAumentoDeFonte = new JSlider(8, 36, 14);
        barraDeAumentoDeFonte.setMajorTickSpacing(4);
        barraDeAumentoDeFonte.setMinorTickSpacing(2);
        barraDeAumentoDeFonte.setPaintTicks(true);
        barraDeAumentoDeFonte.setPaintLabels(true);
        barraDeAumentoDeFonte.addChangeListener(evento -> Utilidades.mudarTamanhoDoTexto(barraDeAumentoDeFonte.getValue(), contadorDeJanelasDeTexto, listaDeAreasDeTexto));

        painelCentral.add(barraDeAumentoDeFonte);

        painelExtra.add(painelEsquerdo, BorderLayout.WEST);
        painelExtra.add(painelCentral, BorderLayout.CENTER);
    }

    private void inserirAlfineteParaFixarTela(JanelaPrincipal janelaPrincipal) {
        labelAlfinete = new JLabel();
        url = "src/main/java/org/keita/editor/img/alfinete.png";
        labelAlfinete.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        labelAlfinete.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                estadoAlfinete = !estadoAlfinete;
                janelaPrincipal.setAlwaysOnTop(estadoAlfinete);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                url = "src/main/java/org/keita/editor/img/alfinete_selecao.png";
                labelAlfinete.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (estadoAlfinete) {
                    url = "src/main/java/org/keita/editor/img/alfinete_selecao.png";
                    labelAlfinete.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

                } else {
                    url = "src/main/java/org/keita/editor/img/alfinete.png";
                    labelAlfinete.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                }

            }
        });
    }

    private void criarBarraDeFerramentas() {
        barraDeFerramentas = new JToolBar(SwingConstants.VERTICAL);
        url = "src/main/java/org/keita/editor/img/botao_de_fechar.png";
        Utilidades.adicionarBotao(url, barraDeFerramentas, "fechar janela atual").addActionListener(e -> {
            int selecao = abaTPane.getSelectedIndex();
            if (selecao != -1) {
                listaDeScrolls.get(abaTPane.getSelectedIndex()).setRowHeaderView(null);
                abaTPane.remove(selecao);
                listaDeAreasDeTexto.remove(selecao);
                listaDeScrolls.remove(selecao);
                listaUndoManager.remove(selecao);
                listaDeArquivos.remove(selecao);
                contadorDeJanelasDeTexto--;
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
        editar = new JMenu(ItemDoMenu.EDITAR);
        selecionar = new JMenu(ItemDoMenu.SELECIONAR);
        visualizar = new JMenu(ItemDoMenu.VISUALIZAR);
        aparencia = new JMenu(ItemDoMenu.APARENCIA);

        barraDeMenu.add(arquivo);
        barraDeMenu.add(editar);
        barraDeMenu.add(selecionar);
        barraDeMenu.add(visualizar);
    }

    private void criarMenuVisualizar() {
        criarItem("Numeração", ItemDoMenu.VISUALIZAR);
        visualizar.addSeparator();
        visualizar.add(aparencia);
        criarItem(normal, "aparência");
        criarItem(dark, "aparência");
    }

    private void criarMenuSelecionar() {
        criarItem(selecionarTudo, ItemDoMenu.SELECIONAR);
    }

    private void criarMenuEditar() {
        criarItem(desfazerSt, ItemDoMenu.EDITAR);
        criarItem(refazerSt, ItemDoMenu.EDITAR);
        editar.addSeparator();
        criarItem(cortarSt, ItemDoMenu.EDITAR);
        criarItem(copiarSt, ItemDoMenu.EDITAR);
        criarItem(colarSt, ItemDoMenu.EDITAR);
    }

    private void criarMenuDeArquivo() {
        criarItem(novoArquivo, ItemDoMenu.ARQUIVO);
        criarItem(abrirArquivo, ItemDoMenu.ARQUIVO);
        criarItem(salvar, ItemDoMenu.ARQUIVO);
        criarItem(salvarComo, ItemDoMenu.ARQUIVO);
    }

    private void criarJanelaDeTexto() {
        JPanel janela = new JPanel();
        janela.setLayout(new BorderLayout());
        listaDeArquivos.add(new File(""));
        listaDeAreasDeTexto.add(new JTextPane());
        listaDeScrolls.add(new JScrollPane(listaDeAreasDeTexto.get(contadorDeJanelasDeTexto)));
        listaUndoManager.add(new UndoManager());

        listaDeAreasDeTexto.get(contadorDeJanelasDeTexto)
                .getDocument()
                .addUndoableEditListener(listaUndoManager.get(contadorDeJanelasDeTexto));

        listaDeAreasDeTexto.get(contadorDeJanelasDeTexto)
                .setComponentPopupMenu(menuEmergente);
        janela.add(listaDeScrolls.get(contadorDeJanelasDeTexto), BorderLayout.CENTER);
        abaTPane.addTab(novoArquivo, janela);

        Utilidades.mostrarNumeracaoInicio(numeracao, listaDeAreasDeTexto.get(contadorDeJanelasDeTexto), listaDeScrolls.get(contadorDeJanelasDeTexto));

        abaTPane.setSelectedIndex(contadorDeJanelasDeTexto);

        contadorDeJanelasDeTexto++;
        Utilidades.aplicarFundo(contadorDeJanelasDeTexto, tipoDeFundo, barraDeAumentoDeFonte.getValue(), listaDeAreasDeTexto);
        existeJanelaDeTexto = true;

        Utilidades.ativaItens(itens);

    }

    private void criarItem(String rotulo, String menu) {
        JMenuItem elementoItem = new JMenuItem(rotulo);

        if (menu.equalsIgnoreCase(ItemDoMenu.ARQUIVO)) {
            criarItemArquivo(rotulo, elementoItem);
        } else if (menu.equalsIgnoreCase(ItemDoMenu.EDITAR)) {
            criarItemEditar(rotulo, elementoItem);
        } else if (menu.equalsIgnoreCase(ItemDoMenu.SELECIONAR)) {
            criarItemSelecionar(rotulo, elementoItem);
        } else if (menu.equalsIgnoreCase(ItemDoMenu.VISUALIZAR)) {
            criarItemVisualizar(rotulo, elementoItem);
        } else if (menu.equalsIgnoreCase(ItemDoMenu.APARENCIA)) {
            criarItemAparencia(rotulo, elementoItem);
        }

    }

    private void criarItemAparencia(String rotulo, JMenuItem elementoItem) {
        aparencia.add(elementoItem);
        if (rotulo.equalsIgnoreCase(normal)) {
            elementoItem.addActionListener(e -> {
                tipoDeFundo = normal;
                if (abaTPane.getTabCount() > 0) {
                    Utilidades.aplicarFundo(contadorDeJanelasDeTexto, tipoDeFundo, barraDeAumentoDeFonte.getValue(), listaDeAreasDeTexto);
                }
            });
        } else if (rotulo.equalsIgnoreCase(dark)) {
            elementoItem.addActionListener(e -> {
                tipoDeFundo = dark;
                if (abaTPane.getTabCount() > 0) {
                    Utilidades.aplicarFundo(contadorDeJanelasDeTexto, tipoDeFundo, barraDeAumentoDeFonte.getValue(), listaDeAreasDeTexto);
                }
            });
        }
    }

    private void criarItemVisualizar(String rotulo, JMenuItem elementoItem) {
        visualizar.add(elementoItem);
        if (rotulo.equalsIgnoreCase("numeração")) {
            elementoItem.addActionListener(evento -> numerarOuNao());
        }
    }

    private void criarItemSelecionar(String rotulo, JMenuItem elementoItem) {
        selecionar.add(elementoItem);
        if (rotulo.equalsIgnoreCase(selecionarTudo)) {
            itens[7] = elementoItem;
            elementoItem.addActionListener(evento -> selecionar());

        }
    }

    private void criarItemEditar(String rotulo, JMenuItem elementoItem) {
        editar.add(elementoItem);
        if (rotulo.equalsIgnoreCase(desfazerSt)) {
            itens[2] = elementoItem;
            elementoItem.addActionListener(evento -> desfazer());
        } else if (rotulo.equalsIgnoreCase(refazerSt)) {
            itens[3] = elementoItem;
            elementoItem.addActionListener(evento -> refazer());
        } else if (rotulo.equalsIgnoreCase(cortarSt)) {
            itens[4] = elementoItem;
            elementoItem.addActionListener(cortar());
        } else if (rotulo.equalsIgnoreCase(copiarSt)) {
            itens[5] = elementoItem;
            elementoItem.addActionListener(copiar());
        } else if (rotulo.equalsIgnoreCase(colarSt)) {
            itens[6] = elementoItem;
            elementoItem.addActionListener(colar());
        }
    }

    private void criarItemArquivo(String rotulo, JMenuItem elementoItem) {
        arquivo.add(elementoItem);
        if (rotulo.equalsIgnoreCase(novoArquivo)) {
            elementoItem.addActionListener(evento -> criarJanelaDeTexto());
        } else if (rotulo.equalsIgnoreCase(abrirArquivo)) {
            elementoItem.addActionListener(evento -> abrirNovaJanelaDeTexto());
        } else if (rotulo.equalsIgnoreCase(salvar)) {
            itens[0] = elementoItem;
            elementoItem.addActionListener(evento -> salvar());
        } else if (rotulo.equalsIgnoreCase(salvarComo)) {
            itens[1] = elementoItem;
            elementoItem.addActionListener(evento -> salvarComo());
        }
    }

    private void numerarOuNao() {
        numeracao = !numeracao;
        Utilidades.verNumeracao(contadorDeJanelasDeTexto, numeracao, listaDeAreasDeTexto, listaDeScrolls);
    }

    private void selecionar() {
        listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()).selectAll();
    }

    private DefaultEditorKit.PasteAction colar() {
        return new DefaultEditorKit.PasteAction();
    }

    private DefaultEditorKit.CopyAction copiar() {
        return new DefaultEditorKit.CopyAction();
    }

    private DefaultEditorKit.CutAction cortar() {
        return new DefaultEditorKit.CutAction();
    }

    private void refazer() {
        if (listaUndoManager.get(abaTPane.getSelectedIndex()).canRedo()) {
            listaUndoManager.get(abaTPane.getSelectedIndex()).redo();
        }
    }

    private void desfazer() {
        if (listaUndoManager.get(abaTPane.getSelectedIndex()).canUndo()) {
            listaUndoManager.get(abaTPane.getSelectedIndex()).undo();

        }
    }

    private void salvarComo() {
        JFileChooser salvarArquivo = new JFileChooser();
        int opcao = salvarArquivo.showSaveDialog(null);

        if (opcao == JFileChooser.APPROVE_OPTION) {

            File arquivoSelecionado = salvarArquivo.getSelectedFile();
            listaDeArquivos.set(abaTPane.getSelectedIndex(), arquivoSelecionado);
            abaTPane.setTitleAt(abaTPane.getSelectedIndex(), arquivoSelecionado.getName());

            try {
                try (FileWriter fileWriter = new FileWriter(listaDeArquivos.get(abaTPane.getSelectedIndex()).getPath())) {
                    String texto = listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()).getText();

                    for (int i = 0; i < texto.length(); i++) {
                        fileWriter.write(texto.charAt(i));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void salvar() {
        if (listaDeArquivos.get(abaTPane.getSelectedIndex()).getPath().equals("")) {
            salvarComo();
        } else {
            try {
                try (FileWriter fileWriter = new FileWriter(listaDeArquivos.get(abaTPane.getSelectedIndex()).getPath())) {
                    String texto = listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()).getText();

                    for (int i = 0; i < texto.length(); i++) {
                        fileWriter.write(texto.charAt(i));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void abrirNovaJanelaDeTexto() {
        criarJanelaDeTexto();
        JFileChooser seletorDeArquivos = new JFileChooser();
        seletorDeArquivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int resultado = seletorDeArquivos.showOpenDialog(listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()));

        if (resultado == JFileChooser.APPROVE_OPTION) {
            escolherOpcaoOKAoAbrirArquivo(seletorDeArquivos);
        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            escolherOpcaoCancelarAoAbrirArquivo();
        }
    }

    private void escolherOpcaoCancelarAoAbrirArquivo() {
        int escolha = abaTPane.getSelectedIndex();
        if (escolha != -1) {

            listaDeAreasDeTexto.remove(abaTPane.getTabCount() - 1);
            listaDeScrolls.remove(abaTPane.getTabCount() - 1);
            listaDeArquivos.remove(abaTPane.getTabCount() - 1);
            abaTPane.remove(abaTPane.getTabCount() - 1);
            contadorDeJanelasDeTexto--;
            if (contadorDeJanelasDeTexto < 1) {
                existeJanelaDeTexto = false;
                Utilidades.desativaItens(itens);
            }
        }
    }

    private void escolherOpcaoOKAoAbrirArquivo(JFileChooser seletorDeArquivos) {
        if (existeJanelaDeTexto) {
            Utilidades.ativaItens(itens);
        }
        try {
            boolean caminhoExiste = false;
            for (int i = 0; i < abaTPane.getTabCount(); i++) {
                File arquivoEscolhido = seletorDeArquivos.getSelectedFile();
                if (listaDeArquivos.get(i).getPath().equals(arquivoEscolhido.getPath())) {
                    caminhoExiste = true;
                }
            }
            if (!caminhoExiste) {
                File arquivoSelecionado = seletorDeArquivos.getSelectedFile();
                listaDeArquivos.set(abaTPane.getSelectedIndex(), arquivoSelecionado);

                FileReader entrada = new FileReader(listaDeArquivos.get(abaTPane.getSelectedIndex()).getPath());

                try (BufferedReader meuBuffer = new BufferedReader(entrada)) {
                    String linha = "";
                    String titulo = listaDeArquivos.get(abaTPane.getSelectedIndex()).getName();
                    //Agregando título à aba do painel que se cria onde se encontra nossa área de texto,
                    //lugar onde irá o texto do arquivo que o usuário selecionou
                    abaTPane.setTitleAt(abaTPane.getSelectedIndex(), titulo);

                    while (linha != null) {
                        // lê cada linha do arquivo e a armazena na string
                        linha = meuBuffer.readLine();

                        if (linha != null) {
                            Utilidades.agregarTexto(linha + "\n", listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()));
                        }
                    }
                }
                Utilidades.aplicarFundo(contadorDeJanelasDeTexto, tipoDeFundo, barraDeAumentoDeFonte.getValue(), listaDeAreasDeTexto);
            } else {
                for (int i = 0; i < abaTPane.getTabCount(); i++) {
                    File mesmoArquivo = seletorDeArquivos.getSelectedFile();
                    if (listaDeArquivos.get(i).getPath().equals(mesmoArquivo.getPath())) {
                        abaTPane.setSelectedIndex(i);

                        listaDeAreasDeTexto.remove(abaTPane.getTabCount() - 1);
                        listaDeScrolls.remove(abaTPane.getTabCount() - 1);
                        listaDeArquivos.remove(abaTPane.getTabCount() - 1);
                        abaTPane.remove(abaTPane.getTabCount() - 1);
                        contadorDeJanelasDeTexto--;
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
