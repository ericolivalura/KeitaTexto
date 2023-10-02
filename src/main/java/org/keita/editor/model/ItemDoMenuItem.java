package org.keita.editor.model;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.io.File;
import java.util.ArrayList;

public class ItemDoMenuItem extends JMenuItem {

    protected JPanel janela;
    protected ArrayList<File> listaDeArquivos;
    protected ArrayList<JTextPane> listaDeAreasDeTexto;
    protected ArrayList<JScrollPane> listaDeScrolls;
    protected int contadorDeJanelasDeTexto;
    protected ArrayList<UndoManager> listaUndoManager;
    protected JTabbedPane abaTPane;
    protected boolean existeJanelaDeTexto;

    public static final String ARQUIVO ="Arquivo" ;
    public static final String EDITAR = "Editar";
    public static final String SELECIONAR = "Selecionar";
    public static final String VISUALIZAR = "Visualizar";
    public static final String APARENCIA = "Aparência";

    protected final JMenuItem[] itens;

    public ItemDoMenuItem(String s) {
        super(s);
        this.janela = new JPanel();
        this.listaDeArquivos = new ArrayList<>();
        this.listaDeAreasDeTexto = new ArrayList<>();
        this.listaDeScrolls = new ArrayList<>();
        this.contadorDeJanelasDeTexto = 0;
        this.listaUndoManager = new ArrayList<>();
        this.abaTPane = new JTabbedPane();
        //this.numeracao = false;
        itens = new JMenuItem[8];
        this.existeJanelaDeTexto = false;
    }

    public ItemDoMenuItem() {
        this.janela = new JPanel();
        this.listaDeArquivos = new ArrayList<>();
        this.listaDeAreasDeTexto = new ArrayList<>();
        this.listaDeScrolls = new ArrayList<>();
        this.contadorDeJanelasDeTexto = 0;
        this.listaUndoManager = new ArrayList<>();
        this.abaTPane = new JTabbedPane();
        this.existeJanelaDeTexto = false;
        itens = new JMenuItem[8];
    }

    public ArrayList<JTextPane> getListaDeAreasDeTexto() {
        return listaDeAreasDeTexto;
    }

    public ArrayList<File> getListaDeArquivos() {
        return listaDeArquivos;
    }

    public ArrayList<JScrollPane> getListaDeScrolls() {
        return listaDeScrolls;
    }

    public ArrayList<UndoManager> getListaUndoManager() {
        return listaUndoManager;
    }

    public JMenuItem[] getItens() {
        return itens;
    }

    public JTabbedPane getAbaTPane() {
        return abaTPane;
    }
}
