package Juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.transform.ErrorListener;

public class VentanaMenu {
	static JFrame ventana;
	JPanel panelMenu;
	JButton botones[];
	JLabel fondoMenu;
	ImageIcon imagenFondoMenu;
	Timer timer1;
	//
	static int bandera;

	public VentanaMenu() {
		ventana = new JFrame("Pacman");
		ventana.setSize(700, 700);
		ventana.setLayout(null);
		ventana.setVisible(true);
		// la ventana de nuestro juego en todo el centro
		ventana.setLocationRelativeTo(null);
		// senos cierre cuando le demos a la x roja y no se nos quede estatica
		// la ventana
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// vamos a desactivar un boton de aumenta tamanio
		ventana.setResizable(false);

		VentanaInicio.ventana.setVisible(false);

		panelMenu = new JPanel();
		panelMenu.setLayout(null);
		// tamanio de la ventana
		panelMenu.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
		panelMenu.setVisible(true);

		fondoMenu = new JLabel();
		fondoMenu.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
		imagenFondoMenu = new ImageIcon("proyectoPacman/Menu.jpg");
		imagenFondoMenu = new ImageIcon(imagenFondoMenu.getImage().getScaledInstance(ventana.getWidth(),
				ventana.getHeight(), Image.SCALE_DEFAULT));
		fondoMenu.setIcon(imagenFondoMenu);
		fondoMenu.setVisible(true);
		panelMenu.add(fondoMenu, 0);

		botones = new JButton[4];
		for (int i = 0; i < botones.length; i++) {
			botones[i] = new JButton();

		}

		botones[0].setText("JUGAR");
		botones[1].setText("TABLERO");
		botones[2].setText("RECORDS");
		botones[3].setText("SALIR");

		for (int i = 0; i < botones.length; i++) {
			// i* lo demas para que se vena los demas botones
			botones[i].setBounds(ventana.getWidth() - (200 + 30), (i + 9) * 50, 200, 40);
			botones[i].setVisible(true);
			botones[i].setBackground(Color.white);

			// aniadimo s anuestro panel los botones
			panelMenu.add(botones[i], 0);
		}

		ventana.add(panelMenu);
		eventoMenu();
	}

	public void eventoMenu() {

		botones[0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Juego.jugador = JOptionPane.showInputDialog(ventana, "Nombre del jugador", "Escribir aqui");
				if (Juego.jugador != null && Juego.jugador.compareTo("Escribir aqui") != 0
						&& Juego.jugador.compareTo("") != 0) {
					cargar c = new cargar();
				} else {
					JOptionPane.showMessageDialog(null, "ACCESO DENEGADO", null, JOptionPane.ERROR_MESSAGE);
				}
				timer1 = new Timer(5000, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (bandera == 8) {
							cargar.carga.setVisible(false);
							Juego c = new Juego();
							timer1.stop();
						} else {
							System.out.println("NO");
						}
					}
				});
				timer1.start();

			}
		});

		botones[1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});

		botones[2].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});

		botones[3].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
	}

}
