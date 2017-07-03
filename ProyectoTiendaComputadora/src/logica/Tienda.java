package logica;

import java.util.ArrayList;

public class Tienda {
	private ArrayList<Producto> misProductos;
	private static Tienda tienda = null;
	
	public Tienda() {
		super();
		misProductos = new ArrayList<Producto>();
	}
	
	public static Tienda getInstance(){
		if(tienda ==null){
			tienda = new Tienda();
		}
		return tienda;
	}

	public ArrayList<Producto> getMisProductos() {
		return misProductos;
	}

	public void setMisProductos(ArrayList<Producto> misProductos) {
		this.misProductos = misProductos;
	}
	
	public void InsertarProducto(Producto produc){
		misProductos.add(produc);
	}

}
