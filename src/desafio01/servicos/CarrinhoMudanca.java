package desafio01.servicos;

import java.util.List;
import java.util.Scanner;

import desafio01.CarrinhoDeCompras;
import desafio01.controlador.ControladorCarrinho;

public class CarrinhoMudanca {
	private String idCarrinho;
	private String quantidadeCarrinho;
	private String idAtualizar;
	Integer resultadoEstoque = null;
	ServicoCarrinho servicoCarrinho = new ServicoCarrinho();
	

	public String verificarMudanca(Scanner leitura, Leitor leitor) {
		return leitor.lerMudanca(leitura);
	}
	
	public String mostrarOpcoesMudanca(Scanner leitura) {
		System.out.println("Selecione uma das opções abaixo: \n"
				+ "1. Deletar algum produto do meu carrinho \n"
				+ "2. Atualizar algum produto do meu carrinho \n"
				+ "3. Voltar");		
		return leitura.nextLine();
	}

	public void realizarMudanca(CarrinhoMudanca carrinhoMudanca, Scanner leitura, Leitor leitor, String tipoMudanca, List<CarrinhoDeCompras> carrinhoDeCompras, ControladorCarrinho controladorCarrinho) {
		switch(tipoMudanca) {
		case "1": 
			carrinhoMudanca.deletarCarrinho(leitura, leitor, carrinhoDeCompras, controladorCarrinho);	
			break;
		case "2":
			carrinhoMudanca.atualizarCarrinho(leitura, leitor, carrinhoDeCompras, controladorCarrinho);
			break;
		default: 
			System.out.println("Voltando...");
			break;
		}
	}
	
	private void deletarCarrinho(Scanner leitura, Leitor leitor, List<CarrinhoDeCompras> carrinhoDeCompras, ControladorCarrinho controladorCarrinho) {
		System.out.println("Aqui está o seu carrinho de compras: ");
		controladorCarrinho.mostrarTudo(carrinhoDeCompras);
		idCarrinho = leitor.lerId(leitura);
		servicoCarrinho.conferirIdRemover(carrinhoDeCompras, controladorCarrinho, idCarrinho);
	}
	
	private void atualizarCarrinho(Scanner leitura, Leitor leitor, List<CarrinhoDeCompras> carrinhoDeCompras, ControladorCarrinho controladorCarrinho) {
		System.out.println("Este é o seu carrinho: ");
		controladorCarrinho.mostrarTudo(carrinhoDeCompras);
		idAtualizar = leitor.lerIdAtualizar(leitura);
		idCarrinho = leitor.lerId(leitura);
		quantidadeCarrinho = leitor.lerQuantidadeCarrinho(leitura);
		resultadoEstoque = servicoCarrinho.verificarEstoque(controladorCarrinho, servicoCarrinho, carrinhoDeCompras, idCarrinho, quantidadeCarrinho, resultadoEstoque);
		if(resultadoEstoque >= 1) {
			servicoCarrinho.conferirIdAtualizar(carrinhoDeCompras, controladorCarrinho, idAtualizar, idCarrinho, quantidadeCarrinho);
		}

	}


}
