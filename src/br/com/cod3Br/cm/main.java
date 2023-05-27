package br.com.cod3Br.cm;

import javax.management.openmbean.TabularData;

import br.com.cod3Br.cm.modelo.Tabuleiro;
import br.com.cod3Br.cm.visao.TabuleiroConsole;

public class main {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
//		System.out.println(tabuleiro.toString());
			new TabuleiroConsole(tabuleiro);
		
	}

}
