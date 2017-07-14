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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel panelTarjetaMadre;
	private JPanel panelMicroprocesador;
	private JPanel panelRam;
	private JPanel panelDiscoDuro;
	private JComboBox cbTipo;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				cbTipo.setSelectedItem(0);
				if(cbTipo.getSelectedIndex()==0){
					panelTarjetaMadre.setVisible(true);
					panelMicroprocesador.setVisible(false);
					panelRam.setVisible(false);
					panelDiscoDuro.setVisible(false);
				}else if(cbTipo.getSelectedIndex()==1){
					panelTarjetaMadre.setVisible(false);
					panelMicroprocesador.setVisible(true);
					panelRam.setVisible(false);
					panelDiscoDuro.setVisible(false);
				}else if(cbTipo.getSelectedIndex()==2){
					panelTarjetaMadre.setVisible(false);
					panelMicroprocesador.setVisible(false);
					panelRam.setVisible(true);
					panelDiscoDuro.setVisible(false);
				}else if(cbTipo.getSelectedIndex()==3){
					panelTarjetaMadre.setVisible(false);
					panelMicroprocesador.setVisible(false);
					panelRam.setVisible(false);
					panelDiscoDuro.setVisible(true);
				}
				
			}
		});
		setBounds(100, 100, 670, 380);
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
					lblPrecioDeCompra.setBounds(10, 19, 111, 20);
					panelGeneral.add(lblPrecioDeCompra);
				}
				{
					JLabel lblMarca = new JLabel("Marca:");
					lblMarca.setBounds(10, 54, 46, 20);
					panelGeneral.add(lblMarca);
				}
				{
					textField = new JTextField();
					textField.setBounds(155, 19, 148, 21);
					panelGeneral.add(textField);
					textField.setColumns(10);
				}
				{
					textField_1 = new JTextField();
					textField_1.setBounds(155, 54, 148, 21);
					panelGeneral.add(textField_1);
					textField_1.setColumns(10);
				}
				{
					JLabel lblCantidad = new JLabel("Cantidad:");
					lblCantidad.setBounds(396, 21, 62, 20);
					panelGeneral.add(lblCantidad);
				}
				
				JSpinner spinner = new JSpinner();
				spinner.setBounds(468, 21, 82, 21);
				panelGeneral.add(spinner);
				
				JLabel lblModelo = new JLabel("Modelo:");
				lblModelo.setBounds(396, 53, 46, 20);
				panelGeneral.add(lblModelo);
				{
					textField_2 = new JTextField();
					textField_2.setBounds(468, 53, 82, 21);
					panelGeneral.add(textField_2);
					textField_2.setColumns(10);
				}
				{
					JLabel lblNmeroDeSerie = new JLabel("N\u00FAmero de serie:");
					lblNmeroDeSerie.setBounds(10, 95, 111, 14);
					panelGeneral.add(lblNmeroDeSerie);
				}
				{
					textField_3 = new JTextField();
					textField_3.setBounds(155, 92, 148, 21);
					panelGeneral.add(textField_3);
					textField_3.setColumns(10);
				}
			}
			{
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(10, 143, 624, 144);
				panelPrincipal.add(panel);
				panel.setLayout(null);
				{
					JLabel lblTipo = new JLabel("Tipo de Producto:");
					lblTipo.setBounds(20, 27, 105, 14);
					panel.add(lblTipo);
				}
				{
					cbTipo = new JComboBox();
					cbTipo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cbTipo.setSelectedItem(0);
							if(cbTipo.getSelectedIndex()==0){
								panelTarjetaMadre.setVisible(true);
								panelMicroprocesador.setVisible(false);
								panelRam.setVisible(false);
								panelDiscoDuro.setVisible(false);
							}else if(cbTipo.getSelectedIndex()==1){
								panelTarjetaMadre.setVisible(false);
								panelMicroprocesador.setVisible(true);
								panelRam.setVisible(false);
								panelDiscoDuro.setVisible(false);
							}else if(cbTipo.getSelectedIndex()==2){
								panelTarjetaMadre.setVisible(false);
								panelMicroprocesador.setVisible(false);
								panelRam.setVisible(true);
								panelDiscoDuro.setVisible(false);
							}else if(cbTipo.getSelectedIndex()==3){
								panelTarjetaMadre.setVisible(false);
								panelMicroprocesador.setVisible(false);
								panelRam.setVisible(false);
								panelDiscoDuro.setVisible(true);
							}
							
						}
					});
					cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta Madre", "Microprocesador", "Memoria RAM", "Disco Duro"}));
					cbTipo.setBounds(125, 24, 148, 21);
					panel.add(cbTipo);
				}
				{
					panelTarjetaMadre = new JPanel();
					panelTarjetaMadre.setBounds(10, 49, 604, 76);
					panel.add(panelTarjetaMadre);
					panelTarjetaMadre.setLayout(null);
					{
						JLabel lblTipoRam = new JLabel("Tipo RAM:");
						lblTipoRam.setBounds(10, 11, 95, 14);
						panelTarjetaMadre.add(lblTipoRam);
					}
					{
						JComboBox cbTipoRam = new JComboBox();
						cbTipoRam.setBounds(115, 11, 148, 21);
						panelTarjetaMadre.add(cbTipoRam);
					}
					{
						JLabel lblTipoSocket = new JLabel("Tipo Socket:");
						lblTipoSocket.setBounds(10, 47, 95, 14);
						panelTarjetaMadre.add(lblTipoSocket);
					}
					{
						JComboBox cbTipoSocket = new JComboBox();
						cbTipoSocket.setBounds(115, 47, 148, 21);
						panelTarjetaMadre.add(cbTipoSocket);
					}
					{
						JLabel lblTipoDiscoDuro = new JLabel("Tipo Disco Duro:");
						lblTipoDiscoDuro.setBounds(294, 11, 108, 20);
						panelTarjetaMadre.add(lblTipoDiscoDuro);
					}
					{
						JComboBox cbTipoDisco = new JComboBox();
						cbTipoDisco.setBounds(412, 11, 129, 21);
						panelTarjetaMadre.add(cbTipoDisco);
					}
				}
				{
					panelMicroprocesador = new JPanel();
					panelMicroprocesador.setBounds(10, 49, 604, 76);
					panel.add(panelMicroprocesador);
					panelMicroprocesador.setLayout(null);
					{
						JLabel label = new JLabel("Tipo Socket:");
						label.setBounds(10, 14, 95, 14);
						panelMicroprocesador.add(label);
					}
					{
						JComboBox cbtiposocketmicro = new JComboBox();
						cbtiposocketmicro.setBounds(115, 11, 148, 21);
						panelMicroprocesador.add(cbtiposocketmicro);
					}
					{
						JLabel lblVelocidadmhz = new JLabel("Velocidad (MHz):");
						lblVelocidadmhz.setBounds(10, 51, 97, 14);
						panelMicroprocesador.add(lblVelocidadmhz);
					}
					{
						JComboBox cbVelocidadmicro = new JComboBox();
						cbVelocidadmicro.setBounds(115, 48, 148, 21);
						panelMicroprocesador.add(cbVelocidadmicro);
					}
				}
				{
					panelRam = new JPanel();
					panelRam.setBounds(10, 49, 604, 76);
					panel.add(panelRam);
					panelRam.setLayout(null);
					{
						JLabel lblTipo_1 = new JLabel("Tipo");
						lblTipo_1.setBounds(10, 11, 46, 14);
						panelRam.add(lblTipo_1);
					}
					{
						JComboBox cbtipodelram = new JComboBox();
						cbtipodelram.setBounds(115, 8, 157, 21);
						panelRam.add(cbtipodelram);
					}
					{
						JLabel lblCapacidadmb = new JLabel("Capacidad (MB):");
						lblCapacidadmb.setBounds(10, 52, 95, 14);
						panelRam.add(lblCapacidadmb);
					}
					{
						JComboBox cbCapacidadmbRam = new JComboBox();
						cbCapacidadmbRam.setBounds(115, 49, 157, 21);
						panelRam.add(cbCapacidadmbRam);
					}
				}
				{
					panelDiscoDuro = new JPanel();
					panelDiscoDuro.setBounds(10, 49, 604, 76);
					panel.add(panelDiscoDuro);
					panelDiscoDuro.setLayout(null);
					{
						JLabel lblCapacidadDeAlmacenamiento = new JLabel("Capacidad de almacenamiento (GB):");
						lblCapacidadDeAlmacenamiento.setBounds(10, 11, 222, 14);
						panelDiscoDuro.add(lblCapacidadDeAlmacenamiento);
					}
					{
						JComboBox cbCapacidadAlmacenamiento = new JComboBox();
						cbCapacidadAlmacenamiento.setBounds(242, 11, 143, 21);
						panelDiscoDuro.add(cbCapacidadAlmacenamiento);
					}
					{
						JLabel lblTipoConexin = new JLabel("Tipo Conexi\u00F3n:");
						lblTipoConexin.setBounds(10, 47, 159, 14);
						panelDiscoDuro.add(lblTipoConexin);
					}
					{
						JComboBox cbTipoConexionDisco = new JComboBox();
						cbTipoConexionDisco.setBounds(242, 47, 143, 21);
						panelDiscoDuro.add(cbTipoConexionDisco);
					}
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
