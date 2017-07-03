package logica;

import java.util.ArrayList;

public class TarjetaMadre extends Producto {
	
	
	private String tipConectorMicro;
	private String puedeMemoriaRam;
	private ArrayList<String> conexionesDiscoDuro;
	
	public TarjetaMadre(double precio, int cantidad, String numeroSerie, String marca, String modelo,
			String tipConectorMicro, String puedeMemoriaRam, ArrayList<String> conexionesDiscoDuro) {
		super(precio, cantidad, numeroSerie, marca, modelo);
		this.tipConectorMicro = tipConectorMicro;
		this.puedeMemoriaRam = puedeMemoriaRam;
		this.conexionesDiscoDuro = conexionesDiscoDuro;
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

	public ArrayList<String> getConexionesDiscoDuro() {
		return conexionesDiscoDuro;
	}

	public void setConexionesDiscoDuro(ArrayList<String> conexionesDiscoDuro) {
		this.conexionesDiscoDuro = conexionesDiscoDuro;
	}
	
	
	
	

}
