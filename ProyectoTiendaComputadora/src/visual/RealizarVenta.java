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


import logica.DiscoDuro;
import logica.MemoriaRam;
import logica.Microprocesador;
import logica.TarjetaMadre;
import logica.Tienda;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class RealizarVenta extends JDialog {
	private JTable tableListaCompra;
	private JTable tableCarrito;
	private static Tienda tienda;
	private static Object[] fila;
	private static DefaultTableModel model;
	private JComboBox cbCliente;
	private JLabel fechaxlabel;
	private JLabel nombreCliente;

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
				tableListaCompra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] columnNames = {"Disponible","Producto","Marca","Precio"};
				model = new DefaultTableModel(){
					 
					 @Override
					 public boolean isCellEditable(int row, int column){
					 return false;
					 }
					};
				model.setColumnIdentifiers(columnNames);
				tableListaCompra.setModel(model);
				loadTable();//Lista de productos a vender
				int n = cbCliente.getSelectedIndex();
				if(n>-1){
					nombreCliente.setText(tienda.getMisClientes().get(n).getNombre());
				}
			
				Date current = new Date();
				SimpleDateFormat d1 = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
				System.out.println(d1.format(current));
				fechaxlabel.setText(d1.format(current));
				
			}

		
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(RealizarVenta.class.getResource("/imagenes/vender.png")));
		tienda = t;
		setTitle("Realizar Compra");
		setBounds(100, 100, 882, 576);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Comprar");
				okButton.setSize(73, 21);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
			cbCliente.setBounds(177, 59, 132, 21);
			panel.add(cbCliente);
			
			JLabel lblCliente = new JLabel("Cliente:");
			lblCliente.setBounds(93, 59, 74, 21);
			panel.add(lblCliente);
			
			JLabel lblNombreDelCliente = new JLabel("Nombre del Cliente:");
			lblNombreDelCliente.setBounds(92, 103, 117, 21);
			panel.add(lblNombreDelCliente);
			
			JLabel lblFechaActual = new JLabel("Fecha actual:");
			lblFechaActual.setBounds(470, 62, 101, 21);
			panel.add(lblFechaActual);
			
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/agregaralCarrito.png")));
			btnAgregar.setBounds(382, 241, 101, 21);
			panel.add(btnAgregar);
			
			JButton btnQuitar = new JButton("Quitar");
			btnQuitar.setIcon(new ImageIcon(RealizarVenta.class.getResource("/imagenes/cancel.png")));
			btnQuitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnQuitar.setBounds(382, 381, 101, 21);
			panel.add(btnQuitar);
			
			fechaxlabel = new JLabel("");
			fechaxlabel.setBounds(560, 62, 123, 21);
			panel.add(fechaxlabel);
			
			nombreCliente = new JLabel("");
			nombreCliente.setBounds(223, 103, 86, 21);
			panel.add(nombreCliente);
		}
	}
	
	private void loadTable() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		//System.out.println(Biblioteca.getInstances().getMisPublicaciones().size());
		for(int i = 0 ; i < (tienda.getMisProductos().size()); i++){
			
			if(tienda.getMisProductos().get(i).getCantReal()>0){
				fila[0] = tienda.getMisProductos().get(i).getCantReal();
				if(tienda.getMisProductos().get(i) instanceof TarjetaMadre){
					fila[1] = "Tarjeta Madre";
				}else if(tienda.getMisProductos().get(i) instanceof Microprocesador){
					fila[1] = "Microprocesador";
				}else if(tienda.getMisProductos().get(i) instanceof MemoriaRam){
					fila[1] = "Memoria Ram";
				}else if(tienda.getMisProductos().get(i) instanceof DiscoDuro){
					fila[1] = "Disco Duro";
				}
				
				fila[2] = tienda.getMisProductos().get(i).getModelo(); 
				fila[3] = tienda.getMisProductos().get(i).precioVenta();
		
				model.addRow(fila);
			}
			
		}
		
		tableListaCompra.setModel(model);
		//table.setEnabled(false); //deshabilita la seleccion.
		tableListaCompra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableListaCompra.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columModel = tableListaCompra.getColumnModel();
		columModel.getColumn(0).setPreferredWidth(100);
		columModel.getColumn(1).setPreferredWidth(100);
		columModel.getColumn(2).setPreferredWidth(100);
		columModel.getColumn(2).setPreferredWidth(100);
		
	}
}
