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
    private DadosBancarios dadosBancarios;


    public Usuario(String nome, String sobrenome, String senha, LocalDate dataDeNascimento,  String cpf,
                    Telefone telefone, Endereco endereco, String email, DadosBancarios dadosBancarios) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dadosBancarios = dadosBancarios;
    }

    public Usuario(int id, String nome, String sobrenome, String senha, LocalDate dataDeNascimento,  String cpf,
                    Telefone telefone, Endereco endereco, String email, DadosBancarios dadosBancarios) {
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

    public Usuario(Usuario usuario) {
        this.nome = usuario.nome;
        this.sobrenome = usuario.sobrenome;
        this.senha = usuario.senha;
        this.dataDeNascimento = usuario.dataDeNascimento;
        this.cpf = usuario.cpf;
        this.telefone = usuario.telefone;
        this.endereco = usuario.endereco;
        this.email = usuario.email;
        this.dadosBancarios = usuario.dadosBancarios;
    }

    public Usuario(int id, Usuario usuario) {
        this.id = id;
        this.nome = usuario.nome;
        this.sobrenome = usuario.sobrenome;
        this.senha = usuario.senha;
        this.dataDeNascimento = usuario.dataDeNascimento;
        this.cpf = usuario.cpf;
        this.telefone = usuario.telefone;
        this.endereco = usuario.endereco;
        this.email = usuario.email;
        this.dadosBancarios = usuario.dadosBancarios;
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

    public int getIdUsuario() {
        return id;
    }

    public void setIdUsuario(int id) {
        this.id = id;
    }

    public DadosBancarios getDadosBancarios() {
        return dadosBancarios;
    }

    public void setDadosBancarios(DadosBancarios dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
    }
    
}
