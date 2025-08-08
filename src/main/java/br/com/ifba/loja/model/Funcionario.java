public class Funcionario extends Usuario {
    private String cargo;
    private String dataAdmissao;
    private double salario;


    public Funcionario(String nome, String senha, String dataNascimento,
                       String cpf, String telefone, String endereco,
                       String email, String cargo, String dataAdmissao, double salario) {
        super(nome, senha, dataNascimento, cpf, telefone, endereco, email);
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getDataAdmissao() {
        return dataAdmissao;
    }
    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
}
