package igu;

import javax.swing.ImageIcon;

import logica.Casilla;
import logica.Escudo;
import logica.Espacio;
import logica.Invasor;
import logica.Meteorito;

public class ImagenFactoria {

	private static final String IMAGEN_INVASOR = "/img/invader.jpg";
	private static final String IMAGEN_SPACE = "/img/space.jpg";
	private static final String IMAGEN_SHOOT = "/img/shoot.png";
	private static final String IMAGEN_METEORITO = "/img/meteorito.png";
	private static final String IMAGEN_ESCUDO = "/img/escudo.png";

	public static ImageIcon getImagen(Casilla casilla) {
		if (casilla instanceof Invasor)
			return cargaImagen(IMAGEN_INVASOR);
		else if (casilla instanceof Espacio)
			return cargaImagen(IMAGEN_SPACE);
		else if (casilla instanceof Meteorito)
			return cargaImagen(IMAGEN_METEORITO);
		else if (casilla instanceof Escudo)
			return cargaImagen(IMAGEN_ESCUDO);
		return null;
	}

	public static ImageIcon getImagen()
	{
		return cargaImagen(IMAGEN_SHOOT);
	}
	
	private static ImageIcon cargaImagen(String fichero) {
		return new ImageIcon(ImagenFactoria.class.getResource(fichero));
	}
}
