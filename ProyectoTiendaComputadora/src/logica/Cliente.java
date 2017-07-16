package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String cedula;
	private String direccion;
	private ArrayList<Factura> misFacturas;
	private int cantFacturas;
	private String telefono;
	


	public Cliente(String nombre, String cedula, String direccion,
			String telefono) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.direccion = direccion;
		misFacturas = new ArrayList<Factura>();
		this.telefono = telefono;
		this.cantFacturas = 0;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getCedula() {
		return cedula;
	}



	public void setCedula(String cedula) {
		this.cedula = cedula;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}



	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}



	public int getCantFacturas() {
		return cantFacturas;
	}



	public void setCantFacturas(int cantFacturas) {
		this.cantFacturas = cantFacturas;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	

}
