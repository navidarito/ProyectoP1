package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logica.Cliente;
import logica.DiscoDuro;
import logica.MemoriaRam;
import logica.Microprocesador;
import logica.TarjetaMadre;
import logica.Tienda;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.ListSelectionModel;
import java.awt.Color;

public class ListarVenta extends JDialog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6051746858869324547L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Tienda tienda;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private static Object[] fila;
	private static DefaultTableModel models;
	private String cedula = "";
	private JButton Detalles;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			ListarVenta dialog = new ListarVenta();
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
	public ListarVenta(Tienda t) {
		setForeground(Color.BLUE);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarVenta.class.getResource("/imagenes/listaVenta.png")));
		tienda = t;
		setTitle("Lista de Ventas");
		setBounds(100, 100, 641, 433);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n de Ventas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					String[] columnNamess = {"Código","Nombre del Cliente","Cédula del Cliente","Cant.Productos","Total"};
					models = new DefaultTableModel(){

						@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					};
					models.setColumnIdentifiers(columnNamess);
					table.setModel(models);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int aux=table.getSelectedRow();
							if(aux>-1){
								btnEliminar.setEnabled(true);
								Detalles.setEnabled(true);
								
							}
							else{
								btnEliminar.setEnabled(false);
								Detalles.setEnabled(false);
							}
						}
					});
					
					loadVentas();
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(null,  "Estas seguro de eliminar venta?", "Información", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
							String codi = (String)table.getModel().getValueAt(table.getSelectedRow(), 0);
							  if(!codi.equalsIgnoreCase("")){
								  tienda.eleminarFactura(codi);;
								  loadVentas();
								  btnEliminar.setEnabled(false);
								  
							  }
							}
					}
					
				});
				{
					Detalles = new JButton("Ver Detalles");
					Detalles.setIcon(new ImageIcon(ListarVenta.class.getResource("/imagenes/detalle.png")));
					Detalles.setEnabled(false);
					Detalles.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int n = table.getModel().getRowCount();
							System.out.println(n);
							DetallesFactura zx = new DetallesFactura(tienda.getMisFacturas().get(n-1));
							zx.setLocationRelativeTo(null);
							zx.setModal(true);
							zx.setVisible(true);
						}
					});
					buttonPane.add(Detalles);
				}
				btnEliminar.setEnabled(false);
				btnEliminar.setIcon(new ImageIcon(ListarVenta.class.getResource("/imagenes/cancel.png")));
				buttonPane.add(btnEliminar);
			}
			{
				btnCancelar = new JButton("Salir");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setIcon(new ImageIcon(ListarVenta.class.getResource("/imagenes/salir.png")));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	private void loadVentas() {
	
		

		models.setRowCount(0);
		fila = new Object[models.getColumnCount()];

		for (int i=0; i<tienda.getMisFacturas().size();i++) {
			/*for (int j = 0; j < tienda.getMisFacturas().get(i).getMisProductos().size(); j++) {
				
			}*/
			fila[0] = tienda.getMisFacturas().get(i).getCodigo();
			fila[1] = tienda.getMisFacturas().get(i).getClient().getNombre();
			fila[2] = tienda.getMisFacturas().get(i).getClient().getCedula();
			fila[3] = tienda.getMisFacturas().get(i).getMisProductos().size();
			fila[4] = tienda.getMisFacturas().get(i).totalFactura();

			models.addRow(fila);
			
		}
		

		table.setModel(models);
		//table.setEnabled(false); //deshabilita la seleccion.
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		//TableColumnModel columModel = table.getColumnModel();
		/*columModel.getColumn(0).setPreferredWidth(100);
		columModel.getColumn(1).setPreferredWidth(100);
		columModel.getColumn(2).setPreferredWidth(100);
		columModel.getColumn(3).setPreferredWidth(100);*/
	

		
	}

}
