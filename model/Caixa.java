import java.time.LocalDate;

public class Caixa {
    private int id;
    private double valor;
    private LocalDate data;
    private String formaPagamento;
    private int idVenda;  

    
    public Caixa(int id, double valor, LocalDate data, String formaPagamento, int idVenda) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.formaPagamento = formaPagamento;
        this.idVenda = idVenda;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    // Mostrar os dados
    public void exibirDadosCaixa() {
        System.out.println("=== DADOS DO CAIXA ===");
        System.out.println("ID: " + id);
        System.out.println("Valor: R$ " + valor);
        System.out.println("Data: " + data);
        System.out.println("Forma de Pagamento: " + formaPagamento);
        System.out.println("ID da Venda: " + idVenda);
    }
}
