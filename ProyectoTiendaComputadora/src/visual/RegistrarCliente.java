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
import logica.Tienda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegistrarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDireccion;
	private JFormattedTextField ftxtCedula;
	private JFormattedTextField ftxtTelefono;
	
	private static Tienda tienda;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			RegistrarCliente dialog = new RegistrarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCliente(Tienda t) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarCliente.class.getResource("/imagenes/cliente.png")));
		tienda=t;
		setTitle("Registrar Cliente");
		setBounds(100, 100, 450, 289);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 414, 195);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblCdula = new JLabel("C\u00E9dula:");
			lblCdula.setBounds(10, 27, 75, 21);
			panel.add(lblCdula);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 68, 75, 21);
			panel.add(lblNombre);
			
			JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
			lblDireccin.setBounds(10, 108, 75, 21);
			panel.add(lblDireccin);
			
			txtDireccion = new JTextField();
			txtDireccion.setBounds(95, 108, 244, 21);
			panel.add(txtDireccion);
			txtDireccion.setColumns(10);
			
			JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
			lblTelfono.setBounds(10, 147, 75, 21);
			panel.add(lblTelfono);
			
			
			try {
				MaskFormatter mask = new MaskFormatter("###-#########-#");
				ftxtCedula = new JFormattedTextField(mask);
				ftxtCedula.setBounds(95, 27, 244, 21);
				panel.add(ftxtCedula);
				
				MaskFormatter mask1 = new MaskFormatter("????????????????????");
				
				MaskFormatter mask2 = new MaskFormatter("(###)-###-####");
				ftxtTelefono = new JFormattedTextField(mask2);
				ftxtTelefono.setBounds(95, 147, 244, 21);
				panel.add(ftxtTelefono);
				
				txtNombre = new JTextField();
				txtNombre.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c = e.getKeyChar();
						if((c<'a' || c>'z')&& (c<'A' || c>'Z')&& c!= KeyEvent.VK_SPACE) e.consume();
					}
				});
				txtNombre.setBounds(95, 68, 244, 21);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
				
				JLabel lblL = new JLabel("");
				lblL.setIcon(new ImageIcon(RegistrarCliente.class.getResource("/imagenes/id.png")));
				lblL.setBounds(345, 30, 46, 14);
				panel.add(lblL);
				
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(RegistrarCliente.class.getResource("/imagenes/escribir.png")));
				label.setBounds(345, 71, 46, 14);
				panel.add(label);
				
				JLabel label_1 = new JLabel("");
				label_1.setIcon(new ImageIcon(RegistrarCliente.class.getResource("/imagenes/locationn.png")));
				label_1.setBounds(345, 111, 46, 14);
				panel.add(label_1);
				
				JLabel label_2 = new JLabel("");
				label_2.setIcon(new ImageIcon(RegistrarCliente.class.getResource("/imagenes/telefono.png")));
				label_2.setBounds(345, 150, 46, 14);
				panel.add(label_2);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setIcon(new ImageIcon(RegistrarCliente.class.getResource("/imagenes/savee.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nom = txtNombre.getText();
						String cedu = ftxtCedula.getText();
						String dire = txtDireccion.getText();
						String tele = ftxtTelefono.getText();
						boolean poder = true;
						
						if(txtNombre.getText().equalsIgnoreCase("") || ftxtCedula.getText().equalsIgnoreCase("   -       - ")  || txtDireccion.getText().equalsIgnoreCase("") || ftxtTelefono.getText().equalsIgnoreCase("(   )-   -    ") ){
							JOptionPane.showMessageDialog(null, "No dejes campos vacios", "Información", JOptionPane.WARNING_MESSAGE);
							poder = false;
							clean();
						}
						
						for (int i = 0; i < tienda.getMisClientes().size(); i++) {
							if(tienda.getMisClientes().get(i).getCedula().equalsIgnoreCase(cedu)){
								JOptionPane.showMessageDialog(null, "No se pudo registrar,\nya existe esta cédula.", "Información", JOptionPane.WARNING_MESSAGE);
								poder = false;
								clean();
							}
							
						}
						if(poder){
							Cliente c1 = new Cliente(nom, cedu, dire, tele);
							tienda.InsertarCliente(c1);
							JOptionPane.showMessageDialog(null, "Operación Exitosa.", "Información", JOptionPane.INFORMATION_MESSAGE);
						
							clean();
						
						}
						/*System.out.println(tienda.getMisClientes().get(0).getNombre());
						System.out.println(tienda.getMisClientes().get(0).getDireccion());
						System.out.println(tienda.getMisClientes().get(0).getCedula());
						System.out.println(tienda.getMisClientes().get(0).getTelefono());*/
					}

					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setIcon(new ImageIcon(RegistrarCliente.class.getResource("/imagenes/cancel.png")));
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
	
	private void clean() {
		ftxtTelefono.setText("");
		txtDireccion.setText("");
		txtNombre.setText("");
		ftxtCedula.setText("");
		
		
	}
}
