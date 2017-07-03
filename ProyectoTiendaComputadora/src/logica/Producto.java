package logica;

public abstract class Producto {
	
	protected double precio;
	protected int cantidad;
	protected String numeroSerie;
	protected String marca;
	protected String modelo;
	
	public Producto(double precio, int cantidad, String numeroSerie, String marca, String modelo) {
		super();
		this.precio = precio;
		this.cantidad = cantidad;
		this.numeroSerie = numeroSerie;
		this.marca = marca;
		this.modelo = modelo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
	
	

	
}
