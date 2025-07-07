//import java.util.ArrayList;

public class Fornecedor {
    private String razaoSocial;
    private String nomeComercial;
    private String cnpj;
    private String endereco;
    private String telefoneComercial;
    private String emailComercial;

    //private ArrayList<Produto> produtosFornecidos;


    public Fornecedor(String razaoSocial, String nomeComercial, String cnpj, String endereco, String telefoneComercial,
            String emailComercial) {
        this.razaoSocial = razaoSocial;
        this.nomeComercial = nomeComercial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefoneComercial = telefoneComercial;
        this.emailComercial = emailComercial;

        //this.produtosFornecidos = new ArrayList<Produto>();
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeComercial() {
        return nomeComercial;
    }

    public void setNomeComercial(String nomeComercial) {
        this.nomeComercial = nomeComercial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getEmailComercial() {
        return emailComercial;
    }

    public void setEmailComercial(String emailComercial) {
        this.emailComercial = emailComercial;
    }

    /*public void adicionarProdutoFornecido(Produto produto) {
        produtosFornecidos.add(produto);
    }*/
}
