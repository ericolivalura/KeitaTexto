package org.keita.editor.model;

import org.keita.editor.util.Utilidades;

public class BotaoVisualizar extends ItemDoMenu{
    private void numerarOuNao() {
        numeracao = !numeracao;
        Utilidades.verNumeracao(contadorDeJanelasDeTexto, numeracao, listaDeAreasDeTexto, listaDeScrolls);
    }
}
