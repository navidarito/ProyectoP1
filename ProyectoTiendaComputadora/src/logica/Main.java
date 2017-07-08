package logica;

import java.time.LocalDate;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		MemoriaRam t1 = new MemoriaRam(100, 20, "A532G2", "Intel", "don", 2000, "DDR5");
		Tienda tienda = new Tienda();
		tienda.InsertarProducto(t1);
		
		Cliente c1 = new Cliente( "juan", "001-97878", "villa magisterial", "809-754-2421");
		//tienda.getMisFacturas().get(0).insertarProducto(p1);
		Date j1= new Date(1900, 2, 20);
		Factura f1 = new Factura(c1,j1 , "01", 100);
		f1.insertarProducto(t1);
		tienda.InsertarFactura(f1);
		
		System.out.println(tienda.getMisFacturas().get(0).getMisProductos().get(0).cantInicial);
		System.out.println(tienda.getMisProductos().get(0).precioVenta());

	}

}
