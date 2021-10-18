/**
 * 
 */
package logica;

/**
 * @author ELOY
 *
 */
public class Escudo extends Casilla{
	private boolean encontrado = false;
	
	public Escudo(int posicion) {
		setPosicion (posicion);
		setEncontrado(false);
		System.out.println("Escudo en: " + getPosicion());
		setPuntos(500);
	}

	public void setEncontrado(boolean b) {
		this.encontrado = b;
	}
	
	public boolean isEncontrado() {
		return this.encontrado;
	}

}
