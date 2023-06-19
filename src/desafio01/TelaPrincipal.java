package desafio01;

import java.util.Scanner;

import desafio01.servicos.ServicoTela;

public class TelaPrincipal {

	public void mostrarMenu() {
		String opcao = null;
		Scanner leitura = new Scanner(System.in);
		ServicoTela telaService = new ServicoTela();
		
		while(opcao != "6") {
			opcao = telaService.mostrarOpcoes(leitura, opcao);
			switch(opcao) {
			case"1":
				telaService.opcao1(leitura);
				break;
			case"2":
				telaService.opcao2(leitura);
				break;
			case "3":
				telaService.opcao3(leitura);
				break;
			case "4":
				telaService.opcao4(leitura);
				break;
			case "5":
				telaService.iniciarOpcao5(leitura, telaService);
				break;
			case "6":
				System.out.println("Desligando...");
				leitura.close();
				System.exit(0);
			}
			
		}
		
	
			
		
	}

}
