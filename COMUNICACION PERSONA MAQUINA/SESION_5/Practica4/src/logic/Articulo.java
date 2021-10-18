package logic;


public class Articulo {
	
	private String codigo;
	private String tipo;
	private String denominacion;
	private float precio;
	private int unidades;
	
	public String getTipo() {
		return tipo;
	}

	public Articulo(String codigo, String tipo, String denominacion, float precio, int unidades){
		this.codigo = codigo;
		this.tipo = tipo;
		this.denominacion = denominacion;
		this.precio = precio;
		this.unidades = unidades;
	}
	
	public Articulo (Articulo otroArticulo) {
        this(otroArticulo.codigo, otroArticulo.tipo, otroArticulo.denominacion, otroArticulo.precio, otroArticulo.unidades);
    }

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	/**
	 * Elimina unidades del articulo y devuelve 1 si el valor resultante no es 0 0 si es 0 y -1 si es negativo
	 * @param unidadesEliminar
	 * @return
	 */
	public int eliminarUnidades(int unidadesEliminar) {
		if(getUnidades()-unidadesEliminar > 0) {
			setUnidades(getUnidades()-unidadesEliminar);
			return 1;
		}
		else if (getUnidades()-unidadesEliminar == 0) {
			setUnidades(getUnidades()-unidadesEliminar);
			return 0;
		}
		else {
			return -1;
		}
		
	}
	
//	public boolean equals(Object obj) {
//		if(obj instanceof Articulo) {
//			((Articulo)obj).getDenominacion().equals(this.getDenominacion());
//			return true;
//		}
//		return false;
//	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.tipo);
		buffer.append(" - ");
		buffer.append(this.denominacion);
		buffer.append(" - ");
		buffer.append(this.precio);
		buffer.append(" €");
		//Ya no hace falta para que el combo haga de carrito pero sí para guardar el pedido en el fichero
		if (this.unidades!=0){
			buffer.append(" (");
			buffer.append(this.unidades);
			buffer.append(" uds)");
			}
		return buffer.toString();
	   }
}
