package model;

public class DadosBancarios {
    
    private int id;
    private String codigoAgencia;
    private String numeroConta;
    private String codigoBanco;


    public DadosBancarios(int id, String codigoAgencia, String numeroConta, String codigoBanco) {
        this.id = id;
        this.codigoAgencia = codigoAgencia;
        this.numeroConta = numeroConta;
        this.codigoBanco = codigoBanco;
    }

    public DadosBancarios(String codigoAgencia, String numeroConta, String codigoBanco) {
        this.codigoAgencia = codigoAgencia;
        this.numeroConta = numeroConta;
        this.codigoBanco = codigoBanco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }
    
}
