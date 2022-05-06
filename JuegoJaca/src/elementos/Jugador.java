package elementos;

public class Jugador extends Element{

	private int dinero;
	private int pociones;
	private int gemas;
	private PlayerType jugador;
	
	public Jugador(PlayerType jugador) {
		super(ElementType.valueOf(jugador.name()));
		this.jugador = jugador;
	}
	
//	public String getNombre() {
//		
//	}
	
	public int getFuerzaParaLuchar() {
		
	}
}
