package desafio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import desafio01.controller.ProdutosController;

public class Application {
	public static void main(String args[]) throws SQLException{
		
		/*Conexão com o banco de dados postgre*/
		String url = "jdbc:postgresql://localhost:5432/Desafio01";
		Connection conn = DriverManager.getConnection(url, "postgres", "H@080804@h");
		Statement statement = conn.createStatement();
		
		Integer produtoId = null;
		/*Criação de variáveis*/
		Produtos produtoManipulado = null;
		Scanner leitura = new Scanner(System.in);
		String id;
		String nome;
		String valor;
		String estoque;
		String opcao = "0";
		Integer contadorWhileLista = 1;
		String simNao;
		
		/*Iniciando as funções do controlador*/
		ProdutosController controlador = new ProdutosController();
			
//		Integer SalvarProduto = controlador.AdicionarProduto(statement, produtoAdicionado);	
//		Integer AtualizarProduto = controlador.AtualizarProduto(statement, produtoAtualizado, produtoId);
//		Integer RemoverProduto = controlador.RemoverProduto(statement, produtoRemovido);
		while(opcao != "6"){
		simNao = "Sim";
		System.out.println("Seja bem-vindo!");
		System.out.println("Este é o nosso sistema de e-commerce!");
		System.out.println("Para começar, selecione uma das funções abaixo: ");
		System.out.println("1. Listar todos os produtos");
		System.out.println("2. Quero cadastrar um novo produto!");
		System.out.println("3. Irei remover um produto já existente!");
		System.out.println("4. Tive uma nova ideia e irei atualizar um de nossos produtos!");
		System.out.println("5. Irei realizar compras");
		System.out.println("6. Sair");
		opcao = leitura.nextLine();
		
		switch(opcao) {
		case "1":
			ResultSet verTudo = controlador.verProdutos(statement);
			while(verTudo.next()) {
				System.out.println("Produto: " + contadorWhileLista);
				System.out.println("ID:" + verTudo.getInt("id"));
				System.out.println("Nome:" + verTudo.getString("nome"));
				System.out.println("Valor:" + verTudo.getDouble("valor"));
				System.out.println("Quantidade em estoque:" + verTudo.getInt("estoque"));
				System.out.println("");
				contadorWhileLista++;
			}	
			System.out.println("Digite algo para continuar");
			simNao = leitura.nextLine();
			break;
			
		case "2":
			while(simNao.equalsIgnoreCase("Sim")) {
				System.out.println("Nessa área você poderá adicionar um novo produto, vamos começar pelo ID: ");
				System.out.println("Digite o ID do novo produto: ");
				id = leitura.nextLine();
				System.out.println("Digite o nome do novo produto: ");
				nome = leitura.nextLine();
				System.out.println("Digite o valor do novo produto: ");
				valor = leitura.nextLine();
				System.out.println("Digite a quantidade em estoque do novo produto: ");
				estoque = leitura.nextLine();
				produtoManipulado = new Produtos (Integer.parseInt(id), nome, Double.parseDouble(valor), Integer.parseInt(estoque));
				Integer SalvarProduto = controlador.AdicionarProduto(statement, produtoManipulado);
				System.out.println("Deseja adicionar um novo produto? (Sim, para continuar)");
				simNao = leitura.nextLine();
			}
			break;
		case "3":
			break;
			
		case "4":
			break;
			
		case "5":
			break;
			
		case "6": 
			System.out.println("Desligando o sistema...");
			opcao = "6";
			break;
			
		default: 
			System.out.println("Número digitado não foi encontrado...");
			break;
		}
		}
		
		
//		/*Criando o carrinho de compras*/
//		List<Produtos> carrinhoDeCompras = new ArrayList<>();
//		
//		Produtos prod1 = null;
//		
//		carrinhoDeCompras.add(prod1);
		
		
		
	
//		Produtos prod2 = new Produtos("02", "Flor de Echeveria", 32.2, 43);
//		Produtos prod3 = new Produtos("03", "Flor de Echeveria-Lotus", 32.2, 43);
//		Produtos prod4 = new Produtos("04", "Flor de Rosa Gallia", 32.2, 43);
//		
//		
//		List<Produtos> lista = new ArrayList<>();
//		lista.add(prod1);
//		lista.add(prod2);
//		lista.add(prod3);
//		lista.add(prod4);
		
		
		
		
	}
}
