package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import logica.Tienda;

public class GraficaBarra extends JDialog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;


	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			GraficaBarra dialog = new GraficaBarra();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public GraficaBarra() {
		
		setResizable(false);
		
		setBounds(100, 100, 700, 465);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		barra();
	}
	
	public void barra(){
		panel = new JPanel();
        getContentPane().add(panel);
        // Fuente de Datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(Tienda.getInstance().cantDiscoDuro(), "Disco Duro", "Disco Duro");
        dataset.setValue(Tienda.getInstance().cantMicroProcesador(), "Microprocesador", "Microprocesador");
        dataset.setValue(Tienda.getInstance().cantTarjetaMadre(), "Tarjeta Madre", "Tarjeta Madre");
        dataset.setValue(Tienda.getInstance().cantMemoriaRam(), "Memoria Ram", "Memoria Ram");
        //dataset.setValue(6, "ROW", "Colum");
        // dataset.setValue(value, rowKey, columnKey); row es leyenda column es eje X
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createBarChart3D
        ("Productos más vendidos","Productos", "Cantidad", 
        dataset, PlotOrientation.VERTICAL, true,true, false);
        chart.setBackgroundPaint(Color.cyan);
        chart.getTitle().setPaint(Color.black); 
        CategoryPlot p = chart.getCategoryPlot(); 
        p.setRangeGridlinePaint(Color.red); 
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
	}

}
