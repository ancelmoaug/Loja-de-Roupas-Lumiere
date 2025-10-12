package model;

import java.time.LocalDate;

public class Funcionario extends Usuario {
    
    private int id;
    private String cargo;
    private LocalDate dataAdmissao;
    private double salario;


    public Funcionario(String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone) {
        super(nome, sobrenome, senha, dataDeNascimento, cpf, telefone, endereco, email, dadosBancarios);
    }

    public Funcionario(String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone,
                    int id, String cargo, LocalDate dataAdmissao, double salario){
        super(nome, sobrenome, senha, dataDeNascimento, cpf, telefone, endereco, email, dadosBancarios);
        this.id = id;
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
    }

    public Funcionario(int idUsuario, String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone,
                    int id, String cargo, LocalDate dataAdmissao, double salario) {
        super(id, nome, sobrenome, senha, dataDeNascimento, cpf, telefone, endereco, email, dadosBancarios);
        this.id = id;
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
    }

    public Funcionario(Usuario usuario, String cargo, LocalDate dataAdmissao, double salario) {
        super(usuario);
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
    }

    public Funcionario() {super();}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    // retorna id do usuario
    public int getIdUsuario() {

        return super.getId();
    }

}
