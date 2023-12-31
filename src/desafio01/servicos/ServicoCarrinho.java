package desafio01.servicos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import desafio01.CarrinhoDeCompras;
import desafio01.controlador.ControladorCarrinho;
import desafio01.controlador.ControladorProduto;

public class ServicoCarrinho {
	String compras = "1";
	int contagem = 0;
	int resultadoEstoque = 0;
	public String Iniciar(Scanner leitura) {
		System.out.println("Seja bem-vindo ao carrinho de compras, para continuar selecione uma das opções abaixo: \n"
				+ "1. Buscar por todos os produtos cadastrados. \n"
				+ "2. Buscar um produto específico por nome. \n"
				+ "3. Ver todas as minhas compras.");
		
		String opcaoCarrinho = leitura.nextLine(); 

		return opcaoCarrinho;
	}
	
	public void mostrarProdutos(String opcaoCarrinho, Scanner leitura, String nome, 
			ControladorProduto controladorProduto) {
		if(opcaoCarrinho.equals("1")){
			controladorProduto.verProdutos();
		}
		else if(opcaoCarrinho.equals("2")) {
			Leitor leitor = new Leitor();
			nome = leitor.lerNome(leitura);
			try {
				controladorProduto.filtrarProdutos(nome);
			} catch (SQLException e) {
				System.out.println("Não foi possível realizar a filtragem e trazer as informações dos produtos.");
			}
		}
		else {
			System.out.println("Opção inválida");
		}
	}
	
	public String mostrarOutrosProdutos(Scanner leitura, String adicionarOutro) {
		System.out.println("Deseja procurar por algum outro produto?\n"
				+ "1.Sim \n"
				+ "2.Não");
		return adicionarOutro = leitura.nextLine();
	}
	
	public String verificarCarrinho(List<CarrinhoDeCompras> carrinhoDeCompras) {
		if(!(carrinhoDeCompras.isEmpty())) {
			return "1";
		}
		else{
			System.out.println("Redirecionando ao menu...");
			return "0";
		}
	}

	public void conferirIdRemover(List<CarrinhoDeCompras> carrinhoDeCompras, ControladorCarrinho controladorCarrinho, 
			String idCarrinho) {
		for(CarrinhoDeCompras carrinhoMostrar : carrinhoDeCompras) {
			if(carrinhoMostrar.getIdProduto().equals(idCarrinho)){
				carrinhoDeCompras = controladorCarrinho.removerProduto(carrinhoDeCompras, carrinhoMostrar, idCarrinho);
				System.out.println("Produto removido com sucesso!");
				break;
			}
			else {
				System.out.println("ID informando não foi encontrado no carrinho!");
			}
		}	
		
	}

	public void conferirIdAtualizar(List<CarrinhoDeCompras> carrinhoDeCompras, ControladorCarrinho controladorCarrinho,
			String idAtualizar, String idCarrinho, String quantidadeCarrinho) {
		for(CarrinhoDeCompras carrinhoMostrar : carrinhoDeCompras) {
			if(carrinhoMostrar.getIdProduto().equals(idAtualizar)){
				carrinhoDeCompras = controladorCarrinho.atualizarProduto(carrinhoDeCompras,carrinhoMostrar, idCarrinho, quantidadeCarrinho);
				System.out.println("Produto atualizado com sucesso");
				break;
			}
			else {
				System.out.println("ID do produto informado não foi encontrado no carrinho");
			}
		}
	}
	public List<CarrinhoDeCompras> manipularProdutos(Scanner leitura, Leitor leitor, 
			List<CarrinhoDeCompras> carrinhoDeCompras,ServicoCarrinho servicoCarrinho, ControladorCarrinho controladorCarrinho) {
		
		String idCarrinho;
		String quantidadeCarrinho;
		compras = "1";
		while(compras.equals("1")) {
			compras = leitor.lerCompras(leitura, compras);
			if(compras.equalsIgnoreCase("1")) {
				idCarrinho = leitor.lerId(leitura);
				quantidadeCarrinho = leitor.lerQuantidadeCarrinho(leitura);
				servicoCarrinho.verificarExistencia(controladorCarrinho, servicoCarrinho, carrinhoDeCompras, idCarrinho, quantidadeCarrinho);
			}
			else {
				System.out.println("Voltando...");
			}
		}			
		return carrinhoDeCompras;
	}
	
	private Integer verificarExistencia(ControladorCarrinho controladorCarrinho, ServicoCarrinho servicoCarrinho, 
			List<CarrinhoDeCompras> carrinhoDeCompras, String idCarrinho, String quantidadeCarrinho){

		contagem = controladorCarrinho.verificarExistenciaProduto(servicoCarrinho, idCarrinho, contagem);
		if(contagem >= 1) {
			resultadoEstoque = servicoCarrinho.verificarEstoque(controladorCarrinho, servicoCarrinho, carrinhoDeCompras, idCarrinho, quantidadeCarrinho, resultadoEstoque);	
			if(resultadoEstoque >= 1) {
				servicoCarrinho.adicionarProduto(controladorCarrinho, carrinhoDeCompras, idCarrinho, quantidadeCarrinho);
			}
		}
		else {	
			resultadoEstoque = 0;
			System.out.println("Produto inexistente");
		}
		return resultadoEstoque;
	}
	
	public Integer verificarEstoque(ControladorCarrinho controladorCarrinho, ServicoCarrinho servicoCarrinho, 
			List<CarrinhoDeCompras> carrinhoDeCompras, String idCarrinho, String quantidadeCarrinho, Integer resultadoEstoque) {
		Integer nEstoque = 0;
		nEstoque = controladorCarrinho.verificarEstoque(carrinhoDeCompras, idCarrinho, nEstoque);
			if(Integer.parseInt(quantidadeCarrinho) <= nEstoque) {
				resultadoEstoque = 1;
			}
			else {
				resultadoEstoque = 0;
				System.out.println("O estoque desse produto é menor do que a quantidade que você gostaria de adicionar ao carrinho.");
		}
		return resultadoEstoque;
	}

	public void adicionarProduto(ControladorCarrinho controladorCarrinho, List<CarrinhoDeCompras> 
				carrinhoDeCompras, String idCarrinho, String quantidadeCarrinho) {
		
		if(!(carrinhoDeCompras.isEmpty())) {
			for(CarrinhoDeCompras carrinhoMostrar : carrinhoDeCompras) {
				if(carrinhoMostrar.getIdProduto().equals(idCarrinho)){
					carrinhoDeCompras = controladorCarrinho.atualizarProduto(carrinhoDeCompras, carrinhoMostrar, idCarrinho, quantidadeCarrinho);
					System.out.println("Produto já existia, portanto atualizamos ele no carrinho");
					break;
				}
				else {
					carrinhoDeCompras.add(new CarrinhoDeCompras(idCarrinho, quantidadeCarrinho));
					System.out.println("Produto adicionado ao carrinho!");
					break;
				}
					
			}
		}
		else {
				carrinhoDeCompras.add(new CarrinhoDeCompras(idCarrinho, quantidadeCarrinho));
				System.out.println("Produto adicionado ao carrinho!");
			}	
	}
	
	public int contarResultado(ResultSet resultSet, Integer contagem) {
		try {
			contagem = 0;
			while(resultSet.next()) {
				contagem++;
			}
			if(contagem == 0) {
				contagem = 0;
			}
			else {
				contagem = 1;
			}
		} catch (SQLException e) {
		System.out.println("Não foi possível executar a querry");
		}
		return contagem;
	}

	public void somarCarrinho(ControladorCarrinho controladorCarrinho, List<CarrinhoDeCompras> carrinhoDeCompras, Integer id_vendas) {
		double totalVendas = 0;
		controladorCarrinho.adicionarBanco(carrinhoDeCompras, id_vendas);
		totalVendas = controladorCarrinho.somarTotal(id_vendas, totalVendas);
		System.out.println("O total do carrinho é de: " +  totalVendas);
	}

	public void finalizarCompra(String confirmarCompra, List<CarrinhoDeCompras> carrinhoDeCompras, ControladorCarrinho controladorCarrinho, Integer id_vendas) {
		if(confirmarCompra.equals("1")) {
			controladorCarrinho.finalizarCompra(carrinhoDeCompras, id_vendas);
			controladorCarrinho.corrigirEstoque(carrinhoDeCompras,id_vendas);
			System.out.println("Compra finalizada com sucesso");
		}
		else {
			System.out.println("Voltando...");
		}
		carrinhoDeCompras.clear();
	}

	public Integer criarIdVendas(ControladorCarrinho controladorCarrinho) {
		return (controladorCarrinho.pegarIdVendas());
	}

	public void mostrarCompras(ControladorCarrinho controladorCarrinho) {
		ResultSet resultSet = controladorCarrinho.mostrarCompras();
		try {
			System.out.println("Mostrando todas as compras: ");
			while(resultSet.next()) {
				System.out.println("\nID da Venda: " + resultSet.getInt("id_vendas") 
				+ "\nID do Produto: " + resultSet.getInt("id_produto")
				+ "\nQuantidade do Produto: " + resultSet.getInt("quantidade") + "\n");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível mostrar todas as compras.");
		}
		
	}
}
	

