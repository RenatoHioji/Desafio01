package desafio01.controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import desafio01.CarrinhoDeCompras;
import desafio01.Produtos;
import desafio01.conexao.Conexao;

public class ControladorProduto {
	
	int contadorWhileLista = 1;
	Produtos produtoManipulado;
	String comando;
	int quantidadeTotal;
	private Connection conectar;
	private Statement declaracao;
	
	public ControladorProduto() {
        Conexao conexao = new Conexao();
        try {
            conectar = conexao.conecta();
            declaracao = conectar.createStatement();
        } catch (SQLException e) {
            System.out.println("Não foi possível estabelecer a conexão...");
        }
        
	}


	
	public void verProdutos(){
		contadorWhileLista = 1;
		try {
		    ResultSet resultSet = declaracao.executeQuery("SELECT * FROM produtos");
		    while (resultSet.next()) {
		        System.out.println("Produto: " + contadorWhileLista);
		        System.out.println("ID:" + resultSet.getInt("id"));
		        System.out.println("Nome:" + resultSet.getString("nome"));
		        System.out.println("Valor:" + resultSet.getDouble("valor"));
		        System.out.println("Quantidade em estoque:" + resultSet.getInt("estoque"));
		        System.out.println("");
		        contadorWhileLista++;
		    }
		} catch (SQLException e) {
		    System.out.println("Ocorreu um erro na listagem dos produtos.");
		}
	}
	
	public String procurarProdutos(Produtos produtoAdicionado) throws SQLException {
		String confirmacao = null;
		try {
		    this.produtoManipulado = produtoAdicionado;
		    ResultSet resultSet = declaracao.executeQuery("SELECT * FROM produtos");
		    while (resultSet.next()) {
		        if (produtoManipulado.getId() == resultSet.getInt("id")) {
		            quantidadeTotal = produtoManipulado.getEstoque() + resultSet.getInt("estoque");
		            comando = "UPDATE produtos SET estoque = " + quantidadeTotal + " WHERE id= " + produtoManipulado.getId();
		            confirmacao = "Produto Atualizado com sucesso";
		            break;
		        } else {
		            comando = "INSERT INTO produtos"
		                    + "(id, nome, valor, estoque) VALUES "
		                    + "(" + produtoAdicionado.getId() + ", '"
		                    + produtoAdicionado.getNome() + "', "
		                    + produtoAdicionado.getValor() + ","
		                    + produtoAdicionado.getEstoque() + ")";
		            confirmacao = "Produto adicionado com sucesso";
		        }
		    }

		} catch (SQLException e) {
		    System.out.println("Não foi possível inserir o produto.");
		}
		System.out.println(confirmacao);
	    return comando;
	}

	public void adicionarProduto(Produtos produtoAdicionado){
		try {
			declaracao.executeUpdate(procurarProdutos(produtoAdicionado));
		} catch (SQLException e) {
			System.out.println("Tivemos algum problema na inserção do produto.");
		}	
	}
	
	public int removerProduto(Integer id){
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
	public Integer atualizarProduto(Produtos produtoAtualizado, Integer produtoId) {
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

	public ResultSet filtrarProdutos(String nome) throws SQLException {
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

	public Integer finalizarCompra(List<CarrinhoDeCompras> carrinhoDeCompras) {
		
		
		return 0;
	}
	
}
