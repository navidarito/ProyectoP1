package logica;

import java.io.Serializable;

public class DiscoDuro extends Producto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String capacidadAlmacenamiento;
	private String tipoConexion;
	
	

	public DiscoDuro(double precio, int cantInicial, String numeroSerie, String marca, String modelo,
			String capacidadAlmacenamiento, String tipoConexion) {
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
