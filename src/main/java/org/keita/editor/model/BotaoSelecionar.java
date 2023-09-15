package org.keita.editor.model;

public class BotaoSelecionar extends ItemDoMenu{
    public BotaoSelecionar(String s) {
        super(s);
    }

    public void selecionar() {
        System.out.println(listaDeAreasDeTexto);
        listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()).selectAll();
    }
}
