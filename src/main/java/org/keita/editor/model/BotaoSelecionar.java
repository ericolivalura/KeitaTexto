package org.keita.editor.model;

public class BotaoSelecionar extends ItemDoMenu{
    public BotaoSelecionar(String s) {
        super(s);
    }

    private void selecionar() {
        listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()).selectAll();
    }
}
