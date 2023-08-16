package org.keita.editor.model;

public class BotaoSelecionar extends ItemDoMenu{
    private void selecionar() {
        listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()).selectAll();
    }
}
