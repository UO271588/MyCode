package logic;

import java.util.*;

public class Pedido {
	
	private boolean descuento = false;
	private boolean local = true;
	
	private List<Articulo> listaPedido = null;
	
	public Pedido(){
		listaPedido = new ArrayList<Articulo>();
	}
	
	public void setLocal(boolean local) {
		this.local = local;
	}
	public int getUnidades(Articulo articulo) {
		for(Articulo art: listaPedido) {
			if(art.getDenominacion().equals(articulo.getDenominacion())){
				return art.getUnidades();
			}
		}
		return 0;
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
		else if (total < 50.00) {
			descuento = false;
		}		
		
		return total;
	}
	
	public void eliminarArticulo(Articulo articuloEliminar){
		for(int i = 0; i<listaPedido.size(); i++) {
			if(listaPedido.get(i).equals(articuloEliminar)){
				listaPedido.remove(i);
				break;
			}
		}
	}
	
	
	public int eliminarUnidadesArticulo(int unidades, Articulo articulo) {
		int undsElim = -1;
		for(Articulo art: listaPedido) {
			if(art.getDenominacion().equals(articulo.getDenominacion())) {
				undsElim = art.eliminarUnidades(unidades);
				if (undsElim == 0) {
					eliminarArticulo(art);
					break;
				}
				return undsElim;
			}
		}
		return undsElim;
	}
	
	public void grabarPedido(String nombreFichero){
		FileUtil.saveToFile(nombreFichero, listaPedido, local);
	  }

	public void inicializar(){
		listaPedido.clear();
	}
	
	public boolean isEmpty() {
		if(listaPedido.size() == 0) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String cadena = "Pedido: \n";
		for(Articulo art: listaPedido) {
			cadena += art.getDenominacion() + " (" + art.getUnidades() + " uds) \n";
		}
		cadena += String.format("Total: %.2f €",calcularTotal());
		return cadena;
	}
}

