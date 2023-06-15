package desafio01.controller;

import java.sql.*;
import java.util.List;

import desafio01.CarrinhoDeCompras;
import desafio01.Produtos;



public class ProdutosController {
	int contadorWhileLista = 1;
	public ResultSet verProdutos(Statement declaracao) throws SQLException {

		ResultSet resultSet =  declaracao.executeQuery("SELECT * FROM produtos");
				while(resultSet.next()) {
					System.out.println("Produto: " + contadorWhileLista);
					System.out.println("ID:" + resultSet.getInt("id"));
					System.out.println("Nome:" + resultSet.getString("nome"));
					System.out.println("Valor:" + resultSet.getDouble("valor"));
					System.out.println("Quantidade em estoque:" + resultSet.getInt("estoque"));
					System.out.println("");
				}
				return resultSet;
			}

	public Integer AdicionarProduto(Statement declaracao, Produtos produtoAdicionado){
		try {
			int AdicionaProduto = declaracao.executeUpdate("INSERT INTO produtos"
					+ "(id, nome, valor, estoque) VALUES "
					+ "(" + produtoAdicionado.getId() + ", '" 
					+ produtoAdicionado.getNome() + "', " 
					+ produtoAdicionado.getValor() + "," 
					+ produtoAdicionado.getEstoque() + ")");
			if(AdicionaProduto >= 1){
				System.out.println("Produto inserido com sucesso.");
			}
			else {
				System.out.println("ID do produto já existente!");
			}
			
			return AdicionaProduto;
		} catch (SQLException e) {
			System.out.println("Tivemos algum problema na inserção do produto.");
			return 0;
		}
		
			
	}
	
	public int RemoverProduto(Statement declaracao, Integer id){
		try {
			int RemoverProduto = declaracao.executeUpdate("DELETE FROM produtos WHERE id =" + id);
			if(RemoverProduto >= 1){
				System.out.println("Deletamos o produto com sucesso!");
			}
			else {
				System.out.println("Produto Inexistente.");
			}
			return RemoverProduto;
		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o produto!");
			return 0;
		}

	}

	public Integer AtualizarProduto(Statement declaracao, Produtos produtoAtualizado, Integer produtoId) {
		try {
			int AtualizarProduto = declaracao.executeUpdate("UPDATE produtos SET id = " + produtoAtualizado.getId() 
			+ ", nome= '" + produtoAtualizado.getNome()
			+ "', valor= " + produtoAtualizado.getValor()
			+ ", estoque= " + produtoAtualizado.getEstoque()
			+ " WHERE id = " + produtoId);
			if(AtualizarProduto >= 1) {
				System.out.println("Produto atualizado com sucesso.");
			}
			else {
				System.out.println("Produto inexistente.");
			}
			return AtualizarProduto;
			
		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o produto.");
			return 0;
		}
		

	}

	public ResultSet filtrarProdutos(Statement declaracao, String nome) throws SQLException {
			ResultSet resultSet =  declaracao.executeQuery("SELECT * FROM produtos WHERE nome LIKE '%" + nome + "%'");
			while(resultSet.next()) {
					System.out.println("Produto: " + contadorWhileLista);
					System.out.println("ID:" + resultSet.getInt("id"));
					System.out.println("Nome:" + resultSet.getString("nome"));
					System.out.println("Valor:" + resultSet.getDouble("valor"));
					System.out.println("Quantidade em estoque:" +resultSet.getInt("estoque"));
					System.out.println("");
					contadorWhileLista++;
			}
			return resultSet;

	}

	public Integer finalizarCompra(Statement declaracao, List<CarrinhoDeCompras> carrinhoDeCompras) {
		
		
		return 0;
	}
	
}
