package logica;

public class Dado {
	
	/**
	 * Metodo que devuelve un valor entre 1 a 6
	 * @return
	 */
	public static int lanzar()
	{ 
		
		return ((int) (Math.random() * Juego.maxDisparos) + 1);
	}


}
