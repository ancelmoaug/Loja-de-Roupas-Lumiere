public class Relatorio {
    private String dataRelatorio;
    private String tipoRelatorio;       
    private int quantidadeVendida;
    private double totalVendas;
    private int itensEmEstoque;
    private String responsavel;
    private String observacoes;

    
    public Relatorio(String dataRelatorio, String tipoRelatorio, int quantidadeVendida,
                         double totalVendas, int itensEmEstoque, String responsavel, String observacoes) {
        this.dataRelatorio = dataRelatorio;
        this.tipoRelatorio = tipoRelatorio;
        this.quantidadeVendida = quantidadeVendida;
        this.totalVendas = totalVendas;
        this.itensEmEstoque = itensEmEstoque;
        this.responsavel = responsavel;
        this.observacoes = observacoes;
    }

   
    public String getDataRelatorio() {
        return dataRelatorio;
    }
    public void setDataRelatorio(String dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }
    public String getTipoRelatorio() {
        return tipoRelatorio;
    }
    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
    public double getTotalVendas() {
        return totalVendas;
    }
    public void setTotalVendas(double totalVendas) {
        this.totalVendas = totalVendas;
    }
    public int getItensEmEstoque() {
        return itensEmEstoque;
    }
    public void setItensEmEstoque(int itensEmEstoque) {
        this.itensEmEstoque = itensEmEstoque;
    }
    public String getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
