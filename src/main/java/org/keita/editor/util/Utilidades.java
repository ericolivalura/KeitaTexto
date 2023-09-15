package org.keita.editor.util;

import org.keita.editor.model.TipoDeFundo;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;

public class Utilidades {

    public static void agregarTexto(String linha, JTextPane areaDeTexto) {
        try {
            Document documento = areaDeTexto.getDocument();
            documento.insertString(documento.getLength(), linha, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarNumeracaoInicio(boolean numeracao, JTextPane areaDeTexto, JScrollPane scroll) {
        if (numeracao) {
            scroll.setRowHeaderView(new TextLineNumber(areaDeTexto));
        } else {
            scroll.setRowHeaderView(null);
        }
    }

    public static void verNumeracao(boolean numeracao, ArrayList<JTextPane> listaDeAreasDeTexto, ArrayList<JScrollPane> listaScroll) {
        if (numeracao) {
            for (int i = 0; i < listaDeAreasDeTexto.size(); i++) {
                listaScroll.get(i).setRowHeaderView(new TextLineNumber(listaDeAreasDeTexto.get(i)));
            }
        } else {
            for (int i = 0; i < listaDeAreasDeTexto.size(); i++) {
                listaScroll.get(i).setRowHeaderView(null);
            }
        }
    }

    public static void aplicarFundo(String tipoDeFundo, int tamanho, ArrayList<JTextPane> listaDeAreasDeTexto) {
        if (tipoDeFundo.equalsIgnoreCase(TipoDeFundo.NORMAL.getNome())) {
            for (int i = 0; i < listaDeAreasDeTexto.size(); i++) {
                listaDeAreasDeTexto.get(i).selectAll();

                StyleContext styleContext = StyleContext.getDefaultStyleContext();

                //Para a cor do texto
                AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.BLACK);

                // Para o tipo de texto
                attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Arial");

                attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontSize, tamanho);

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

                attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontSize, tamanho);

                listaDeAreasDeTexto.get(i).setCharacterAttributes(attributeSet, false);
                listaDeAreasDeTexto.get(i).setBackground(Color.BLACK);
            }
        }
    }

    //--------Botão----------
    public static JButton adicionarBotao(String url, Object objetoContenedor, String rotulo) {
        JButton botao = new JButton(
                new ImageIcon(
                        new ImageIcon(url)
                                .getImage()
                                .getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

        botao.setToolTipText(rotulo);
        ((Container) objetoContenedor).add(botao);
        return botao;
    }

    public static void mudarTamanhoDoTexto(int tamanho, ArrayList<JTextPane> listaDeAreasDeTexto) {
        for (int i = 0; i < listaDeAreasDeTexto.size(); i++) {
            JTextPane jTextPane = listaDeAreasDeTexto.get(i);
            jTextPane.selectAll();
            StyleContext styleContext = StyleContext.getDefaultStyleContext();
            AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.FontSize, tamanho);

            jTextPane.setCharacterAttributes(attributeSet, false);
        }
    }

    public static void ativaItens(JMenuItem[] itens) {
        for (JMenuItem item : itens) {
            item.setEnabled(true);
        }
    }

    public static void desativaItens(JMenuItem[] itens) {
        for (JMenuItem item : itens) {
            item.setEnabled(false);
        }
    }
}
