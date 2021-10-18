package logica;

public class Meteorito extends Casilla {
	
	private boolean encontrado = false;
	
	public Meteorito(int posicion) {
		setPosicion (posicion);
		setEncontrado(false);
		System.out.println("Meteorito en: " + getPosicion());
		setPuntos(0);
	}

	public void setEncontrado(boolean b) {
		this.encontrado = b;
	}
	
	public boolean isEncontrado() {
		return this.encontrado;
	}
	
}
