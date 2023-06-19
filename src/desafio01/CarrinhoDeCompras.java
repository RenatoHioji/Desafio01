package desafio01;

public class CarrinhoDeCompras {

	String idProduto;
	String quantidade;
	

	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public CarrinhoDeCompras(String idProduto, String quantidade) {
		super();
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}
	@Override
	public String toString() {
		return "CarrinhoDeCompras [idProduto=" + idProduto + ", quantidade=" + quantidade + "]";
	}
	
	
	
	
}
