package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logica.Tienda;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class ListarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static Tienda tienda;
	private static Object[] fila;
	private static DefaultTableModel model;
	private JButton btnEliminar;
	private JButton okButton;
	private String cedula = "";
	private int ind=0;

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
	public ListarCliente(Tienda t) {
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
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int aux = table.getSelectedRow();
							cedula = (String) table.getModel().getValueAt(aux, 1);
							if(aux>-1){
								 okButton.setEnabled(true);
								 btnEliminar.setEnabled(true);
								 
							}
							else{
								 okButton.setEnabled(false);
								 btnEliminar.setEnabled(false);
								 cedula="";
							}
						}
					});
					String[] columnNames = {"Cédula","Nombre","Teléfono","Dirección"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnNames);
					table.setModel(model);
					loadTable();
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
				btnEliminar.setEnabled(false);
				btnEliminar.setIcon(new ImageIcon(ListarCliente.class.getResource("/imagenes/cancel.png")));
				buttonPane.add(btnEliminar);
			}
			{
				okButton = new JButton("Modificar");
				okButton.setEnabled(false);
				okButton.setIcon(new ImageIcon(ListarCliente.class.getResource("/imagenes/modificar.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
		
		table.setModel(model);
		//table.setEnabled(false); //deshabilita la seleccion.
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columModel = table.getColumnModel();
		columModel.getColumn(0).setPreferredWidth(110);
		columModel.getColumn(1).setPreferredWidth(120);
		columModel.getColumn(2).setPreferredWidth(100);
		columModel.getColumn(3).setPreferredWidth(203);
		
	}

}
