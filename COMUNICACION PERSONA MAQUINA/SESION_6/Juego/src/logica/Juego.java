package logica;

public class Juego {
	
	public static final int maxDisparos = 4;
	int puntos;
	int disparos;
	private Tablero tablero; 
	private boolean invasorEncontrado;
	private boolean meteoritoEncontrado;
	
	public Tablero getTablero() {
		return tablero;
	}

	public Juego(){
		inicializarJuego();
	}
	
	public void inicializarJuego(){
		tablero = new Tablero();
		puntos = 800;
		disparos = 0;
		invasorEncontrado = false;
		meteoritoEncontrado = false;
	}

	public void dispara(int i){
		disparos --;
		if (tablero.getCasillas()[i] instanceof Invasor) {
			((Invasor)tablero.getCasillas()[i]).setEliminado(true);
			invasorEncontrado = true;
		}
		if(tablero.getCasillas()[i] instanceof Meteorito) {
			((Meteorito)tablero.getCasillas()[i]).setEncontrado(true);
			meteoritoEncontrado = true;
			puntos = 0;
		}
		puntos = puntos + tablero.getCasillas()[i].getPuntos();
	}
	
	public boolean isPartidaFinalizada() {
		return (invasorEncontrado || disparos == 0 || meteoritoEncontrado);
	}
	
	public boolean getInvasorEncontrado() {
		return invasorEncontrado;
	}
	
	public boolean getMeteoritoEncontrado() {
		return meteoritoEncontrado;
	}

	/**
	 * Metodo que devuelve el numero de puntos
	 * @return
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * Metodo que llama al metodo lanzar de la clase dado
	 */
	public void lanzar() {
		setDisparos(Dado.lanzar());	
		System.out.println("Numero de disparos: " + getDisparos());
	}
	
	public int getDisparos() {
		return disparos;
	}

	private void setDisparos(int disparos) {
		this.disparos = disparos;
	}
}
