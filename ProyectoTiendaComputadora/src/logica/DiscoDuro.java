package logica;

public class DiscoDuro extends Producto {
	
	private String capacidadAlmacenamiento;
	private String tipoConexion;
	
	public DiscoDuro() {
		super(precio, cantInicial, numeroSerie, marca, modelo);
		this.capacidadAlmacenamiento = capacidadAlmacenamiento;
		this.tipoConexion = tipoConexion;
	}

	public String getCapacidadAlmacenamiento() {
		return capacidadAlmacenamiento;
	}

	public void setCapacidadAlmacenamiento(String capacidadAlmacenamiento) {
		this.capacidadAlmacenamiento = capacidadAlmacenamiento;
	}

	public String getTipoConexion() {
		return tipoConexion;
	}

	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}
	
	
	

}
