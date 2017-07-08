package logica;

public abstract class Producto {
	
	protected double precio;
	protected int cantInicial;
	protected int cantReal;
	protected int compra;
	protected String numeroSerie;
	protected String marca;
	protected String modelo;
	
	public Producto(double precio, int cantInicial, String numeroSerie, String marca, String modelo) {
		super();
		this.precio = precio;
		this.cantInicial = cantInicial;
		this.cantReal = cantInicial;
		this.numeroSerie = numeroSerie;
		this.marca = marca;
		this.modelo = modelo;
		compra = 0;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantInicial() {
		return cantInicial;
	}
	public void setCantInicial(int cantInicial) {
		this.cantInicial = cantInicial;
	}
	public int getCantReal() {
		return cantReal;
	}
	public void setCantReal(int cantReal) {
		this.cantReal = cantReal;
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
	
	
	
	public int getCompra() {
		return compra;
	}
	public void setCompra(int compra) {
		this.compra = compra;
	}
	public double precioVenta(){
		double pv = 0;
		pv = precio + precio * 0.20;
		return pv;
	}
	

	
}
