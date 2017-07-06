package logica;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	
	private Cliente client;
	private ArrayList<Producto> misProductos;
	private Date fechaCompra;
	private String codigo;
	private double precioVenta;
	
	public Factura(Cliente client, Date fechaCompra, String codigo,
			double precioVenta) {
		super();
		this.client = client;
		misProductos = new ArrayList<Producto>();
		this.fechaCompra = fechaCompra;
		this.codigo = codigo;
		this.precioVenta = precioVenta;
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
	
	
	

}
