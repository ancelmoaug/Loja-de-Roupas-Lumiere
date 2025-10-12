package model;

import java.time.LocalDate;

public class Usuario {
    
    private int id;
    private String nome;
    private String sobrenome;
    private String senha;
    private LocalDate dataDeNascimento;
    private String cpf;
    private Telefone telefone;
    private Endereco endereco;
    private String email;


    public Usuario(String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    public Usuario(int id, String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    public Usuario() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
