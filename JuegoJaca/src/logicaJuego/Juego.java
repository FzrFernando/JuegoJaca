package logicaJuego;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import elementos.*;

public class Juego {

	private HashMap<Coordenada, Element> tablero;
	private ArrayList<Coordenada> coordenadaJugadores;
	private int jugadorJuega;
	private int dado;
	
	public Juego(PlayerType[] jugadores) {
		super();
	}

	private void crearTablero() {
		crearRocas();
		crearGemas();
		crearPociones();
		crearDinero();
	}
	
	private boolean crearJugador(PlayerType jugador) { //No estoy muy seguro de este m�todo
		boolean creado = false;
		for(PlayerType pt1 : PlayerType.values()) {
			if (pt1.equals(jugador) && creado!= true) {
				Jugador j1 = new Jugador(jugador);
				creado = true;
			}
		}
		return creado;
	}
	
	private void crearRocas() {
		for (int i=0;i<Constantes.NUM_ROCAS;i++) {
			Element r = new Element(ElementType.valueOf("ROCA"));
		}
	}
	
	private void crearGemas() {
		for (int i=0;i<Constantes.NUM_GEMAS;i++) {
			Element g = new Element(ElementType.valueOf("GEMAS"));
		}
	}
	
	private void crearPociones() {
		for (int i=0;i<Constantes.NUM_POCIONES;i++) {
			Element p = new Element(ElementType.valueOf("POCIONES"));
		}
	}
	
	private void crearDinero() {
		for (int i=0;i<Constantes.NUM_DINERO;i++) {
			Element d = new Element(ElementType.valueOf("DINERO"));
		}
	}
	
	/**
	 * Escribe el tablero en formato no gráfico. Devuelve el string con la
	 * información
	 */
	@Override
	public String toString() {
		StringBuilder resul = new StringBuilder();
		resul.append(barra());
		resul.append("     |");

		for (int fila = 0; fila < Constantes.TAMANNO; fila++) {
			for (int columna = 0; columna < Constantes.TAMANNO; columna++) {
				Coordenada coor = new Coordenada(columna, fila);

				if (this.tablero.get(coor) != null) {
					resul.append(" " + this.tablero.get(coor).getType().getSymbol() + " ");
				} else {
					resul.append("   ");
				}

				resul.append("|");
			}
			resul.append("\n");
			resul.append(barra());
			resul.append("     |");
		}
		resul.delete(resul.length() - 5, resul.length());
		return resul.toString();
	}

	public boolean isTerminado() {
		boolean terminado = false;
		if(coordenadaJugadores.size() == 1) {
			terminado = true;
		}
		return terminado;
	}
	

	/**
	 * Simplemente escribe una barra en pantalla
	 * 
	 * @return
	 */
	private String barra() {
		StringBuilder resul = new StringBuilder();
		resul.append("     ");
		for (int i = 0; i < Constantes.TAMANNO * 4; i++) {
			resul.append("-");
		}
		resul.append("\n");
		return resul.toString();
	}

	public String imprimeNombreJugadores() {
		StringBuilder nombresJugadores = new StringBuilder();
		//Un for de las coordenadas de los jugadores y mostrar getPlayer?
		return nombresJugadores.toString();
	}
	
	public String imprimeValoreJugadores() {
		StringBuilder valoresJugadores = new StringBuilder();
		//Un for de las coordenadas de los jugadores y motrar sus pociones, dinero, etc...?
		return valoresJugadores.toString();
	}

	private void eliminarJugador(Coordenada c) {
		if (tablero.get(c) instanceof Jugador) {
			tablero.remove(c);
			coordenadaJugadores.remove(c);
		}
	}
	
	private Coordenada getNextPosition(char posicion) {
		Coordenada c = coordenadaJugadores.get(jugadorJuega);
		//Hay que lanzar excepci�n aqu�?
		if (posicion == 'N') {
			c.goUp();
		}
		else if(posicion == 'E'){
			c.goRight();
		}
		else if (posicion == 'S') {
			c.goDown();
		}
		else {
			c.goLeft();
		}
		return c;
	}
	
	private void cambiaJugadorAPosicion(Coordenada c) {
		Coordenada sitioActual = coordenadaJugadores.get(jugadorJuega);
		Jugador j = (Jugador) tablero.get(sitioActual);
		tablero.put(c, j);
		tablero.remove(sitioActual);
		coordenadaJugadores.remove(jugadorJuega);
		coordenadaJugadores.add(jugadorJuega, c);
	}
	
	
	/**
	 * Mover el jugador
	 * 
	 * @param direction
	 * @return
	 * @throws JuegoException
	 * @throws JugadorException
	 */
	public String movePlayer(char direction) throws JuegoException, JugadorException {
		// Si no es una dirección válida, mando un exception
		String resul = "";
		Jugador jugador = (Jugador) this.tablero.get(this.coordenadaJugadores.get(jugadorJuega));

		Coordenada coordDestino = getNextPosition(direction);

		// Tengo que ver que hay en la nueva casilla
		Element elemento = this.tablero.get(coordDestino);

		if (elemento != null) { // Hay algo en la casilla
			if (elemento instanceof Jugador) {

				Jugador enemigo = (Jugador) elemento;
				int resultadoLucha = jugador.lucha(enemigo);
				switch (resultadoLucha) {
				case Constantes.EMPATE:
					resul = "Empate entre los jugadore";
					break;
				case Constantes.GANA_USA_POCIMA:
					resul = "El jugador " + jugador.getNombre() + " gana. Le quita una pócima al enemigo";
					break;
				case Constantes.GANA_DINERO:
					resul = "El jugador " + jugador.getNombre() + " gana. Le quita el dinero al enemigo";
					break;
				case Constantes.GANA_MUERE:
					resul = "El jugador " + jugador.getNombre() + " gana. El enemigo muere";
					this.eliminarJugador(coordDestino);
					// Si se elimina el jugador que juega el dado se pone a 0 para que no siga
					// tirando
					break;
				case Constantes.PIERDE_USA_POCIMA:
					resul = "El enemigo " + enemigo.getNombre() + " gana. Le quita una pócima al jugador";
					break;
				case Constantes.PIERDE_DINERO:
					resul = "El enemigo " + enemigo.getNombre() + " gana. Le quita el dinero al jugador";
					break;
				case Constantes.PIERDE_MUERE:
					resul = "El enemigo " + enemigo.getNombre() + " gana. El jugador muere";
					this.eliminarJugador(this.coordenadaJugadores.get(jugadorJuega));
					dado = 0;
					// Decrementamos en uno el jugador, para que no se salte al siguiente
					// ya que al borrarlo el siguiente apunta al siguiente, y al incrementarlo
					// se le salta
					this.jugadorJuega--;
					break;
				}
				// Después de la lucha los jugadores no se mueven
			} else if (elemento.getType() == ElementType.ROCA) {
				int resultadoRoca = jugador.encuentraRoca();
				switch (resultadoRoca) {
				case Constantes.ROMPE_ROCA_CON_GEMA:
					resul = "El jugador " + jugador.getNombre() + " rompe la roca con una gema";
					this.cambiaJugadorAPosicion(coordDestino);
					break;

				case Constantes.GANA_A_LA_ROCA:
					resul = "El jugador " + jugador.getNombre() + " gana a la roca";
					this.cambiaJugadorAPosicion(coordDestino);
					break;

				case Constantes.PIERDE_A_LA_ROCA:
					resul = "El jugador " + jugador.getNombre() + " pierde. No se mueve";
					break;
				}
			} else if (elemento.getType() == ElementType.GEMA) {
				jugador.encuentraGema();
				this.cambiaJugadorAPosicion(coordDestino);

			} else if (elemento.getType() == ElementType.DINERO) {
				jugador.encuentraDinero();
				this.cambiaJugadorAPosicion(coordDestino);

			} else if (elemento.getType() == ElementType.POCION) {
				jugador.encuentraPocion();
				this.cambiaJugadorAPosicion(coordDestino);

			}

		} else {
			this.cambiaJugadorAPosicion(coordDestino);
		}

		return resul;
	}

	public void proximoJugador() {
		jugadorJuega++;
		if (jugadorJuega == (Constantes.NUM_JUGADORES - 1)) {
			jugadorJuega = 0;
		}
	}
	
	public String getGanador() {
		String resul = "";
		Jugador aux;
		if (coordenadaJugadores.size() == 1) {
			aux = (Jugador) tablero.get(coordenadaJugadores.get(0));
			resul = aux.toString();
		} else {
			for (Element e : tablero.values()) {
				if (e instanceof Jugador) {
					if (((Jugador) e).getDinero() == Constantes.DINERO) {
						resul = e.toString();
					}
				}
			}
		}
		return resul;
	}
	
	public String getNombreJugadorQueJuega() {
		Jugador aux = (Jugador) tablero.get(coordenadaJugadores.get(jugadorJuega));
		return aux.getNombre();
	}
	
	public int getMovimientoJugador() {
		Coordenada c = coordenadaJugadores.get(jugadorJuega);
		Jugador aux = (Jugador) tablero.get(c);
		return aux.getVelocidadParaLuchar();
	}
	
	public int getValorDado() {
		return dado;
	}
	
	public void decrementoDado() {
		dado--;
	}
}
