package logica;

import java.util.*;

public class Pedido {
	
	private List<Articulo> listaPedido = null;
	
	public Pedido(){
		listaPedido = new ArrayList<Articulo>();
	}

	
	public void add(Articulo articuloDelCatalogo, int unidades){
		Articulo articuloEnPedido = null;
	
		for (Articulo a : listaPedido){
			if (a.getCodigo().equals(articuloDelCatalogo.getCodigo()))
				articuloEnPedido = a;
		}
		
		if (articuloEnPedido == null){
			Articulo articuloAPedido = new Articulo(articuloDelCatalogo);
			articuloAPedido.setUnidades(unidades);
			listaPedido.add(articuloAPedido);
		}
		else{
			int totalUnidades = articuloEnPedido.getUnidades() + unidades;
			articuloEnPedido.setUnidades(totalUnidades);
		}
	}
	
	public void remove(Articulo articulo, int unidades) {
		Articulo articuloEnPedido = null;
		
		for (Articulo a : listaPedido){
			if (a.getCodigo().equals(articulo.getCodigo()))
				articuloEnPedido = a;
		}
		if (articuloEnPedido != null){
			int totalUnidades = articuloEnPedido.getUnidades() - unidades;
			if (totalUnidades>=0)
				{articuloEnPedido.setUnidades(totalUnidades);
				if (totalUnidades == 0 )
					listaPedido.remove(articuloEnPedido);	
				}
		}
	}
	
	public float calcularTotalSinIva(){
		float total = 0.0f;
		
		for (Articulo a : listaPedido){
			total += a.getPrecio()* a.getUnidades();
		}
		return total;
	}
	
	public void grabarPedido(String nombreFichero){
		FileUtil.saveToFile(nombreFichero, listaPedido);
	  }

	public void inicializar(){
		listaPedido.clear();
	}
}

