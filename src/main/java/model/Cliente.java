package model;

import java.time.LocalDate;

public class Cliente extends Usuario {
    
    private int id;


    public Cliente(String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone) {
        super(nome, sobrenome, dataDeNascimento, senha, cpf, email, dadosBancarios, endereco, telefone);
    }

    public Cliente(String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone,
                    int id) {
        super(nome, sobrenome, dataDeNascimento, senha, cpf, email, dadosBancarios, endereco, telefone);
        this.id = id;
    }

    public Cliente(int idUsuario, String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone,
                    int id) {
        super(idUsuario, nome, sobrenome, dataDeNascimento, senha, cpf, email, dadosBancarios, endereco, telefone);
        this.id = id;
    }

    public Cliente() {super();}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
}

