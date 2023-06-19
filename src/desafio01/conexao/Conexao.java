package desafio01.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	    private Connection criarConexao;
	    private String URL;
	    private String USUARIO;
	    private String SENHA;
	    
	    public Conexao() {
	       	criarConexao = null;
	    }
	    
	    public Connection conecta() {
	        try {
	        	URL = "jdbc:postgresql://localhost:5432/Desafio01";
	            USUARIO = "postgres";
	            SENHA= "H@080804@h";
	            criarConexao= DriverManager.getConnection(URL, USUARIO, SENHA);
	        } catch (SQLException e) {
	            System.out.println("Falha ao conectar ao banco de dados...");
	        }
	        return criarConexao;
	    }
	    public void fecharConexao() {
	        try {
	            if (criarConexao != null) {
	                criarConexao.close();
	                System.out.println("Conexão foi fechada com sucesso.");
	            }
	        } catch (SQLException e) {
	            System.out.println("Não foi possível desconectar...");
	        }
	    }
}
