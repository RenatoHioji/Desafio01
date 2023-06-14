package desafio01.controller;

import java.sql.*;

import desafio01.Produtos;



public class ProdutosController {
	public ResultSet verProdutos(Statement statement) throws SQLException {
		ResultSet resultSet =  statement.executeQuery("SELECT * FROM produtos");
				return resultSet;
			}

	public Integer AdicionarProduto(Statement statement, Produtos produtoAdicionado){
		try {
			int AdicionaProduto = statement.executeUpdate("INSERT INTO produtos"
					+ "(id, nome, valor, estoque) VALUES "
					+ "(" + produtoAdicionado.getId() + ", '" 
					+ produtoAdicionado.getNome() + "', " 
					+ produtoAdicionado.getValor() + "," 
					+ produtoAdicionado.getEstoque() + ")");
			System.out.println("Produto inserido com sucesso");
			return AdicionaProduto;
		} catch (SQLException e) {
			System.out.println("Tivemos algum problema na inserção do produto");
			return 0;
		}
		
			
	}
	
	public int RemoverProduto(Statement statement, Produtos produtoRemovido){
		try {
			int RemoverProduto = statement.executeUpdate("DELETE FROM produtos WHERE id =" + produtoRemovido.getId());
			System.out.println("Deletamos o produto com sucesso!");
			return RemoverProduto;
		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o produto!");
			return 0;
		}

	}

	public Integer AtualizarProduto(Statement statement, Produtos produtoAtualizado, Integer produtoId) {
		try {
			int AtualizarProduto = statement.executeUpdate("UPDATE produtos SET id = " + produtoAtualizado.getId() 
			+ ", nome= '" + produtoAtualizado.getNome()
			+ "', valor= " + produtoAtualizado.getValor()
			+ ", estoque= " + produtoAtualizado.getEstoque()
			+ " WHERE id = " + produtoId);
			
			System.out.println("Produto atualizado com sucesso");
			return AtualizarProduto;
			
		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o produto");
			return 0;
		}
		

	}
	
}
