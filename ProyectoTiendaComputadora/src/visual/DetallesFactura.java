package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logica.DiscoDuro;
import logica.Factura;
import logica.MemoriaRam;
import logica.Microprocesador;
import logica.TarjetaMadre;
import logica.Tienda;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class DetallesFactura extends JDialog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	private static Factura fac;
	private JLabel cliente;
	private JLabel fechacompra;
	private JLabel codigo;
	private JLabel total;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			DetallesFactura dialog = new DetallesFactura(fac);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public DetallesFactura(Factura t) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DetallesFactura.class.getResource("/imagenes/factura.png")));
		setTitle("Factura");
		fac = t;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				codigo.setText(t.getCodigo());
				SimpleDateFormat d1 = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
				//Date ahora = new Date();
				//fechacompra.setText(d1.format(ahora));
				fechacompra.setText(d1.format(t.getFechaCompra()));
				cliente.setText(t.getClient().getNombre());
				double x = t.totalFactura();
				String z = String.valueOf(x);
				total.setText(z);
				loadTable();
			}
		});
		
		setBounds(100, 100, 694, 680);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Informaci\u00F3n de la Factura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Productos comprados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 212, 658, 303);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					String[] columnNamess = {"# de Serie","Producto","Cant.Productos","Precio Unitario"};
					model = new DefaultTableModel(){

						@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					};
					model.setColumnIdentifiers(columnNamess);
					table.setModel(model);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					//loadTable();
					scrollPane.setViewportView(table);
				}
			}
		}
		
		JLabel lblCliente = new JLabel("Cliente: ");
		lblCliente.setIcon(new ImageIcon(DetallesFactura.class.getResource("/imagenes/cliente1.png")));
		lblCliente.setBounds(445, 158, 86, 21);
		contentPanel.add(lblCliente);
		
		cliente = new JLabel("");
		cliente.setBounds(541, 158, 127, 21);
		contentPanel.add(cliente);
		{
			JLabel lblFechaDeCompra = new JLabel("Fecha de Compra:");
			lblFechaDeCompra.setIcon(new ImageIcon(DetallesFactura.class.getResource("/imagenes/date1.png")));
			lblFechaDeCompra.setBounds(165, 158, 129, 21);
			contentPanel.add(lblFechaDeCompra);
		}
		{
			fechacompra = new JLabel("");
			fechacompra.setBounds(304, 158, 121, 21);
			contentPanel.add(fechacompra);
		}
		{
			JLabel lblCdigo = new JLabel("C\u00F3digo:");
			lblCdigo.setIcon(new ImageIcon(DetallesFactura.class.getResource("/imagenes/codigo.png")));
			lblCdigo.setBounds(27, 158, 70, 21);
			contentPanel.add(lblCdigo);
		}
		{
			codigo = new JLabel("");
			codigo.setBounds(106, 158, 49, 21);
			contentPanel.add(codigo);
		}
		{
			JLabel lblTotal = new JLabel("Total:");
			lblTotal.setIcon(new ImageIcon(DetallesFactura.class.getResource("/imagenes/precio.png")));
			lblTotal.setBounds(316, 537, 64, 21);
			contentPanel.add(lblTotal);
		}
		{
			total = new JLabel("");
			total.setBounds(390, 537, 219, 21);
			contentPanel.add(total);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(DetallesFactura.class.getResource("/imagenes/invoice (2).png")));
			label.setBounds(259, 11, 146, 136);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelar = new JButton("Cerrar");
				cancelar.setIcon(new ImageIcon(DetallesFactura.class.getResource("/imagenes/cancel.png")));
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelar.setActionCommand("Cancel");
				buttonPane.add(cancelar);
			}
		}
	}

	private void loadTable() {

		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		for (int i=0; i<fac.getMisProductos().size();i++) {
			/*for (int j = 0; j < tienda.getMisFacturas().get(i).getMisProductos().size(); j++) {
				
			}*/
			fila[0] = fac.getMisProductos().get(i).getNumeroSerie();
			if(fac.getMisProductos().get(i) instanceof TarjetaMadre){
				fila[1] = "Tarjeta Madre";
			}else if(fac.getMisProductos().get(i) instanceof Microprocesador){
				fila[1] = "Microprocesador";
			}else if(fac.getMisProductos().get(i) instanceof MemoriaRam){
				fila[1] = "Memoria Ram";
			}else if(fac.getMisProductos().get(i) instanceof DiscoDuro){
				fila[1] = "Disco Duro";
			}
			fila[2] = fac.getMisProductos().get(i).getCompra();
			fila[3] = fac.getMisProductos().get(i).precioVenta();

			model.addRow(fila);
			
		}
		

		table.setModel(model);
		//table.setEnabled(false); //deshabilita la seleccion.
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
	}
}
