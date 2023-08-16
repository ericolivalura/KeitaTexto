package org.keita.editor.model;

public enum TipoDeFundo {
    NORMAL("Normal"),
    DARK("Dark");
    String nome;

    TipoDeFundo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
