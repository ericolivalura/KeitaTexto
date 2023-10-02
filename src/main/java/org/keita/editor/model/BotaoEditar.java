package org.keita.editor.model;

import org.keita.editor.util.CriadoraDeItens;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

public class BotaoEditar extends ItemDoMenu {

    private final String cortarSt = Acoes.CORTAR.getNome();
    private final String copiarSt = Acoes.COPIAR.getNome();
    private final String colarSt = Acoes.COLAR.getNome();
    private final String desfazerSt = Acoes.DESFAZER.getNome();
    private final String refazerSt = Acoes.REFAZER.getNome();

    private JPopupMenu menuEmergente;
    public BotaoEditar(String s) {
        super(s);
    }

    private void desfazer() {
        if (listaUndoManager.get(abaTPane.getSelectedIndex()).canUndo()) {
            listaUndoManager.get(abaTPane.getSelectedIndex()).undo();

        }
    }

    private void refazer() {
        if (listaUndoManager.get(abaTPane.getSelectedIndex()).canRedo()) {
            listaUndoManager.get(abaTPane.getSelectedIndex()).redo();
        }
    }



    public void criarMenuEditar() {
        CriadoraDeItens.criarItem(desfazerSt, ItemDoMenu.EDITAR);
        CriadoraDeItens.criarItem(refazerSt, ItemDoMenu.EDITAR);
        this.addSeparator();
        CriadoraDeItens.criarItem(cortarSt, ItemDoMenu.EDITAR);
        CriadoraDeItens.criarItem(copiarSt, ItemDoMenu.EDITAR);
        CriadoraDeItens.criarItem(colarSt, ItemDoMenu.EDITAR);
    }

    public void criarItemEditar(String rotulo, JMenuItem elementoItem) {
        this.add(elementoItem);
        if (rotulo.equalsIgnoreCase(desfazerSt)) {
           // itens[2] = elementoItem;
            listaDeItens.add(elementoItem);
            elementoItem.addActionListener(evento -> desfazer());
        } else if (rotulo.equalsIgnoreCase(refazerSt)) {
            //itens[3] = elementoItem;
            listaDeItens.add(elementoItem);
            elementoItem.addActionListener(evento -> refazer());
        } else if (rotulo.equalsIgnoreCase(cortarSt)) {
            //itens[4] = elementoItem;
            listaDeItens.add(elementoItem);
            elementoItem.addActionListener(cortar());
        } else if (rotulo.equalsIgnoreCase(copiarSt)) {
            //itens[5] = elementoItem;
            listaDeItens.add(elementoItem);
            elementoItem.addActionListener(copiar());
        } else if (rotulo.equalsIgnoreCase(colarSt)) {
            //itens[6] = elementoItem;
            listaDeItens.add(elementoItem);
            elementoItem.addActionListener(colar());
        }
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

    public JPopupMenu criarMenuEmergente() {
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
        return menuEmergente;
    }
}
