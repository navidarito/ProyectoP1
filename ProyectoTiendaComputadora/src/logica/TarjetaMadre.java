package logica;



public class TarjetaMadre extends Producto {
	
	
	private String tipConectorMicro;
	private String puedeMemoriaRam;
	private String puedeDiscoDuro;
	

	public TarjetaMadre(double precio, int cantInicial, String numeroSerie, String marca, String modelo,
			String tipConectorMicro, String puedeMemoriaRam, String puedeDiscoDuro) {
		super(precio, cantInicial, numeroSerie, marca, modelo);
		this.tipConectorMicro = tipConectorMicro;
		this.puedeMemoriaRam = puedeMemoriaRam;
		this.puedeDiscoDuro = puedeDiscoDuro;
	}

	public String getTipConectorMicro() {
		return tipConectorMicro;
	}

	public void setTipConectorMicro(String tipConectorMicro) {
		this.tipConectorMicro = tipConectorMicro;
	}

	public String getPuedeMemoriaRam() {
		return puedeMemoriaRam;
	}

	public void setPuedeMemoriaRam(String puedeMemoriaRam) {
		this.puedeMemoriaRam = puedeMemoriaRam;
	}

	public String getPuedeDiscoDuro() {
		return puedeDiscoDuro;
	}

	public void setPuedeDiscoDuro(String puedeDiscoDuro) {
		this.puedeDiscoDuro = puedeDiscoDuro;
	}


	
	

}
