package logica;

public class Microprocesador extends Producto {
	
	private String tipoConexion;
	private double velocidadProcesamiento;
	
	public Microprocesador(double precio, int cantidad, String numeroSerie, String marca, String modelo,
			String tipoConexion, double velocidadProcesamiento) {
		super(precio, cantidad, numeroSerie, marca, modelo);
		this.tipoConexion = tipoConexion;
		this.velocidadProcesamiento = velocidadProcesamiento;
	}

	public String getTipoConexion() {
		return tipoConexion;
	}

	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}

	public double getVelocidadProcesamiento() {
		return velocidadProcesamiento;
	}

	public void setVelocidadProcesamiento(double velocidadProcesamiento) {
		this.velocidadProcesamiento = velocidadProcesamiento;
	}
	
	
	

}
