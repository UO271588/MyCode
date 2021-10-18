package logic;

import java.util.*;

public class Carta {
	
	private static final String FICHERO_ARTICULOS = "files/articulos.dat";
	private List<Articulo> listaArticulos = null;
	
	/**
	 * Constructor por defecto clase Carta
	 */
	public Carta(){
		listaArticulos = new ArrayList<Articulo>();
		cargarArticulos();
	}

	/**
	 * Carga los articulos desde el fichero de articulos en listaArticulos
	 */
	private void cargarArticulos(){
		FileUtil.loadFile (FICHERO_ARTICULOS, listaArticulos);
	  }

	/**
	 * Devuelve la lista completa de articulos
	 * @return articulos -Array de articulos
	 */
	public Articulo[] getArticulos(){
		Articulo[] articulos = listaArticulos.toArray(new Articulo[listaArticulos.size()]);
		return articulos;
	}
	
	/**
	 * Devuelve una lista de articulos definida por el tipo del articulo
	 * @param tipo -Tipo del articulo
	 * @return articulosTipo -Array de articulos
	 */
	public Articulo[] getArticulosEspecificos( String tipo) {
		ArrayList<Articulo> articulosTipo= new ArrayList<Articulo>();		// Se crea un ArrayList de articulos
		for(Articulo art: listaArticulos) {									// Se recorre la listaArticulos
			if(art.getTipo().equals(tipo)) {								// Para cada articulo se comprueba si es del tipo correcto
				articulosTipo.add(art);										// Si lo es se añade a articulosTipo
			}
		}
		return articulosTipo.toArray(new Articulo[articulosTipo.size()]);	//Se devuelve articulosTipo en forma de array de articulos
	}
}
