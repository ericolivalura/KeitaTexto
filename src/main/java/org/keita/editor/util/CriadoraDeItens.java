package org.keita.editor.util;

import org.keita.editor.model.*;

import javax.swing.*;

public class CriadoraDeItens {

    static BotaoSelecionar botaoSelecionar = new BotaoSelecionar(ItemDoMenu.SELECIONAR);
    static BotaoAparencia botaoAparencia = new BotaoAparencia(ItemDoMenu.APARENCIA);
    static BotaoArquivo botaoArquivo = new BotaoArquivo(ItemDoMenu.ARQUIVO);
    static BotaoVisualizar botaoVisualizar = new BotaoVisualizar(ItemDoMenu.VISUALIZAR);
    static BotaoEditar botaoEditar = new BotaoEditar(ItemDoMenu.EDITAR);

    public static JMenuItem criarItem(String rotulo, String menu) {
        JMenuItem elementoItem = new JMenuItem(rotulo);

        if (menu.equalsIgnoreCase(ItemDoMenu.ARQUIVO)) {
            botaoArquivo.criarItemArquivo(rotulo, elementoItem);
        } else if (menu.equalsIgnoreCase(ItemDoMenu.EDITAR)) {
            botaoEditar.criarItemEditar(rotulo, elementoItem);
        } else if (menu.equalsIgnoreCase(ItemDoMenu.SELECIONAR)) {
            botaoSelecionar.criarItemSelecionar(rotulo, elementoItem);
        } else if (menu.equalsIgnoreCase(ItemDoMenu.VISUALIZAR)) {
            botaoVisualizar.criarItemVisualizar(rotulo, elementoItem);
        } else if (menu.equalsIgnoreCase(ItemDoMenu.APARENCIA)) {
            botaoAparencia.criarItemAparencia(rotulo);
        }
      return elementoItem;
    }
}
