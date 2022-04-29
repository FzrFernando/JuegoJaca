package elementos;

import java.util.Objects;
import java.util.Random;

import logicaJuego.Constantes;

public class Coordenada {

	private int x;
	private int y;
	
	public Coordenada() {
		super();
		Random r1 = new Random();
		this.x = r1.nextInt(Constantes.TAMANNO);
		this.y = r1.nextInt(Constantes.TAMANNO);
	}

	public Coordenada(int x, int y) {
		super();
		if (x < 0 || x >= Constantes.TAMANNO || y < 0 || y >= Constantes.TAMANNO){
			this.x = 0;
			this.y = 0;
		}
		else {
			this.x = x;
			this.y = y;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "Coordenada [x=" + x + ", y=" + y + "]";
	}
	
	public boolean goRight() {
		boolean movimiento;
		if(x == Constantes.TAMANNO-1) {
			movimiento = false;
		}
		else {
			x++;
			movimiento = true;
		}
		return movimiento;
	}
	
	public boolean goLeft() {
		boolean movimiento;
		if(x == 0) {
			movimiento = false;
		}
		else {
			x--;
			movimiento = true;
		}
		return movimiento;
	}
	
	public boolean goUp() {
		boolean movimiento;
		if(y == 0) {
			movimiento = false;
		}
		else {
			y--;
			movimiento = true;
		}
		return movimiento;
	}
	
	public boolean goDown() {
		boolean movimiento;
		if(y == Constantes.TAMANNO-1) {
			movimiento = false;
		}
		else {
			y++;
			movimiento = true;
		}
		return movimiento;
	}
	
	protected Object clone() throws CloneNotSupportedException {
	    return new Coordenada (this.x,this.y);
	}
}
