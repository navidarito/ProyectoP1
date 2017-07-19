package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logica.Cliente;
import logica.Tienda;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.ListSelectionModel;
import java.awt.Color;

public class ListarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static JTable table_1;
	private static Tienda tienda;
	private static Object[] fila;
	private static DefaultTableModel model;
	private JButton btnEliminar;
	private JButton btnModificar;
	private String cedula = "";
	private int ind=0;
	private Cliente clien = null;
	//private  String nombreCliente= "";

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			ListarCliente dialog = new ListarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarCliente(Tienda t ) {
		setBackground(new Color(253, 245, 230));
		setForeground(Color.BLUE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarCliente.class.getResource("/imagenes/listCliente.png")));
		setTitle("Listar Cliente");
		tienda=t;
		setBounds(100, 100, 572, 456);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Listado de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				{
					table = new JTable();
					table_1 = new JTable();
					table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table_1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int aux = table_1.getSelectedRow();
							if(aux>-1){
								 btnModificar.setEnabled(true);
								 btnEliminar.setEnabled(true);
								 cedula= (String) table_1.getModel().getValueAt(aux, 0);
								 clien = tienda.indexCliente(cedula);
								 
							}
							else{
								 btnModificar.setEnabled(false);
								 btnEliminar.setEnabled(false);
								 cedula="";
							}
						}
					});
					String[] columnNames = {"Cédula","Nombre","Teléfono","Dirección"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnNames);
					table_1.setModel(model);
					loadTable();
					scrollPane.setViewportView(table_1);
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
						if(JOptionPane.showConfirmDialog(null,  "Estas seguro de eliminar cliente?", "Información", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					  if(!cedula.equalsIgnoreCase("")){
						  tienda.eliminiarCliente(cedula);
						  loadTable();
						  btnEliminar.setEnabled(false);
						  btnModificar.setEnabled(false);
					  }
						}
					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setIcon(new ImageIcon(ListarCliente.class.getResource("/imagenes/cancel.png")));
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarCliente cli = new RegistrarCliente(t, clien, true);
						cli.setModal(true);
						cli.setLocationRelativeTo(null);
						cli.setVisible(true);
						loadTable();
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setIcon(new ImageIcon(ListarCliente.class.getResource("/imagenes/modificar.png")));
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setIcon(new ImageIcon(ListarCliente.class.getResource("/imagenes/salir.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void loadTable() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for(int i = 0 ; i < tienda.getMisClientes().size(); i++){
			
		
			fila[0] = tienda.getMisClientes().get(i).getCedula();
			fila[1] = tienda.getMisClientes().get(i).getNombre();
			fila[2] = tienda.getMisClientes().get(i).getTelefono();
			fila[3] = tienda.getMisClientes().get(i).getDireccion();
	
			model.addRow(fila);
			
		}
		
		table_1.setModel(model);
		//table.setEnabled(false); //deshabilita la seleccion.
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columModel = table_1.getColumnModel();
		columModel.getColumn(0).setPreferredWidth(110);
		columModel.getColumn(1).setPreferredWidth(120);
		columModel.getColumn(2).setPreferredWidth(100);
		columModel.getColumn(3).setPreferredWidth(203);
		
	}

}
