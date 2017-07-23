package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.sun.security.ntlm.Client;

import javafx.scene.shape.ClosePathBuilder;
import logica.Cliente;
import logica.DiscoDuro;
import logica.Factura;
import logica.MemoriaRam;
import logica.Microprocesador;
import logica.Producto;
import logica.TarjetaMadre;
import logica.Tienda;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class RealizarVenta extends JDialog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableListaCompra;
	private JTable tableCarrito;
	private static Object[] fila;
	private static DefaultTableModel model;
	private static DefaultTableModel models;
	private JLabel fechaxlabel;
	private JSpinner spCantComprar;
	private ArrayList<Producto> miCarrito;
	private ArrayList<Producto> productosEnVenta;
	private JButton btnQuitar;
	private JButton btnAgregar;
	private JButton comprarbtn;
	private Date current;
	private JLabel totallabel;
	private JButton cancelar;
	private JTable table_clientes;
	private JTable  table_comp;//Nuevo
	private DefaultTableModel model_clientes;
	private JLabel nombreCliente;
	private JPanel panelinfoCliente;
	private JLabel cedulaCliente;
	private JLabel direccionCliente;
	private JLabel telefonoCliente;
	private int indeclient = -1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			RealizarVenta dialog = new RealizarVenta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
	/**
	 * Create the dialog.
	 */
	public RealizarVenta() {
		setForeground(Color.BLUE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowActivated(WindowEvent e) {

				double z = 0;

				loadCarrito();
				loadTable();
				current = new Date();
				SimpleDateFormat d1 = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
				//System.out.println(d1.format(current));
				fechaxlabel.setText(d1.format(current));
				if(miCarrito.size()>0 && nombreCliente.getText()!=""){
					comprarbtn.setEnabled(true);
				}
				for (int i = 0; i < miCarrito.size(); i++) {
					z += miCarrito.get(i).precioVXP();
				}
				toString();
				String d = String.valueOf(z);
				totallabel.setText(d);
				if(indeclient!=-1){
					cedulaCliente.setText(Tienda.getInstance().getMisClientes().get(indeclient).getCedula());
					direccionCliente.setText(Tienda.getInstance().getMisClientes().get(indeclient).getDireccion());
					nombreCliente.setText(Tienda.getInstance().getMisClientes().get(indeclient).getNombre());
					telefonoCliente.setText(Tienda.getInstance().getMisClientes().get(indeclient).getTelefono());
				}
				/*if(miCarrito.size()>0){
					cancelar.setEnabled(false);
				}else{
					cancelar.setEnabled(true);
				}*/

			}




			@Override
			public void windowClosing(WindowEvent e) {
				if(miCarrito.size()>0){

					//if(JOptionPane.showConfirmDialog(null,  "Estas seguro de salir", "Información", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
						for (int i = 0; i < miCarrito.size(); i++) {
							for (int j = 0; j < productosEnVenta.size(); j++) {
								if(miCarrito.get(i).getNumeroSerie().equalsIgnoreCase(productosEnVenta.get(j).getNumeroSerie())){
									productosEnVenta.get(j).setCantReal(productosEnVenta.get(j).getCantReal()+miCarrito.get(i).getCompra());
									miCarrito.get(i).setCompra(0);
									break;

								}else if(j==productosEnVenta.size()-1){
									productosEnVenta.add(miCarrito.get(i));
									productosEnVenta.get(productosEnVenta.size()-1).setCantReal(miCarrito.get(i).getCompra());
									miCarrito.get(i).setCompra(0);
									break;

								}
							}


						}
						miCarrito.removeAll(miCarrito);

						dispose();
					//}

				}
				else{
					dispose();
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(RealizarVenta.class.getResource("/imagenes/vender.png")));
		miCarrito = new ArrayList<Producto>();
		productosEnVenta = new ArrayList<Producto>();
		setTitle("Realizar Compra");
		setBounds(100, 100, 1332, 751);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				comprarbtn = new JButton("Comprar");
				comprarbtn.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/compra1.png")));
				comprarbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						Cliente aux = Tienda.getInstance().getMisClientes().get(indeclient);//coje la posicion del cliente 
						Factura f1 = new Factura(aux, current);//se crea una factura.. el current es la hora actual
						for (int i = 0; i < miCarrito.size(); i++) {//y esa factura recorre el csarrtio entero
							f1.insertarProducto(miCarrito.get(i), miCarrito.get(i).getCompra());//inserta to lo prodcuto en l factura
						}
						Tienda.getInstance().InsertarFactura(f1);//inserta la factura en tienda
						miCarrito.removeAll(miCarrito);//se borra el carrito entero[[[
						JOptionPane.showMessageDialog(null, Tienda.getInstance().getMisClientes().get(indeclient).getNombre()+ " Realizo una compra", "Información", JOptionPane.INFORMATION_MESSAGE);

					}
				});
				comprarbtn.setEnabled(false);
				comprarbtn.setSize(73, 21);
				comprarbtn.setActionCommand("OK");
				buttonPane.add(comprarbtn);
				getRootPane().setDefaultButton(comprarbtn);
			}
			{
				cancelar = new JButton("Cancelar");
				cancelar.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/cancel.png")));
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(miCarrito.size()>0){// se verifica si hay producto en carrito

							if(JOptionPane.showConfirmDialog(null,  "Estas seguro de salir", "Información", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
								for (int i = 0; i < miCarrito.size(); i++) {
									for (int j = 0; j < productosEnVenta.size(); j++) {
										if(miCarrito.get(i).getNumeroSerie().equalsIgnoreCase(productosEnVenta.get(j).getNumeroSerie())){
											productosEnVenta.get(j).setCantReal(productosEnVenta.get(j).getCantReal()+miCarrito.get(i).getCompra());
											miCarrito.get(i).setCompra(0);
											break;

										}else if(j==productosEnVenta.size()-1){
											productosEnVenta.add(miCarrito.get(i));
											productosEnVenta.get(productosEnVenta.size()-1).setCantReal(miCarrito.get(i).getCompra());
											miCarrito.get(i).setCompra(0);
											break;

										}
									}


								}
								miCarrito.removeAll(miCarrito);

								dispose();
							}
							
						}
						else{
							dispose();
						}
					}
				});
				cancelar.setSize(75, 21);
				cancelar.setActionCommand("Cancel");
				buttonPane.add(cancelar);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JPanel panelCompra = new JPanel();
			panelCompra.setBorder(new TitledBorder(null, "Lista de Productos a comprar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCompra.setBounds(80, 240, 524, 331);
			panel.add(panelCompra);
			panelCompra.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelCompra.add(scrollPane, BorderLayout.CENTER);

				tableListaCompra = new JTable();
				tableListaCompra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] columnNames = {"Disponible","Producto","Marca","Precio Unitario","# de Serie"};
				model = new DefaultTableModel(){

					@Override
					public boolean isCellEditable(int row, int column){
						return false;
					}
				};
				model.setColumnIdentifiers(columnNames);
				tableListaCompra.setModel(model);
				//loadTable();//Lista de productos a vender
				tableListaCompra.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int aux = tableListaCompra.getSelectedRow();
						if(aux>-1){
							btnAgregar.setEnabled(true);
							btnQuitar.setEnabled(false);
						}
						else{
							btnAgregar.setEnabled(false);
							btnQuitar.setEnabled(false);
						}
					}
				});
				scrollPane.setViewportView(tableListaCompra);
			}

			JPanel panelCarrito = new JPanel();
			panelCarrito.setBorder(new TitledBorder(null, "Carrito de compra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCarrito.setBounds(714, 240, 524, 331);
			panel.add(panelCarrito);
			panelCarrito.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setSize(419, 371);
				panelCarrito.add(scrollPane, BorderLayout.CENTER);

				tableCarrito = new JTable();
				tableCarrito.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int aux = tableCarrito.getSelectedRow();
						if(aux>-1){
							btnAgregar.setEnabled(false);
							btnQuitar.setEnabled(true);
						}
						else{
							btnAgregar.setEnabled(false);
							btnQuitar.setEnabled(false);
						}
					}
				});
				scrollPane.setViewportView(tableCarrito);
			}


			JLabel lblFechaActual = new JLabel("Fecha actual:");
			lblFechaActual.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/date1.png")));
			lblFechaActual.setBounds(714, 40, 101, 21);
			panel.add(lblFechaActual);

			btnAgregar = new JButton("Agregar");
			btnAgregar.setEnabled(false);
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int n = (int)spCantComprar.getValue();//se cogio la cantidad
					if(n>(int)tableListaCompra.getModel().getValueAt(tableListaCompra.getSelectedRow(), 0)){//se verifica si esa cantidad es mayor que la cantidad de producto real q hay
						JOptionPane.showMessageDialog(null,  "La cantidad que pide\nes mayor a lo disponible", "WARNING", JOptionPane.WARNING_MESSAGE);
					}else{
						for (int i = 0; i < productosEnVenta.size(); i++) {//coge to lo producto
							if(productosEnVenta.get(i).getNumeroSerie().equalsIgnoreCase((String)tableListaCompra.getModel().getValueAt(tableListaCompra.getSelectedRow(), 4))){// si el producto es igual al numero serie del click de la tabla .. es para cojerlo
								if(miCarrito.size()>0){//si hay producto
									for (int j = 0; j < miCarrito.size(); j++) {//recorre el carrito
										if(productosEnVenta.get(i).getNumeroSerie().equalsIgnoreCase(miCarrito.get(j).getNumeroSerie())){//si ya hay un producto de lo q agregas
											miCarrito.get(j).setCompra(miCarrito.get(j).getCompra()+n);// en vez de estar agregando aumentamos la cantidad
											productosEnVenta.get(i).setCantReal(productosEnVenta.get(i).getCantReal()-n);// se reducr la cantidad de la venta
											//loadCarrito();
											JOptionPane.showMessageDialog(null,  "Se agregó de nuevo exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
											break; //hay q poner break sino va a seguir y no va a parar

										}else if(j==miCarrito.size()-1){//ocurrira despues de recorrer 
											miCarrito.add(productosEnVenta.get(i));
											miCarrito.get(miCarrito.size()-1).setCompra(n);//size-1 siempre sera el ultimo y sepone la canridad de compra que se eligio
											productosEnVenta.get(i).setCantReal(productosEnVenta.get(i).getCantReal()-n);//reducir el valor de la venta.
											//loadCarrito();
											JOptionPane.showMessageDialog(null,  "Se agregó exitosamente-1", "Información", JOptionPane.INFORMATION_MESSAGE);
											//loadCarrito();
											break;

										}
									}
								}else{
									miCarrito.add(productosEnVenta.get(i));// la misma que el anterior.. el primero para despues.. el segundo para agregar nuevo producto pasa solo una vez
									miCarrito.get(miCarrito.size()-1).setCompra(n);
									productosEnVenta.get(i).setCantReal(productosEnVenta.get(i).getCantReal()-n);
									//loadCarrito();
									JOptionPane.showMessageDialog(null,  "Se agregó exitosamente-0", "Información", JOptionPane.INFORMATION_MESSAGE);
									//loadCarrito();
									break;

								}

							}
						}
					}
					System.out.println(miCarrito.size()+"<---Tamano del carrito");
					//loadCarrito();
					//loadTable();
					//JOptionPane.showMessageDialog(null,  "Se agregó exitosamente-0", "Información", JOptionPane.INFORMATION_MESSAGE);
					btnAgregar.setEnabled(false);
				}
			});
			btnAgregar.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/agregaralCarrito.png")));
			btnAgregar.setBounds(607, 274, 101, 21);
			panel.add(btnAgregar);

			btnQuitar = new JButton("Quitar");
			btnQuitar.setEnabled(false);
			btnQuitar.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/cancel.png")));
			btnQuitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int n = (int)spCantComprar.getValue();
					if(n>(int)tableCarrito.getModel().getValueAt(tableCarrito.getSelectedRow(), 0)){
						JOptionPane.showMessageDialog(null,  "La cantidad que pide\nes mayor a lo disponible", "WARNING", JOptionPane.WARNING_MESSAGE);
					}else{
						for (int i = 0; i < miCarrito.size(); i++) {
							if(miCarrito.get(i).getNumeroSerie().equalsIgnoreCase((String)tableCarrito.getModel().getValueAt(tableCarrito.getSelectedRow(), 4))){
								if(productosEnVenta.size()>0){
									for (int j = 0; j < productosEnVenta.size(); j++) {// en vez de recorre el carrito se recorre la venta
										if(miCarrito.get(i).getNumeroSerie().equalsIgnoreCase(productosEnVenta.get(j).getNumeroSerie())){
											productosEnVenta.get(j).setCantReal(productosEnVenta.get(j).getCantReal()+n);//ahi aumenta la cntidd real de ese producto
											miCarrito.get(i).setCompra(miCarrito.get(i).getCompra()-n);
											JOptionPane.showMessageDialog(null,  "Se devolvio de nuevo exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
											break;

										}else if(j==productosEnVenta.size()-1){
											productosEnVenta.add(miCarrito.get(i));
											productosEnVenta.get(productosEnVenta.size()-1).setCantReal(n);
											miCarrito.get(i).setCompra(miCarrito.get(i).getCompra()-n);
											JOptionPane.showMessageDialog(null,  "Se devolvio exitosamente - z", "Información", JOptionPane.INFORMATION_MESSAGE);
											break;

										}
									}
								}else{
									productosEnVenta.add(miCarrito.get(i));
									productosEnVenta.get(productosEnVenta.size()-1).setCantReal(n);
									miCarrito.get(i).setCompra(miCarrito.get(i).getCompra()-n);
									JOptionPane.showMessageDialog(null,  "Se agregó exitosamente-0", "Información", JOptionPane.INFORMATION_MESSAGE);
									break;

								}

							}
						}
					}
					System.out.println(miCarrito.size()+"<---Tamano del carrito");
					//loadCarrito();
					btnQuitar.setEnabled(false);
				}
			});
			btnQuitar.setBounds(607, 437, 101, 21);
			panel.add(btnQuitar);

			fechaxlabel = new JLabel("");
			fechaxlabel.setBounds(847, 40, 123, 21);
			panel.add(fechaxlabel);

			JLabel lblCantidadDeProducto = new JLabel("Cantidad de Producto a comprar o devolver: ");
			lblCantidadDeProducto.setBounds(92, 185, 271, 21);
			panel.add(lblCantidadDeProducto);

			spCantComprar = new JSpinner();
			spCantComprar.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spCantComprar.setBounds(378, 185, 49, 21);
			panel.add(spCantComprar);

			JLabel lblTotalDelCarrito = new JLabel("Total del carrito:");
			lblTotalDelCarrito.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/precio.png")));
			lblTotalDelCarrito.setBounds(714, 610, 117, 21);
			panel.add(lblTotalDelCarrito);

			totallabel = new JLabel("");
			totallabel.setBounds(858, 610, 176, 21);
			panel.add(totallabel);

			panelinfoCliente = new JPanel();
			panelinfoCliente.setBorder(new TitledBorder(null, "Info del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelinfoCliente.setBounds(80, 11, 346, 163);
			panel.add(panelinfoCliente);
			panelinfoCliente.setLayout(null);

			JLabel labelNombredelCliente = new JLabel("Nombre del Cliente:");
			labelNombredelCliente.setBounds(10, 59, 117, 21);
			panelinfoCliente.add(labelNombredelCliente);

			nombreCliente = new JLabel("");
			nombreCliente.setBounds(157, 59, 179, 21);
			panelinfoCliente.add(nombreCliente);

			JLabel lblCdula = new JLabel("C\u00E9dula:");
			lblCdula.setBounds(10, 27, 46, 21);
			panelinfoCliente.add(lblCdula);

			cedulaCliente = new JLabel("");
			cedulaCliente.setBounds(157, 27, 179, 21);
			panelinfoCliente.add(cedulaCliente);

			JLabel lblDireccin = new JLabel("Direcci\u00F3n :");
			lblDireccin.setBounds(10, 91, 117, 21);
			panelinfoCliente.add(lblDireccin);

			direccionCliente = new JLabel("");
			direccionCliente.setBounds(157, 91, 179, 21);
			panelinfoCliente.add(direccionCliente);

			JLabel lblTlefono = new JLabel("T\u00E9lefono:");
			lblTlefono.setBounds(10, 123, 117, 21);
			panelinfoCliente.add(lblTlefono);

			telefonoCliente = new JLabel("");
			telefonoCliente.setBounds(157, 123, 179, 21);
			panelinfoCliente.add(telefonoCliente);

			table_clientes = new JTable();
			model_clientes = new DefaultTableModel(){

				@Override
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			String[] columnas = {"Nombre","Cedula","Telefono","Direccion"};
			model_clientes.setColumnIdentifiers(columnas);
			table_clientes.setModel(model_clientes);


			JButton btnBuscarCliente = new JButton("Buscar Cliente");
			btnBuscarCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Tienda.getInstance().getMisClientes().size()>0){
						loadTableCliente();
						int dialogButton=0;
						int dialogResult = JOptionPane.showConfirmDialog(null, new JScrollPane(table_clientes), "Escoger el  Cliente", dialogButton);
						if(dialogResult == JOptionPane.OK_OPTION && table_clientes.getSelectedRow()!=-1){
							indeclient = table_clientes.getSelectedRow();
						}

					}
					else{
						JOptionPane.showMessageDialog(null,  "No hay Clientes", "Información", JOptionPane.INFORMATION_MESSAGE);
					}

				}

			});
			btnBuscarCliente.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/buscarCliente1.png")));
			btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnBuscarCliente.setBounds(437, 84, 156, 25);
			panel.add(btnBuscarCliente);

			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/buy (1).png")));
			label.setBounds(979, 60, 156, 158);
			panel.add(label);
		}
	}

	private void loadTable() {
		int aux = -1;
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		//System.out.println(Biblioteca.getInstances().getMisPublicaciones().size());
		if(productosEnVenta.size()==0){
			for(int i = 0 ; i < (Tienda.getInstance().getMisProductos().size()); i++){//para agregar si no hay nada en la venta se va abrir to lo producto q tienes
				productosEnVenta.add(Tienda.getInstance().getMisProductos().get(i));

			}
		}

		for (int i = 0; i < productosEnVenta.size(); i++) {
			if(productosEnVenta.get(i).getCantReal()>0){//si cantidad es mayor q cero visualiza todo
				fila[0] = productosEnVenta.get(i).getCantReal();
				if(productosEnVenta.get(i) instanceof TarjetaMadre){
					fila[1] = "Tarjeta Madre";
				}else if(productosEnVenta.get(i) instanceof Microprocesador){
					fila[1] = "Microprocesador";
				}else if(productosEnVenta.get(i) instanceof MemoriaRam){
					fila[1] = "Memoria Ram";
				}else if(productosEnVenta.get(i) instanceof DiscoDuro){
					fila[1] = "Disco Duro";
				}

				fila[2] = productosEnVenta.get(i).getModelo(); 
				fila[3] = productosEnVenta.get(i).precioVenta();
				fila[4] = productosEnVenta.get(i).getNumeroSerie();

				model.addRow(fila);
			}else{


			}
			if(aux!=-1){
				productosEnVenta.remove(i);
			}

		}

		tableListaCompra.setModel(model);
		//table.setEnabled(false); //deshabilita la seleccion.
		//tableListaCompra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableListaCompra.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columModel = tableListaCompra.getColumnModel();
		/*columModel.getColumn(0).setPreferredWidth(100);
		columModel.getColumn(1).setPreferredWidth(100);
		columModel.getColumn(2).setPreferredWidth(100);
		columModel.getColumn(3).setPreferredWidth(100);*/

	}

	private void loadCarrito() {
		int aux = -1;
		String[] columnNamess = {"Cantidad","Producto","Marca","Precio Unitario","# Serie"};
		models = new DefaultTableModel(){

			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		models.setColumnIdentifiers(columnNamess);
		tableCarrito.setModel(models);

		models.setRowCount(0);
		fila = new Object[models.getColumnCount()];

		for (int i=0; i<miCarrito.size();i++) {
			if(miCarrito.get(i).getCompra()>0){//si hay mas de un producto se visualiza
				fila[0] = miCarrito.get(i).getCompra();
				if(miCarrito.get(i) instanceof TarjetaMadre){
					fila[1] = "Tarjeta Madre";

				}else if(miCarrito.get(i) instanceof Microprocesador){
					fila[1] = "Microprocesador";

				}else if(miCarrito.get(i) instanceof MemoriaRam){
					fila[1] = "Memoria Ram";

				}else if(miCarrito.get(i) instanceof DiscoDuro){
					fila[1] = "Disco Duro";
				}
				fila[2] = miCarrito.get(i).getMarca();
				fila[3] = miCarrito.get(i).precioVenta();
				fila[4] = miCarrito.get(i).getNumeroSerie();

				models.addRow(fila);
			}else{//si hay producto q no tiene cantidad entra aqui
				aux = i;//aux va a poner en esa posicion q no tiene nada
			}
		}
		if(aux!=-1){
			miCarrito.remove(aux);//quita ese producto.. si lo dejo ahi siempre se va a poner en menos 1
		}
		aux = -1;

		tableCarrito.setModel(models);
		//table.setEnabled(false); //deshabilita la seleccion.
		//tableCarrito.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCarrito.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columModel = tableCarrito.getColumnModel();
		/*columModel.getColumn(0).setPreferredWidth(100);
		columModel.getColumn(1).setPreferredWidth(100);
		columModel.getColumn(2).setPreferredWidth(100);
		columModel.getColumn(3).setPreferredWidth(100);
		columModel.getColumn(4).setPreferredWidth(100);*/

	}
	private void loadTableCliente() {
		// TODO Auto-generated method stub
		model_clientes.setRowCount(0);
		fila = new Object[model_clientes.getColumnCount()];
		for(int i = 0 ; i < Tienda.getInstance().getMisClientes().size(); i++){


			fila[0] = Tienda.getInstance().getMisClientes().get(i).getCedula();
			fila[1] = Tienda.getInstance().getMisClientes().get(i).getNombre();
			fila[2] = Tienda.getInstance().getMisClientes().get(i).getTelefono();
			fila[3] = Tienda.getInstance().getMisClientes().get(i).getDireccion();

			model_clientes.addRow(fila);

		}
		table_clientes.setModel(model_clientes);

		table_clientes.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columModel = table_clientes.getColumnModel();


	}
}
