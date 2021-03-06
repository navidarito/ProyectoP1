package logica;

import java.time.LocalDate;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		MemoriaRam t1 = new MemoriaRam(100, 20, "A532G2", "Intel", "don", "2000", "DDR5");
		TarjetaMadre t2 = new TarjetaMadre(200, 20, "A532G3", "Dragon", "AMD", "S1", "2GB", "2GB");
		Tienda tienda = new Tienda();
		tienda.InsertarProducto(t1);
		tienda.InsertarProducto(t2);
		
		Cliente c1 = new Cliente( "juan", "001-97878", "villa magisterial", "809-754-2421");
		//tienda.getMisFacturas().get(0).insertarProducto(p1);
		Date j1= new Date(1900, 2, 20);
		Factura f1 = new Factura(c1,j1);
		f1.insertarProducto(t1,19);
		f1.insertarProducto(t2, 2);
		//f1.insertarProducto(t2, 5);
		tienda.InsertarFactura(f1);
		
		
		for (int i = 0; i < tienda.getMisProductos().size(); i++) {
			System.out.println(tienda.getMisProductos().get(i).getMarca());
		}
		for (int i = 0; i < tienda.getMisFacturas().size(); i++) {
			for (int j = 0; j < tienda.getMisFacturas().get(i).getMisProductos().size(); j++) {
				System.out.println(tienda.getMisFacturas().get(i).getMisProductos().get(j).precioVenta());
			}
		}
		
		System.out.println("");
		System.out.println(tienda.getMisFacturas().get(0).totalFactura());
		System.out.println("");
		System.out.println("");
		for (int i = 0; i < tienda.getMisProductos().size(); i++) {
			System.out.println(tienda.getMisProductos().get(i).getCantInicial()+"  <--Cantidad Inicial");
			System.out.println(tienda.getMisProductos().get(i).getCantReal()+"  <--Cantidad Real");
		}
		
		//System.out.println(tienda.indexFactura("001"));
		System.out.println("Cantidad Tarjeta :"+tienda.cantTarjetaMadre());
		System.out.println("Cantidad Disco :"+tienda.cantDiscoDuro());
		System.out.println("Cantidad Memoria Ram :"+tienda.cantMemoriaRam());
		System.out.println("Cantidad MicroProcesador :"+tienda.cantMicroProcesador());
		System.out.println("");
		System.out.println("Cantidad % Ram :"+(tienda.cantMemoriaRam()/tienda.totaltodo())*100);
		for (int i = 0; i < tienda.getMisProductos().size(); i++) {
			System.out.println(tienda.getMisProductos().get(i).ordencompra());
		}
		for (int i = 0; i < tienda.getMisProductos().size(); i++) {
			System.out.println((int)(tienda.getMisProductos().get(i).getCantInicial()*0.2*2));
		}
		System.out.println("");
		double er = 20;
		double erz = 8;
		double tox = erz/er;
		System.out.println(tox);
		/*
		tienda.eleminarFactura("001");*/
		/*System.out.println(tienda.getMisFacturas().get(0).getMisProductos().get(0).compra);
		System.out.println();
		System.out.println(tienda.getMisProductos().get(0).getCantReal());
		System.out.println(tienda.getMisProductos().get(0).precioVenta());
		System.out.println();
		System.out.println(tienda.TotalVentas());*/

	}

}
