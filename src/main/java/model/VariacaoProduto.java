package model;

public class VariacaoProduto {
    
    private int id;
    private String nomeCor;
    private String hexaDecimalCor;
    private int quantidadeEmEstoque;
    private String urlImagem;


    private ProdutoBase produtoBase;
    private Tamanho tamanho;


    public VariacaoProduto(String nomeCor, String hexadecimalCor, ProdutoBase produtoBase,
                            Tamanho tamanho, int quantidadeEmEstoque, String urlImagem) {
        this.nomeCor = nomeCor;
        this.hexaDecimalCor = hexadecimalCor;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.urlImagem = urlImagem;

        this.tamanho = tamanho;
        this.produtoBase = produtoBase;
    }

    public VariacaoProduto(int id, String nomeCor, String hexadecimalCor, ProdutoBase produtoBase,
                            Tamanho tamanho, int quantidadeEmEstoque, String urlImagem) {
        this.id = id;
        this.nomeCor = nomeCor;
        this.hexaDecimalCor = hexadecimalCor;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.urlImagem = urlImagem;

        this.tamanho = tamanho;
        this.produtoBase = produtoBase;
    }

    public VariacaoProduto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCor() {
        return nomeCor;
    }

    public void setNomeCor(String nomeCor) {
        this.nomeCor = nomeCor;
    }

    public String getHexaDecimalCor() {
        return hexaDecimalCor;
    }

    public void setHexaDecimalCor(String hexaDecimalCor) {
        this.hexaDecimalCor = hexaDecimalCor;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public ProdutoBase getProdutoBase() {
        return produtoBase;
    }

    public void setProdutoBase(ProdutoBase produtoBase) {
        this.produtoBase = produtoBase;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
    
    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

}
