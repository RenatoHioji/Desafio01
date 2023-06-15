package desafio01;

import java.util.List;

public class CarrinhoDeCompras {
	Integer idProduto;
	Integer quantidade;
	
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public CarrinhoDeCompras(Integer idProduto, Integer quantidade) {
		super();
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}
	
	public static void MostrarTudo(List<CarrinhoDeCompras> carrinhoDeCompras) {
		int i = 1;
		for(CarrinhoDeCompras carrinhoMostrar : carrinhoDeCompras) {
			System.out.println("Produto: " + i);
			System.out.println("ID do produto: " + carrinhoMostrar.getIdProduto());
			System.out.println("Quantidade: " + carrinhoMostrar.getQuantidade());
			i++;
		}
		
	}
	public static void mostrarOpcoes() {
		System.out.println("1. Excluir um produto do meu carrinho");
		System.out.println("2. Modificar a quantidade de um produto no meu carrinho");
		System.out.println("3. Voltar");
	}
	public static void Iniciar() {
		System.out.println("Seja bem-vindo ao carrinho de compras, para continuar selecione uma das opções abaixo: ");
		System.out.println("1. Buscar por todos os produtos cadastrados. ");
		System.out.println("2. Buscar um produto específico por nome. ");
		
	}
	
	
}
