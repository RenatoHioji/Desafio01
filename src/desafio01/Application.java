package desafio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import desafio01.controller.ProdutosController;

public class Application {
	public static void main(String args[]) throws SQLException, InterruptedException{
		
		/*Conexão com o banco de dados postgre*/
		String url = "jdbc:postgresql://localhost:5432/Desafio01";
		Connection conexao = DriverManager.getConnection(url, "postgres", "H@080804@h");
		Statement declaracao = conexao.createStatement();
		
		/*Criação de variáveis*/
		Produtos produtoManipulado = null;
		Scanner leitura = new Scanner(System.in);
		String id;
		String nome;
		String valor;
		String estoque;
		String idAtualizar;
		String opcao = "0";
		String simNao;
		String opcaoCarrinho;
		String compras;
		String quantidade;
		String adicionarOutro;
		String mudancaCarrinho;
		String tipoMudanca;
		String confirmacaoCompra;
		String continuarMudanca;
		ResultSet verTudo;
		ResultSet filtrarProduto;

		List<CarrinhoDeCompras> carrinhoDeCompras = new ArrayList<>();
	
		/*Iniciando as funções do controlador*/
		ProdutosController controlador = new ProdutosController();
			
		
		/*Iniciando a interação com o usuário*/
		
		while(opcao != "6"){
		carrinhoDeCompras.clear();
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
			verTudo = controlador.verProdutos(declaracao);
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
				Integer SalvarProduto = controlador.AdicionarProduto(declaracao, produtoManipulado);
				System.out.println("Deseja adicionar um novo produto? (Sim, para continuar)");
				simNao = leitura.nextLine();
			}
			break;
		case "3":
			while(simNao.equalsIgnoreCase("Sim")) {
			System.out.println("Nessa área você poderá remover um produto, vamos começar pelo ID: ");
			System.out.println("Digite o ID do produto a ser excluído: ");
			id = leitura.nextLine();
			Integer RemoverProduto = controlador.RemoverProduto(declaracao, Integer.parseInt(id));
			System.out.println("Deseja removendo produtos? (Sim, para continuar)");
			simNao = leitura.nextLine();
		}
			break;
			
		case "4":
			while(simNao.equalsIgnoreCase("Sim")) {
				System.out.println("Nessa área você poderá atualizar um produto, vamos começar pelo ID: ");
				System.out.println("Digite o ID de atualização do produto: ");
				id = leitura.nextLine();
				System.out.println("Digite o nome de atualização do produto: ");
				nome = leitura.nextLine();
				System.out.println("Digite o valor de atualização do produto: ");
				valor = leitura.nextLine();
				System.out.println("Digite a quantidade em estoque de atualização do produto: ");
				estoque = leitura.nextLine();
				System.out.println("Digite o ID atual do produto a ser atualizado: ");
				idAtualizar = leitura.nextLine();
				produtoManipulado = new Produtos (Integer.parseInt(id), nome, Double.parseDouble(valor), Integer.parseInt(estoque));
				Integer AtualizarProduto = controlador.AtualizarProduto(declaracao, produtoManipulado, Integer.parseInt(idAtualizar));
				System.out.println("Deseja atualizar outro produto? (Sim, para continuar)");
				simNao = leitura.nextLine();
			}
			break;
			
		case "5":
			/*Iniciando a parte de carrinho*/

			while(simNao.equalsIgnoreCase("Sim")) {
				compras = "Sim";
				adicionarOutro = "Sim";
				while(adicionarOutro.equalsIgnoreCase("Sim")) {
					/*Mostrar opções do carrinho*/
					CarrinhoDeCompras.Iniciar();
					opcaoCarrinho = leitura.nextLine();
					/*Mostrar todos os produtos*/
					if(opcaoCarrinho.equalsIgnoreCase("1")){
						verTudo = controlador.verProdutos(declaracao);
					}
					/*Mostrar a filtragem*/
					else if(opcaoCarrinho.equalsIgnoreCase("2")) {
						System.out.println("Digite o nome de um produto para ser pesquisado: ");
						nome = leitura.nextLine();
						filtrarProduto = controlador.filtrarProdutos(declaracao, nome);
					}
					
					/*Se opção for diferente de 1 e 2*/
					else {
						System.out.println("Opção inválida");
					}
					/*Início da manipulação do carrinho*/
					System.out.println("Deseja adicionar algum desses produtos ao seu carrinho?");
					compras = leitura.nextLine();
					while(compras.equalsIgnoreCase("Sim")) {
						
						/* Construção do carrinho*/
						if(compras.equalsIgnoreCase("Sim")) {

							System.out.println("Digite o ID do produto a ser adicionado no carrinho: ");
							id = leitura.nextLine();
							System.out.println("Digite a quantidade desse produto a ser adicionado no carrinho: ");
							quantidade = leitura.nextLine();
							/*Adição de produtos ao carrinho*/
							carrinhoDeCompras.add(new CarrinhoDeCompras(Integer.parseInt(id), Integer.parseInt(quantidade)));
							System.out.println("Produto adicionado na sua lista de carrinho");
							System.out.println("Deseja continuar adicionando algum desses produtos? (Sim, para continuar)");
							compras = leitura.nextLine();
						}
					}						
					
					/*Pergunta se é necessário voltar ao início da sessão para que o usuário procure por outro produto.*/
					System.out.println("Deseja procurar por algum outro produto? (Sim, para continuar)");
					adicionarOutro = leitura.nextLine();
					adicionarOutro.replace("ã", "a");
					while(adicionarOutro.equalsIgnoreCase("Nao")) {
						if(adicionarOutro.equalsIgnoreCase("Nao")) {
							/*Se não precisar, e o carrinho não estiver vazio ele é redirecionado para a sala de compras.*/
							if(!(carrinhoDeCompras.isEmpty())) {
								/* Iniciando a compra.*/
								/*Mostra o carrinho de compras.*/
								System.out.println("Aqui está o seu carrinho de compras: ");
								CarrinhoDeCompras.MostrarTudo(carrinhoDeCompras);
								/*Pergunta se podemos continuar a compra*/
								System.out.println("Gostaria de mudar alguma coisa? (Sim/Não)");
								mudancaCarrinho = leitura.nextLine();
								mudancaCarrinho.replace("ã", "a");
								/*Se o usuário escolher mudar algo do carrinho de compras.*/
								if(mudancaCarrinho.equalsIgnoreCase("Sim")) {
									/*Remoção ou Atualização do carrinho.*/
									continuarMudanca = "Sim";
									while(continuarMudanca.equalsIgnoreCase("Sim")) {
										CarrinhoDeCompras.mostrarOpcoes();
										System.out.println("Selecione uma das opções abaixo:");
										tipoMudanca = leitura.nextLine();
										switch(tipoMudanca) {
										case "1":
											break;
										case "2":
											break;
										case "3":
											break;
										default:
											System.out.println("Opção Inválida!");
							
										}
									}
								}
								/*Se estiver tudo certo, ele é redirecionado para a confirmação de compras.*/
								else if(mudancaCarrinho.equalsIgnoreCase("Nao")) {
									/*Finalizando a compra*/
									System.out.println("Vamos concluir sua compra!");
									System.out.println("Você confirma a realização da compra dos itens acima? (Sim/Não)");
									confirmacaoCompra = leitura.nextLine();
									confirmacaoCompra.replace("ã", "a");
									if(confirmacaoCompra.equals("Sim")) {
										/*Se ele confirmar, a compra é executado*/
										Integer RealizarCompra = controlador.finalizarCompra(declaracao, carrinhoDeCompras);
										break;
									}
									else if(confirmacaoCompra.equals("Nao")) {
										/*Se não estiver tudo certo, ele retornará a perguntar se o carrinho de compras está tudo certo.*/
									System.out.println("Redirecionando para o carrinho de compras...");
									}
									/*Caso não for digiado Sim nem Não;*/
									else {
										System.out.println("Opção Inválida!");
									}
								}
								/*Caso não for digiado Sim nem Não;*/
								else {
									System.out.println("Opção inválida!");
								}						
							}
							else if(carrinhoDeCompras.isEmpty()) {
								/*Se o carrinho esiver vazio, ele será redirecionado ao início da sessão;*/
								System.out.println("Carrinho vazio, redirecionando para o ínicio da sessão...");
								Thread.sleep(200);
								adicionarOutro = "Sim";
							}
							/*Se algum erro acontecer.*/
							else {
								System.out.println("Erro no carrinho!");
							}
						}
					}
					
			}
				/*Pergunta se ele deseja retornar ao menu inicial.*/
				System.out.println("Deseja continuar na sessão de compras? (Sim,para continuar)");
				simNao = leitura.nextLine();
				/*Continua na seção de compras para adicionar outros produtos ao carrinh ou sai da seção de compras, resetando o carrinho.*/
	
			}

			
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
		
		conexao.close();
		declaracao.close();
		
	}
}
