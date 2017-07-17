package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import logica.DiscoDuro;

import logica.MemoriaRam;
import logica.Microprocesador;
import logica.Producto;

import logica.TarjetaMadre;
import logica.Tienda;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Tienda tienda;
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;
	private JScrollPane scrollPane;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton cancelButton;
	private String numeroSerie = "";

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ListarProducto dialog = new ListarProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListarProducto(Tienda t) {
		tienda = t;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarProducto.class.getResource("/imagenes/pcparts.png")));
		setTitle("Lista de Productos");
		setBounds(100, 100, 758, 467);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n de productos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				scrollPane = new JScrollPane();
				scrollPane.setBounds(6, 77, 720, 301);
				panel.add(scrollPane);
				{
					
					String[] columnNames = {"Número de serie","Marca","Modelo","Tipo","Cantidad Inicial","Cantidad Real"};
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int aux=table.getSelectedRow();
							if(aux>-1){
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);
							    numeroSerie  = (String) table.getModel().getValueAt(aux, 0);
							   
							}
							else{
								btnEliminar.setEnabled(false);
								btnModificar.setEnabled(false);
								numeroSerie = "";
							}
						
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnNames);
					table.setModel(model);
					loadTable();
					scrollPane.setViewportView(table);
				}
			}
			
			JComboBox cbOpcion = new JComboBox();
			cbOpcion.setModel(new DefaultComboBoxModel(new String[] {"Todo", "Tarjeta Madre", "Microprocesador", "Memoria Ram", "Disco Duro"}));
			cbOpcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(cbOpcion.getSelectedIndex() == 0){
						//Todo
						String[] columnNames = {"Número de serie","Marca","Modelo","Tipo","Cantidad Inicial","Cantidad Real"};
						model = new DefaultTableModel();
						model.setColumnIdentifiers(columnNames);
						table.setModel(model);
						loadTable();
						scrollPane.setViewportView(table);
						
					}
					else if(cbOpcion.getSelectedIndex() == 1){
						//Tarjeta Madre
						String[] columnNames = {"Número de serie","Marca","Modelo","Tipo Conector Micro","Tipo de Ram","Tipo de Disco Duro"};
						model = new DefaultTableModel();
						model.setColumnIdentifiers(columnNames);
						table.setModel(model);
						loadTabletTarjeta();
						scrollPane.setViewportView(table);
					}
					else if(cbOpcion.getSelectedIndex() == 2){
						//Microprocesador
						String[] columnNames = {"Número de serie","Marca","Modelo","Tipo Socket","Velocidad de procesamiento(MHz)"};
						model = new DefaultTableModel();
						model.setColumnIdentifiers(columnNames);
						table.setModel(model);
						loadTableMicro();
						scrollPane.setViewportView(table);
					}
					else if(cbOpcion.getSelectedIndex() == 3){
						//Memoria Ram
						String[] columnNames = {"Número de serie","Marca","Modelo","Capacidad (MB)","Tipo Memoria"};
						model = new DefaultTableModel();
						model.setColumnIdentifiers(columnNames);
						table.setModel(model);
						loadTableMemoriaRam();
						scrollPane.setViewportView(table);

					}
					else if(cbOpcion.getSelectedIndex() == 4){
						//Disco Duro
						String[] columnNames = {"Número de serie","Marca","Modelo","Capacidad (GB)","Tipo Conexión"};
						model = new DefaultTableModel();
						model.setColumnIdentifiers(columnNames);
						table.setModel(model);
						loadTableDiscoDuro();
						scrollPane.setViewportView(table);

					}
					
				}

				

				

				

				
			});
			cbOpcion.setBounds(559, 30, 127, 21);
			panel.add(cbOpcion);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!numeroSerie.equalsIgnoreCase("")){
						 tienda.eliminiarProducto(numeroSerie);
						    loadTable();
						    btnEliminar.setEnabled(false);
						    btnModificar.setEnabled(false);
						}
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setIcon(new ImageIcon(ListarProducto.class.getResource("/imagenes/cancel.png")));
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.setIcon(new ImageIcon(ListarProducto.class.getResource("/imagenes/modificar.png")));
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setIcon(new ImageIcon(ListarProducto.class.getResource("/imagenes/salir.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static void loadTable() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for(int i = 0 ; i < tienda.getMisProductos().size(); i++){
			fila[0] = tienda.getMisProductos().get(i).getNumeroSerie();
			fila[1] = tienda.getMisProductos().get(i).getMarca();
			fila[2] = tienda.getMisProductos().get(i).getModelo();
			
			if(tienda.getMisProductos().get(i) instanceof TarjetaMadre){
				fila[3] = "Tarjeta Madre";
			}
			else if(tienda.getMisProductos().get(i) instanceof Microprocesador){
				fila[3] = "Microprocesador";
			}
			else if(tienda.getMisProductos().get(i) instanceof MemoriaRam){
				fila[3] = "MemoriaRam";
			}
			else if(tienda.getMisProductos().get(i) instanceof DiscoDuro){
				fila[3] = "Disco Duro";
			}
			fila[4] = tienda.getMisProductos().get(i).getCantInicial();
			fila[5] = tienda.getMisProductos().get(i).getCantReal();
			
	
			model.addRow(fila);
			
			/*switch (selection){
			case 0:
				for (Producto aux : tienda.getInstance().getMisProductos()) {
					if(aux instanceof T){
						
					}
					
				}
			}
			*/
			
			}
	
			table.setModel(model);
			//table.setEnabled(false); //deshabilita la seleccion.
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel columModel = table.getColumnModel();
			columModel.getColumn(0).setPreferredWidth(130);
			columModel.getColumn(1).setPreferredWidth(95);
			columModel.getColumn(2).setPreferredWidth(130);
			columModel.getColumn(3).setPreferredWidth(110);
			columModel.getColumn(4).setPreferredWidth(126);
			columModel.getColumn(5).setPreferredWidth(126);
		
	}
	
	
	private void loadTabletTarjeta() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (Producto aux : Tienda.getInstance().getMisProductos()) {
		 if(aux instanceof TarjetaMadre){
				fila[0] = aux.getNumeroSerie();
				fila[1] = aux.getMarca();
				fila[2] = aux.getModelo();
				fila[3] = ((TarjetaMadre) aux).getTipConectorMicro();
				fila[4] = ((TarjetaMadre) aux).getPuedeMemoriaRam();
				fila[5] = ((TarjetaMadre) aux).getPuedeDiscoDuro();
				model.addRow(fila);
			}
		}
	
			table.setModel(model);
			//table.setEnabled(false); //deshabilita la seleccion.
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel columModel = table.getColumnModel();
			columModel.getColumn(0).setPreferredWidth(130);
			columModel.getColumn(1).setPreferredWidth(95);
			columModel.getColumn(2).setPreferredWidth(120);
			columModel.getColumn(3).setPreferredWidth(131);
			columModel.getColumn(4).setPreferredWidth(118);
			columModel.getColumn(5).setPreferredWidth(123);
		
	}
	private void loadTableMicro() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (Producto aux : Tienda.getInstance().getMisProductos()) {
			if(aux instanceof Microprocesador){
				fila[0] = aux.getNumeroSerie();
				fila[1] = aux.getMarca();
				fila[2] = aux.getModelo();
				fila[3] = ((Microprocesador) aux).getTipoConexion();
				fila[4] = ((Microprocesador) aux).getVelocidadProcesamiento();
	
				model.addRow(fila);
			}
		}
	
			table.setModel(model);
			//table.setEnabled(false); //deshabilita la seleccion.
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel columModel = table.getColumnModel();
			columModel.getColumn(0).setPreferredWidth(130);
			columModel.getColumn(1).setPreferredWidth(100);
			columModel.getColumn(2).setPreferredWidth(130);
			columModel.getColumn(3).setPreferredWidth(150);
			columModel.getColumn(4).setPreferredWidth(207);
		
		
	}
	private void loadTableMemoriaRam() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (Producto aux : Tienda.getInstance().getMisProductos()) {
			if(aux instanceof MemoriaRam){
				fila[0] = aux.getNumeroSerie();
				fila[1] = aux.getMarca();
				fila[2] = aux.getModelo();
				fila[3] = ((MemoriaRam) aux).getCantidadMemoria();
				fila[4] = ((MemoriaRam) aux).getTipoMemoria();
	
				model.addRow(fila);
			}
		}
	
			table.setModel(model);
			//table.setEnabled(false); //deshabilita la seleccion.
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel columModel = table.getColumnModel();
			columModel.getColumn(0).setPreferredWidth(140);
			columModel.getColumn(1).setPreferredWidth(140);
			columModel.getColumn(2).setPreferredWidth(137);
			columModel.getColumn(3).setPreferredWidth(150);
			columModel.getColumn(4).setPreferredWidth(150);
		
	}
	private void loadTableDiscoDuro() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (Producto aux : Tienda.getInstance().getMisProductos()) {
			if(aux instanceof DiscoDuro){
				fila[0] = aux.getNumeroSerie();
				fila[1] = aux.getMarca();
				fila[2] = aux.getModelo();
				fila[3] = ((DiscoDuro) aux).getCapacidadAlmacenamiento();
				fila[4] = ((DiscoDuro) aux).getTipoConexion();
	
				model.addRow(fila);
			}
		}
	
			table.setModel(model);
			//table.setEnabled(false); //deshabilita la seleccion.
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel columModel = table.getColumnModel();
			columModel.getColumn(0).setPreferredWidth(140);
			columModel.getColumn(1).setPreferredWidth(135);
			columModel.getColumn(2).setPreferredWidth(140);
			columModel.getColumn(3).setPreferredWidth(150);
			columModel.getColumn(4).setPreferredWidth(152);
		
	}
}
