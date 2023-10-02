package org.keita.editor.model;

import org.keita.editor.util.CriadoraDeItens;
import org.keita.editor.util.Utilidades;
import org.keita.editor.view.Painel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.io.*;

public class BotaoArquivo extends ItemDoMenu {

    private final String novoArquivo = Acoes.NOVO_ARQUIVO.getNome();
    private final String abrirArquivo = Acoes.ABRIR_ARQUIVO.getNome();
    private final String salvar = Acoes.SALVAR.getNome();
    private final String salvarComo = Acoes.SALVAR_COMO.getNome();


    public BotaoArquivo(String s) {
        super(s);
    }

    public void criarMenuDeArquivo() {
        JMenuItem novoArquivoItem = CriadoraDeItens.criarItem(novoArquivo, ItemDoMenu.ARQUIVO);
        JMenuItem abrirArquivoItem = CriadoraDeItens.criarItem(abrirArquivo, ItemDoMenu.ARQUIVO);
        JMenuItem salvarItem = CriadoraDeItens.criarItem(salvar, ItemDoMenu.ARQUIVO);
        JMenuItem salvarComoItem = CriadoraDeItens.criarItem(salvarComo, ItemDoMenu.ARQUIVO);
        this.add(novoArquivoItem);
        this.add(abrirArquivoItem);
        this.add(salvarItem);
        this.add(salvarComoItem);

    }

    public void criarJanelaDeTexto() {
        JPanel janela;

        janela = new JPanel();
        janela.setLayout(new BorderLayout());
        listaDeArquivos.add(new File(""));
        listaDeAreasDeTexto.add(new JTextPane());
        listaDeScrolls.add(new JScrollPane(listaDeAreasDeTexto.get(listaDeAreasDeTexto.size()-1)));
        listaUndoManager.add(new UndoManager());

        listaDeAreasDeTexto.get(listaDeAreasDeTexto.size()-1)
                .getDocument()
                .addUndoableEditListener(listaUndoManager.get(listaDeAreasDeTexto.size()-1));
        listaDeAreasDeTexto.get(listaDeAreasDeTexto.size() -1)
                .setComponentPopupMenu(new MenuEmergente());
        janela.add(listaDeScrolls.get(listaDeAreasDeTexto.size()-1));
        abaTPane.addTab(novoArquivo, janela);

        Utilidades.mostrarNumeracaoInicio(Painel.getNumeracao(), listaDeAreasDeTexto.get(listaDeAreasDeTexto.size()-1), listaDeScrolls.get(listaDeAreasDeTexto.size()-1));

        abaTPane.setSelectedIndex(listaDeAreasDeTexto.size()-1);
        BotaoAparencia.aplicarFundo(TipoDeFundo.NORMAL.getNome(), listaDeAreasDeTexto);
        existeJanelaDeTexto = true;
        Utilidades.ativaItens(listaDeItens);
        System.out.println(existeJanelaDeTexto);

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
        System.out.println(existeJanelaDeTexto);
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



    public void criarItemArquivo(String rotulo, JMenuItem elementoItem) {
        this.add(elementoItem);
        if (rotulo.equalsIgnoreCase(novoArquivo)) {
            elementoItem.addActionListener(evento -> criarJanelaDeTexto());
        } else if (rotulo.equalsIgnoreCase(abrirArquivo)) {
            elementoItem.addActionListener(evento -> abrirNovaJanelaDeTexto());
        } else if (rotulo.equalsIgnoreCase(salvar)) {
            //itens[0] = elementoItem;
            listaDeItens.add(elementoItem);
            elementoItem.addActionListener(evento -> salvar());
        } else if (rotulo.equalsIgnoreCase(salvarComo)) {
            //itens[1] = elementoItem;
            listaDeItens.add(elementoItem);
            elementoItem.addActionListener(evento -> salvarComo());
        }
    }


    public void salvarComo() {
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

    private void escolherOpcaoCancelarAoAbrirArquivo() {
        int escolha = abaTPane.getSelectedIndex();
        if (escolha != -1) {

            listaDeAreasDeTexto.remove(abaTPane.getTabCount() - 1);
            listaDeScrolls.remove(abaTPane.getTabCount() - 1);
            listaDeArquivos.remove(abaTPane.getTabCount() - 1);
            abaTPane.remove(abaTPane.getTabCount() - 1);
            if (listaDeAreasDeTexto.isEmpty()) {
                existeJanelaDeTexto = false;
                Utilidades.desativaItens(listaDeItens);
            }
        }
    }

    private void escolherOpcaoOKAoAbrirArquivo(JFileChooser seletorDeArquivos) {
        if (existeJanelaDeTexto) {
            Utilidades.ativaItens(listaDeItens);
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
                seOCaminhoNaoExistir(seletorDeArquivos);
//                BotaoAparencia.aplicarFundo(Painel.getTipoDeFundo(), Painel.getTamanhoDaFonte(), listaDeAreasDeTexto);
                BotaoAparencia.aplicarFundo(Painel.getTipoDeFundo(), listaDeAreasDeTexto);
            } else {
                for (int i = 0; i < abaTPane.getTabCount(); i++) {
                    File mesmoArquivo = seletorDeArquivos.getSelectedFile();
                    if (listaDeArquivos.get(i).getPath().equals(mesmoArquivo.getPath())) {
                        abaTPane.setSelectedIndex(i);

                        listaDeAreasDeTexto.remove(abaTPane.getTabCount() - 1);
                        listaDeScrolls.remove(abaTPane.getTabCount() - 1);
                        listaDeArquivos.remove(abaTPane.getTabCount() - 1);
                        abaTPane.remove(abaTPane.getTabCount() - 1);
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void seOCaminhoNaoExistir(JFileChooser seletorDeArquivos) throws IOException {
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
    }
}
