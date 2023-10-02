package org.keita.editor.model;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

public class MenuEmergente extends JPopupMenu {
    private BotaoEditar botaoEditar;
    private final String cortarSt = Acoes.CORTAR.getNome();
    private final String copiarSt = Acoes.COPIAR.getNome();
    private final String colarSt = Acoes.COLAR.getNome();

public MenuEmergente(){
    JMenuItem cortar = new JMenuItem(this.cortarSt);
    cortar.addActionListener(cortar());
    JMenuItem copiar = new JMenuItem(this.copiarSt);
    copiar.addActionListener(copiar());
    JMenuItem colar = new JMenuItem(this.colarSt);
    colar.addActionListener(colar());
    this.add(cortar);
    this.add(copiar);
    this.add(colar);
}
    public JPopupMenu criarMenuEmergente() {

        JMenuItem cortar = new JMenuItem(this.cortarSt);
        cortar.addActionListener(cortar());
        JMenuItem copiar = new JMenuItem(this.copiarSt);
        copiar.addActionListener(copiar());
        JMenuItem colar = new JMenuItem(this.colarSt);
        colar.addActionListener(colar());
        this.add(cortar);
        this.add(copiar);
        this.add(colar);
        return this;
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
}
