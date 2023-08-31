package org.keita.editor.model;

import org.keita.editor.util.Utilidades;

import javax.swing.*;

public class BotaoAparencia extends ItemDoMenu {
    public BotaoAparencia(String s) {
        super(s);
    }
//    private final String normal = TipoDeFundo.NORMAL.getNome();
//    private final String dark = TipoDeFundo.DARK.getNome();
//    private String tipoDeFundo = normal;
//    //private JSlider barraDeAumentoDeFonte;
//
//
//
//
//    public void criarItemAparencia(String rotulo, JMenuItem elementoItem, JSlider barraDeAumentoDeFonte, int contadorDeJanelasDeTexto) {
//        add(elementoItem);
//        if (rotulo.equalsIgnoreCase(normal)) {
//            elementoItem.addActionListener(e -> {
//                tipoDeFundo = normal;
//                if (abaTPane.getTabCount() > 0) {
//                    Utilidades.aplicarFundo(contadorDeJanelasDeTexto, tipoDeFundo, barraDeAumentoDeFonte.getValue(), listaDeAreasDeTexto);
//                }
//            });
//        } else if (rotulo.equalsIgnoreCase(dark)) {
//            elementoItem.addActionListener(e -> {
//                tipoDeFundo = dark;
//                if (abaTPane.getTabCount() > 0) {
//                    Utilidades.aplicarFundo(contadorDeJanelasDeTexto, tipoDeFundo, barraDeAumentoDeFonte.getValue(), listaDeAreasDeTexto);
////                    System.out.println(listaDeAreasDeTexto.size());
////                    System.out.println(tipoDeFundo);
//                }
//            });
//        }
//
//    }
}
