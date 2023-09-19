package org.keita.editor.model;

import org.keita.editor.util.CriadoraDeItens;
import org.keita.editor.util.Utilidades;

import javax.swing.*;

public class BotaoVisualizar extends ItemDoMenu{

    BotaoAparencia botaoAparencia = new BotaoAparencia(ItemDoMenu.APARENCIA);
    public BotaoVisualizar(String s) {
        super(s);
        numeracao = false;
    }

    private boolean numeracao;

    public void numerarOuNao() {
        numeracao = !numeracao;
        Utilidades.verNumeracao(numeracao, listaDeAreasDeTexto, listaDeScrolls);
    }


    public void criarMenuVisualizar() {
        CriadoraDeItens.criarItem("Numeração", ItemDoMenu.VISUALIZAR);
        this.addSeparator();
        this.add(botaoAparencia);
        botaoAparencia.criarItemAparencia(TipoDeFundo.NORMAL.getNome());
        botaoAparencia.criarItemAparencia(TipoDeFundo.DARK.getNome());
        }

    public void criarItemVisualizar(String rotulo, JMenuItem elementoItem) {
        this.add(elementoItem);
        if (rotulo.equalsIgnoreCase("numeração")) {
            elementoItem.addActionListener(evento -> numerarOuNao());
        }
    }
}
