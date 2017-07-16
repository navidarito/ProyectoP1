package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import logica.Tienda;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class RealizarVenta extends JDialog {
	private JTable tableListaCompra;
	private JTable tableCarrito;
	private JTextField txtnombreCliente;
	private JTextField txtFechaActual;
	private static Tienda tienda;

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
			
			JComboBox cbCliente = new JComboBox();
			cbCliente.setBounds(177, 59, 132, 21);
			panel.add(cbCliente);
			
			txtnombreCliente = new JTextField();
			txtnombreCliente.setEnabled(false);
			txtnombreCliente.setBounds(223, 103, 86, 21);
			panel.add(txtnombreCliente);
			txtnombreCliente.setColumns(10);
			
			JLabel lblCliente = new JLabel("Cliente:");
			lblCliente.setBounds(93, 59, 74, 21);
			panel.add(lblCliente);
			
			JLabel lblNombreDelCliente = new JLabel("Nombre del Cliente:");
			lblNombreDelCliente.setBounds(92, 103, 117, 21);
			panel.add(lblNombreDelCliente);
			
			JLabel lblFechaActual = new JLabel("Fecha actual:");
			lblFechaActual.setBounds(470, 62, 101, 21);
			panel.add(lblFechaActual);
			
			txtFechaActual = new JTextField();
			txtFechaActual.setEnabled(false);
			txtFechaActual.setBounds(581, 62, 123, 21);
			panel.add(txtFechaActual);
			txtFechaActual.setColumns(10);
			
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
		}
	}
}
