package logica;

public class MemoriaRam extends Producto {
	private double cantidadMemoria;
	private String tipoMemoria;
	
	public MemoriaRam(double precio, int cantidad, String numeroSerie, String marca, String modelo,
			double cantidadMemoria, String tipoMemoria) {
		super(precio, cantidad, numeroSerie, marca, modelo);
		this.cantidadMemoria = cantidadMemoria;
		this.tipoMemoria = tipoMemoria;
	}

	public double getCantidadMemoria() {
		return cantidadMemoria;
	}

	public void setCantidadMemoria(double cantidadMemoria) {
		this.cantidadMemoria = cantidadMemoria;
	}

	public String getTipoMemoria() {
		return tipoMemoria;
	}

	public void setTipoMemoria(String tipoMemoria) {
		this.tipoMemoria = tipoMemoria;
	}
	
	
	

}
