package Juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Juego {

	static JFrame ventana;
	// juego
	static JPanel panelJuego;
	JLabel fondoJuego;
	ImageIcon imagenJuego;
	static int mat[][];
	static JLabel matriz[][];

	int px;
	int py;

	static int matAux[][];

	// banderas
	int abajo;
	int arriba;
	int izquierda;
	int derecha;
	// cosas nesesarias para el juego
	static String jugador;
	JLabel nombre;
	int puntos;
	JLabel record;

	// metodo mover atributos
	Timer timer;
	Timer cargarT;
	// lamar a faltasmas
	Fantasmas fantasma1;
	Fantasmas fantasma2;
	Fantasmas fantasma3;
	Fantasmas fantasma4;
	// variable para siguiente nivel
	static int banderan1;

	// vidas
	JLabel[] vidas;
	ImageIcon vid;
	int numeroVidas = 3;
	// contador tercer nivel
	static int tercer = 0;
	// timer paara palanat
	static Timer palanca;
	Timer palanca1;
	int numero;

	public Juego() {
		VentanaMenu.ventana.setVisible(false);
		ventana = new JFrame();
		ventana.setSize(700, 700);
		ventana.setLayout(null);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// juego
		// cramos label para insertar las imagenes

		mat = new int[15][15];
		// mi matriz de numero va a ser igual a atablero
		mat = tablero(1);
		// imprimir
		// matAux=tablero(1);
		matriz = new JLabel[15][15];
		matAux = new int[15][15];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				matriz[i][j] = new JLabel();

				matAux[i][j] = mat[i][j];
			}
		}

		px = 1;
		py = 1;
		// muestra el pacman
		mat[px][py] = 3;
		// columnas filas

		abajo = 0;
		arriba = 0;
		izquierda = 0;
		derecha = 0;

		////////////////////////////////////////////////////////////////////////////////////
		// vamos a ocultar nuestra primera ventana

		panelJuego = new JPanel();
		panelJuego.setLayout(null);
		// tamanio de la ventana
		panelJuego.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
		panelJuego.setVisible(true);

		fondoJuego = new JLabel();
		fondoJuego.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
		imagenJuego = new ImageIcon("proyectoPacman/untitled.png");
		imagenJuego = new ImageIcon(
				imagenJuego.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
		fondoJuego.setIcon(imagenJuego);
		fondoJuego.setVisible(true);
		panelJuego.add(fondoJuego, 0);

		// vamos a llenar el vector con las fotos
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				// agregar las imagenes a la matriz
				matriz[i][j].setIcon(new ImageIcon("proyectoPacman/" + mat[i][j] + ".png"));
				// 200pixeles marjen
				matriz[i][j].setBounds(120 + (i * 30), 120 + (j * 30), 30, 30);
				matriz[i][j].setVisible(true);
				// es para superponer paneles
				panelJuego.add(matriz[i][j], 0);
			}
		}
		////////////////////////////////////////////////////////////////////////////////
		// creamos las vidas del pacmana
		vidas = new JLabel[3];

		for (int i = 0; i < vidas.length; i++) {
			vidas[i] = new JLabel();
		}
		for (int i1 = 0; i1 < vidas.length; i1++) {
			vidas[i1].setIcon(new ImageIcon("proyectoPacman/vidas.png"));
			vidas[i1].setBounds(120 + (i1 * 30), 600, 30, 30);
			vidas[i1].setVisible(true);
			panelJuego.add(vidas[i1], 0);
		}

		/////////////////////////////////////////////////////////////////////////////////////
		nombre = new JLabel("Jugador: " + jugador);
		nombre.setBounds(20, 20, 120, 30);
		nombre.setVisible(true);
		nombre.setForeground(Color.WHITE);
		panelJuego.add(nombre, 0);

		puntos = 0;

		record = new JLabel("Puntos: " + puntos);
		record.setBounds(ventana.getWidth() - (250 + 20), 20, 200, 30);
		record.setVisible(true);
		record.setForeground(Color.WHITE);
		panelJuego.add(record, 0);

		mover();
		// iniciamos la coneccion

		fantasma1 = new Fantasmas(12, 13);
		fantasma2 = new Fantasmas(13, 13);
		// fantasma3 = new Fantasmas(13, 12);

		ventana.add(panelJuego);
	}

	public int[][] tablero(int option) {

		int[][] aux1 = new int[15][15];

		if (option == 1) {
			int aux[][] = {

					{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 2 }, { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2 },
					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 },
					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },

					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 },
					{ 2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, { 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },

			};
			return aux;
		}
		if (option == 2) {
			int aux[][] = {

					{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 2 }, { 2, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 1, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 2, 2, 2 }, { 2, 1, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 },
					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, { 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },

					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 },
					{ 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 2, 2, 2 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2 }, { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },

			};
			return aux;
		}

		if (option == 3) {
			int aux[][] = {

					{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 2 }, { 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, { 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 },
					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, { 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },

					{ 2, 1, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, { 2, 1, 2, 1, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 },
					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, { 2, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2 }, { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
					{ 2, 6, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },

			};
			return aux;
		}

		return aux1;
	}

	public void mover() {

		timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (arriba == 1 && (mat[px][py - 1] == 1 || mat[px][py - 1] == 0)) {

					if (mat[px][py - 1] == 1) {
						puntos = puntos + 5;
						record.setText("Puntos: " + puntos);
					}

					// me muevo una pocicion
					mat[px][py] = 0;
					matAux[px][py] = mat[px][py];// esto es nuevo para todo
													// actualizacion
					py = py - 1;
					// coloco el munieco
					mat[px][py] = 3;
					pintarMatriz();
				}
				if (abajo == 1 && (mat[px][py + 1] == 1 || mat[px][py + 1] == 0)) {
					// puntos
					if (mat[px][py + 1] == 1) {
						puntos = puntos + 5;
						record.setText("Puntos: " + puntos);
					}

					// me muevo una pocicion
					mat[px][py] = 0;
					matAux[px][py] = mat[px][py];
					py = py + 1;
					// coloco el munieco
					mat[px][py] = 3;
					pintarMatriz();
				}

				if (izquierda == 1 && (mat[px - 1][py] == 1 || mat[px - 1][py] == 0 || mat[px - 1][py] == 5)) {

					// vamos aponer al pacman en una nueva pocicion
					if (mat[px - 1][py] == 5) {
						mat[px][py] = 0;
						matAux[px][py] = mat[px][py];
						px = 13;
						py = 1;
						mat[px][py] = 3;
						// cinco actulizaciones
						pintarMatriz();
					}

					if (mat[px - 1][py] == 1) {
						puntos = puntos + 5;
						record.setText("Puntos: " + puntos);
					}

					// me muevo una pocicion
					mat[px][py] = 0;
					matAux[px][py] = mat[px][py];
					px = px - 1;
					// coloco el munieco
					mat[px][py] = 3;
					pintarMatriz();
				}
				if (derecha == 1 && (mat[px + 1][py] == 1 || mat[px + 1][py] == 0 || mat[px + 1][py] == 6)) {

					if (mat[px + 1][py] == 6) {
						mat[px][py] = 0;
						matAux[px][py] = mat[px][py];
						px = 1;
						py = 13;
						mat[px][py] = 3;
						// sexta actulizaciones
						pintarMatriz();
					}
					if (mat[px + 1][py] == 1) {
						puntos = puntos + 5;
						record.setText("Puntos: " + puntos);
					}

					// me muevo una pocicion
					mat[px][py] = 0;
					matAux[px][py] = mat[px][py];
					px = px + 1;
					// coloco el munieco
					mat[px][py] = 3;
					pintarMatriz();
				}

				int encontrado = 0;
				for (int i = 0; i < mat.length && encontrado == 0; i++) {
					for (int j = 0; j < mat.length && encontrado == 0; j++) {
						if (mat[i][j] == 1) {
							encontrado = 1;
						}
					}
				}

				/////////////////////////////////////////// USB
				/////////////////////////////////////////// SENIAL/////////////////////////////////////////////////////////////
				if (VentanaPrincipal.y == 1) {
					if (mat[px][py - 1] == 1 || mat[px][py - 1] == 0) {
						arriba = 1;
						abajo = 0;
						izquierda = 0;
						derecha = 0;
					}
				}

				if (VentanaPrincipal.y == 3) {
					if (mat[px][py + 1] == 1 || mat[px][py + 1] == 0) {
						arriba = 0;
						abajo = 1;
						izquierda = 0;
						derecha = 0;
					}
				}

				if (VentanaPrincipal.y == 2) {
					if (mat[px - 1][py] == 1 || mat[px - 1][py] == 0) {
						arriba = 0;
						abajo = 0;
						izquierda = 1;
						derecha = 0;
					}
				}

				if (VentanaPrincipal.y == 4) {
					if (mat[px + 1][py] == 1 || mat[px + 1][py] == 0) {
						arriba = 0;
						abajo = 0;
						izquierda = 0;
						derecha = 1;
					}
				}
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				if (encontrado == 0) {
					// JOptionPane.showMessageDialog(ventana, "Felicitaciones: "
					// + jugador + " as
					// ganado");
					timer.stop();
					ventana.setVisible(false);
					JOptionPane.showMessageDialog(null,
							"Felicitaciones: " + jugador + " as ganado" + "\nSIGUIENTE NIVEL ", null,
							JOptionPane.INFORMATION_MESSAGE);
					// paramos el juego
					timer.stop();
					// siguiente nivel
					// Juego j=new Juego();
					cargar c = new cargar();
					cargarT = new Timer(5000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println(banderan1);
							if (banderan1 == 2) {
								Juego c = new Juego();
								mat = tablero(2);
								// fantasma4 = new Fantasmas(13, 12);
								banderan1++;
								cargar.carga.setVisible(false);
								cargarT.stop();
							}

							if (tercer == 3) {
								JOptionPane.showMessageDialog(null,
										"Felicitaciones: " + jugador + " as ganado"
												+ "\nAhora este sera tu reto final ",
										null, JOptionPane.INFORMATION_MESSAGE);

								Juego niv3 = new Juego();
								mat = tablero(3);

								cargar.carga.setVisible(false);
								cargarT.stop();
							}

						}
					});
					cargarT.start();

					// tiempo de ejecucion del juego
					timer.stop();
				}

				// matar pacman //pacman fantasma

				if (Juego.mat[px][py + 1] == 7 || Juego.mat[px][py - 1] == 7 || Juego.mat[px - 1][py] == 7
						|| Juego.mat[px + 1][py] == 7) {
					// fantasma4.timer.stop();

					if (numeroVidas == 1) {
						fantasma1.timer.stop();
						fantasma2.timer.stop();
						// fantasma3.timer.stop();
						JOptionPane.showMessageDialog(ventana, "ESTAS MUERTO");
						// ventana.setVisible(false);

						ImageIcon perder;
						cargar.carga.setVisible(false);
						perder = new ImageIcon("proyectoPacman/gameOver.png");
						perder = new ImageIcon(perder.getImage().getScaledInstance(ventana.getWidth(),
								ventana.getHeight(), Image.SCALE_DEFAULT));
						fondoJuego.setIcon(perder);
						panelJuego.setVisible(false);
						ventana.add(fondoJuego, 0);
						// System.exit(0);
						timer.stop();
					} else {

						fantasma1.timer.stop();
						fantasma2.timer.stop();
						// fantasma3.timer.stop();

						numeroVidas--;
						vidas[numeroVidas].setVisible(false);
						mat[px][py] = 0;
						px = 1;
						py = 1;
						Timer timer1 = new Timer(200, new ActionListener() {
							public void actionPerformed(ActionEvent e) {

							}
						});

						mat[px][py] = 3;
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						fantasma1.timer.start();
						fantasma2.timer.start();
						// fantasma3.timer.start();
					}
				}

			}
		});
		// arraco el timer cuando se mueve
		timer.start();

		ventana.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					System.out.println("Tecleado hacia arriba");
					if (mat[px][py - 1] == 1 || mat[px][py - 1] == 0) {
						arriba = 1;
						abajo = 0;
						izquierda = 0;
						derecha = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					System.out.println("Tecleado hacia abajo ");
					if (mat[px][py + 1] == 1 || mat[px][py + 1] == 0) {
						arriba = 0;
						abajo = 1;
						izquierda = 0;
						derecha = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					System.out.println("Tecleado haci izquierda");
					if (mat[px - 1][py] == 1 || mat[px - 1][py] == 0) {
						arriba = 0;
						abajo = 0;
						izquierda = 1;
						derecha = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					System.out.println("Tecleado hacia derecha");
					if (mat[px + 1][py] == 1 || mat[px + 1][py] == 0) {
						arriba = 0;
						abajo = 0;
						izquierda = 0;
						derecha = 1;
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}

	/////
	/// matirzzzz...................
	public static void pintarMatriz() {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				// agregar las imagenes a la matriz
				matriz[i][j].setIcon(new ImageIcon("proyectoPacman/" + mat[i][j] + ".png"));
				// 200pixeles marjen
				matriz[i][j].setBounds(120 + (i * 30), 120 + (j * 30), 30, 30);
				matriz[i][j].setVisible(true);
				// es para superponer paneles
				panelJuego.add(matriz[i][j], 0);
			}
		}

	}

}
