package dao;

import java.util.List;

import model.Cliente;

public interface ClienteDAO {
    void inserir(Cliente cliente);
    void atualizar(Cliente cliente);
    void deletar(int id);
    Cliente buscarPorId(int id);
    List<Cliente> listarTodos();
    //List<Pedido> listarPedidosDoCliente(int clienteId);
    //Carrinho buscarCarrinhoDoCliente(int clienteId);
    
}
