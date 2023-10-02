package org.keita.editor.util;

import org.keita.editor.model.*;

import javax.swing.*;

public class CriadoraDeMenu {
//    BotaoSelecionar botaoSelecionar = new BotaoSelecionar(ItemDoMenu.SELECIONAR);
//    BotaoAparencia botaoAparencia = new BotaoAparencia(ItemDoMenu.APARENCIA);
//    BotaoArquivo botaoArquivo = new BotaoArquivo(ItemDoMenu.ARQUIVO);
//    BotaoVisualizar botaoVisualizar = new BotaoVisualizar(ItemDoMenu.VISUALIZAR);
//    BotaoEditar botaoEditar = new BotaoEditar(ItemDoMenu.EDITAR);
    private  BotaoSelecionar botaoSelecionar ;
    private BotaoAparencia botaoAparencia ;
    private  BotaoArquivo botaoArquivo;
    private  BotaoVisualizar botaoVisualizar;
    private  BotaoEditar botaoEditar;

//    public CriadoraDeMenu(BotaoSelecionar botaoSelecionar, BotaoAparencia botaoAparencia, BotaoArquivo botaoArquivo, BotaoVisualizar botaoVisualizar, BotaoEditar botaoEditar) {
//        this.botaoSelecionar = botaoSelecionar;
//        this.botaoAparencia = botaoAparencia;
//        this.botaoArquivo = botaoArquivo;
//        this.botaoVisualizar = botaoVisualizar;
//        this.botaoEditar = botaoEditar;
//    }

    public JMenuBar criarTodosOsMenus(){

        JMenuBar barraDeMenu = new JMenuBar();
        botaoArquivo = new BotaoArquivo(ItemDoMenu.ARQUIVO);
        botaoEditar = new BotaoEditar(ItemDoMenu.EDITAR);
        botaoSelecionar = new BotaoSelecionar(ItemDoMenu.SELECIONAR);
        botaoVisualizar = new BotaoVisualizar(ItemDoMenu.VISUALIZAR);
        //aparencia = new BotaoAparencia(ItemDoMenu.APARENCIA, barraDeAumentoDeFonte);

        //botaoAparencia = new BotaoAparencia(ItemDoMenu.APARENCIA);

        barraDeMenu.add(botaoArquivo);
        barraDeMenu.add(botaoEditar);
        barraDeMenu.add(botaoSelecionar);
        barraDeMenu.add(botaoVisualizar);
        //-----Agregando Opções ao Menu Arquivo
        botaoArquivo.criarMenuDeArquivo();

        //-----Agregando opções ao menu Editar
        botaoEditar.criarMenuEditar();

        //-----Agregando opções ao menu Selecionar
        botaoSelecionar.criarMenuSelecionar();

        //-----Agregando opções ao menu Visualizar
        botaoVisualizar.criarMenuVisualizar();
        return barraDeMenu;
    }
}
