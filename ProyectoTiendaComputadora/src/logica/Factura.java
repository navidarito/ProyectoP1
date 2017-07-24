package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Cliente client;
	private ArrayList<Producto> misProductos;
	private Date fechaCompra;
	private String codigo;
	private double precioVenta;
	
	
	public Factura(Cliente client, Date fechaCompra) {
		super();
		this.client = client;
		misProductos = new ArrayList<Producto>();
		this.fechaCompra = fechaCompra;
	}

	public Cliente getClient() {
		return client;
	}

	public void setClient(Cliente client) {
		this.client = client;
	}

	public ArrayList<Producto> getMisProductos() {
		return misProductos;
	}

	public void setMisProductos(ArrayList<Producto> misProductos) {
		this.misProductos = misProductos;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	
	
	public void insertarProducto(Producto p1,int cant){
		
		misProductos.add(p1);
		System.out.println(p1.cantReal);
		p1.setCompra(cant);

	}
	public double totalFactura(){
		double total = 0;
		for (int i = 0; i < misProductos.size(); i++) {
				total += misProductos.get(i).precioVenta()*misProductos.get(i).getCompra();
			
		}
		return total;
	}
	


}
