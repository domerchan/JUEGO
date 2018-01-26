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

import tablero.Gestion;
import tablero.VentanaPuntajes;

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

	// muerte
	int contM = 1;
	Timer timer1;

	// cosas nesesarias para el juego
	public static String jugador;
	JLabel nombre;
	public static int puntos;
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
		ventana.setSize(1380, 800);
		ventana.setLayout(null);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// juego
		// timer2 = new Timer(1, new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// Sonido s=new Sonido();
		// try {
		// s.sonido();
		// } catch (Exception e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// timer2.stop();
		// }
		// });
		// timer2.start();
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
		mat[px][py] = 15;
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
		imagenJuego = new ImageIcon("image/untitled.png");
		imagenJuego = new ImageIcon(
				imagenJuego.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
		fondoJuego.setIcon(imagenJuego);
		fondoJuego.setVisible(true);
		panelJuego.add(fondoJuego, 0);

		// vamos a llenar el vector con las fotos
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				// agregar las imagenes a la matriz
				matriz[i][j].setIcon(new ImageIcon(VentanaInicio.pathP+ mat[i][j] + ".png"));
				// 200pixeles marjen
				matriz[i][j].setBounds(400 + (i * 30), 120 + (j * 30), 30, 30);
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
			vidas[i1].setIcon(new ImageIcon(VentanaInicio.pathP+"vidas.png"));
			vidas[i1].setBounds(400 + (i1 * 30), 600, 30, 30);
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

		fantasma1 = new Fantasmas(6, 7);
		//fantasma2 = new Fantasmas(6, 6);
		// fantasma3 = new Fantasmas(13, 12);

		ventana.add(panelJuego);
	}

	public int[][] tablero(int option) {

		int[][] aux1 = new int[15][15];

		if (option == 1) {
			int aux[][] = {

					{ 3, 6, 6,14, 6, 6, 2,22, 3, 6, 6, 6, 6, 6, 2},
					{ 5,15,19,10,19,19, 5,19, 5,19,19,19,19,19, 5},
					{ 5,19,19,19,19,19,10,19, 5,19,24,19,24,19, 5},
					{ 5,19, 3, 6, 2,19,19,19, 4, 6, 1,19, 5,19, 5},
					{ 5,19, 5, 0, 5,19,19,19,19,19,19,19, 5,19, 5},
					{ 5,19, 4, 6, 1,19, 7, 6, 2,19, 7, 6, 1,19, 5},
					{ 5,19,19,19,19,19, 0, 0, 5,19,19,19,19,19, 5},
					{12, 6, 6, 6, 6,25, 0, 0, 5,19, 3, 6, 2,19, 5},

					{ 5,19,19,19,19,19, 0, 0, 5,19, 5, 0, 5,19, 5},
					{ 5,19,24,19,24,19, 7, 6, 1,19, 4, 6, 1,19, 5},
					{ 5,19, 5,19, 5,19,19,19,19,19,19,19,19,19, 5},
					{ 5,19, 5,19, 5,19,24,19, 7, 6, 2,19,24,19, 5},
					{ 5,19,10,19, 4, 6,13,19,19,19,10,19,10,19, 5},
					{ 5,19,19,19,19,19, 5,19,24,19,19,19,19,19, 5},
					{ 4, 6, 6, 6, 6, 6, 1,23, 4, 6, 6, 6, 6, 6, 1},

			};
			return aux;
		}
		if (option == 2) {
			int aux[][] = {

					{ 3, 6, 6,14, 6, 6, 2,22, 6, 6, 6, 6, 6, 6, 2},
					{ 5,19,19,10,19,19, 5,19, 5,19,19,19,19,19, 5},
					{ 5,19,19,19,19,19,10,19, 5,19,24,19,24,19, 5},
					{ 5,19, 3, 6, 2,19,19,19, 4, 6, 1,19, 5,19, 5},
					{ 5,19, 5, 0, 5,19,19,19,19,19,19,19, 5,19, 5},
					{ 5,19, 4, 6, 1,19, 7, 6, 2,19, 2, 6, 7,19, 5},
					{ 5,19,19,19,19,19, 0, 0, 5,19,19,19,19,19, 5},
					{12, 6, 6, 6, 6,25, 0, 0, 5,19, 3, 6, 2,19, 5},

					{ 5,19,19,19,19,19,19,19, 5,19, 5, 0, 5,19, 5},
					{ 5,19,24,19,24,19, 7, 6, 1,19, 4, 6, 1,19, 5},
					{ 5,19, 5,19, 5,19,19,19,19,19,19,19,19,19, 5},
					{ 5,19, 5,19, 5,19,24,19, 7, 6, 2,19,24,19, 5},
					{ 5,19,10,19, 4, 6,13,19,19,19,10,19,10,19, 5},
					{ 5,19,19,19,19,19, 5,19,24,19,19,19,19,19, 5},
					{ 4, 6, 6, 6, 6, 6, 1,23, 4, 6, 6, 6, 6, 6, 1},
			};
			return aux;
		}

		if (option == 3) {
			int aux[][] = {
					// 5 (13,1) x6,y7

					{ 3, 6, 6,14, 6, 6, 2,22, 6, 6, 6, 6, 6, 6, 2},
					{ 5,19,19,10,19,19, 5,19, 5,19,19,19,19,19, 5},
					{ 5,19,19,19,19,19,10,19, 5,19,24,19,24,19, 5},
					{ 5,19, 3, 6, 2,19,19,19, 4, 6, 1,19, 5,19, 5},
					{ 5,19, 5, 0, 5,19,19,19,19,19,19,19, 5,19, 5},
					{ 5,19, 4, 6, 1,19, 7, 6, 2,19, 2, 6, 7,19, 5},
					{ 5,19,19,19,19,19, 0, 0, 5,19,19,19,19,19, 5},
					{12, 6, 6, 6, 6,25, 0, 0, 5,19, 3, 6, 2,19, 5},

					{ 5,19,19,19,19,19,19,19, 5,19, 5, 0, 5,19, 5},
					{ 5,19,24,19,24,19, 7, 6, 1,19, 4, 6, 1,19, 5},
					{ 5,19, 5,19, 5,19,19,19,19,19,19,19,19,19, 5},
					{ 5,19, 5,19, 5,19,24,19, 7, 6, 2,19,24,19, 5},
					{ 5,19,10,19, 4, 6,13,19,19,19,10,19,10,19, 5},
					{ 5,19,19,19,19,19, 5,19,24,19,19,19,19,19, 5},
					{ 4, 6, 6, 6, 6, 6, 1,23, 4, 6, 6, 6, 6, 6, 1},
			};
			return aux;
		}

		return aux1;
	}

	public void mover() {

		timer = new Timer(150, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (arriba == 1 && (mat[px][py - 1] == 19 || mat[px][py - 1] == 0)) {

					if (mat[px][py - 1] == 19) {
						puntos = puntos + 5;
						record.setText("Puntos: " + puntos);
					}

					// me muevo una pocicion
					mat[px][py] = 0;
					matAux[px][py] = mat[px][py];// esto es nuevo para todo
													// actualizacion
					py = py - 1;
					// coloco el munieco
					mat[px][py] =18;
					pintarMatriz();
				}
				if (abajo == 1 && (mat[px][py + 1] == 19 || mat[px][py + 1] == 0)) {
					// puntos
					if (mat[px][py + 1] == 1) {
						puntos = puntos + 5;
						record.setText("Puntos: " + puntos);
					}

					// me muevo una pocicion
					mat[px][py] = 0;
					matAux[px][py] = mat[px][py];
					py++;
					// coloco el munieco
					mat[px][py] =16;
					pintarMatriz();
				}

				if (izquierda == 1 && (mat[px - 1][py] == 19 || mat[px - 1][py] == 0 || mat[px - 1][py] == 22)) {

					// vamos aponer al pacman en una nueva pocicion
					if (mat[px - 1][py] == 22) {
						mat[px][py] = 0;
						matAux[px][py] = mat[px][py];
						px = 13;
						py = 7;
						mat[px][py] = 17;
						// cinco actulizaciones
						pintarMatriz();
					}

					if (mat[px - 1][py] == 19) {
						puntos = puntos + 5;
						record.setText("Puntos: " + puntos);
					}

					// me muevo una pocicion
					mat[px][py] = 0;
					matAux[px][py] = mat[px][py];
					px = px - 1;
					// coloco el munieco
					mat[px][py] = 17;
					pintarMatriz();
				}
				if (derecha == 1 && (mat[px + 1][py] == 19 || mat[px + 1][py] == 0 || mat[px + 1][py] == 23)) {

					if (mat[px + 1][py] == 23) {
						mat[px][py] = 0;
						matAux[px][py] = mat[px][py];
						px = 1;
						py = 7;
						mat[px][py] = 15;
						// sexta actulizaciones
						pintarMatriz();
					}
					
					if (mat[px + 1][py] == 19) {
						puntos = puntos + 5;
						record.setText("Puntos: " + puntos);
					}

					// me muevo una pocicion
					mat[px][py] = 0;
					matAux[px][py] = mat[px][py];
					px = px + 1;
					// coloco el munieco
					mat[px][py] = 15;
					pintarMatriz();
				}

				boolean encontrado = true;
				for (int i = 0; i < mat.length && encontrado; i++) {
					for (int j = 0; j < mat.length && encontrado; j++) {
						if (mat[i][j] == 19) {
							encontrado = false;
						}
					}
				}

				if (encontrado) {
					// JOptionPane.showMessageDialog(ventana, "Felicitaciones: "
					// + jugador + " as
					// ganado");
					timer.stop();
					ventana.setVisible(false);
					JOptionPane.showMessageDialog(null,
							"Felicitaciones: " + jugador + " as ganado" + "\nSIGUIENTE NIVEL ", null,
							JOptionPane.INFORMATION_MESSAGE);
					tercer++;
					// paramos el juego
					timer.stop();
					// siguiente nivel
					// Juego j=new Juego();
					cargar c = new cargar();
					cargarT = new Timer(5000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println(banderan1);
							if (banderan1 == 2 && tercer !=4) {
								Juego c = new Juego();
								mat = tablero(2);
								// fantasma4 = new Fantasmas(13, 12);
								banderan1++;
								cargar.carga.setVisible(false);
								cargarT.stop();
							}

							if (tercer == 4) {

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

				//////////////////////////////////////////////////////////// USB
				//////////////////////////////////////////////////////////// PALANCA//////////////////////////////////////////
				palanca = new Timer(100, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/////////////////////////////////////////// USB
						/////////////////////////////////////////// SENIAL/////////////////////////////////////////////////////////////
						if (VentanaPrincipal.y == 1) {
							if (mat[px][py - 1] == 19 || mat[px][py - 1] == 0) {
								arriba = 1;
								abajo = 0;
								izquierda = 0;
								derecha = 0;
							}
						}

						if (VentanaPrincipal.y == 3) {
							if (mat[px][py + 1] == 19 || mat[px][py + 1] == 0) {
								arriba = 0;
								abajo = 1;
								izquierda = 0;
								derecha = 0;
							}
						}

						if (VentanaPrincipal.y == 2) {
							if (mat[px - 1][py] == 19 || mat[px - 1][py] == 0) {
								arriba = 0;
								abajo = 0;
								izquierda = 1;
								derecha = 0;
							}
						}

						if (VentanaPrincipal.y == 4) {
							if (mat[px + 1][py] == 19 || mat[px + 1][py] == 0) {
								arriba = 0;
								abajo = 0;
								izquierda = 0;
								derecha = 1;
							}
						}
						////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					}
				});
				palanca.start();
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// matar pacman //pacman fantasma

				if (Juego.mat[px][py + 1] == 21 || Juego.mat[px][py - 1] == 21 || Juego.mat[px - 1][py] == 21
						|| Juego.mat[px + 1][py] == 21) {
					// fantasma4.timer.stop();

					if (numeroVidas == 1) {
						fantasma1.timer.stop();
						//fantasma2.timer.stop();
						// fantasma3.timer.stop();
						JOptionPane.showMessageDialog(ventana, "ESTAS MUERTO");
						// ventana.setVisible(false);

						ImageIcon perder;
						cargar.carga.setVisible(false);
						perder = new ImageIcon("image/gameOver.png");
						perder = new ImageIcon(perder.getImage().getScaledInstance(ventana.getWidth(),
								ventana.getHeight(), Image.SCALE_DEFAULT));
						fondoJuego.setIcon(perder);
						panelJuego.setVisible(false);
						ventana.add(fondoJuego, 0);
						///////// cargamos los datos ////
						Gestion gs = new Gestion();
						VentanaPuntajes vp = new VentanaPuntajes(gs);
						vp.nuevoJugador();
						///////////////////////////////
						VentanaMenu.ventana.setVisible(true);
						ventana.setVisible(false);
						// System.exit(0);
						timer.stop();

					} else {

						fantasma1.timer.stop();
						//fantasma2.timer.stop();
						// try {
						// Thread.sleep(1000);
						// } catch (InterruptedException e1) {
						// // TODO Auto-generated catch block
						// e1.printStackTrace();
						// }
						// mat[fantasma1.fanx][fantasma1.fany]=0;
						// mat[fantasma2.fanx][fantasma2.fany]=0;
						// pintarMatriz();
						// fantasma3.timer.stop();

						// timer1 = new Timer(100, new ActionListener() {
						// public void actionPerformed(ActionEvent e) {
						// timer.stop();
						// matriz[px][py].setIcon(new ImageIcon("muerte/" +
						// contM + ".png"));
						// contM++;
						// if (contM > 11) {
						// contM = 0;
						// vidas[numeroVidas].setVisible(false);
						// numeroVidas--;
						// mat[px][py] = 0;
						// px = 1;
						// py = 1;
						// mat[px][py] = 3;
						// timer1.stop();
						// timer.start();
						//
						// }
						// }
						// });
						// timer1.start();
						
						numeroVidas--;
						vidas[numeroVidas].setVisible(false);
						mat[px][py] = 0;
						px = 1;
						py = 1;
						Timer timer1 = new Timer(200, new ActionListener() {
							public void actionPerformed(ActionEvent e) {

							}
						});

						mat[px][py] = 15;
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						fantasma1.timer.start();
						//fantasma2.timer.start();
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
					System.out.println("Tecleado haci aarriba");
					if (mat[px][py - 1] == 19 || mat[px][py - 1] == 0) {
						arriba = 1;
						abajo = 0;
						izquierda = 0;
						derecha = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					System.out.println("Tecleado hacia abajo ");
					if (mat[px][py + 1] == 19 || mat[px][py + 1] == 0) {
						arriba = 0;
						abajo = 1;
						izquierda = 0;
						derecha = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					System.out.println("Tecleado haci izquierda");
					if (mat[px - 1][py] == 19 || mat[px - 1][py] == 0) {
						arriba = 0;
						abajo = 0;
						izquierda = 1;
						derecha = 0;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					System.out.println("Tecleado hacia derecha");
					if (mat[px + 1][py] == 19 || mat[px + 1][py] == 0) {
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
				matriz[i][j].setIcon(new ImageIcon("image/" + mat[i][j] + ".png"));
				// 200pixeles marjen
				matriz[i][j].setBounds(400 + (i * 30), 120 + (j * 30), 30, 30);
				matriz[i][j].setVisible(true);
				// es para superponer paneles
				panelJuego.add(matriz[i][j], 0);
			}
		}

	}

}
