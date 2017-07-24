package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Tienda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> misProductos;
	private ArrayList<Producto> misOrdenes;
	private int cantProductos=0;
	private ArrayList<Cliente> misClientes;
	private ArrayList<Factura> misFacturas;
	private static Tienda tienda = null;
	
	public Tienda() {
		super();
		misProductos = new ArrayList<Producto>();
		misClientes = new ArrayList<Cliente>();
		misFacturas = new ArrayList<Factura>();
		misOrdenes = new ArrayList<Producto>();
		this.cantProductos=0;
	}
	
	public  static Tienda getInstance(){
		if(tienda ==null){
			tienda = new Tienda();
		}
		return tienda;
	}
	

	public ArrayList<Producto> getMisOrdenes() {
		return misOrdenes;
	}

	public void setMisOrdenes(ArrayList<Producto> misOrdenes) {
		this.misOrdenes = misOrdenes;
	}

	public ArrayList<Producto> getMisProductos() {
		return misProductos;
	}

	public static Tienda getTienda() {
		return tienda;
	}

	public static void setTienda(Tienda tienda) {
		Tienda.tienda = tienda;
	}

	public void setMisProductos(ArrayList<Producto> misProductos) {
		this.misProductos = misProductos;
	}
	
	
	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public int getCantProductos() {
		return cantProductos;
	}

	public void setCantProductos(int cantProductos) {
		this.cantProductos = cantProductos;
	}

	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}

	public void InsertarProducto(Producto produc){
		misProductos.add(produc);
	}
	
	public void InsertarCliente(Cliente clien){
		misClientes.add(clien);
	}
	
	public void InsertarFactura(Factura factu){
		misFacturas.add(factu);
		misFacturas.get(misFacturas.size()-1).setCodigo("00"+misFacturas.size());
	}
	
	public double TotalVentas(){
		double total = 0;
		for (int i = 0; i < misFacturas.size(); i++) {
			for (int j = 0; j < misFacturas.get(i).getMisProductos().size(); j++) {
				total += misFacturas.get(i).getMisProductos().get(j).precioVenta()*misFacturas.get(i).getMisProductos().get(j).compra;
			}			
		}
		return total;
	}
	public Producto indexProducto(String numeroSerie){
		Producto ind = null;
		for (Producto aux : misProductos) {
			if(aux.getNumeroSerie().equalsIgnoreCase(numeroSerie)){
				ind = aux;
			}
		}
		return ind;
	}
	public Cliente indexCliente(String cedula){
		Cliente ind = null;
		for (Cliente aux : misClientes) {
			if(aux.getCedula().equalsIgnoreCase(cedula)){
				ind = aux;
			}
		}
		return ind;
	}
	
	public Factura indexFactura(String codigo){
		Factura ind = null;
		for (Factura aux : misFacturas) {
			if(aux.getCodigo().equalsIgnoreCase(codigo)){
				ind = aux;
			}
		}
		return ind;
	}

	public void eliminiarProducto(String numeroSerie) {
		Producto index = indexProducto(numeroSerie);
		
			misProductos.remove(index);
	}

	public void eliminiarCliente(String cedula) {
		Cliente index =indexCliente(cedula);
		misClientes.remove(index);
		
	}
	
	public void eleminarFactura(String codigo){
		Factura index = indexFactura(codigo);
		misFacturas.remove(index);
		System.out.println("Se elimino");
	}
	 
	
	public float cantTarjetaMadre(){
		float cantidad=0;
		for(int i=0; i<misFacturas.size();i++){
			for(int j=0; j<misFacturas.get(i).getMisProductos().size();j++){
				if(misFacturas.get(i).getMisProductos().get(j) instanceof TarjetaMadre){
					cantidad +=misFacturas.get(i).getMisProductos().get(j).getCompra();
				}
			}
		}
			
			return cantidad;
		
	}
	public float cantMicroProcesador(){
		float cantidad=0;
		for(int i=0; i<misFacturas.size();i++){
			for(int j=0; j<misFacturas.get(i).getMisProductos().size();j++){
				if(misFacturas.get(i).getMisProductos().get(j) instanceof Microprocesador){
					cantidad +=misFacturas.get(i).getMisProductos().get(j).getCompra();
				}
			}
		}
			
			return cantidad;
		
	}
	public float cantMemoriaRam(){
		float cantidad=0;
		for(int i=0; i<misFacturas.size();i++){
			for(int j=0; j<misFacturas.get(i).getMisProductos().size();j++){
				if(misFacturas.get(i).getMisProductos().get(j) instanceof MemoriaRam){
					cantidad +=misFacturas.get(i).getMisProductos().get(j).getCompra();
				}
			}
		}
			
			return cantidad;
		
	}
	public float cantDiscoDuro(){
		float cantidad=0;
		for(int i=0; i<misFacturas.size();i++){
			for(int j=0; j<misFacturas.get(i).getMisProductos().size();j++){
				if(misFacturas.get(i).getMisProductos().get(j) instanceof DiscoDuro){
					cantidad +=misFacturas.get(i).getMisProductos().get(j).getCompra();
				}
			}
		}
			
			return cantidad;
		
	}
	public float totaltodo(){
		float total = 0;
		total = cantDiscoDuro()+cantMemoriaRam()+cantMicroProcesador()+cantTarjetaMadre();
		return total;
	}
	
///////////////////////////////////////////////////////////////////////////////////
	public void cargarTienda(Tienda tx){
		try {
			FileInputStream file= new FileInputStream("Tienda.dat");
			ObjectInputStream object = new ObjectInputStream(file);
			Tienda comp=(Tienda)object.readObject();
			Tienda.setTienda(comp);
			object.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void guardarTienda(Tienda tz){
		File file= new File("Tienda.dat");
		FileOutputStream fo= null;
		ObjectOutputStream ob =null;
		
		try {
			fo= new FileOutputStream(file);
			ob = new ObjectOutputStream(fo);
			ob.writeObject(tz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(ob!=null)
					ob.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void crearFichero1() throws IOException{
		FileOutputStream file = new FileOutputStream("Tiendaz.dat");
		ObjectOutputStream escritor = new ObjectOutputStream(file);
		escritor.writeInt(misFacturas.size());
		for (Factura fac : misFacturas) 
			escritor.writeObject(fac);	
		escritor.close();
		file.close();
	}
	
	public void txtFichero1(String codigo){
		FileInputStream file;
		ObjectInputStream lector;
		ObjectOutputStream escritor;
		FileOutputStream salida;
		FileWriter writter = null;
		File archivo = new File("UltimaFactura.txt");
		try {
			writter = new FileWriter(archivo);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			file = new FileInputStream("Tiendaz.dat");
			salida = new FileOutputStream("UltimaFactura1.txt");
			try {
				lector = new ObjectInputStream(file);
				escritor = new ObjectOutputStream(salida);
				int index = lector.readInt();
				for (int i = 0; i < index; i++) {
					if(misFacturas.get(i).getCodigo()==codigo){
						System.out.println("Entro: ");
						Factura aux = (Factura) lector.readObject();
						writter.write("Código de la factura: "+aux.getCodigo()+"\n");
						int total = 0;
						for (int j = 0; j < aux.getMisProductos().size(); j++) {
							total += aux.getMisProductos().get(j).getCompra();
						}
						writter.write("Cantidad de Productos:"+String.valueOf(total)+"\n");
						writter.write("----------------------------------------------------------------\n");
						for (int j = 0; j < aux.getMisProductos().size(); j++) {
	
							writter.write("Número de serie: "+aux.getMisProductos().get(j).numeroSerie+", Cantidad del producto:"+aux.getMisProductos().get(j).getCompra()+", Precio Unitario: "+aux.getMisProductos().get(j).precioVenta()+"\n");
							
						}
						writter.write("Total : "+aux.totalFactura()+"\n");
						writter.write("----------------------------------------------------------------\n");
						writter.write("\n");
					}
					
					
				}
				writter.close();
				lector.close();
				escritor.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
