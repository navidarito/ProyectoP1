package logica;

public class DiscoDuro extends Producto {
	
	private double capacidadAlmacenamiento;
	private String tipoConexion;
	
	public DiscoDuro(double precio, int cantidad, String numeroSerie, String marca, String modelo,
			double capacidadAlmacenamiento, String tipoConexion) {
		super(precio, cantidad, numeroSerie, marca, modelo);
		this.capacidadAlmacenamiento = capacidadAlmacenamiento;
		this.tipoConexion = tipoConexion;
	}

	public double getCapacidadAlmacenamiento() {
		return capacidadAlmacenamiento;
	}

	public void setCapacidadAlmacenamiento(double capacidadAlmacenamiento) {
		this.capacidadAlmacenamiento = capacidadAlmacenamiento;
	}

	public String getTipoConexion() {
		return tipoConexion;
	}

	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}
	
	
	

}
