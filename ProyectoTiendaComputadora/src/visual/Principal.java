package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Tienda;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Principal extends JFrame {

	private JPanel contentPane;
	private static Tienda tienda;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tienda tz = new Tienda();
					Principal frame = new Principal(tz);
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
	public Principal(Tienda t1) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imagenes/pc.png")));
		tienda=t1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 387);
		dim = super.getToolkit().getScreenSize();
		super.setSize((dim.width ), (dim.height - 40));
		this.setResizable(false);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCliente = new JMenu("Cliente");
		mnCliente.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/cliente.png")));
		menuBar.add(mnCliente);
		
		JMenuItem mntmRegistrarCliente = new JMenuItem("Registrar Cliente");
		mntmRegistrarCliente.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/registrarCliente.png")));
		mntmRegistrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCliente r1 = new RegistrarCliente(tienda, null, false);
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
				ListarCliente l1 = new ListarCliente(tienda);
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
				RegistrarProducto p = new RegistrarProducto(tienda);
				p.setLocationRelativeTo(null);
				p.setModal(true);
				p.setVisible(true);
			}
		});
		mnProducto.add(mntmRegistrarProducto);
		
		JMenuItem mntmListaDeProducto = new JMenuItem("Lista de producto");
		mntmListaDeProducto.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/listar.png")));
		mntmListaDeProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarProducto p = new ListarProducto(tienda);
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
				RealizarVenta r = new RealizarVenta(tienda);
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
				ListarVenta v = new ListarVenta(tienda);
				v.setLocationRelativeTo(null);
				v.setModal(true);
				v.setVisible(true);
			}
		});
		mnVenta.add(mntmListaDeV);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
	}
}
