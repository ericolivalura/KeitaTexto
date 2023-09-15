package org.keita.editor.model;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.io.File;
import java.util.ArrayList;

public class ItemDoMenu extends JMenu {

    protected JPanel janela;
    protected ArrayList<File> listaDeArquivos;
    protected ArrayList<JTextPane> listaDeAreasDeTexto;
    protected ArrayList<JScrollPane> listaDeScrolls;
    protected int contadorDeJanelasDeTexto;
    protected ArrayList<UndoManager> listaUndoManager;
    protected JTabbedPane abaTPane;
//    protected boolean numeracao;
    protected boolean existeJanelaDeTexto;

    public static final String ARQUIVO ="Arquivo" ;
    public static final String EDITAR = "Editar";
    public static final String SELECIONAR = "Selecionar";
    public static final String VISUALIZAR = "Visualizar";
    public static final String APARENCIA = "Aparência";

    public ItemDoMenu(String s) {
        super(s);
        this.janela = new JPanel();
        this.listaDeArquivos = new ArrayList<>();
        this.listaDeAreasDeTexto = new ArrayList<>();
        this.listaDeScrolls = new ArrayList<>();
        this.contadorDeJanelasDeTexto = 0;
        this.listaUndoManager = new ArrayList<>();
        this.abaTPane = new JTabbedPane();
        //this.numeracao = false;
        this.existeJanelaDeTexto = false;
    }

    public ItemDoMenu() {
        this.janela = new JPanel();
        this.listaDeArquivos = new ArrayList<>();
        this.listaDeAreasDeTexto = new ArrayList<>();
        this.listaDeScrolls = new ArrayList<>();
        this.contadorDeJanelasDeTexto = 0;
        this.listaUndoManager = new ArrayList<>();
        this.abaTPane = new JTabbedPane();
        //this.numeracao = false;
        this.existeJanelaDeTexto = false;
    }

    public JTabbedPane getAbaTPane() {
        return abaTPane;
    }

    public ArrayList<JTextPane> getListaDeAreasDeTexto() {
        return listaDeAreasDeTexto;
    }

    public JPanel getJanela() {
        return janela;
    }

    public void setJanela(JPanel janela) {
        this.janela = janela;
    }

    public ArrayList<File> getListaDeArquivos() {
        return listaDeArquivos;
    }

    public void setListaDeArquivos(ArrayList<File> listaDeArquivos) {
        this.listaDeArquivos = listaDeArquivos;
    }

    public void setListaDeAreasDeTexto(ArrayList<JTextPane> listaDeAreasDeTexto) {
        this.listaDeAreasDeTexto = listaDeAreasDeTexto;
    }

    public ArrayList<JScrollPane> getListaDeScrolls() {
        return listaDeScrolls;
    }

    public void setListaDeScrolls(ArrayList<JScrollPane> listaDeScrolls) {
        this.listaDeScrolls = listaDeScrolls;
    }

    public int getContadorDeJanelasDeTexto() {
        return contadorDeJanelasDeTexto;
    }

    public void setContadorDeJanelasDeTexto(int contadorDeJanelasDeTexto) {
        this.contadorDeJanelasDeTexto = contadorDeJanelasDeTexto;
    }

    public ArrayList<UndoManager> getListaUndoManager() {
        return listaUndoManager;
    }

    public void setListaUndoManager(ArrayList<UndoManager> listaUndoManager) {
        this.listaUndoManager = listaUndoManager;
    }

    public void setAbaTPane(JTabbedPane abaTPane) {
        this.abaTPane = abaTPane;
    }

    public boolean isExisteJanelaDeTexto() {
        return existeJanelaDeTexto;
    }

    public void setExisteJanelaDeTexto(boolean existeJanelaDeTexto) {
        this.existeJanelaDeTexto = existeJanelaDeTexto;
    }
}
