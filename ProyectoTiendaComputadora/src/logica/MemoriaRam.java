package logica;

public class MemoriaRam extends Producto {
	private String cantidadMemoria;
	private String tipoMemoria;
	

	public MemoriaRam(double precio, int cantInicial, String numeroSerie, String marca, String modelo,
			String cantidadMemoria, String tipoMemoria) {
		super(precio, cantInicial, numeroSerie, marca, modelo);
		this.cantidadMemoria = cantidadMemoria;
		this.tipoMemoria = tipoMemoria;
	}

	public String getCantidadMemoria() {
		return cantidadMemoria;
	}

	public void setCantidadMemoria(String cantidadMemoria) {
		this.cantidadMemoria = cantidadMemoria;
	}

	public String getTipoMemoria() {
		return tipoMemoria;
	}

	public void setTipoMemoria(String tipoMemoria) {
		this.tipoMemoria = tipoMemoria;
	}
	
	
	

}
