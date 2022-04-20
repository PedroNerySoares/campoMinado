package br.com.cod3Br.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cod3Br.cm.execao.ExplosaoException;
import br.com.cod3Br.cm.execao.SairException;
import br.com.cod3Br.cm.modelo.Tabuleiro;

public class TabuleiroConsole {
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar=true;
			while(continuar) {
				
				cicloDoJogo();
				System.out.println("Outra partida: (S/N)");
				String resposta = entrada.nextLine();
				if("N".equalsIgnoreCase(resposta)) {
					continuar = false;
				
				}else{
					tabuleiro.reiniciar();
				};
			}
		} catch (SairException e) {
			System.out.println("Tchau!!!!");
		}finally {
			entrada.close();
			
		}
	}

	private void cicloDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()){
				System.out.println(tabuleiro.toString());
				String digitado = caputrarValorDigitado("Digite (x,y): ");
				Iterator<Integer> xy= Arrays.stream(digitado.split(","))
					.map(e->Integer.parseInt(e.trim())).iterator();
//				System.out.println(xy.next());
//				System.out.println(xy.next());
				
				digitado = caputrarValorDigitado("1-abrir 2-Marcar/Desmarcar");	
				
				
				
				if("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(xy.next(),xy.next());
				}else if("2".equalsIgnoreCase(digitado)){
					tabuleiro.alternarMarcacao(xy.next(),xy.next());
				}
			}
			System.out.println("Voce Ganhou!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Voce Perdeu!");
		}
	}
	private String  caputrarValorDigitado(String text) {
		System.out.println(text);
		String digitado = entrada.nextLine();
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
	
	

}
