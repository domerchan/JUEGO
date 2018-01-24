package Juego;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class secundaria {
	JFrame pantalla;
	ImageIcon opciones;
	JPanel panelP;
	JLabel fondo;
	int[][] jugadores = { { 101, 101 }, { 101, 101 } };
	// lemos orizontal
	JButton[][] jugada;

	public secundaria() {
		pantalla = new JFrame();
		pantalla.setLocale(null);
		pantalla.setSize(700, 700);

		panelP = new JPanel();
		panelP.setSize(pantalla.getWidth(), pantalla.getHeight());
		panelP.setLayout(null);
		opciones = new ImageIcon("proyectoPacman/fondoOpcion.jpg");
		opciones = new ImageIcon(
				opciones.getImage().getScaledInstance(pantalla.getWidth(), pantalla.getHeight(), Image.SCALE_DEFAULT));

		fondo = new JLabel();
		fondo.setBounds(0, 0, pantalla.getWidth(), pantalla.getHeight());
		fondo.setIcon(opciones);
		fondo.setVisible(true);
		panelP.add(fondo);

		jugada = new JButton[2][2];

		for (int i = 0; i < jugada.length; i++) {
			for (int j = 0; j < jugada.length; j++) {
				jugada[i][j] = new JButton();
			}
		}

		for (int i = 0; i < jugada.length; i++) {
			for (int j = 0; j < jugada.length; j++) {
				jugada[i][j].setIcon(new ImageIcon("proyectoPacman/" + jugadores[i][j] + ".png"));
				jugada[i][j].setBounds(120 + (i * 50), 120 + (j * 50), 50, 50);
				jugada[i][j].setVisible(true);
				panelP.add(jugada[i][j], 0);
			}
		}

		pantalla.setVisible(true);
		pantalla.add(panelP);

	}

	public void eventoMenu() {
		jugada[0][0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// jugadas de mario
			}
		});
	}
}
