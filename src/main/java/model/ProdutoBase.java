package model;

import java.util.ArrayList;
import java.util.List;

public class ProdutoBase {
    
    private int id;
    private String nome;
    private String descricao;
    private double precoBase;
    private Categoria categoria;
    private List<VariacaoProduto> variacoes;
    private List<MedidaTamanho> medidasPorTamanho;
    private Fornecedor fornecedor;


    public ProdutoBase(String nome, String descricao, double precoBase, Categoria categoria,
                        Fornecedor fornecedor) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.categoria = categoria;
        this.fornecedor = fornecedor;

        this.variacoes = new ArrayList<VariacaoProduto>();
        this.medidasPorTamanho = new ArrayList<MedidaTamanho>();
    }

    public ProdutoBase(int id, String nome, String descricao, double precoBase, Categoria categoria,
                        Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.categoria = categoria;
        this.fornecedor = fornecedor;

        this.variacoes = new ArrayList<VariacaoProduto>();
        this.medidasPorTamanho = new ArrayList<MedidaTamanho>();
    }

    public ProdutoBase() {
        this.variacoes = new ArrayList<VariacaoProduto>();
        this.medidasPorTamanho = new ArrayList<MedidaTamanho>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<VariacaoProduto> getVariacoes() {
        return variacoes;
    }

    public void setVariacoes(List<VariacaoProduto> variacoes) {
        this.variacoes = variacoes;
    }

    public List<MedidaTamanho> getMedidasPorTamanho() {
        return medidasPorTamanho;
    }

    public void setMedidasPorTamanho(List<MedidaTamanho> medidasPorTamanho) {
        this.medidasPorTamanho = medidasPorTamanho;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void addMedidaTamanho(MedidaTamanho medidaTamanho) {
        this.medidasPorTamanho.add(medidaTamanho);
    }

    public void addVariacao(VariacaoProduto variacaoProduto) {
        this.variacoes.add(variacaoProduto);
    }
    
}
