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
import javax.swing.table.DefaultTableCellRenderer;
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
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListarProducto extends JDialog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;
	private JScrollPane scrollPane;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton cancelButton;
	private String numeroSerie = "";
	private JLabel label;
	private Producto prod=null;
	private JTable tablaOrden;
	private ArrayList<Producto> orden;
	private JButton btnProcesar;
	private JLabel lblAlgo;
	private JComboBox cbOpcion;

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
	public ListarProducto() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if(orden.size()>0){
					btnProcesar.setEnabled(true);
					lblAlgo.setText("Hay ordenes para procesar");
				}else{
					btnProcesar.setEnabled(false);
					lblAlgo.setText("No hay ordenes para procesar");
				}
				if(cbOpcion.getSelectedIndex() == 0){
					//Todo
					String[] columnNames = {"Número de serie","Marca","Modelo","Tipo","Cantidad Inicial","Cantidad Real","Precio Unitario"};
					model = new DefaultTableModel(){

						@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					};
					model.setColumnIdentifiers(columnNames);
					table.setModel(model);
					loadTable();
					scrollPane.setViewportView(table);
					
				}
				else if(cbOpcion.getSelectedIndex() == 1){
					//Tarjeta Madre
					String[] columnNames = {"Número de serie","Marca","Modelo","Tipo Conector Micro","Tipo de Ram","Tipo de Disco Duro"};
					model = new DefaultTableModel(){

						@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					};
					model.setColumnIdentifiers(columnNames);
					table.setModel(model);
					loadTabletTarjeta();
					scrollPane.setViewportView(table);
				}
				else if(cbOpcion.getSelectedIndex() == 2){
					//Microprocesador
					String[] columnNames = {"Número de serie","Marca","Modelo","Tipo Socket","Velocidad de procesamiento(MHz)"};
					model = new DefaultTableModel(){

						@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					};
					model.setColumnIdentifiers(columnNames);
					table.setModel(model);
					loadTableMicro();
					scrollPane.setViewportView(table);
				}
				else if(cbOpcion.getSelectedIndex() == 3){
					//Memoria Ram
					String[] columnNames = {"Número de serie","Marca","Modelo","Capacidad (MB)","Tipo Memoria"};
					model = new DefaultTableModel(){

						@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					};
					model.setColumnIdentifiers(columnNames);
					table.setModel(model);
					loadTableMemoriaRam();
					scrollPane.setViewportView(table);

				}
				else if(cbOpcion.getSelectedIndex() == 4){
					//Disco Duro
					String[] columnNames = {"Número de serie","Marca","Modelo","Capacidad (GB)","Tipo Conexión"};
					model = new DefaultTableModel(){

						@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					};
					model.setColumnIdentifiers(columnNames);
					table.setModel(model);
					loadTableDiscoDuro();
					scrollPane.setViewportView(table);

				}
				
			}
		});
		setForeground(Color.BLUE);
		orden = new ArrayList<Producto>();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarProducto.class.getResource("/imagenes/pcparts.png")));
		setTitle("Lista de Productos");
		setBounds(100, 100, 1300, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				scrollPane = new JScrollPane();
				scrollPane.setBounds(6, 77, 722, 328);
				panel.add(scrollPane);
				{
					
					String[] columnNames = {"Número de serie","Marca","Modelo","Tipo","Cantidad Inicial","Cantidad Real","Precio Unitario"};
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int aux=table.getSelectedRow();
							if(aux>-1){
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);
							    numeroSerie  = (String) table.getModel().getValueAt(aux, 0);
							    prod = Tienda.getInstance().indexProducto(numeroSerie);
							   
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
			
			cbOpcion = new JComboBox();
			cbOpcion.setModel(new DefaultComboBoxModel(new String[] {"Todo", "Tarjeta Madre", "Microprocesador", "Memoria Ram", "Disco Duro"}));
			cbOpcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(cbOpcion.getSelectedIndex() == 0){
						//Todo
						String[] columnNames = {"Número de serie","Marca","Modelo","Tipo","Cantidad Inicial","Cantidad Real","Precio Unitario"};
						model = new DefaultTableModel(){

							@Override
							public boolean isCellEditable(int row, int column){
								return false;
							}
						};
						model.setColumnIdentifiers(columnNames);
						table.setModel(model);
						loadTable();
						scrollPane.setViewportView(table);
						
					}
					else if(cbOpcion.getSelectedIndex() == 1){
						//Tarjeta Madre
						String[] columnNames = {"Número de serie","Marca","Modelo","Tipo Conector Micro","Tipo de Ram","Tipo de Disco Duro"};
						model = new DefaultTableModel(){

							@Override
							public boolean isCellEditable(int row, int column){
								return false;
							}
						};
						model.setColumnIdentifiers(columnNames);
						table.setModel(model);
						loadTabletTarjeta();
						scrollPane.setViewportView(table);
					}
					else if(cbOpcion.getSelectedIndex() == 2){
						//Microprocesador
						String[] columnNames = {"Número de serie","Marca","Modelo","Tipo Socket","Velocidad de procesamiento(MHz)"};
						model = new DefaultTableModel(){

							@Override
							public boolean isCellEditable(int row, int column){
								return false;
							}
						};
						model.setColumnIdentifiers(columnNames);
						table.setModel(model);
						loadTableMicro();
						scrollPane.setViewportView(table);
					}
					else if(cbOpcion.getSelectedIndex() == 3){
						//Memoria Ram
						String[] columnNames = {"Número de serie","Marca","Modelo","Capacidad (MB)","Tipo Memoria"};
						model = new DefaultTableModel(){

							@Override
							public boolean isCellEditable(int row, int column){
								return false;
							}
						};
						model.setColumnIdentifiers(columnNames);
						table.setModel(model);
						loadTableMemoriaRam();
						scrollPane.setViewportView(table);

					}
					else if(cbOpcion.getSelectedIndex() == 4){
						//Disco Duro
						String[] columnNames = {"Número de serie","Marca","Modelo","Capacidad (GB)","Tipo Conexión"};
						model = new DefaultTableModel(){

							@Override
							public boolean isCellEditable(int row, int column){
								return false;
							}
						};
						model.setColumnIdentifiers(columnNames);
						table.setModel(model);
						loadTableDiscoDuro();
						scrollPane.setViewportView(table);

					}
					
				}

				

				

				

				
			});
			cbOpcion.setBounds(559, 23, 127, 21);
			panel.add(cbOpcion);
			{
				label = new JLabel("");
				label.setIcon(new ImageIcon(ListarProducto.class.getResource("/imagenes/alll.png")));
				label.setBounds(697, 15, 25, 36);
				panel.add(label);
			}
			
			JPanel ordernar = new JPanel();
			ordernar.setBorder(new TitledBorder(null, "Ordenes de Compra", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
			ordernar.setBounds(738, 11, 526, 394);
			panel.add(ordernar);
			ordernar.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(10, 93, 506, 290);
			ordernar.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane_1 = new JScrollPane();
				panel_1.add(scrollPane_1, BorderLayout.CENTER);
				{
					tablaOrden = new JTable();
					loadTablex();
					scrollPane_1.setViewportView(tablaOrden);
				}
			}
			btnProcesar = new JButton("Procesar");
			btnProcesar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < orden.size(); i++) {
						for (int j = 0; j <  Tienda.getInstance().getMisProductos().size(); j++) {
							if(orden.get(i).getNumeroSerie().equalsIgnoreCase( Tienda.getInstance().getMisProductos().get(j).getNumeroSerie())){
								 //Tienda.getInstance().getMisProductos().get(j).setCantInicial(Tienda.getInstance().getMisProductos().get(j));
								 Tienda.getInstance().getMisProductos().get(j).setCantReal(Tienda.getInstance().getMisProductos().get(j).getCantReal()+orden.get(i).getCantReal());
								 break;
							}
						}
					}
					orden.removeAll(orden);
					JOptionPane.showMessageDialog(null, "Productos procesados", "Información", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			btnProcesar.setEnabled(false);
			btnProcesar.setBounds(427, 58, 89, 23);
			ordernar.add(btnProcesar);
			lblAlgo = new JLabel("");
			lblAlgo.setBounds(10, 27, 506, 21);
			ordernar.add(lblAlgo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(null,  "Estas seguro de eliminar producto?", "Información", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
						if(!numeroSerie.equalsIgnoreCase("")){
							Tienda.getInstance().eliminiarProducto(numeroSerie);
						    loadTable();
						    btnEliminar.setEnabled(false);
						    btnModificar.setEnabled(false);
						}
						}
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setIcon(new ImageIcon(ListarProducto.class.getResource("/imagenes/cancel.png")));
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
					}
				});
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

	private void loadTablex() {
		String[] columnNamex = {"Número de serie","Marca","Modelo","Producto","Cant. a Comprar"};
		
		//tablaOrden.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNamex);
		tablaOrden.setModel(model);
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for(int i = 0 ; i < Tienda.getInstance().getMisProductos().size(); i++){
			if(Tienda.getInstance().getMisProductos().get(i).ordencompra()<0.2){
				orden.add(Tienda.getInstance().getMisProductos().get(i));
				orden.get(orden.size()-1).setCantInicial((int)(Tienda.getInstance().getMisProductos().get(i).getCantInicial()*0.2*2));
				orden.get(orden.size()-1).setCantReal((int)(Tienda.getInstance().getMisProductos().get(i).getCantInicial()*0.2*2));
			}
		}
		
		if(orden.size()>0){
			for(int i = 0 ; i < orden.size(); i++){
				fila[0] = orden.get(i).getNumeroSerie();
				fila[1] = orden.get(i).getMarca();
				fila[2] = orden.get(i).getModelo();
				
				if(orden.get(i) instanceof TarjetaMadre){
					fila[3] = "Tarjeta Madre";
				}
				else if(orden.get(i) instanceof Microprocesador){
					fila[3] = "Microprocesador";
				}
				else if(orden.get(i) instanceof MemoriaRam){
					fila[3] = "MemoriaRam";
				}
				else if(orden.get(i) instanceof DiscoDuro){
					fila[3] = "Disco Duro";
				}
				fila[4] = orden.get(i).getCantInicial();
				
				
		
				model.addRow(fila);
				
				
				
				}
		}
	
		tablaOrden.setModel(model);
			//table.setEnabled(false); //deshabilita la seleccion.
		//tablaOrden.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//TableColumnModel columModel = table.getColumnModel();
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for (int i = 0; i < tablaOrden.getColumnCount(); i++) {
				tablaOrden.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		
	}

	public static void loadTable() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		for(int i = 0 ; i < Tienda.getInstance().getMisProductos().size(); i++){
			fila[0] = Tienda.getInstance().getMisProductos().get(i).getNumeroSerie();
			fila[1] = Tienda.getInstance().getMisProductos().get(i).getMarca();
			fila[2] = Tienda.getInstance().getMisProductos().get(i).getModelo();
			
			if(Tienda.getInstance().getMisProductos().get(i) instanceof TarjetaMadre){
				fila[3] = "Tarjeta Madre";
			}
			else if(Tienda.getInstance().getMisProductos().get(i) instanceof Microprocesador){
				fila[3] = "Microprocesador";
			}
			else if(Tienda.getInstance().getMisProductos().get(i) instanceof MemoriaRam){
				fila[3] = "MemoriaRam";
			}
			else if(Tienda.getInstance().getMisProductos().get(i) instanceof DiscoDuro){
				fila[3] = "Disco Duro";
			}
			fila[4] = Tienda.getInstance().getMisProductos().get(i).getCantInicial();
			fila[5] = Tienda.getInstance().getMisProductos().get(i).getCantReal();
			fila[6] = Tienda.getInstance().getMisProductos().get(i).getPrecio();
			
	
			model.addRow(fila);
			
			
			
			}
	
			table.setModel(model);
			//table.setEnabled(false); //deshabilita la seleccion.
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel columModel = table.getColumnModel();
			/*columModel.getColumn(0).setPreferredWidth(130);
			columModel.getColumn(1).setPreferredWidth(95);
			columModel.getColumn(2).setPreferredWidth(130);
			columModel.getColumn(3).setPreferredWidth(110);
			columModel.getColumn(4).setPreferredWidth(126);
			columModel.getColumn(5).setPreferredWidth(126);*/
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		
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
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel columModel = table.getColumnModel();
			/*columModel.getColumn(0).setPreferredWidth(130);
			columModel.getColumn(1).setPreferredWidth(95);
			columModel.getColumn(2).setPreferredWidth(120);
			columModel.getColumn(3).setPreferredWidth(131);
			columModel.getColumn(4).setPreferredWidth(118);
			columModel.getColumn(5).setPreferredWidth(123);*/
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		
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
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel columModel = table.getColumnModel();
			/*columModel.getColumn(0).setPreferredWidth(130);
			columModel.getColumn(1).setPreferredWidth(100);
			columModel.getColumn(2).setPreferredWidth(130);
			columModel.getColumn(3).setPreferredWidth(150);*/
			columModel.getColumn(4).setPreferredWidth(207);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		
		
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
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel columModel = table.getColumnModel();
		/*	columModel.getColumn(0).setPreferredWidth(140);
			columModel.getColumn(1).setPreferredWidth(140);
			columModel.getColumn(2).setPreferredWidth(137);
			columModel.getColumn(3).setPreferredWidth(150);
			columModel.getColumn(4).setPreferredWidth(150);*/
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		
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
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel columModel = table.getColumnModel();
		/*	columModel.getColumn(0).setPreferredWidth(140);
			columModel.getColumn(1).setPreferredWidth(135);
			columModel.getColumn(2).setPreferredWidth(140);
			columModel.getColumn(3).setPreferredWidth(150);
			columModel.getColumn(4).setPreferredWidth(152);*/
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		
	}
}
