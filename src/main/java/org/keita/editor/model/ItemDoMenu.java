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
    protected int contadorDeJanelasDeTexto = 0;
    protected ArrayList<UndoManager> listaUndoManager;
    protected JTabbedPane abaTPane;
    protected boolean numeracao = false;
    protected boolean existeJanelaDeTexto = false;

    public static final String ARQUIVO ="Arquivo" ;
    public static final String EDITAR = "Editar";
    public static final String SELECIONAR = "Selecionar";
    public static final String VISUALIZAR = "Visualizar";
    public static final String APARENCIA = "Aparência";
}
