package model;

import java.time.LocalDate;

public class Cliente extends Usuario {

    //private Carrinho carrinho; está comentado pois envolve outro caso de uso não escolhido
    //private List<Pedido> pedidos; mesma coisa


    public Cliente(String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone) {
        super(nome, sobrenome, senha, dataDeNascimento, cpf, telefone, endereco, email, dadosBancarios);
    }

    public Cliente(int idUsuario, String nome, String sobrenome, LocalDate dataDeNascimento, String senha, String cpf,
                    String email, DadosBancarios dadosBancarios, Endereco endereco, Telefone telefone) {
        super(nome, sobrenome, senha, dataDeNascimento, cpf, telefone, endereco, email, dadosBancarios);
    }

    public Cliente(Usuario usuario) {
        super(usuario);
    }

    public Cliente() {super();}

}

