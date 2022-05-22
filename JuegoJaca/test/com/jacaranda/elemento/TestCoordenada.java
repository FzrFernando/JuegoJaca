package com.jacaranda.elemento;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import elementos.Coordenada;

class TestCoordenada {

	Coordenada c1 = new Coordenada(0, 0);
	Coordenada c2 = new Coordenada(-1, -1);
	Coordenada c3 = new Coordenada(-1, 1);
	Coordenada c4 = new Coordenada(1, -1);
	Coordenada c5 = new Coordenada(5, 5);
	Coordenada c6 = new Coordenada(9, 1);

	@Test
	public void testCoordenadaInicial() {
		Coordenada expected = new Coordenada(0, 0);
		Assert.assertEquals(expected, c1);
	}

	@Test
	public void testCoordenadaIncorrecta() {
		Coordenada expected = new Coordenada(0, 0);
		Assert.assertEquals(expected, c2);
	}

	@Test
	public void testCoordenadaXIncorrecta() {
		Coordenada expected = new Coordenada(0, 0);
		Assert.assertEquals(expected, c3);
	}

	@Test
	public void testCoordenadaYIncorrecta() {
		Coordenada expected = new Coordenada(0, 0);
		Assert.assertEquals(expected, c4);
	}

	@Test
	public void testGoDownBien() {
		Assert.assertTrue(c5.goRight());
	}

	@Test
	public void testGoDownMal() {
		Assert.assertFalse(c6.goRight());
	}

	@Test
	public void goRightBien() {
		assertTrue(c1.goRight());
	}

	@Test
	public void goRightMal() {
		Coordenada c8 = new Coordenada(9, 0);
		assertFalse(c8.goRight());
	}

	@Test
	public void goLeftBien() {
		assertTrue(c1.goLeft());
	}

	@Test
	public void goLeftMal() {
		Coordenada c9 = new Coordenada(0, 0);
		assertFalse(c9.goLeft());
	}

	@Test
	public void goUpBien() {
		assertTrue(c1.goUp());
	}

	@Test
	public void goUpMal() {
		Coordenada c15 = new Coordenada(1, 0);
		assertFalse(c15.goUp());
	}

}
