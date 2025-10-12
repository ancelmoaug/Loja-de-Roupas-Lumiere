package model;

public class Tamanho {
    
    private int id;
    private String nomeTamanho;

    
    public Tamanho(String nomeTamanho) {
        this.nomeTamanho = nomeTamanho;
    }

    public Tamanho(int id, String nomeTamanho) {
        this.id = id;
        this.nomeTamanho = nomeTamanho;
    }

    public Tamanho() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeTamanho() {
        return nomeTamanho;
    }

    public void setNomeTamanho(String nomeTamanho) {
        this.nomeTamanho = nomeTamanho;
    }

    
}
