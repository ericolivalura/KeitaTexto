package org.keita.editor.util;

import org.keita.editor.model.*;

public class CriadoraDeMenus {
    BotaoSelecionar botaoSelecionar = new BotaoSelecionar(ItemDoMenu.SELECIONAR);
    BotaoAparencia botaoAparencia = new BotaoAparencia(ItemDoMenu.APARENCIA);
    BotaoArquivo botaoArquivo = new BotaoArquivo(ItemDoMenu.ARQUIVO);
    BotaoVisualizar botaoVisualizar = new BotaoVisualizar(ItemDoMenu.VISUALIZAR);
    BotaoEditar botaoEditar = new BotaoEditar(ItemDoMenu.EDITAR);
    public void criarTodosOsMenus(){
        //-----Agregando Opções ao Menu Arquivo
        botaoArquivo.criarMenuDeArquivo();

        //-----Agregando opções ao menu Editar
        botaoEditar.criarMenuEditar();

        //-----Agregando opções ao menu Selecionar
        botaoSelecionar.criarMenuSelecionar();

        //-----Agregando opções ao menu Visualizar
        botaoVisualizar.criarMenuVisualizar();
    }
}
