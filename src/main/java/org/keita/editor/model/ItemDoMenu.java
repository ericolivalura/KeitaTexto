package org.keita.editor.model;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.io.File;
import java.util.ArrayList;

public class ItemDoMenu extends JMenu {

    protected JPanel janela;
    protected ArrayList<File> listaDeArquivos;
    protected ArrayList<JTextPane> listaDeAreasDeTexto = new ArrayList<>();
    protected ArrayList<JScrollPane> listaDeScrolls;
    //protected int contadorDeJanelasDeTexto;
    protected ArrayList<UndoManager> listaUndoManager;
    protected JTabbedPane abaTPane = new JTabbedPane();
    protected boolean numeracao = false;
    protected boolean existeJanelaDeTexto = false;

    public static final String ARQUIVO ="Arquivo" ;
    public static final String EDITAR = "Editar";
    public static final String SELECIONAR = "Selecionar";
    public static final String VISUALIZAR = "Visualizar";
    public static final String APARENCIA = "Aparência";

    public ItemDoMenu(String s) {
        super(s);
    }

    public JTabbedPane getAbaTPane() {
        return abaTPane;
    }

    public ArrayList<JTextPane> getListaDeAreasDeTexto() {
        return listaDeAreasDeTexto;
    }
}
