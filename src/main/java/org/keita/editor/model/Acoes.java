package org.keita.editor.model;

public enum Acoes {
    CORTAR("Cortar"),
    COPIAR("Copiar"),
    COLAR("Colar"),
    DESFAZER("Desfazer"),
    REFAZER("Refazer"),
    NOVO_ARQUIVO("Novo Arquivo"),
    //ABRIR("Abrir"),
    ABRIR_ARQUIVO("Abrir arquivo"),
    SALVAR("Salvar"),
    SALVAR_COMO("Salvar como"),
    SELECIONAR_TUDO("Selecionar Tudo");


    private String nome;

    Acoes(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
