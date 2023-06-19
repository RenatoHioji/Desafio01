package desafio01.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import desafio01.CarrinhoDeCompras;
import desafio01.Produtos;
import desafio01.controlador.ControladorCarrinho;
import desafio01.controlador.ControladorProduto;

public class ServicoTela {
	
	String simNao = "1";
	String id;
	String nome;
	String valor;
	String estoque;
	String opcaoMudanca;
	String verificarCarrinho = "1";
	String verificarMudanca = "0";
	String tipoMudanca;
	String confirmarCompra;
	Produtos produtoManipulado;
	ControladorProduto controladorProduto = new ControladorProduto();
	ControladorCarrinho controladorCarrinho = new ControladorCarrinho();
	ServicoCarrinho servicoCarrinho = new ServicoCarrinho();
	Leitor leitor = new Leitor();
	
	
	public String mostrarOpcoes(Scanner leitura, String opcao ) {
		opcao = leitor.lerOpcao(leitura);
		return opcao;
	}
	
	public void opcao1(Scanner leitura) {
		simNao = "1";
		while(simNao.equals("1")) {
			controladorProduto.verProdutos();
			simNao = leitor.lerContinuar(leitura);
		}
	}
	
	public void opcao2(Scanner leitura) {
		simNao = "1";
		while(simNao.equals("1")) {
			System.out.println("Nessa área você poderá adicionar um novo produto, vamos começar pelo ID: ");
			
			id = leitor.lerId(leitura);
			nome = leitor.lerNome(leitura); 
			valor = leitor.lerValor(leitura);
			estoque = leitor.lerEstoque(leitura);
			if(Double.parseDouble(valor) < 0 || Integer.parseInt(estoque) < 0 || Integer.parseInt(id) <= 0) {
				System.out.println("Os valores de valor, estoque ou ID não podem ser negativos!");
			}
			else {
				produtoManipulado = new Produtos (Integer.parseInt(id), nome, Double.parseDouble(valor), Integer.parseInt(estoque));
				controladorProduto.adicionarProduto(produtoManipulado);
			}
			simNao = leitor.lerContinuar(leitura);
		}
	}
	
	public void opcao3(Scanner leitura) {
		simNao = "1";
		while(simNao.equals("1")) {
			System.out.println("Nessa área você poderá remover um produto: ");
			id = leitor.lerId(leitura); 
			controladorProduto.removerProduto(Integer.parseInt(id)); 
			simNao = leitor.lerContinuar(leitura);
		}
	}

	public void opcao4(Scanner leitura) {
		String idAtualizar = "0";
		simNao = "1";
		while(simNao.equals("1")) {
			System.out.println("Nessa área você poderá atualizar um produto, vamos começar pelo ID: ");
			id = leitor.lerId(leitura);
			nome = leitor.lerNome(leitura); 
			valor = leitor.lerValor(leitura);
			estoque = leitor.lerEstoque(leitura);
			idAtualizar = leitor.lerIdAtualizar(leitura);
			produtoManipulado = new Produtos (Integer.parseInt(id), nome, Double.parseDouble(valor), Integer.parseInt(estoque));
			controladorProduto.atualizarProduto(produtoManipulado, Integer.parseInt(idAtualizar));
			simNao = leitor.lerContinuar(leitura);
		}
	}
	
	public void iniciarOpcao5(Scanner leitura, ServicoTela servicoTela) {
			Integer id_vendas = 0;
			String adicionarOutro = "1";
			String opcaoCarrinho;
			List<CarrinhoDeCompras> carrinhoDeCompras = new ArrayList<>();
			id_vendas = servicoCarrinho.criarIdVendas(controladorCarrinho);
			while(adicionarOutro.equals("1")) {
				opcaoCarrinho = servicoCarrinho.Iniciar(leitura); 
				if(opcaoCarrinho.equals("3")) {
					servicoCarrinho.mostrarCompras(controladorCarrinho);
				}
				else if(opcaoCarrinho.equals("1") || opcaoCarrinho.equals("2")) {
					servicoCarrinho.mostrarProdutos(opcaoCarrinho, leitura, nome, controladorProduto);
					carrinhoDeCompras = servicoCarrinho.manipularProdutos(leitura, leitor, carrinhoDeCompras, servicoCarrinho, controladorCarrinho);
					adicionarOutro = servicoCarrinho.mostrarOutrosProdutos(leitura, adicionarOutro);
				}
			}

			while(adicionarOutro.equals("2")) {
				if(verificarCarrinho.equals(servicoCarrinho.verificarCarrinho(carrinhoDeCompras))) {
					System.out.println("Aqui está o seu carrinho de compras:");
					CarrinhoMudanca carrinhoMudanca = new CarrinhoMudanca();	
					controladorCarrinho.mostrarTudo(carrinhoDeCompras);
					opcaoMudanca = carrinhoMudanca.verificarMudanca(leitura,leitor);		
					if(opcaoMudanca.equals("2")){
						servicoCarrinho.somarCarrinho(controladorCarrinho, carrinhoDeCompras, id_vendas);
						confirmarCompra = leitor.lerContinuar(leitura);
						servicoCarrinho.finalizarCompra(confirmarCompra, carrinhoDeCompras, controladorCarrinho, id_vendas);			
					}
					else {
						tipoMudanca = carrinhoMudanca.mostrarOpcoesMudanca(leitura);
						carrinhoMudanca.realizarMudanca(carrinhoMudanca,leitura, leitor, tipoMudanca, carrinhoDeCompras, controladorCarrinho);
					}
				}
				else {
					System.out.println("Retornando ao menu...");
					adicionarOutro = "0";
				}
			}
		
			
			
		}
	
	
	

}
