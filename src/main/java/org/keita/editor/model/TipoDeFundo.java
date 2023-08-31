package org.keita.editor.model;

public enum TipoDeFundo {
    NORMAL("normal"),
    DARK("dark");
    String nome;

    TipoDeFundo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
