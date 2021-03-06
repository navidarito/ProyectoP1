package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import logica.Producto;
import logica.Tienda;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Serializable;

public class Principal extends JFrame  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Tienda tz = new Tienda();
					Principal frame = new Principal();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal()  {
		Tienda.getInstance();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Tienda.getInstance().guardarTienda(Tienda.getInstance());
				e.getWindow().dispose();
			
			}
		});
		Tienda.getInstance().cargarTienda(Tienda.getInstance());
	
		

		setForeground(Color.BLUE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imagenes/pc.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 387);
		dim = super.getToolkit().getScreenSize();
		super.setSize((dim.width ), (dim.height - 40));
		this.setResizable(false);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(224, 255, 255));
		menuBar.setFont(new Font("Calisto MT", Font.PLAIN, 12));
		menuBar.setForeground(Color.BLUE);
		setJMenuBar(menuBar);
		
		JMenu mnCliente = new JMenu("Cliente");
		mnCliente.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/cliente.png")));
		menuBar.add(mnCliente);
		
		JMenuItem mntmRegistrarCliente = new JMenuItem("Registrar Cliente");
		mntmRegistrarCliente.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/registrarCliente.png")));
		mntmRegistrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCliente r1 = new RegistrarCliente(null, false);
				r1.setLocationRelativeTo(null);
				r1.setModal(true);
				r1.setVisible(true);
			}
		});
		mnCliente.add(mntmRegistrarCliente);
		
		JMenuItem mntmListarCliente = new JMenuItem("Lista de Cliente");
		mntmListarCliente.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/listar.png")));
		mntmListarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCliente l1 = new ListarCliente();
				l1.setLocationRelativeTo(null);
				l1.setModal(true);
				l1.setVisible(true);
			}
		});
		mnCliente.add(mntmListarCliente);
		
		JMenu mnProducto = new JMenu("Producto");
		mnProducto.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/productos.png")));
		menuBar.add(mnProducto);
		
		JMenuItem mntmRegistrarProducto = new JMenuItem("Registrar Producto");
		mntmRegistrarProducto.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/productos.png")));
		mntmRegistrarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarProducto prod = new RegistrarProducto(null, false);
				prod.setLocationRelativeTo(null);
				prod.setModal(true);
				prod.setVisible(true);
			}
		});
		mnProducto.add(mntmRegistrarProducto);
		
		JMenuItem mntmListaDeProducto = new JMenuItem("Lista de producto");
		mntmListaDeProducto.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/listar.png")));
		mntmListaDeProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarProducto p = new ListarProducto();
				p.setLocationRelativeTo(null);
				p.setModal(true);
				p.setVisible(true);
			}
		});
		mnProducto.add(mntmListaDeProducto);
		
		JMenu mnVenta = new JMenu("Venta");
		mnVenta.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/vender.png")));
		menuBar.add(mnVenta);
		
		JMenuItem mntmRealizarVenta = new JMenuItem("Realizar venta");
		mntmRealizarVenta.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/compra.png")));
		mntmRealizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RealizarVenta r = new RealizarVenta();
				
				r.setLocationRelativeTo(null);
				r.setModal(true);
				r.setVisible(true);
				
			}
		});
		mnVenta.add(mntmRealizarVenta);
		
		JMenuItem mntmListaDeV = new JMenuItem("Lista de Venta");
		mntmListaDeV.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/listar.png")));
		mntmListaDeV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarVenta v = new ListarVenta();
				v.setLocationRelativeTo(null);
				v.setModal(true);
				v.setVisible(true);
			}
		});
		mnVenta.add(mntmListaDeV);
		
		JMenu mnGrficas = new JMenu("Gr\u00E1ficas");
		menuBar.add(mnGrficas);
		
		JMenu mnProductoMsVendido = new JMenu("Producto m\u00E1s vendido");
		mnGrficas.add(mnProductoMsVendido);
		
		JMenuItem mntmPastel = new JMenuItem("Pastel");
		mntmPastel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Tienda.getInstance().getMisFacturas().size()>0){
					Graficos g = new Graficos();
					g.setLocationRelativeTo(null);
					g.setModalExclusionType(null);
				}else{
					JOptionPane.showMessageDialog(null, "No se ha vendido ning�n Producto", "Informaci�n", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnProductoMsVendido.add(mntmPastel);
		
		JMenuItem mntmBarra = new JMenuItem("Barra");
		mntmBarra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Tienda.getInstance().getMisFacturas().size()>0){
					GraficaBarra b = new GraficaBarra();
					b.setLocationRelativeTo(null);
					b.setModalExclusionType(null);
					b.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "No se ha vendido ning�n Producto", "Informaci�n", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnProductoMsVendido.add(mntmBarra);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setBackground("background.png");
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/background.jpg")));
		panel.add(lblNewLabel, BorderLayout.CENTER);
	}
}
