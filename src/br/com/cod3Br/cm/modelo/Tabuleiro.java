package br.com.cod3Br.cm.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import br.com.cod3Br.cm.execao.ExplosaoException;

public class Tabuleiro {
	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarVizingos();
		sortearMinas();
	}
	private void gerarCampos() {
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna< colunas; coluna++) {
				campos.add(new Campo(linha, coluna));
			}
		}
		
	}

	public void abrir(int linha, int coluna) {
		try {
			campos.parallelStream()
				.filter(c-> c.getLinha() == linha && c.getColuna()==coluna)
				.findFirst().ifPresent(c->c.abrir());
		} catch (ExplosaoException e ) {
			
			campos.forEach(c->c.setAberto(true));
			throw e;
		}
	}
	
	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream()
				.filter(c-> c.getLinha() == linha && c.getColuna()==coluna)
				.findFirst().ifPresent(c->c.alternarMarcacao());
	}
	
	
	private void associarVizingos() {
		for (Campo c1:campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}
	private void sortearMinas() {
		long minasArmadas= 0;
		Predicate<Campo> minado  = c->c.isMinado();
		do {
			minasArmadas = campos.stream()
					.filter(minado).count();
			int aleatortio = (int) (Math.random()*campos.size());
			
			campos.get(aleatortio).minar();
			
		}while(minasArmadas<minas);
		
		
		
	}
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c->c.objetivoAlcacado());
	}
	public void reiniciar() {
		campos.stream().forEach(c-> c.reiniciar());
		sortearMinas();
		System.out.println(toString()); 
	}
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("   ");
		for (int c = 0; c < colunas; c++) {
			sb.append(" ");
			sb.append(c);
			sb.append(" ");
		}
		sb.append("\n");
		int i = 0;
		for (int l = 0; l < linhas; l++) {
			sb.append(" ");
			sb.append(l);
			sb.append(" ");
			for (int c = 0; c < colunas; c++) {
			sb.append(" ");
			sb.append(campos.get(i));
			sb.append(" ");
			i++;
		}
			sb.append("\n");
		}
		return sb.toString();
	}
	 

}
