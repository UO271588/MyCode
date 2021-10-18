package logic;

import java.util.*;

public class Pedido {
	
	private boolean descuento = false;
	
	private List<Articulo> listaPedido = null;
	
	public Pedido(){
		listaPedido = new ArrayList<Articulo>();
	}
	
	public boolean getDescuento() {
		return descuento;
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
	
	public float calcularTotal(){
		float total = 0.0f;
		for (Articulo a : listaPedido){
			total += a.getPrecio()* a.getUnidades();
		}
		
		if (total > 50.00) {
			total = total - total*10/100;
			descuento = true;
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

