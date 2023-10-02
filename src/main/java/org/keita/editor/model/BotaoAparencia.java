package org.keita.editor.model;

import org.keita.editor.view.Painel;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.ArrayList;

public class BotaoAparencia extends ItemDoMenu {

    private static BarraDeAumentoDeFonte barraDeAumentoDeFonte;
    public BotaoAparencia(String s) {
        super(s);
    }

    public BotaoAparencia(String s, BarraDeAumentoDeFonte barraDeAumentoDeFonte) {
        super(s);
        this.barraDeAumentoDeFonte = barraDeAumentoDeFonte;
    }

    private final String normal = TipoDeFundo.NORMAL.getNome();
    private final String dark = TipoDeFundo.DARK.getNome();
    private String tipoDeFundo = normal;

    public void criarItemAparencia(String rotulo) {
        JMenuItem elementoItem = new JMenuItem(rotulo);
        this.add(elementoItem);
        if (rotulo.equalsIgnoreCase(normal)) {
            elementoItem.addActionListener(e -> {
                tipoDeFundo = normal;
                if (abaTPane.getTabCount() > 0) {
//                    aplicarFundo(tipoDeFundo, Painel.getTamanhoDaFonte(), listaDeAreasDeTexto);
                    aplicarFundo(tipoDeFundo, listaDeAreasDeTexto);
                }
            });
        } else if (rotulo.equalsIgnoreCase(dark)) {
            elementoItem.addActionListener(e -> {
                tipoDeFundo = dark;
                if (abaTPane.getTabCount() > 0) {
//                    aplicarFundo(tipoDeFundo, Painel.getTamanhoDaFonte(), listaDeAreasDeTexto);
                    aplicarFundo(tipoDeFundo, listaDeAreasDeTexto);
                }
            });
        }
    }

    public static void aplicarFundo(String tipoDeFundo, ArrayList<JTextPane> listaDeAreasDeTexto) {
        if (tipoDeFundo.equalsIgnoreCase(TipoDeFundo.NORMAL.getNome())) {
            for (int i = 0; i < listaDeAreasDeTexto.size(); i++) {
                listaDeAreasDeTexto.get(i).selectAll();

                StyleContext styleContext = StyleContext.getDefaultStyleContext();

                //Para a cor do texto
                AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.BLACK);

                // Para o tipo de texto
                attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Arial");

                attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontSize, barraDeAumentoDeFonte.getValue());

                listaDeAreasDeTexto.get(i).setCharacterAttributes(attributeSet, false);
                listaDeAreasDeTexto.get(i).setBackground(Color.WHITE);
            }
        } else if (tipoDeFundo.equalsIgnoreCase(TipoDeFundo.DARK.getNome())) {
            for (int i = 0; i < listaDeAreasDeTexto.size(); i++) {
                listaDeAreasDeTexto.get(i).selectAll();

                StyleContext styleContext = StyleContext.getDefaultStyleContext();

                //Para a cor do texto
                AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.WHITE);

                // Para o tipo de texto
                attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Arial");

                attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontSize, barraDeAumentoDeFonte.getValue());

                listaDeAreasDeTexto.get(i).setCharacterAttributes(attributeSet, false);
                listaDeAreasDeTexto.get(i).setBackground(Color.BLACK);
            }
        }
    }
}
