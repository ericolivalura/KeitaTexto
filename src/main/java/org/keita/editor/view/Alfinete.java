package org.keita.editor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Alfinete {

    private static String url;
    private static boolean estadoAlfinete;

    public static JLabel inserirAlfineteParaFixarTela(JanelaPrincipal janelaPrincipal) {
        JLabel labelAlfinete = new JLabel();
        url = "src/main/java/org/keita/editor/img/alfinete.png";
        labelAlfinete.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        labelAlfinete.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                estadoAlfinete = !estadoAlfinete;
                janelaPrincipal.setAlwaysOnTop(estadoAlfinete);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                url = "src/main/java/org/keita/editor/img/alfinete_selecao.png";
                labelAlfinete.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (estadoAlfinete) {
                    url = "src/main/java/org/keita/editor/img/alfinete_selecao.png";
                    labelAlfinete.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

                } else {
                    url = "src/main/java/org/keita/editor/img/alfinete.png";
                    labelAlfinete.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                }

            }
        });
        return labelAlfinete;
    }
}
