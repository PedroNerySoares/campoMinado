package br.com.codeBr.cm.modelo;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import br.com.codeBr.cm.excecao.ExplosaoException;

public class CampoTeste {
	
	private Campo campo; 
	
	@BeforeEach
	void IniciarCampo() {
		campo = new Campo(3, 3);
		
	}
	
	@Test
	void testeVizinhosReal() {
		Campo vizinho  = new Campo(3, 2);
		 boolean resultado = campo.adicionarVizinho(vizinho);
		 
		assertTrue(resultado);
	}
	@Test  
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	@Test  
	void testeAbrirNaoMinadoNaoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirMinaMarcado(){
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirMinaNaoMarcado(){
		campo.minar();
		assertThrows(ExplosaoException.class,()->{
			assertFalse(campo.abrir());
			
		});
	}
	@Test
	void testeAbrirVizinho(){
		Campo vizinho1 = new Campo(2,2);
		Campo vizinhoDoVizinho01= new Campo(1,1);
		
		vizinho1.adicionarVizinho(vizinhoDoVizinho01);
		
		campo.adicionarVizinho(vizinho1);
		
		campo.abrir();
		assertTrue(vizinho1.isAberto() && vizinhoDoVizinho01.isAberto());


	}
	/*
		 * @Test void testeAbrirVizinho2(){ Campo vizinho1 = new Campo(1,1); Campo
		 * vizinho2 = new Campo(1,1); vizinho2.minar();
		 * 
		 * Campo vizinhoDoVizinho = new Campo(2,2);
		 * vizinhoDoVizinho.adicionarVizinho(vizinho1);
		 * vizinhoDoVizinho.adicionarVizinho(vizinho2);
		 * 
		 * campo.adicionarVizinho(vizinho1); campo.abrir();
		 * 
		 * assertTrue(vizinhoDoVizinho.isAberto() && vizinho1.isFechado() );
		 * 
		 * 
		 * }
		 */

	@Test
	void testeAbrirCampoMarcado(){
		campo.alternarMarcacao();
		assertEquals("X",campo.toString());
		
	}
	
	
	@Test
	void testeAbrirCampoMinado() {
		campo.abrir();
		campo.minar();
		assertEquals("*",campo.toString());
		
	}
	@Test
	void testeAbrirCampo() {
		campo.abrir();
		assertEquals(" ", campo.toString());
	}
	@Test
	void testeCampoMinadoFechado() {
		
		assertEquals("?",campo.toString());
		
	}
	
	
	
}
