package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegistrarProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarProducto dialog = new RegistrarProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarProducto() {
		setBounds(100, 100, 670, 760);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelPrincipal = new JPanel();
			contentPanel.add(panelPrincipal, BorderLayout.CENTER);
			panelPrincipal.setLayout(null);
			{
				JPanel panelGeneral = new JPanel();
				panelGeneral.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelGeneral.setBounds(10, 11, 624, 129);
				panelPrincipal.add(panelGeneral);
				panelGeneral.setLayout(null);
				{
					JLabel lblPrecioDeCompra = new JLabel("Precio de Compra:");
					lblPrecioDeCompra.setBounds(10, 19, 96, 20);
					panelGeneral.add(lblPrecioDeCompra);
				}
				{
					JLabel lblMarca = new JLabel("Marca");
					lblMarca.setBounds(10, 54, 46, 20);
					panelGeneral.add(lblMarca);
				}
				{
					textField = new JTextField();
					textField.setBounds(107, 19, 148, 21);
					panelGeneral.add(textField);
					textField.setColumns(10);
				}
				{
					textField_1 = new JTextField();
					textField_1.setBounds(107, 54, 148, 21);
					panelGeneral.add(textField_1);
					textField_1.setColumns(10);
				}
				{
					JLabel lblCantidad = new JLabel("Cantidad:");
					lblCantidad.setBounds(302, 22, 62, 20);
					panelGeneral.add(lblCantidad);
				}
				
				JSpinner spinner = new JSpinner();
				spinner.setBounds(374, 22, 82, 21);
				panelGeneral.add(spinner);
				
				JLabel lblModelo = new JLabel("Modelo:");
				lblModelo.setBounds(302, 54, 46, 20);
				panelGeneral.add(lblModelo);
				{
					textField_2 = new JTextField();
					textField_2.setBounds(374, 54, 82, 21);
					panelGeneral.add(textField_2);
					textField_2.setColumns(10);
				}
				{
					JLabel lblNmeroDeSerie = new JLabel("N\u00FAmero de serie:");
					lblNmeroDeSerie.setBounds(10, 95, 82, 14);
					panelGeneral.add(lblNmeroDeSerie);
				}
				{
					textField_3 = new JTextField();
					textField_3.setBounds(107, 92, 148, 21);
					panelGeneral.add(textField_3);
					textField_3.setColumns(10);
				}
			}
			{
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(10, 143, 624, 50);
				panelPrincipal.add(panel);
				panel.setLayout(null);
				{
					JLabel lblTipo = new JLabel("Tipo:");
					lblTipo.setBounds(10, 24, 46, 14);
					panel.add(lblTipo);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta Madre", "Microprocesador", "Memoria RAM", "Disco Duro"}));
					comboBox.setBounds(45, 21, 159, 20);
					panel.add(comboBox);
				}
			}
			{
				JPanel panelTarjetaMadre = new JPanel();
				panelTarjetaMadre.setBounds(10, 204, 624, 90);
				panelPrincipal.add(panelTarjetaMadre);
				panelTarjetaMadre.setLayout(null);
				{
					JLabel lblTipoRam = new JLabel("Tipo RAM:");
					lblTipoRam.setBounds(10, 11, 66, 14);
					panelTarjetaMadre.add(lblTipoRam);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setBounds(97, 8, 148, 20);
					panelTarjetaMadre.add(comboBox);
				}
				{
					JLabel lblTipoSocket = new JLabel("Tipo Socket:");
					lblTipoSocket.setBounds(10, 47, 66, 14);
					panelTarjetaMadre.add(lblTipoSocket);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setBounds(97, 44, 148, 20);
					panelTarjetaMadre.add(comboBox);
				}
				{
					JLabel lblTipoDiscoDuro = new JLabel("Tipo Disco Duro:");
					lblTipoDiscoDuro.setBounds(294, 11, 96, 20);
					panelTarjetaMadre.add(lblTipoDiscoDuro);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setBounds(385, 11, 129, 20);
					panelTarjetaMadre.add(comboBox);
				}
			}
			{
				JPanel panelMicroprocesador = new JPanel();
				panelMicroprocesador.setBounds(10, 305, 624, 84);
				panelPrincipal.add(panelMicroprocesador);
				panelMicroprocesador.setLayout(null);
				{
					JLabel label = new JLabel("Tipo Socket:");
					label.setBounds(10, 14, 66, 14);
					panelMicroprocesador.add(label);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setBounds(107, 11, 148, 20);
					panelMicroprocesador.add(comboBox);
				}
				{
					JLabel lblVelocidadmhz = new JLabel("Velocidad (MHz):");
					lblVelocidadmhz.setBounds(10, 51, 87, 14);
					panelMicroprocesador.add(lblVelocidadmhz);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setBounds(107, 48, 148, 20);
					panelMicroprocesador.add(comboBox);
				}
			}
			{
				JPanel panelRam = new JPanel();
				panelRam.setBounds(10, 400, 624, 90);
				panelPrincipal.add(panelRam);
				panelRam.setLayout(null);
				{
					JLabel lblTipo_1 = new JLabel("Tipo");
					lblTipo_1.setBounds(10, 11, 46, 14);
					panelRam.add(lblTipo_1);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setBounds(107, 8, 157, 20);
					panelRam.add(comboBox);
				}
				{
					JLabel lblCapacidadmb = new JLabel("Capacidad (MB):");
					lblCapacidadmb.setBounds(10, 52, 87, 14);
					panelRam.add(lblCapacidadmb);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setBounds(107, 49, 157, 20);
					panelRam.add(comboBox);
				}
			}
			{
				JPanel panelDiscoDuro = new JPanel();
				panelDiscoDuro.setBounds(10, 501, 624, 120);
				panelPrincipal.add(panelDiscoDuro);
				panelDiscoDuro.setLayout(null);
				{
					JLabel lblCapacidadDeAlmacenamiento = new JLabel("Capacidad de almacenamiento (GB):");
					lblCapacidadDeAlmacenamiento.setBounds(10, 11, 181, 14);
					panelDiscoDuro.add(lblCapacidadDeAlmacenamiento);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setBounds(201, 8, 143, 20);
					panelDiscoDuro.add(comboBox);
				}
				{
					JLabel lblTipoConexin = new JLabel("Tipo Conexi\u00F3n:");
					lblTipoConexin.setBounds(10, 47, 81, 14);
					panelDiscoDuro.add(lblTipoConexin);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.setBounds(201, 39, 143, 20);
					panelDiscoDuro.add(comboBox);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
