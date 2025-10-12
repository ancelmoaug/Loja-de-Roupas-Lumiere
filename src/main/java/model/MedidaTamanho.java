package model;

public class MedidaTamanho {
    
    private int id;
    private ProdutoBase produtoBase;
    private Tamanho tamanho;
    private int comprimento;
    private int quadril;
    private int cintura;
    private int busto;
    private int manga;

    
    public MedidaTamanho(ProdutoBase produtoBase, Tamanho tamanho, int comprimento, int quadril, 
                        int cintura, int busto, int manga) {
        this.produtoBase = produtoBase;
        this.tamanho = tamanho;
        this.comprimento = comprimento;
        this.quadril = quadril;
        this.cintura = cintura;
        this.busto = busto;
        this.manga = manga;
    }

    public MedidaTamanho(int id, ProdutoBase produtoBase, Tamanho tamanho, int comprimento, int quadril, 
                        int cintura, int busto, int manga) {
        this.id = id;
        this.produtoBase = produtoBase;
        this.tamanho = tamanho;
        this.comprimento = comprimento;
        this.quadril = quadril;
        this.cintura = cintura;
        this.busto = busto;
        this.manga = manga;
    }

    public MedidaTamanho() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getComprimento() {
        return comprimento;
    }

    public void setComprimento(int comprimento) {
        this.comprimento = comprimento;
    }

    public int getQuadril() {
        return quadril;
    }

    public void setQuadril(int quadril) {
        this.quadril = quadril;
    }

    public int getCintura() {
        return cintura;
    }

    public void setCintura(int cintura) {
       
        this.cintura = cintura;
    }
    public int getBusto() {
        return busto;
    }

    public void setBusto(int busto) {
        this.busto = busto;
    }

    public int getManga() {
        return manga;
    }

    public void setManga(int manga) {
        this.manga = manga;
    }
    
}
