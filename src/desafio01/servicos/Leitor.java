package desafio01.servicos;

import java.util.Scanner;

public class Leitor {
	
	public String lerValor(Scanner leitura) {
		System.out.println("Digite o valor do produto: ");
		return leitura.nextLine();
	}

	public String lerId(Scanner leitura) {
		System.out.println("Digite o ID do produto: ");
		return leitura.nextLine();
	}
	
	public String lerNome(Scanner leitura) {
		System.out.println("Digite o nome do produto: ");
		return leitura.nextLine();
	}

	public String lerEstoque(Scanner leitura) {
		System.out.println("Digite a quantidade em estoque do produto: ");
		return leitura.nextLine();
	}

	public String lerContinuar(Scanner leitura) {
		System.out.println("Deseja continuar nessa seção? \n"
				+ "1.Sim \n"
				+ "2.Não");
		return leitura.nextLine();
	}

	public String lerIdAtualizar(Scanner leitura) {
		System.out.println("Digite o ID atual do produto a ser atualizado: ");
		return leitura.nextLine();
	}

	public String lerOpcao(Scanner leitura) {
		System.out.println("Seja bem-vindo!");
		System.out.println("Este é o nosso sistema de e-commerce!");
		System.out.println("Para começar, selecione uma das funções abaixo: \n"
				+ "1. Listar todos os produtos \n"
				+ "2. Quero cadastrar um novo produto! \n"
				+ "3. Irei remover um produto já existente! \n"
				+ "4. Tive uma nova ideia e irei atualizar um de nossos produtos! \n"
				+ "5. Irei realizar compras \n"
				+ "6. Sair");
		return leitura.nextLine();
	}

	public String lerCompras(Scanner leitura, String compras) {
		System.out.println("Deseja adicionar algum desses produtos ao seu carrinho? \n"
				+ "1.Sim \n"
				+ "2.Não");
		return leitura.nextLine();
		
	}

	public String lerQuantidadeCarrinho(Scanner leitura) {
		System.out.println("Digite a quantidade desse produto a ser adicionado no carrinho");
		return leitura.nextLine();
	}

	public String lerIdProdutoCarrinho(Scanner leitura) {
		System.out.println("Digite o ID do produto a ser adicionado no carrinho: ");
		return leitura.nextLine();
	}

	public String lerMudanca(Scanner leitura) {
		System.out.println("Gostaria de mudar alguma coisa? \n1.Sim \n2.Não");
		return leitura.nextLine();
		
	}

}
