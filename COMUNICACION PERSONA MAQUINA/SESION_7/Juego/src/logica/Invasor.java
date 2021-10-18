package logica;

/**
 * Clase que deriva de casilla
 * @author ELOY
 *
 */
public class Invasor extends Casilla{
	
	boolean eliminado;
	
	public boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	public Invasor (int posicion) {
	 setPosicion(posicion);
	 System.out.println("Invasor en: " + getPosicion());
	 setPuntos(3000);
	 setEliminado (false);
	}
}
