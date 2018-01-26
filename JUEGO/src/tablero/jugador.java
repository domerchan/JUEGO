package tablero;

public class jugador {
	private String nombre;
	private int puntaje;
	
	public jugador(String nombre, int puntaje) {
		super();
		this.nombre = nombre;
		this.puntaje = puntaje;
	}
	public jugador(){
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	@Override
	public String toString() {
		return "jugador [nombre=" + nombre + ", puntaje=" + puntaje + "]";
	}
	
	

}
