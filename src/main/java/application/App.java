package application;

import java.util.List;
import java.util.Scanner;

import controller.ClienteController;
import controller.FornecedorController;
import controller.FuncionarioController;
import controller.ProdutoBaseController;
import model.Categoria;
import model.Cliente;
import model.Fornecedor;
import model.Funcionario;
import model.ProdutoBase;

public class App {

    private static Scanner sc = new Scanner(System.in);
    private static ClienteController clienteController = new ClienteController();
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static FornecedorController fornecedorController = new FornecedorController();
    private static ProdutoBaseController produtoBaseController = new ProdutoBaseController();

    public static void main(String[] args) {
        while (true) {
            limparTela();
            System.out.println("=== LOJA ONLINE ===");
            System.out.println("1 - Login Cliente");
            System.out.println("2 - Login Funcionário");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuLoginCliente();
                case 2 -> menuLoginFuncionario();
                case 3 -> {
                    System.out.println("Encerrando o sistema...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ---------------- MENU CLIENTE ----------------
    private static void menuLoginCliente() {
        limparTela();
        System.out.println("=== LOGIN CLIENTE ===");
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Cliente cliente = clienteController.buscarPorEmail(email);

        if (cliente == null) {
            System.out.println("Cliente não encontrado! Deseja se cadastrar? (s/n)");
            String resp = sc.nextLine();
            if (resp.equalsIgnoreCase("s")) {
                cadastrarCliente();
            }
            return;
        }

        System.out.println("Login realizado com sucesso!");
        menuCliente(cliente);
    }

    private static void menuCliente(Cliente cliente) {
        while (true) {
            limparTela();
            System.out.println("=== MENU CLIENTE ===");
            System.out.println("1 - Minha Conta");
            System.out.println("2 - Listar Produtos por Categoria");
            System.out.println("3 - Voltar ao Menu Inicial");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> mostrarMinhaConta(cliente);
                case 2 -> listarProdutosPorCategoria();
                case 3 -> { return; } // volta ao menu inicial
                default -> System.out.println("Opção inválida!");
            }

            System.out.println("\nPressione ENTER para continuar...");
            sc.nextLine();
        }
    }

    private static void mostrarMinhaConta(Cliente cliente) {
        limparTela();
        System.out.println("=== MINHA CONTA ===");
        System.out.println("Nome: " + cliente.getNome() + " " + cliente.getSobrenome());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Endereço: " + cliente.getEndereco().getRua() + ", " + cliente.getEndereco().getNumero());
    }

    private static void listarProdutosPorCategoria() {
        limparTela();
        System.out.println("=== CATEGORIAS ===");
        System.out.println("1 - Blusas");
        System.out.println("2 - Saias");
        System.out.println("3 - Shorts");
        System.out.println("4 - Calças");
        System.out.println("5 - Macacão");
        System.out.println("6 - Vestido");
        System.out.println("7 - Conjunto");
        System.out.print("Escolha uma categoria: ");

        int cat = sc.nextInt();
        sc.nextLine();

        String nomeCategoria = switch (cat) {
            case 1 -> "BLUSA";
            case 2 -> "SAIA";
            case 3 -> "SHORT";
            case 4 -> "CALÇA";
            case 5 -> "MACACÃO";
            case 6 -> "VESTIDO";
            case 7 -> "CONJUNTO";
            default -> null;
        };

        if (nomeCategoria == null) {
            System.out.println("Categoria inválida!");
            return;
        }

        Categoria categoria = new Categoria(nomeCategoria);
        List<ProdutoBase> produtos = produtoBaseController.buscarPorCategoria(nomeCategoria);

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado nessa categoria.");
        } else {
            System.out.println("\nProdutos disponíveis:");
            for (ProdutoBase p : produtos) {
                System.out.println("- " + p.getNome() + " | R$" + p.getPrecoBase());
            }
        }
    }

    private static void cadastrarCliente() {
        limparTela();
        System.out.println("=== CADASTRO DE CLIENTE ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = sc.nextLine();
        System.out.print("Ano de nascimento: ");
        int ano = sc.nextInt();
        System.out.print("Mês de nascimento: ");
        int mes = sc.nextInt();
        System.out.print("Dia de nascimento: ");
        int dia = sc.nextInt();
        sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Estado: ");
        String estado = sc.nextLine();
        System.out.print("Município: ");
        String municipio = sc.nextLine();
        System.out.print("CEP: ");
        String cep = sc.nextLine();
        System.out.print("Bairro: ");
        String bairro = sc.nextLine();
        System.out.print("Rua: ");
        String rua = sc.nextLine();
        System.out.print("Número: ");
        String numero = sc.nextLine();
        System.out.print("Complemento: ");
        String complemento = sc.nextLine();
        System.out.println("=== DADOS BANCÁRIOS ===");
        System.out.print("Código da agência: ");
        String codigoAgencia = sc.nextLine();
        System.out.print("Número da conta: ");
        String numeroConta = sc.nextLine();
        System.out.print("Código do banco: ");
        String codigoBanco = sc.nextLine();

        clienteController.inserirCliente(nome, sobrenome, ano, mes, dia, senha, cpf, email,
                codigoAgencia, numeroConta, codigoBanco, estado, municipio, cep, bairro, rua, numero, complemento, telefone);

        System.out.println("\nCliente cadastrado com sucesso!");
    }

    // ---------------- MENU FUNCIONÁRIO ----------------
    private static void menuLoginFuncionario() {
        limparTela();
        System.out.println("=== LOGIN FUNCIONÁRIO ===");
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Funcionario funcionario = funcionarioController.buscarPorId(2); // exemplo simples
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        System.out.println("Login realizado com sucesso!");
        menuFuncionario(funcionario);
    }

    private static void menuFuncionario(Funcionario funcionario) {
        while (true) {
            limparTela();
            System.out.println("=== MENU FUNCIONÁRIO ===");
            System.out.println("1 - Meus Dados");
            System.out.println("2 - Cadastrar Fornecedor");
            System.out.println("3 - Cadastrar Produto");
            System.out.println("4 - Voltar ao Menu Inicial");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> mostrarDadosFuncionario(funcionario);
                case 2 -> cadastrarFornecedor();
                case 3 -> cadastrarProduto();
                case 4 -> { return; }
                default -> System.out.println("Opção inválida!");
            }

            System.out.println("\nPressione ENTER para continuar...");
            sc.nextLine();
        }
    }

    private static void mostrarDadosFuncionario(Funcionario funcionario) {
        limparTela();
        System.out.println("=== MEUS DADOS ===");
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("Cargo: " + funcionario.getCargo());
        System.out.println("Email: " + funcionario.getEmail());
    }

    private static void cadastrarFornecedor() {
        limparTela();
        System.out.println("=== CADASTRO DE FORNECEDOR ===");
        System.out.print("Razão Social: ");
        String razao = sc.nextLine();
        System.out.print("Nome Comercial: ");
        String nomeComercial = sc.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = sc.nextLine();
        System.out.print("Estado: ");
        String estado = sc.nextLine();
        System.out.print("Município: ");
        String municipio = sc.nextLine();
        System.out.print("CEP: ");
        String cep = sc.nextLine();
        System.out.print("Bairro: ");
        String bairro = sc.nextLine();
        System.out.print("Rua: ");
        String rua = sc.nextLine();
        System.out.print("Número: ");
        String numero = sc.nextLine();
        System.out.print("Complemento: ");
        String complemento = sc.nextLine();
        System.out.print("Telefone comercial: ");
        String telefone = sc.nextLine();
        System.out.print("Email comercial: ");
        String email = sc.nextLine();

        fornecedorController.inserirFornecedor(razao, nomeComercial, cnpj, estado, municipio, cep, bairro, rua, numero, complemento, telefone, email);
        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    private static void cadastrarProduto() {
        limparTela();
        System.out.println("=== CADASTRO DE PRODUTO ===");

        List<Fornecedor> fornecedores = fornecedorController.listarTodos();

        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado. Cadastre um fornecedor primeiro.");
            return;
        }

        System.out.println("Escolha o fornecedor:");
        for (int i = 0; i < fornecedores.size(); i++) {
            System.out.println((i + 1) + " - " + fornecedores.get(i).getNomeComercial());
        }
        System.out.print("Opção: ");
        int opcFornecedor = sc.nextInt();
        sc.nextLine();
        Fornecedor fornecedor = fornecedores.get(opcFornecedor - 1);

        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Preço base: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        System.out.println("\nEscolha a categoria:");
        System.out.println("1 - Blusas");
        System.out.println("2 - Saias");
        System.out.println("3 - Shorts");
        System.out.println("4 - Calças");
        System.out.println("5 - Macacão");
        System.out.println("6 - Vestido");
        System.out.println("7 - Conjunto");
        System.out.print("Opção: ");
        int cat = sc.nextInt();
        sc.nextLine();

        String nomeCategoria = switch (cat) {
            case 1 -> "Blusas";
            case 2 -> "Saias";
            case 3 -> "Shorts";
            case 4 -> "Calças";
            case 5 -> "Macacão";
            case 6 -> "Vestido";
            case 7 -> "Conjunto";
            default -> null;
        };

        if (nomeCategoria == null) {
            System.out.println("Categoria inválida!");
            return;
        }

        Categoria categoria = new Categoria(nomeCategoria);

        produtoBaseController.inserirProduto(nome, descricao, preco, categoria, null, null, fornecedor);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

