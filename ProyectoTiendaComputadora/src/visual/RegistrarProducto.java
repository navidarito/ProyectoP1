package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logica.Cliente;
import logica.DiscoDuro;
import logica.MemoriaRam;
import logica.Microprocesador;
import logica.Producto;
import logica.TarjetaMadre;
import logica.Tienda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class RegistrarProducto extends JDialog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String capram = null;
	protected static final String tipram = null;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtmarca;
	private JTextField txtmodelo;
	private JPanel panelTarjetaMadre;
	private JPanel panelMicroprocesador;
	private JPanel panelRam;
	private JPanel panelDiscoDuro;
	private JComboBox cbTipo;
	private JSpinner spcantidad;
	private JComboBox cbCapacidadAlmacenamiento;
	private JComboBox cbtipodelram;
	private JComboBox cbVelocidadmicro;
	private JLabel lblTipoConexin;
	private JComboBox cbTipoConexionDisco;
	private JComboBox cbTipoRam;
	private JComboBox cbTipoSocket;
	private JComboBox cbCapacidadmbRam;
	private JComboBox cbTipoDisco;
	private JComboBox cbtiposocketmicro;
	private JTextField txtPrecioCompra;
	private boolean modificar;
	private JButton btnRegistrar;
	private JTextField numeroserie;



	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			RegistrarProducto dialog = new RegistrarProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public RegistrarProducto(Producto p, boolean modif) {
		setForeground(Color.BLUE);
		setResizable(false);
		modificar =modif;
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarProducto.class.getResource("/imagenes/pcparts.png")));
		if(modificar == true){
			cargar(p);
			setTitle("Modificar Producto");
		}else{
			setTitle("Registrar Producto");
		}
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
		setBounds(100, 100, 588, 408);
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
				panelGeneral.setBounds(10, 11, 542, 129);
				panelPrincipal.add(panelGeneral);
				panelGeneral.setLayout(null);
				{
					JLabel lblPrecioDeCompra = new JLabel("Precio de Compra:");
					lblPrecioDeCompra.setBounds(10, 19, 111, 21);
					panelGeneral.add(lblPrecioDeCompra);
				}
				{
					JLabel lblMarca = new JLabel("Marca:");
					lblMarca.setBounds(10, 54, 46, 21);
					panelGeneral.add(lblMarca);
				}
				{
					txtmarca = new JTextField();
					txtmarca.setBounds(120, 54, 182, 23);
					panelGeneral.add(txtmarca);
					txtmarca.setColumns(10);
				}
				{
					JLabel lblCantidad = new JLabel("Cantidad:");
					lblCantidad.setBounds(329, 19, 62, 21);
					panelGeneral.add(lblCantidad);
				}

				spcantidad = new JSpinner();
				spcantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				spcantidad.setBounds(401, 19, 103, 21);
				panelGeneral.add(spcantidad);

				JLabel lblModelo = new JLabel("Modelo:");
				lblModelo.setBounds(329, 51, 46, 21);
				panelGeneral.add(lblModelo);
				{
					txtmodelo = new JTextField();
					txtmodelo.setBounds(401, 51, 103, 21);
					panelGeneral.add(txtmodelo);
					txtmodelo.setColumns(10);
				}
				{
					JLabel lblNmeroDeSerie = new JLabel("N\u00FAmero de serie:");
					lblNmeroDeSerie.setBounds(10, 95, 111, 21);
					panelGeneral.add(lblNmeroDeSerie);
				}
				try {


					//MaskFormatter mask1 = new MaskFormatter("????????????????????");

					
					{
						txtPrecioCompra = new JTextField();	
						txtPrecioCompra.addKeyListener(new KeyAdapter() {
							@Override
							public void keyTyped(KeyEvent e) {
								//char c= e.getKeyChar();
								//if(c<'0' || c>'9'  && c!= KeyEvent.VK_PERIOD) e.consume();
								char c = e.getKeyChar();
								if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
								&& (c != '.')) {
								e.consume();
								}
								if (c == '.' && txtPrecioCompra.getText().contains(".")) {
								e.consume();
								}
								
							}
						});
						txtPrecioCompra.setBounds(120, 19, 182, 20);
						panelGeneral.add(txtPrecioCompra);
						txtPrecioCompra.setColumns(10);
					}
					
					numeroserie = new JTextField();
					numeroserie.addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent e) {
							char c = e.getKeyChar();
							if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
							&& ((c < 'A')||(c > 'Z'))) {
							e.consume();
							}
							/*if (c == '.' && txtPrecioCompra.getText().contains(".")) {
							e.consume();
							}*/
							
						}
					});
					numeroserie.setBounds(120, 93, 182, 23);
					panelGeneral.add(numeroserie);
					numeroserie.setColumns(10);

				} catch (Exception e) {
					// TODO: handle exception
				}


			}
			{
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(10, 151, 542, 170);
				panelPrincipal.add(panel);
				panel.setLayout(null);
				{
					JLabel lblTipo = new JLabel("Tipo de Producto:");
					lblTipo.setBounds(20, 27, 105, 21);
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
					cbTipo.setBounds(124, 27, 148, 21);
					panel.add(cbTipo);
				}
				{
					panelDiscoDuro = new JPanel();
					panelDiscoDuro.setBounds(10, 49, 522, 104);
					panel.add(panelDiscoDuro);
					panelDiscoDuro.setLayout(null);
					{
						JLabel lblCapacidadDeAlmacenamiento = new JLabel("Capacidad de almacenamiento (GB):");
						lblCapacidadDeAlmacenamiento.setBounds(10, 25, 222, 21);
						panelDiscoDuro.add(lblCapacidadDeAlmacenamiento);
					}
					{
						cbCapacidadAlmacenamiento = new JComboBox();
						cbCapacidadAlmacenamiento.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "160 GB", "250 GB", "320 GB", "500 GB", "1 TB", "2 TB"}));
						cbCapacidadAlmacenamiento.setBounds(242, 25, 143, 21);
						panelDiscoDuro.add(cbCapacidadAlmacenamiento);
					}
					{
						lblTipoConexin = new JLabel("Tipo Conexi\u00F3n:");
						lblTipoConexin.setBounds(10, 64, 159, 21);
						panelDiscoDuro.add(lblTipoConexin);
					}
					{
						cbTipoConexionDisco = new JComboBox();
						cbTipoConexionDisco.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "SSD", "SATA", "SAS", "SCSI"}));
						cbTipoConexionDisco.setBounds(242, 64, 143, 21);
						panelDiscoDuro.add(cbTipoConexionDisco);
					}
					{
						JLabel label = new JLabel("");
						label.setBounds(437, 20, 64, 64);
						panelDiscoDuro.add(label);
						label.setIcon(new ImageIcon(RegistrarProducto.class.getResource("/imagenes/if_drive-harddisk_118849.png")));
					}
				}
				{
					panelRam = new JPanel();
					panelRam.setBounds(10, 49, 522, 104);
					panel.add(panelRam);
					panelRam.setLayout(null);
					{
						JLabel lblTipo_1 = new JLabel("Tipo");
						lblTipo_1.setBounds(10, 25, 46, 21);
						panelRam.add(lblTipo_1);
					}
					{
						cbtipodelram = new JComboBox();
						cbtipodelram.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "DRAM", "SRAM"}));
						cbtipodelram.setBounds(115, 25, 157, 21);
						panelRam.add(cbtipodelram);
					}
					{
						JLabel lblCapacidadmb = new JLabel("Capacidad:");
						lblCapacidadmb.setBounds(10, 64, 95, 21);
						panelRam.add(lblCapacidadmb);
					}
					{
						cbCapacidadmbRam = new JComboBox();
						cbCapacidadmbRam.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "4 GB", "6 GB", "8 GB", "12 GB "}));
						cbCapacidadmbRam.setBounds(115, 64, 157, 21);
						panelRam.add(cbCapacidadmbRam);
					}
					{
						JLabel label = new JLabel("");
						label.setIcon(new ImageIcon(RegistrarProducto.class.getResource("/imagenes/if_hardware_random_access_memory_ram_computer_card_998239.png")));
						label.setBounds(427, 20, 64, 64);
						panelRam.add(label);
					}
				}
				{
					panelTarjetaMadre = new JPanel();
					panelTarjetaMadre.setBounds(10, 49, 522, 104);
					panel.add(panelTarjetaMadre);
					panelTarjetaMadre.setLayout(null);
					{
						JLabel lblTipoRam = new JLabel("Tipo RAM:");
						lblTipoRam.setBounds(10, 42, 95, 21);
						panelTarjetaMadre.add(lblTipoRam);
					}
					{
						cbTipoRam = new JComboBox();
						cbTipoRam.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "DDR", "DDR-2", "DDR-3", "DDR-4"}));
						cbTipoRam.setBounds(115, 41, 148, 21);
						panelTarjetaMadre.add(cbTipoRam);
					}
					{
						JLabel lblTipoSocket = new JLabel("Tipo Socket:");
						lblTipoSocket.setBounds(10, 73, 95, 21);
						panelTarjetaMadre.add(lblTipoSocket);
					}
					{
						cbTipoSocket = new JComboBox();
						cbTipoSocket.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Socket 7", "Socket370", "Socket 423", "Socket 478", "Socket 771"}));
						cbTipoSocket.setBounds(115, 72, 148, 21);
						panelTarjetaMadre.add(cbTipoSocket);
					}
					{
						JLabel lblTipoDiscoDuro = new JLabel("Tipo Disco Duro:");
						lblTipoDiscoDuro.setBounds(10, 11, 108, 21);
						panelTarjetaMadre.add(lblTipoDiscoDuro);
					}
					{
						cbTipoDisco = new JComboBox();
						cbTipoDisco.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "IDE", "SATA", "SATA-2", "SATA-3"}));
						cbTipoDisco.setBounds(115, 10, 148, 21);
						panelTarjetaMadre.add(cbTipoDisco);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("");
						lblNewLabel_1.setIcon(new ImageIcon(RegistrarProducto.class.getResource("/imagenes/if_1_graphic_card_electronic_device_motherboard_smps_hardware_chip_998234.png")));
						lblNewLabel_1.setBounds(437, 20, 64, 64);
						panelTarjetaMadre.add(lblNewLabel_1);
					}
				}
				{
					panelMicroprocesador = new JPanel();
					panelMicroprocesador.setBounds(10, 49, 522, 104);
					panel.add(panelMicroprocesador);
					panelMicroprocesador.setLayout(null);
					{
						JLabel label = new JLabel("Tipo Socket:");
						label.setBounds(10, 25, 95, 21);
						panelMicroprocesador.add(label);
					}
					{
						cbtiposocketmicro = new JComboBox();
						cbtiposocketmicro.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Socket 7", "Socket370", "Socket 423", "Socket 478", "Socket 771"}));
						cbtiposocketmicro.setBounds(115, 25, 148, 21);
						panelMicroprocesador.add(cbtiposocketmicro);
					}
					{
						JLabel lblVelocidadmhz = new JLabel("Velocidad (MHz):");
						lblVelocidadmhz.setBounds(10, 62, 97, 21);
						panelMicroprocesador.add(lblVelocidadmhz);
					}
					{
						cbVelocidadmicro = new JComboBox();
						cbVelocidadmicro.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "8", "33", "100", "800", "1300", "2600", "3000"}));
						cbVelocidadmicro.setBounds(115, 62, 148, 21);
						panelMicroprocesador.add(cbVelocidadmicro);
					}
					{
						JLabel lblNewLabel = new JLabel("");
						lblNewLabel.setIcon(new ImageIcon(RegistrarProducto.class.getResource("/imagenes/if_chip_circuit_ic_microchip_microprocessor_semiconductor_integratedcircuit_1_998236.png")));
						lblNewLabel.setBounds(437, 16, 64, 64);
						panelMicroprocesador.add(lblNewLabel);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				{
					JButton btnCancelar = new JButton("Cancelar");
					btnCancelar.setIcon(new ImageIcon(RegistrarProducto.class.getResource("/imagenes/cancel.png")));
					btnCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					btnRegistrar = new JButton("Registrar");
					if(modificar == true){
						btnRegistrar = new JButton("Modificar");
					}					
					btnRegistrar.setSelectedIcon(null);
					btnRegistrar.setIcon(new ImageIcon(RegistrarProducto.class.getResource("/imagenes/savee.png")));
					btnRegistrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							boolean poder = true;
							boolean cedul = true;

							double prec = Double.parseDouble(txtPrecioCompra.getText());
							String numserie = numeroserie.getText();
							String marc = txtmarca.getText();
							String model = txtmodelo.getText();
							int cant = (int) spcantidad.getValue();
							if(numeroserie.getText().equalsIgnoreCase("") || txtmodelo.getText().equalsIgnoreCase("") || txtmarca.getText().equalsIgnoreCase("") || spcantidad.getValue().equals("")){
								JOptionPane.showMessageDialog(null, " Revise si hay campos vac�os y vuelve a intentarlo", "ERROR", JOptionPane.WARNING_MESSAGE);
								poder = false;
							}
							for (int i = 0; i < Tienda.getInstance().getMisProductos().size(); i++) {
								if(numeroserie.getText().equalsIgnoreCase(Tienda.getInstance().getMisProductos().get(i).getNumeroSerie())){
									JOptionPane.showMessageDialog(null, " Ya existe este n�mero de serie", "ERROR", JOptionPane.WARNING_MESSAGE);
									cedul = false;
									numeroserie.setText("");
								}
							}

							if(cbTipo.getSelectedIndex()==0){
								//Registrar Tarjeta Madre
								if(cbTipoRam.getSelectedIndex()==0 || cbTipoDisco.getSelectedIndex()==0 || cbTipoSocket.getSelectedIndex()==0){
									JOptionPane.showMessageDialog(null,  " Hay campos que no est�n seleccionados", "WARNING", JOptionPane.WARNING_MESSAGE);

								}if(modificar==false && poder ==true && cedul == true){

									String tipsock = cbTipoSocket.getSelectedItem().toString();
									String tipram = cbTipoRam.getSelectedItem().toString();
									String tipdisc = cbTipoDisco.getSelectedItem().toString();
									TarjetaMadre t = new TarjetaMadre(prec, cant, numserie, marc, model, tipsock, tipram, tipdisc);
									Tienda.getInstance().InsertarProducto(t);
									Clean();
								}

								}

							 if(cbTipo.getSelectedIndex()==1){
								//Registrar Microprocesador
								if(poder==false &&(cbtiposocketmicro.getSelectedIndex()==0 || cbVelocidadmicro.getSelectedIndex()==0)){
									JOptionPane.showMessageDialog(null,  "Hay campos que no est�n seleccionados", "WARNING", JOptionPane.WARNING_MESSAGE);
								}
								if(modificar==false && poder ==true && cedul == true){
									String velomic = cbVelocidadmicro.getSelectedItem().toString();
									String tiposockmi = cbtiposocketmicro.getSelectedItem().toString();
									Microprocesador m = new Microprocesador(prec, cant, numserie, marc, model, velomic,tiposockmi );
									Tienda.getInstance().InsertarProducto(m);
									Clean();
									 
								}

							}else if(cbTipo.getSelectedIndex()==2){
								//Registrar MemoriaRam
								if(cbtipodelram.getSelectedIndex()== 0 || cbCapacidadmbRam.getSelectedIndex()==0){
									JOptionPane.showMessageDialog(null,  "hay campos que no est�n seleccionados", "WARNING", JOptionPane.WARNING_MESSAGE);
								}
								if(modificar==false  && poder ==true && cedul == true){

									String capram = cbCapacidadmbRam.getSelectedItem().toString();
									String tipram = cbtipodelram.getSelectedItem().toString();
									MemoriaRam r = new MemoriaRam(prec, cant, numserie, marc, model, capram, tipram);
									Tienda.getInstance().InsertarProducto(r);
									Clean();
								}


							}else if(cbTipo.getSelectedIndex()==3){
								//Registrar Disco Duro
								if(cbCapacidadAlmacenamiento.getSelectedIndex()==0 || cbTipoConexionDisco.getSelectedIndex()==0){
									JOptionPane.showMessageDialog(null,  "Hay campos que no est�n seleccionados", "WARNING", JOptionPane.WARNING_MESSAGE);
								}
								if(modificar==false  && poder ==true && cedul == true){

									String capdis = cbCapacidadAlmacenamiento.getSelectedItem().toString();
									String conecdis = cbTipoConexionDisco.getSelectedItem().toString();
									DiscoDuro d = new DiscoDuro(prec, cant, numserie, marc, model, capdis, conecdis);
									Tienda.getInstance().InsertarProducto(d);
									Clean();
								}

							}
							if(poder== true && cedul == true){
								
								Clean();
								System.out.println(Tienda.getInstance().getMisProductos().get(0).getMarca());
								JOptionPane.showMessageDialog(null, "Se ha registrado satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE);
							}

						}


					});
					btnRegistrar.setSize(77, 20);
					btnRegistrar.setActionCommand("OK");
					buttonPane.add(btnRegistrar);
					getRootPane().setDefaultButton(btnRegistrar);
					btnCancelar.setSize(75, 20);
					btnCancelar.setActionCommand("Cancel");
					buttonPane.add(btnCancelar);
				}
			}
		}
	}
	private void cargar(Producto p) {
		// TODO Auto-generated method stub
          //if(modificar instanceof TarjetaMadre){
			
		//}
		
	}
	private void Clean() {
		txtmarca.setText("");
		txtmodelo.setText("");
		txtPrecioCompra.setText("");
		numeroserie.setText("");
		spcantidad.setValue(1);
		cbCapacidadAlmacenamiento.setSelectedIndex(0);
		cbCapacidadmbRam.setSelectedIndex(0);
		cbTipo.setSelectedIndex(0);
		cbTipoConexionDisco.setSelectedIndex(0);
		cbtipodelram.setSelectedIndex(0);
		cbTipoDisco.setSelectedIndex(0);
		cbTipoRam.setSelectedIndex(0);
		cbTipoSocket.setSelectedIndex(0);
		cbtiposocketmicro.setSelectedIndex(0);
		cbVelocidadmicro.setSelectedIndex(0);

	}
}
