package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.sun.javafx.fxml.ParseTraceElement;

import logica.Tienda;

public class Graficos extends JFrame  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Tienda tienda;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graficos frame = new Graficos();
					frame.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Graficos(Tienda t) {
		tienda = t;
		pastel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	private void pastel(){
		 // Fuente de Datos
        DefaultPieDataset data = new DefaultPieDataset();
        float disco = tienda.cantDiscoDuro()/tienda.totaltodo();
        float memo = tienda.cantMemoriaRam()/tienda.totaltodo();
        float micro = tienda.cantMicroProcesador()/tienda.totaltodo();
        float madre = tienda.cantTarjetaMadre()/tienda.totaltodo();
        if(disco>0){
        	data.setValue("Disco Duro", disco);
        }
        if(memo>0){
        	data.setValue("MemoriaRam", memo);
        }
        if(micro>0){
        	data.setValue("Microprocesador", micro);
        }
        if(madre>0){
        	data.setValue("TarjetaMadre", madre);
        }
        /*data.setValue("MemoriaRam", 60.0);
        data.setValue("TarjetaMadre", 20.0);
        data.setValue("Microprocesador", 0.0);
        data.setValue("Disco Duro", 0.0);*/
 
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
         "Productos más vendido", 
         data, 
         true, 
         true, 
         false);
 
        // Mostrar Grafico
        ChartFrame frame = new ChartFrame("Productos más vendido", chart);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
	}

}
