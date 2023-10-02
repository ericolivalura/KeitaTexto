package org.keita.editor.model;

import org.keita.editor.util.Utilidades;

import javax.swing.*;
import java.util.ArrayList;

public class BarraDeAumentoDeFonte extends JSlider {

    public int getTamanhoDaFonte(){
        return getValue();
    }
    public BarraDeAumentoDeFonte(int min, int max, int value, ArrayList<JTextPane> listaDeAreasDeTexto) {
        super(min, max, value);
        this.setMajorTickSpacing(4);
        this.setMinorTickSpacing(2);
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        this.addChangeListener(evento -> Utilidades.mudarTamanhoDoTexto(this.getValue(), listaDeAreasDeTexto));
    }

//    public JSlider criarBarraDeAumentoDeFonte(ArrayList<JTextPane> listaDeAreasDeTexto) {
//        new BarraDeAumentoDeFonte(8, 36, 14);
//        this.setMajorTickSpacing(4);
//        this.setMinorTickSpacing(2);
//        this.setPaintTicks(true);
//        this.setPaintLabels(true);
//        this.addChangeListener(evento -> Utilidades.mudarTamanhoDoTexto(this.getValue(), listaDeAreasDeTexto));
//        return this;
//    }
}
