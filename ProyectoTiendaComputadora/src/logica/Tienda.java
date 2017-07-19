package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Tienda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> misProductos;
	private int cantProductos=0;
	private ArrayList<Cliente> misClientes;
	private ArrayList<Factura> misFacturas;
	private static Tienda tienda = null;
	
	public Tienda() {
		super();
		misProductos = new ArrayList<Producto>();
		misClientes = new ArrayList<Cliente>();
		misFacturas = new ArrayList<Factura>();
		this.cantProductos=0;
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

	public int getCantProductos() {
		return cantProductos;
	}

	public void setCantProductos(int cantProductos) {
		this.cantProductos = cantProductos;
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
	private Producto indexProducto(String numeroSerie){
		Producto ind = null;
		for (Producto aux : misProductos) {
			if(aux.getNumeroSerie().equalsIgnoreCase(numeroSerie)){
				ind = aux;
			}
		}
		return ind;
	}
	public Cliente indexCliente(String cedula){
		Cliente ind = null;
		for (Cliente aux : misClientes) {
			if(aux.getCedula().equalsIgnoreCase(cedula)){
				ind = aux;
			}
		}
		return ind;
	}

	public void eliminiarProducto(String numeroSerie) {
		Producto index = indexProducto(numeroSerie);
			misProductos.remove(index);
	}

	public void eliminiarCliente(String cedula) {
		Cliente index =indexCliente(cedula);
		misClientes.remove(index);
		
	}
	

	
	
		
		
	}


