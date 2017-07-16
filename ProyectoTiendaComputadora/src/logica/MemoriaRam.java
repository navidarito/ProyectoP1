package logica;

public class MemoriaRam extends Producto {
	private String cantidadMemoria;
	private String tipoMemoria;
	

	public MemoriaRam() {
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
