package logica;

public class Main {

	public static void main(String[] args) {
		MemoriaRam t1 = new MemoriaRam(100, 20, "A532G2", "Intel", "don", 2000, "DDR5");
		Tienda tienda = new Tienda();
		tienda.InsertarProducto(t1);
		
		System.out.println(tienda.getMisProductos().get(0).precioVenta());

	}

}
