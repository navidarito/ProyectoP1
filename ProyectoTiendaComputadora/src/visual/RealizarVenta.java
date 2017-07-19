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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RealizarVenta extends JDialog {
	private JTable tableListaCompra;
	private JTable tableCarrito;
	private static Tienda tienda;
	private static Object[] fila;
	private static DefaultTableModel model;
	private static DefaultTableModel models;
	private JComboBox cbCliente;
	private JLabel fechaxlabel;
	private JLabel nombreCliente;
	private JSpinner spCantComprar;
	private ArrayList<Producto> miCarrito;
	private ArrayList<Producto> productosEnVenta;
	private JButton btnQuitar;
	private JButton btnAgregar;
	private JButton comprarbtn;
	private Date current;

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
	public RealizarVenta(Tienda t) {
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowActivated(WindowEvent e) {
				
				
				int n = cbCliente.getSelectedIndex();
				if(n>-1){
					nombreCliente.setText(tienda.getMisClientes().get(n).getNombre());
				}
				
				//loadCarrito();
				loadTable();
				current = new Date();
				SimpleDateFormat d1 = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
				System.out.println(d1.format(current));
				fechaxlabel.setText(d1.format(current));
				if(miCarrito.size()>0 && nombreCliente.getText()!=""){
					comprarbtn.setEnabled(true);
				}

			}




		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(RealizarVenta.class.getResource("/imagenes/vender.png")));
		tienda = t;
		miCarrito = new ArrayList<Producto>();
		productosEnVenta = new ArrayList<Producto>();
		setTitle("Realizar Compra");
		setBounds(100, 100, 882, 576);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				comprarbtn = new JButton("Comprar");
				comprarbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int n = cbCliente.getSelectedIndex();
						Cliente aux = tienda.getMisClientes().get(n);
						Factura f1 = new Factura(aux, current);
						for (int i = 0; i < miCarrito.size(); i++) {
							f1.insertarProducto(miCarrito.get(i), miCarrito.get(i).getCompra());
						}
						tienda.InsertarFactura(f1);
						JOptionPane.showMessageDialog(null, tienda.getMisClientes().get(n).getNombre()+ " Realizo una compra", "Información", JOptionPane.INFORMATION_MESSAGE);
						
					}
				});
				comprarbtn.setEnabled(false);
				comprarbtn.setSize(73, 21);
				comprarbtn.setActionCommand("OK");
				buttonPane.add(comprarbtn);
				getRootPane().setDefaultButton(comprarbtn);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setSize(75, 21);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JPanel panelCompra = new JPanel();
			panelCompra.setBorder(new TitledBorder(null, "Lista de Productos a comprar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCompra.setBounds(61, 185, 318, 274);
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
			panelCarrito.setBounds(485, 185, 318, 274);
			panel.add(panelCarrito);
			panelCarrito.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
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

			cbCliente = new JComboBox();
			for (int i = 0; i < tienda.getMisClientes().size(); i++) {
				cbCliente.addItem(tienda.getMisClientes().get(i).getCedula());
			}
			cbCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int n = cbCliente.getSelectedIndex();
					nombreCliente.setText(tienda.getMisClientes().get(n).getNombre());
				}
			});
			cbCliente.setBounds(176, 40, 132, 21);
			panel.add(cbCliente);

			JLabel lblCliente = new JLabel("Cliente:");
			lblCliente.setBounds(92, 40, 74, 21);
			panel.add(lblCliente);

			JLabel lblNombreDelCliente = new JLabel("Nombre del Cliente:");
			lblNombreDelCliente.setBounds(91, 84, 117, 21);
			panel.add(lblNombreDelCliente);

			JLabel lblFechaActual = new JLabel("Fecha actual:");
			lblFechaActual.setBounds(470, 62, 101, 21);
			panel.add(lblFechaActual);

			btnAgregar = new JButton("Agregar");
			btnAgregar.setEnabled(false);
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int n = (int)spCantComprar.getValue();
					if(n>(int)tableListaCompra.getModel().getValueAt(tableListaCompra.getSelectedRow(), 0)){
						JOptionPane.showMessageDialog(null,  "La cantidad que pide\nes mayor a lo disponible", "WARNING", JOptionPane.WARNING_MESSAGE);
					}else{
						for (int i = 0; i < productosEnVenta.size(); i++) {
							if(productosEnVenta.get(i).getNumeroSerie().equalsIgnoreCase((String)tableListaCompra.getModel().getValueAt(tableListaCompra.getSelectedRow(), 4))){
								if(miCarrito.size()>0){
									for (int j = 0; j < miCarrito.size(); j++) {
										if(productosEnVenta.get(i).getNumeroSerie().equalsIgnoreCase(miCarrito.get(j).getNumeroSerie())){
											miCarrito.get(j).setCompra(miCarrito.get(j).getCompra()+n);
											productosEnVenta.get(i).setCantReal(productosEnVenta.get(i).getCantReal()-n);
											//loadCarrito();
											JOptionPane.showMessageDialog(null,  "Se agregó de nuevo exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
											break;

										}else if(j==miCarrito.size()-1){
											miCarrito.add(productosEnVenta.get(i));
											miCarrito.get(miCarrito.size()-1).setCompra(n);
											productosEnVenta.get(i).setCantReal(productosEnVenta.get(i).getCantReal()-n);
											//loadCarrito();
											JOptionPane.showMessageDialog(null,  "Se agregó exitosamente-1", "Información", JOptionPane.INFORMATION_MESSAGE);
											//loadCarrito();
											break;

										}
									}
								}else{
									miCarrito.add(productosEnVenta.get(i));
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
					loadCarrito();
					//loadTable();
					//JOptionPane.showMessageDialog(null,  "Se agregó exitosamente-0", "Información", JOptionPane.INFORMATION_MESSAGE);
					btnAgregar.setEnabled(false);
				}
			});
			btnAgregar.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/agregaralCarrito.png")));
			btnAgregar.setBounds(382, 241, 101, 21);
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
									for (int j = 0; j < productosEnVenta.size(); j++) {
										if(miCarrito.get(i).getNumeroSerie().equalsIgnoreCase(productosEnVenta.get(j).getNumeroSerie())){
											productosEnVenta.get(j).setCantReal(productosEnVenta.get(j).getCantReal()+n);
											miCarrito.get(i).setCompra(miCarrito.get(i).getCompra()-n);
											//loadCarrito();
											JOptionPane.showMessageDialog(null,  "Se devolvio de nuevo exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
											break;

										}else if(j==productosEnVenta.size()-1){
											productosEnVenta.add(miCarrito.get(i));
											productosEnVenta.get(productosEnVenta.size()-1).setCantReal(n);
											miCarrito.get(i).setCompra(miCarrito.get(i).getCompra()-n);
											//loadCarrito();
											JOptionPane.showMessageDialog(null,  "Se devolvio exitosamente - z", "Información", JOptionPane.INFORMATION_MESSAGE);
											//loadCarrito();
											break;

										}
									}
								}else{
									productosEnVenta.add(miCarrito.get(i));
									productosEnVenta.get(productosEnVenta.size()-1).setCantReal(n);
									miCarrito.get(i).setCompra(miCarrito.get(i).getCompra()-n);
									//loadCarrito();
									JOptionPane.showMessageDialog(null,  "Se agregó exitosamente-0", "Información", JOptionPane.INFORMATION_MESSAGE);
									//loadCarrito();
									break;

								}

							}
						}
					}
					System.out.println(miCarrito.size()+"<---Tamano del carrito");
					loadCarrito();
					//loadTable();
					//JOptionPane.showMessageDialog(null,  "Se agregó exitosamente-0", "Información", JOptionPane.INFORMATION_MESSAGE);
					btnQuitar.setEnabled(false);
				}
			});
			btnQuitar.setBounds(382, 381, 101, 21);
			panel.add(btnQuitar);

			fechaxlabel = new JLabel("");
			fechaxlabel.setBounds(560, 62, 123, 21);
			panel.add(fechaxlabel);

			nombreCliente = new JLabel("");
			nombreCliente.setBounds(222, 84, 86, 21);
			panel.add(nombreCliente);

			JLabel lblCantidadDeProducto = new JLabel("Cantidad de Producto a comprar o devolver: ");
			lblCantidadDeProducto.setBounds(93, 135, 271, 21);
			panel.add(lblCantidadDeProducto);

			spCantComprar = new JSpinner();
			spCantComprar.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spCantComprar.setBounds(374, 135, 49, 21);
			panel.add(spCantComprar);
		}
	}

	private void loadTable() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		//System.out.println(Biblioteca.getInstances().getMisPublicaciones().size());
		if(productosEnVenta.size()==0){
			for(int i = 0 ; i < (tienda.getMisProductos().size()); i++){
				productosEnVenta.add(tienda.getMisProductos().get(i));

			}
		}

		for (int i = 0; i < productosEnVenta.size(); i++) {
			if(productosEnVenta.get(i).getCantReal()>0){
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
			}/*else{
				
				productosEnVenta.remove(i+1);
			}*/
			
		}

		tableListaCompra.setModel(model);
		//table.setEnabled(false); //deshabilita la seleccion.
		tableListaCompra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableListaCompra.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columModel = tableListaCompra.getColumnModel();
		columModel.getColumn(0).setPreferredWidth(100);
		columModel.getColumn(1).setPreferredWidth(100);
		columModel.getColumn(2).setPreferredWidth(100);
		columModel.getColumn(3).setPreferredWidth(100);

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
			if(miCarrito.get(i).getCompra()>0){
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
			}/*else{
				aux = i;
			}*/
		}
		//miCarrito.remove(aux);

		tableCarrito.setModel(models);
		//table.setEnabled(false); //deshabilita la seleccion.
		tableCarrito.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCarrito.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columModel = tableCarrito.getColumnModel();
		columModel.getColumn(0).setPreferredWidth(100);
		columModel.getColumn(1).setPreferredWidth(100);
		columModel.getColumn(2).setPreferredWidth(100);
		columModel.getColumn(3).setPreferredWidth(100);
		columModel.getColumn(4).setPreferredWidth(100);

	}
}
