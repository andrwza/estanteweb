package br.com.estanteweb.Models;

import java.util.Map;

public class Livro {
    private long id;
    private String titulo;
    private String autor;
    private int ano;
    private double preco;

    public Livro() {
    }

    public static Livro deMap(Map<String, Object> linha) {
        Livro livro = new Livro();
        livro.setId(((Number) linha.get("id")).longValue());
        livro.setTitulo((String) linha.get("titulo"));
        livro.setAutor((String) linha.get("autor"));
        livro.setAno(((Number) linha.get("ano")).intValue());
        livro.setPreco(((Number) linha.get("preco")).doubleValue());
        return livro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return titulo + ", de " + autor + " (R$ " + preco + ")";
    }
}
