package logica;

public class Microprocesador extends Producto {
	
	private String tipoConexion;
	private String velocidadProcesamiento;
	
	

	public Microprocesador(double precio, int cantInicial, String numeroSerie, String marca, String modelo,
			String tipoConexion, String velocidadProcesamiento) {
		super(precio, cantInicial, numeroSerie, marca, modelo);
		this.tipoConexion = tipoConexion;
		this.velocidadProcesamiento = velocidadProcesamiento;
	}

	public String getTipoConexion() {
		return tipoConexion;
	}

	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}

	public String getVelocidadProcesamiento() {
		return velocidadProcesamiento;
	}

	public void setVelocidadProcesamiento(String velocidadProcesamiento) {
		this.velocidadProcesamiento = velocidadProcesamiento;
	}
	
	
	

}
