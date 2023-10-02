package org.keita.editor.model;

import org.keita.editor.util.CriadoraDeItens;
import org.keita.editor.util.Utilidades;
import org.keita.editor.view.Painel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.io.*;

public class BotaoArquivoItem extends ItemDoMenuItem {

//    private final String novoArquivo = Acoes.NOVO_ARQUIVO.getNome();
//    private final String abrirArquivo = Acoes.ABRIR_ARQUIVO.getNome();
//    private final String salvar = Acoes.SALVAR.getNome();
//    private final String salvarComo = Acoes.SALVAR_COMO.getNome();
//    private final String selecionarTudo = Acoes.SELECIONAR_TUDO.getNome();
//
//    public BotaoArquivoItem(String s) {
//        super(s);
//    }
//
//    @Override
//    public void updateUI() {
//        janela = new JPanel();
//        listaDeArquivos.add(new File(""));
//        listaDeAreasDeTexto.add(new JTextPane());
//        listaDeScrolls.add(new JScrollPane(listaDeAreasDeTexto.get(contadorDeJanelasDeTexto)));
//        listaUndoManager.add(new UndoManager());
//
//        listaDeAreasDeTexto.get(contadorDeJanelasDeTexto)
//                .getDocument()
//                .addUndoableEditListener(listaUndoManager.get(contadorDeJanelasDeTexto));
//
//        janela.add(listaDeScrolls.get(contadorDeJanelasDeTexto));
//        abaTPane.addTab("Novo Arquivo", janela);
//
//        Utilidades.mostrarNumeracaoInicio(Painel.getNumeracao(), listaDeAreasDeTexto.get(contadorDeJanelasDeTexto), listaDeScrolls.get(contadorDeJanelasDeTexto));
//
//        abaTPane.setSelectedIndex(contadorDeJanelasDeTexto);
//
//        contadorDeJanelasDeTexto++;
//        existeJanelaDeTexto = true;
//    }
//
//    public void criarMenuDeArquivo() {
//        CriadoraDeItens.criarItem(novoArquivo, ItemDoMenu.ARQUIVO);
//        CriadoraDeItens.criarItem(abrirArquivo, ItemDoMenu.ARQUIVO);
//        CriadoraDeItens.criarItem(salvar, ItemDoMenu.ARQUIVO);
//        CriadoraDeItens.criarItem(salvarComo, ItemDoMenu.ARQUIVO);
//    }
//
//    private void criarJanelaDeTexto() {
//        janela = new JPanel();
//        listaDeArquivos.add(new File(""));
//        listaDeAreasDeTexto.add(new JTextPane());
//        listaDeScrolls.add(new JScrollPane(listaDeAreasDeTexto.get(contadorDeJanelasDeTexto)));
//        listaUndoManager.add(new UndoManager());
//
//        listaDeAreasDeTexto.get(contadorDeJanelasDeTexto)
//                .getDocument()
//                .addUndoableEditListener(listaUndoManager.get(contadorDeJanelasDeTexto));
//
//        janela.add(listaDeScrolls.get(contadorDeJanelasDeTexto));
//        abaTPane.addTab("Novo Arquivo", janela);
//
//        Utilidades.mostrarNumeracaoInicio(Painel.getNumeracao(), listaDeAreasDeTexto.get(contadorDeJanelasDeTexto), listaDeScrolls.get(contadorDeJanelasDeTexto));
//
//        abaTPane.setSelectedIndex(contadorDeJanelasDeTexto);
//
//        contadorDeJanelasDeTexto++;
//        existeJanelaDeTexto = true;
//    }
//
//
//    private void abrirNovaJanelaDeTexto() {
//        criarJanelaDeTexto();
//        JFileChooser seletorDeArquivos = new JFileChooser();
//        seletorDeArquivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//        int resultado = seletorDeArquivos.showOpenDialog(listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()));
//
//        if (resultado == JFileChooser.APPROVE_OPTION) {
//            try {
//                boolean caminhoExiste = false;
//                for (int i = 0; i < abaTPane.getTabCount(); i++) {
//                    File arquivoEscolhido = seletorDeArquivos.getSelectedFile();
//                    if (listaDeArquivos.get(i).getPath().equals(arquivoEscolhido.getPath())) {
//                        caminhoExiste = true;
//                    }
//                }
//                if (!caminhoExiste) {
//                    File arquivo = seletorDeArquivos.getSelectedFile();
//                    listaDeArquivos.set(abaTPane.getSelectedIndex(), arquivo);
//
//                    FileReader entrada = new FileReader(listaDeArquivos.get(abaTPane.getSelectedIndex()).getPath());
//
//                    BufferedReader meuBuffer = new BufferedReader(entrada);
//                    String linha = "";
//                    String titulo = listaDeArquivos.get(abaTPane.getSelectedIndex()).getName();
//                    //Agregando título à aba do painel que se cria onde se encontra nossa área de texto,
//                    //lugar onde irá o texto do arquivo que o usuário selecionou
//                    abaTPane.setTitleAt(abaTPane.getSelectedIndex(), titulo);
//
//                    while (linha != null) {
//                        // lê cada linha do arquivo e a armazena na string
//                        linha = meuBuffer.readLine();
//
//                        if (linha != null) {
//                            Utilidades.agregarTexto(linha + "\n", listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()));
//                        }
//                    }
//                } else {
//                    for (int i = 0; i < abaTPane.getTabCount(); i++) {
//                        File mesmoArquivo = seletorDeArquivos.getSelectedFile();
//                        if (listaDeArquivos.get(i).getPath().equals(mesmoArquivo.getPath())) {
//                            abaTPane.setSelectedIndex(i);
//
//                            listaDeAreasDeTexto.remove(abaTPane.getTabCount() - 1);
//                            listaDeScrolls.remove(abaTPane.getTabCount() - 1);
//                            listaDeArquivos.remove(abaTPane.getTabCount() - 1);
//                            abaTPane.remove(abaTPane.getTabCount() - 1);
//                            contadorDeJanelasDeTexto--;
//                            break;
//                        }
//                    }
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (resultado == JFileChooser.CANCEL_OPTION) {
//            int escolha = abaTPane.getSelectedIndex();
//            if (escolha != -1) {
//                listaDeAreasDeTexto.remove(abaTPane.getTabCount() - 1);
//                listaDeScrolls.remove(abaTPane.getTabCount() - 1);
//                listaDeArquivos.remove(abaTPane.getTabCount() - 1);
//                abaTPane.remove(abaTPane.getTabCount() - 1);
//                contadorDeJanelasDeTexto--;
//            }
//
//        }
//    }
//
//
//    private void salvar() {
//        if (listaDeArquivos.get(abaTPane.getSelectedIndex()).getPath().equals("")) {
//            salvarComo();
//        } else {
//            try {
//                FileWriter fileWriter = new FileWriter(listaDeArquivos.get(abaTPane.getSelectedIndex()).getPath());
//                String texto = listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()).getText();
//
//                for (int i = 0; i < texto.length(); i++) {
//                    fileWriter.write(texto.charAt(i));
//                }
//                fileWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//
//    public void criarItemArquivo(String rotulo, JMenuItem elementoItem) {
//        this.add(elementoItem);
//        if (rotulo.equalsIgnoreCase(novoArquivo)) {
//            elementoItem.addActionListener(evento -> criarJanelaDeTexto());
//        } else if (rotulo.equalsIgnoreCase(abrirArquivo)) {
//            elementoItem.addActionListener(evento -> abrirNovaJanelaDeTexto());
//        } else if (rotulo.equalsIgnoreCase(salvar)) {
//            itens[0] = elementoItem;
//            elementoItem.addActionListener(evento -> salvar());
//        } else if (rotulo.equalsIgnoreCase(salvarComo)) {
//            itens[1] = elementoItem;
//            elementoItem.addActionListener(evento -> salvarComo());
//        }
//    }
//
//
//    public void salvarComo() {
//        JFileChooser salvarArquivo = new JFileChooser();
//        int opcao = salvarArquivo.showSaveDialog(null);
//
//        if (opcao == JFileChooser.APPROVE_OPTION) {
//
//            File arquivoSelecionado = salvarArquivo.getSelectedFile();
//            listaDeArquivos.set(abaTPane.getSelectedIndex(), arquivoSelecionado);
//            abaTPane.setTitleAt(abaTPane.getSelectedIndex(), arquivoSelecionado.getName());
//
//            try {
//                try (FileWriter fileWriter = new FileWriter(listaDeArquivos.get(abaTPane.getSelectedIndex()).getPath())) {
//                    String texto = listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()).getText();
//
//                    for (int i = 0; i < texto.length(); i++) {
//                        fileWriter.write(texto.charAt(i));
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void escolherOpcaoCancelarAoAbrirArquivo() {
//        int escolha = abaTPane.getSelectedIndex();
//        if (escolha != -1) {
//
//            listaDeAreasDeTexto.remove(abaTPane.getTabCount() - 1);
//            listaDeScrolls.remove(abaTPane.getTabCount() - 1);
//            listaDeArquivos.remove(abaTPane.getTabCount() - 1);
//            abaTPane.remove(abaTPane.getTabCount() - 1);
//            if (listaDeAreasDeTexto.isEmpty()) {
//                existeJanelaDeTexto = false;
//                Utilidades.desativaItens(itens);
//            }
//        }
//    }
//
//    private void escolherOpcaoOKAoAbrirArquivo(JFileChooser seletorDeArquivos) {
//        if (existeJanelaDeTexto) {
//            Utilidades.ativaItens(itens);
//        }
//        try {
//            boolean caminhoExiste = false;
//            for (int i = 0; i < abaTPane.getTabCount(); i++) {
//                File arquivoEscolhido = seletorDeArquivos.getSelectedFile();
//                if (listaDeArquivos.get(i).getPath().equals(arquivoEscolhido.getPath())) {
//                    caminhoExiste = true;
//                }
//            }
//            if (!caminhoExiste) {
//                File arquivoSelecionado = seletorDeArquivos.getSelectedFile();
//                listaDeArquivos.set(abaTPane.getSelectedIndex(), arquivoSelecionado);
//
//                FileReader entrada = new FileReader(listaDeArquivos.get(abaTPane.getSelectedIndex()).getPath());
//
//                try (BufferedReader meuBuffer = new BufferedReader(entrada)) {
//                    String linha = "";
//                    String titulo = listaDeArquivos.get(abaTPane.getSelectedIndex()).getName();
//                    //Agregando título à aba do painel que se cria onde se encontra nossa área de texto,
//                    //lugar onde irá o texto do arquivo que o usuário selecionou
//                    abaTPane.setTitleAt(abaTPane.getSelectedIndex(), titulo);
//
//                    while (linha != null) {
//                        // lê cada linha do arquivo e a armazena na string
//                        linha = meuBuffer.readLine();
//
//                        if (linha != null) {
//                            Utilidades.agregarTexto(linha + "\n", listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()));
//                        }
//                    }
//                }
//                BotaoAparencia.aplicarFundo(Painel.getTipoDeFundo(), Painel.getTamanhoDaFonte(), listaDeAreasDeTexto);
//            } else {
//                for (int i = 0; i < abaTPane.getTabCount(); i++) {
//                    File mesmoArquivo = seletorDeArquivos.getSelectedFile();
//                    if (listaDeArquivos.get(i).getPath().equals(mesmoArquivo.getPath())) {
//                        abaTPane.setSelectedIndex(i);
//
//                        listaDeAreasDeTexto.remove(abaTPane.getTabCount() - 1);
//                        listaDeScrolls.remove(abaTPane.getTabCount() - 1);
//                        listaDeArquivos.remove(abaTPane.getTabCount() - 1);
//                        abaTPane.remove(abaTPane.getTabCount() - 1);
//                        break;
//                    }
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
