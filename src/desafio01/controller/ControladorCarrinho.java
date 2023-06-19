package desafio01.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import desafio01.CarrinhoDeCompras;
import desafio01.conexao.Conexao;
import desafio01.services.ServicoCarrinho;


public class ControladorCarrinho{
	String id_produtoAdicionar;
	String quantidadeAdicionar;
	private Connection conectar2;
	private Statement declaracao2;
	ResultSet resultSet;
	
	public ControladorCarrinho() {
		
        Conexao conexao2 = new Conexao();
        try {
            conectar2 = conexao2.conecta();
            declaracao2 = conectar2.createStatement();
        } catch (SQLException e) {
            System.out.println("Não foi possível estabelecer a conexão...");
            e.printStackTrace();
        }
	}

	
	public void mostrarTudo(List<CarrinhoDeCompras> carrinhoDeCompras) {
		int i = 1;
		for(CarrinhoDeCompras carrinhoMostrar : carrinhoDeCompras) {
			System.out.println("Produto: " + i);
			System.out.println("ID do produto: " + carrinhoMostrar.getIdProduto());
			System.out.println("Quantidade: " + carrinhoMostrar.getQuantidade());
			i++;
		}	
	}


	public List<CarrinhoDeCompras> removerProduto(List<CarrinhoDeCompras> carrinhoDeCompras, CarrinhoDeCompras carrinhoMostrar, String id) {
		carrinhoDeCompras.remove(carrinhoMostrar);
		return carrinhoDeCompras;
	}

	public List<CarrinhoDeCompras> atualizarProduto(List<CarrinhoDeCompras> carrinhoDeCompras,
		CarrinhoDeCompras carrinhoMostrar, String idCarrinho, String quantidadeCarrinho) {	
		carrinhoDeCompras.remove(carrinhoMostrar);
		carrinhoDeCompras.add(new CarrinhoDeCompras(idCarrinho, quantidadeCarrinho));
		return carrinhoDeCompras;
		
	}

	public Integer verificarExistenciaProduto(ServicoCarrinho servicoCarrinho, String idCarrinho, Integer contagem) {
		try {
			ResultSet resultSet = declaracao2.executeQuery("SELECT * FROM produtos WHERE id =" + idCarrinho);
			contagem = servicoCarrinho.contarResultado(resultSet, contagem);
			System.out.println(contagem);
		} catch (SQLException e) {
			System.out.println("Não foi possível listar os produtos filtrados");
		}
		return contagem;
	}


	public void adicionarBanco(List<CarrinhoDeCompras> carrinhoDeCompras) {

		for(CarrinhoDeCompras carrinhoMostrar : carrinhoDeCompras) {
			id_produtoAdicionar = carrinhoMostrar.getIdProduto();
			quantidadeAdicionar = carrinhoMostrar.getQuantidade();
			try {
				declaracao2.executeQuery("INSERT INTO compras(id_produto, quantidade, id_vendas) VALUES (" 
			+ Integer.getInteger(id_produtoAdicionar) + ", " 
			+ Integer.getInteger(quantidadeAdicionar) + ", 1");				
				
			} catch (SQLException e) {
				System.out.println("Não foi possível inserir os dados do carrinho ao banco.");
			}
		}	
		
		
	}


	public double somarTotal(Integer id_vendas, double valorTotal) {
		try {
			ResultSet resultSet = declaracao2.executeQuery("SELECT sum(valor * quantidade) FROM vendas JOIN produtos ON vendas.id_produto = produtos.id WHERE id_vendas ="+ id_vendas);
			while(resultSet.next()) {
				valorTotal = resultSet.getDouble(0);
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível totalizar o valor do carrinho.");
		}
		
		
		try {
			declaracao2.executeQuery("DELETE * FROM vendas WHERE id_vendas = " + id_vendas);
		} catch (SQLException e) {
			System.out.println("Não foi possível remover os dados do banco.");
		}
		
		return valorTotal;
		
	}


	public void finalizarCompra(List<CarrinhoDeCompras> carrinhoDeCompras, Integer id_vendas){

		for(CarrinhoDeCompras carrinhoMostrar : carrinhoDeCompras) {
			id_produtoAdicionar = carrinhoMostrar.getIdProduto();
			quantidadeAdicionar = carrinhoMostrar.getQuantidade();
			try {
				declaracao2.executeQuery("INSERT INTO compras(id_produto, quantidade, id_vendas) VALUES (" 
			+ Integer.getInteger(id_produtoAdicionar) + ", " 
			+ Integer.getInteger(quantidadeAdicionar) + ", " + id_vendas + "");				
				
			} catch (SQLException e) {
				System.out.println("Não foi possível finalizar a compra.");
			}
		}	
		
	}


	public void corrigirEstoque(List<CarrinhoDeCompras> carrinhoDeCompras, Integer id_vendas) {
		try {
			declaracao2.executeQuery("UDPATE SET estoque = estoque - (SELECT vendas.quantidade FROM vendas "
					+ "WHERE produtos.id = vendas.id_produto AND vendas.id_vendas = "  + id_vendas +") "
					+ "WHERE id IN ( SELECT id_produto FROM vendas WHERE id_vendas = " + id_vendas + ")");
		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o estoque.");
		}
	}


	public Integer pegarIdVendas() {
		int pegarId = 0;
		try {
			resultSet = declaracao2.executeQuery("SELECT id_vendas FROM vendas ORDER BY id_vendas LIMIT 1");
			pegarId = resultSet.getInt("id_vendas");

		} catch (SQLException e) {
			System.out.println("Não foi possível criar um id do carrinho");
		}
		return pegarId;
	
	}


	public ResultSet mostrarCompras() {
		try {
			resultSet = declaracao2.executeQuery("SELECT * FROM vendas ORDER BY id_vendas DESC");
		} catch (SQLException e) {
			System.out.println("Não foi possível listar suas compras");
		}
		return resultSet;
		
	}	
		
}

	
//	public String adicionarProdutos(Scanner leitura, ControladorCarrinho carrinhoController) {
//		carrinhoController.lerInformacaoCarrinho(leitura);
//		String compras;
//		/*Adição de produtos ao carrinho*/
//		System.out.println("Produto adicionado na sua lista de carrinho");
//		System.out.println("Deseja continuar adicionando algum desses produtos? (Sim, para continuar)");
//		compras = leitura.nextLine();
//		return compras;
//	}
//	
//	private List<CarrinhoDeCompras> lerInformacaoCarrinho(Scanner leitura) {
//		String idProduto;
//		String quantidade;
//		idProduto = leitor.lerIdProdutoCarrinho(leitura, idProduto);
//		System.out.println("Digite o ID do produto a ser adicionado no carrinho: ");
//		idProduto = leitura.nextLine();
//		System.out.println("Digite a quantidade desse produto a ser adicionado no carrinho: ");
//		quantidade = leitura.nextLine();
//		carrinhoDeCompras.add(new CarrinhoDeCompras(idProduto, quantidade));
//		return carrinhoDeCompras;
//	}


