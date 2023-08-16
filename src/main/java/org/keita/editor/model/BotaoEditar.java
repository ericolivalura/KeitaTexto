package org.keita.editor.model;

import javax.swing.text.DefaultEditorKit;

public class BotaoEditar extends ItemDoMenu {
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
