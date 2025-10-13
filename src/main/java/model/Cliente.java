package model;

import java.time.LocalDate;

public class Cliente extends Usuario {
    
    private int idCliente;


    public Cliente(String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone) {
        super(nome, sobrenome, senha, dataDeNascimento, cpf, telefone, endereco, email, dadosBancarios);
    }

    public Cliente(String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone,
                    int idCliente) {
        super(nome, sobrenome, senha, dataDeNascimento, cpf, telefone, endereco, email, dadosBancarios);
        this.idCliente = idCliente;
    }

    public Cliente(int idUsuario, String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone,
                    int idCliente) {
        super(nome, sobrenome, senha, dataDeNascimento, cpf, telefone, endereco, email, dadosBancarios);
        this.idCliente = idCliente;
    }

    public Cliente(Usuario usuario) {
        super(usuario);
    }

    public Cliente() {super();}

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}

