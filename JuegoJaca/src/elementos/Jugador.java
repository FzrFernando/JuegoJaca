package elementos;

import java.util.Random;

public class Jugador extends Element {

	private int dinero;
	private int pociones;
	private int gemas;
	private PlayerType jugador;

	public Jugador(PlayerType jugador) {
		super(ElementType.valueOf(jugador.name()));
		this.jugador = jugador;
	}

	public String getNombre() {
		return jugador.name();
	}

	public int getFuerzaParaLuchar() {
		int fuerzaLucha = (int) (Math.random() * (jugador.getFuerza() - 0 + 1) + jugador.getFuerza());
		return fuerzaLucha;
	}

	private int getFuerza() {
		return jugador.getFuerza();
	}

	private int getMagia() {
		return jugador.getMagia();
	}

	public int getMagiaParaLuchar() {
		int magiaLucha = (int) (Math.random() * (jugador.getMagia() - 0 + 1) + jugador.getMagia());
		return magiaLucha;
	}

	private int getVelocidad() {
		return jugador.getVelocidad();
	}

	public int getVelocidadParaLuchar() {
		int velocidadLucha = (int) (Math.random() * (jugador.getVelocidad() - 0 + 1) + jugador.getVelocidad());
		return velocidadLucha;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) throws JugadorException {
		if (dinero < 0) {
			throw new JugadorException("El dinero no puede ser negativo");
		}
		this.dinero = dinero;
	}

	public int getPociones() {
		return pociones;
	}

	public void setPociones(int pociones) throws JugadorException {
		if (pociones < 0) {
			throw new JugadorException("Las pociones no pueden ser negativas");
		}
		this.pociones = pociones;
	}

	public int getGemas() {
		return gemas;
	}

	public void setGemas(int gemas) throws JugadorException {
		if (gemas < 0) {
			throw new JugadorException("Las gemas no pueden ser negativas");
		}
		this.gemas = gemas;
	}

	public String resumen() {
		return "Jugador [dinero=" + dinero + ", pociones=" + pociones + ", gemas=" + gemas + ", jugador=" + jugador
				+ "]";
	}

	public PlayerType getJugador() {
		return jugador;
	}

	public int lucha(Jugador rival) {
		int pelea = 0;
		if (getFuerzaParaLuchar() == rival.getFuerzaParaLuchar()) {
			pelea = 0;
		}
		else if (getFuerzaParaLuchar() > rival.getFuerzaParaLuchar()) {
			if (rival.pociones > 0) {
				rival.pociones -= 1;
				pelea = 1;
			} else if (rival.dinero > 0) {
				dinero += rival.getDinero();
				rival.dinero = 0;
				pelea = 2;
			} else {
				pelea = 3;
				// No sé si servirá para matar al rival rival = null;
			}
		}
		else {
			if (pociones > 0) {
				pociones -= 1;
				pelea = 4;
			} else if (dinero > 0) {
				rival.dinero += getDinero();
				dinero = 0;
				pelea = 5;
			} else {
				pelea = 6;
				// No sé si servirá para matar al jugador this.jugador = null;
			}
		}
		return pelea;
	}

	public int encuentraRoca() {
		int pasarRoca = 2;
		if (gemas > 0) {
			pasarRoca = 0;
			this.gemas -= 1;
		} 
		else {
			if (getMagia() > 4) {
				pasarRoca = 1;
			}
		}
		return pasarRoca;
	}

	public void encuentraDinero() {
		this.dinero += 1;
	}

	public void encuentraPocion() {
		this.pociones += 1;
	}

	public void encuentraGema() {
		this.gemas += 1;
	}

}
