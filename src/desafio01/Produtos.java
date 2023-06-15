package desafio01;

public class Produtos {
	int id;
	Double valor;
	String nome;
	int estoque;
	
	
	public Produtos(int id, String nome,Double  valor, int estoque) {
		super();
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		this.estoque = estoque;
	}

	public String toString() {
		return "Produtos [id=" + id + ", valor=" + valor + ", nome=" + nome + ", estoque=" + estoque + "]";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	
}
