package model;

import java.util.Date;
import java.util.List;

public class Venda {
    private int id;
    private Date data;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<Produto> produtos;
    private double total;

   
    public Venda(int id, Date data, Cliente cliente, Funcionario funcionario, List<Produto> produtos) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produtos = produtos;
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        double soma = 0;
        for (Produto p : produtos) {
            soma += p.getPreco();
        }
        return soma;
    }

    
    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        this.total = calcularTotal(); 
    }

    @Override
    public String toString() {
        return "Venda ID: " + id + ", Data: " + data + ", Cliente: " + cliente.getNome() +
               ", Funcionario: " + funcionario.getNome() + ", Total: R$ " + total;
    }
}
