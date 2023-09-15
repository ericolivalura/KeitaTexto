package org.keita.editor.model;

import org.keita.editor.util.Utilidades;

public class BotaoVisualizar extends ItemDoMenu{
    public BotaoVisualizar(String s) {
        super(s);
        numeracao = false;
    }

    private boolean numeracao;

    public void numerarOuNao() {
        numeracao = !numeracao;
        Utilidades.verNumeracao(numeracao, listaDeAreasDeTexto, listaDeScrolls);
    }
}
