package org.keita.editor.view;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

public class JanelaPrincipal extends JFrame {
    public JanelaPrincipal() throws HeadlessException, MalformedURLException {
        setBounds(300, 300, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Keita Texto");
        add(new Painel(this));
        setVisible(true); // deve estar no final
    }
}
