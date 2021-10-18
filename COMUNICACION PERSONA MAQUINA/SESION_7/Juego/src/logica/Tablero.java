package logica;

public class Tablero {
	
	//Dimension del tablero
	public static final int DIM = 8;
	//Casillas del tablero
	Casilla[] casillas;
	
	/**
	 * Constructor de la clase tablero
	 */
	public Tablero() {
		casillas = new Casilla[DIM];
		for (int i=0;i<DIM;i++)
			casillas[i] = new Espacio(i);
		int posicionInvasor = (int) (Math.random() * DIM); //Se genera un numero aleatorio entre 0 y dim
		casillas[posicionInvasor] = new Invasor(posicionInvasor); //Genera un invasor que posiciona en posicionInvasor
		
		int posicionMeteorito = (int) (Math.random() * DIM);
		while(posicionMeteorito == posicionInvasor) {
			posicionMeteorito = (int) (Math.random() * DIM);
		}
		casillas[posicionMeteorito] = new Meteorito(posicionMeteorito);
		
		int posicionEscudo = (int) (Math.random() * DIM);
		while(posicionEscudo == posicionInvasor ||posicionEscudo == posicionMeteorito ) {
			posicionEscudo = (int) (Math.random() * DIM);
		}
		casillas[posicionEscudo] = new Escudo(posicionEscudo);
		
	}
	
	/**
	 * Get del array de casillas
	 * @return
	 */
	public Casilla[] getCasillas() {
		return casillas;
	}

	/**
	 * Set del array de casillas
	 * @param casillas
	 */
	public void setCasillas(Casilla[] casillas) {
		this.casillas = casillas;
	}

}
