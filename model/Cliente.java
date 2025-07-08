import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private static List<Cliente> clientes;

 
    public Cliente(String nome, String senha, String dataNascimento,
                       String cpf, String telefone, String endereco,
                       String email, String cargo) {
        super(nome, senha, dataNascimento, cpf, telefone, endereco, email);
        clientes = new ArrayList<Cliente>();
    }


    public static void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public static void atualizarCliente(String cpf, Cliente novoCliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cpf)) {
                clientes.set(i, novoCliente);
                break;
            }
        }
    }

    public static void removerCliente(String cpf) {
        clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
    }

    public static void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public static Cliente pesquisarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "CPF='" + getCpf() + '\'' +
                ", dataNascimento=" + getDataDeNascimento() +
                ", contato='" + getTelefone() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                '}';
    }

  
}

