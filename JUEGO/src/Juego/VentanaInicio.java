package Juego;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class VentanaInicio {
	// contructor
		static JFrame ventana;
		// presentacion
		 JPanel presentacion;
		 JButton iniciar;
		 JLabel fondoPresentacion;
		 ImageIcon imagenpres;
		static String pathP;
		static String pathM;
		// pat de las imagenes 
		public VentanaInicio() {
			ventana = new JFrame("Pacman");
			ventana.setSize(700, 700);
			ventana.setLayout(null);
			// la ventana de nuestro juego en todo el centro
			ventana.setLocationRelativeTo(null);
			// senos cierre cuando le demos a la x roja y no se nos quede estatica
			// la ventana
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// vamos a desactivar un boton de aumenta tamanio
			ventana.setResizable(false);

			presentacion = new JPanel();
			presentacion.setLayout(null);
			// tamanio de la ventana
			presentacion.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
			presentacion.setVisible(true);
			// color del panel
			presentacion.setBackground(Color.red);
			iniciar = new JButton("MENU");
			// pocision luego tamanio(derecha , iz , ancho ,largo)
			iniciar.setBounds(ventana.getWidth()-500, 380, 150, 30);
			iniciar.setVisible(true);
			iniciar.setBackground(Color.GRAY);
			presentacion.add(iniciar, 0);

			fondoPresentacion = new JLabel();
			fondoPresentacion.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
			imagenpres = new ImageIcon("image/grupo.jpg");
			imagenpres = new ImageIcon(
					imagenpres.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
			fondoPresentacion.setIcon(imagenpres);
			fondoPresentacion.setVisible(true);
			presentacion.add(fondoPresentacion, 0);
			
			
			iniciar.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					System.out.println("Iniciar");
					VentanaMenu c=new VentanaMenu();
					pathP="image/";
					
				}
			});
			
		
			ventana.add(presentacion,-1);
			
			
			ventana.setVisible(true);
		}
}
