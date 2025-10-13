package model;

import java.util.List;

import java.util.ArrayList;

public class Fornecedor {
    
    private int id;
    private String razaoSocial;
    private String nomeComercial;
    private String cnpj;
    private Endereco endereco;
    private Telefone telefoneComercial;
    private String emailComercial;

    private List<ProdutoBase> produtosFornecidos;


    public Fornecedor(String razaoSocial, String nomeComercial, String cnpj, Endereco endereco, 
                      Telefone telefoneComercial, String emailComercial) {
        this.razaoSocial = razaoSocial;
        this.nomeComercial = nomeComercial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefoneComercial = telefoneComercial;
        this.emailComercial = emailComercial;

        this.produtosFornecidos = new ArrayList<ProdutoBase>();
    }

    public Fornecedor(int id, String razaoSocial, String nomeComercial, String cnpj, Endereco endereco, 
                      Telefone telefoneComercial, String emailComercial) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeComercial = nomeComercial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefoneComercial = telefoneComercial;
        this.emailComercial = emailComercial;

        this.produtosFornecidos = new ArrayList<ProdutoBase>();
    }

    public Fornecedor() {}

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeComercial() {
        return nomeComercial;
    }

    public void setNomeComercial(String nomeComercial) {
        this.nomeComercial = nomeComercial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(Telefone telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getEmailComercial() {
        return emailComercial;
    }

    public void setEmailComercial(String emailComercial) {
        this.emailComercial = emailComercial;
    }

    public void adicionarProdutoFornecido(ProdutoBase produto) {
        produtosFornecidos.add(produto);
    }

}
