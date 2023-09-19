package org.keita.editor.model;

import org.keita.editor.util.CriadoraDeItens;

import javax.swing.*;

public class BotaoSelecionar extends ItemDoMenu{
    private final String selecionarTudo = Acoes.SELECIONAR_TUDO.getNome();
    public BotaoSelecionar(String s) {
        super(s);
    }

    public void selecionar() {
        listaDeAreasDeTexto.get(abaTPane.getSelectedIndex()).selectAll();
    }


    public void criarMenuSelecionar() {
        CriadoraDeItens.criarItem(selecionarTudo, ItemDoMenu.SELECIONAR);
    }

    public void criarItemSelecionar(String rotulo, JMenuItem elementoItem) {
        this.add(elementoItem);
        if (rotulo.equalsIgnoreCase(selecionarTudo)) {
            itens[7] = elementoItem;
            elementoItem.addActionListener(evento -> selecionar());

        }
    }
}
