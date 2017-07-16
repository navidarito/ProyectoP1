package logica;

import java.util.ArrayList;

public class Tienda {
	private ArrayList<Producto> misProductos;
	private ArrayList<Cliente> misClientes;
	private ArrayList<Factura> misFacturas;
	private static Tienda tienda = null;
	
	public Tienda() {
		super();
		misProductos = new ArrayList<Producto>();
		misClientes = new ArrayList<Cliente>();
		misFacturas = new ArrayList<Factura>();
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
	
	
	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}

	public void InsertarProducto(Producto produc){
		misProductos.add(produc);
	}
	
	public void InsertarCliente(Cliente clien){
		misClientes.add(clien);
	}
	
	public void InsertarFactura(Factura factu){
		misFacturas.add(factu);
	}
	
	public double TotalVentas(){
		double total = 0;
		for (int i = 0; i < misFacturas.size(); i++) {
			for (int j = 0; j < misFacturas.get(i).getMisProductos().size(); j++) {
				total += misFacturas.get(i).getMisProductos().get(j).precioVenta()*misFacturas.get(i).getMisProductos().get(j).compra;
			}			
		}
		return total;
	}
	
	
		
		
	}


