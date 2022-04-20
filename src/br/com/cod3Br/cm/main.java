package br.com.cod3Br.cm;

import javax.management.openmbean.TabularData;

import br.com.cod3Br.cm.modelo.Tabuleiro;

public class main {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		tabuleiro.abrir(3,3);
//		tabuleiro.abrir(4,4);
		tabuleiro.alternarMarcacao(0,0);
		
		System.out.println(tabuleiro);
		
	}

}
