/**
 * 
 */
package logic;

import java.util.Scanner;

/**
 * 
 * @author Eloy Alfredo Schmidt Rguez UO271588
 * @version 1.0.0
 *
 */

public class Main {

	/**
	 * Constante de tipo String que contiene el valor String 0 
	 * como valor de salida
	 */
	private static final String EXIT = "0";
	
	private static Carta carta = new Carta();
	private static Articulo[] articulos;
	private static Pedido pedido;
	private static Scanner keyboard;

	/**
	 * Metodo main
	 * @param args
	 */
	public static void main(String[] args) {
		run();
	}
	
	/**
	 * Metodo que muestra ejecuta el menu de forma que se muestre
	 * la carta y se pueda elegir el producto y las unidades que se desean
	 * y se pueda grabar dicho pedido en un fichero de texto
	 */
	public static void run() {
		String option = EXIT;
		do {
			showCarta();
			option = readString("Opcion");
			processOption(option);
		} while (!option.equals(EXIT));
		float total = pedido.calcularTotal();
		pedido.grabarPedido("pedido");
		System.out.println(total);
	}
	
	/**
	 * Procesa una opción <opcion> del menu
	 * @param option Opción a procesar
	 */
	private static void processOption(String option){
		for (int i=1; i<=carta.getArticulos().length; i++) {
			if (i == Integer.parseInt(option)) {
				int unidades = readInteger("Cuantas unidades desea?:");
				pedido.add(articulos[i-1], unidades);
			}
		}
	}

	/**
	 * Muestra la carta 
	 */
	public static void showCarta() {
		articulos= carta.getArticulos();
		System.out.println("Carta:");
		for (int i = 0; i < articulos.length; i++) {
			System.out.println(""+ (i+1)+".\t" + articulos[i].toString());
		}
	}
	
	/**
	 * Lee un caracter por consola
	 * @param msg Mensaje que se quiere mostrar por pantalla
	 * @return Devuelve el caracter introducido por consola
	 */
	public static char readChar(String msg) {
		keyboard = new Scanner( System.in );
		System.out.println( msg + ": ");
		keyboard.useDelimiter(System.lineSeparator());
		char res = keyboard.next().charAt(0);
		keyboard.reset();
		return res;
	}
	
	/**
	 * Lee un String por consola
	 * @param msg Mensaje que se quiere mostrar por pantalla
	 * @return Devuelve el caracter introducido por consola
	 */
	public static String readString(String msg) {
		keyboard = new Scanner( System.in );
		System.out.println( msg + ": ");
		keyboard.useDelimiter(System.lineSeparator());
		String res = keyboard.next();
		keyboard.reset();
		return res;
	}
	
	/**
	 * Lee un entero por consola
	 * @param msg Mensaje que se quiere mostrar por pantalla
	 * @return Devuelve el caracter introducido por consola
	 */
	public static int readInteger(String msg) {
		keyboard = new Scanner( System.in );
		System.out.println( msg + ": ");
		return keyboard.nextInt();
	}
}
