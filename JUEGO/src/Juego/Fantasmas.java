package Juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Fantasmas {
	// atributos

	int fanx;
	int fany;

	Timer timer;
	Random aleatorio;

	int direccion;
	int mx;
	int my;

	public Fantasmas(int x, int y) {
		aleatorio = new Random();
		fanx = x;
		fany = y;
		Juego.mat[fanx][fany] = 21;
		direccion = aleatorio.nextInt(4);
		this.movimiento();

	}// constructor

	public void movimiento() {
		timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// aizquierda
				if (fanx > 0 && direccion == 0) {
					// camina
					if (Juego.mat[fanx - 1][fany] != 6 && Juego.mat[fanx - 1][fany] == 0
							|| Juego.mat[fanx - 1][fany] == 19) {

						Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
						fanx -= 1;
						Juego.mat[fanx][fany] = 21;
						Juego.pintarMatriz();
						// choca con la pared
					} else if (fanx > 0 && Juego.mat[fanx - 1][fany] == 2 || Juego.mat[fanx - 1][fany] == 3
							|| Juego.mat[fanx - 1][fany] == 4 || Juego.mat[fanx - 1][fany] == 5
							|| Juego.mat[fanx - 1][fany] == 6 || Juego.mat[fanx - 1][fany] == 7
							|| Juego.mat[fanx - 1][fany] == 10 || Juego.mat[fanx - 1][fany] == 11
							|| Juego.mat[fanx - 1][fany] == 12 || Juego.mat[fanx - 1][fany] == 13
							|| Juego.mat[fanx - 1][fany] == 14 || Juego.mat[fanx - 1][fany] == 22
							|| Juego.mat[fanx - 1][fany] == 21 || Juego.mat[fanx - 1][fany] == 23
							|| Juego.mat[fanx - 1][fany] == 24 || Juego.mat[fanx - 1][fany] == 25) {
						// cambiar direccion si encuentra un muero
						direccion = aleatorio.nextInt(4);
						// }else
						// //choca con otro fantasma
						// if( Juego.mat[fanx-1][fany] == 7){
						//
						// direccion = aleatorio.nextInt(4);
						//
					}

				}

				// derecha
				if (direccion == 1) {

					if (Juego.mat[fanx + 1][fany] != 6 && Juego.mat[fanx + 1][fany] == 0
							|| Juego.mat[fanx + 1][fany] == 1) {

						Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
						fanx += 1;
						Juego.mat[fanx][fany] = 21;
						Juego.pintarMatriz();

					} else if (fanx < 14 && Juego.mat[fanx + 1][fany] == 2 || Juego.mat[fanx + 1][fany] == 3
							|| Juego.mat[fanx + 1][fany] == 4 || Juego.mat[fanx + 1][fany] == 5
							|| Juego.mat[fanx + 1][fany] == 6 || Juego.mat[fanx + 1][fany] == 7
							|| Juego.mat[fanx + 1][fany] == 10 || Juego.mat[fanx + 1][fany] == 11
							|| Juego.mat[fanx + 1][fany] == 12 || Juego.mat[fanx + 1][fany] == 13
							|| Juego.mat[fanx + 1][fany] == 14 || Juego.mat[fanx + 1][fany] == 22
							|| Juego.mat[fanx + 1][fany] == 21 || Juego.mat[fanx + 1][fany] == 23
							|| Juego.mat[fanx + 1][fany] == 24 || Juego.mat[fanx + 1][fany] == 25) {
						// cambiar direccion si encuentra un muero
						direccion = aleatorio.nextInt(4);
						// } else
						//
						// // choca con otro fantasma
						// if (Juego.mat[fanx + 1][fany] == 7) {
						// direccion = aleatorio.nextInt(4);

					}

				}

				// arriba
				if (fany > 0 && direccion == 2) {

					if (Juego.mat[fanx][fany - 1] != 5 && Juego.mat[fanx][fany - 1] == 0
							|| Juego.mat[fanx][fany - 1] == 19) {
						// se mueve si no hay un muro y hay un espacio libre o
						// una moneda
						Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
						fany -= 1;
						Juego.mat[fanx][fany] = 21;
						Juego.pintarMatriz();

					} else if (fany > 0 && Juego.mat[fanx][fany - 1] == 2 || Juego.mat[fanx][fany - 1] == 3
							|| Juego.mat[fanx][fany - 1] == 4 || Juego.mat[fanx][fany - 1] == 5
							|| Juego.mat[fanx][fany - 1] == 6 || Juego.mat[fanx][fany - 1] == 7
							|| Juego.mat[fanx][fany - 1] == 10 || Juego.mat[fanx][fany - 1] == 11
							|| Juego.mat[fanx][fany - 1] == 12 || Juego.mat[fanx][fany - 1] == 13
							|| Juego.mat[fanx][fany - 1] == 14 || Juego.mat[fanx][fany - 1] == 22
							|| Juego.mat[fanx][fany - 1] == 21 || Juego.mat[fanx][fany - 1] == 23
							|| Juego.mat[fanx][fany - 1] == 24 || Juego.mat[fanx][fany - 1] == 25) {
						// cambiar direccion si encuentra un muro
						direccion = aleatorio.nextInt(4);
						// } else
						// // choca con otro fantasma
						// if (Juego.mat[fanx][fany - 1] == 7) {
						// direccion = aleatorio.nextInt(4);

					}

				}

				// abajo
				if (direccion == 3) {

					if (Juego.mat[fanx][fany + 1] != 5 && Juego.mat[fanx][fany + 1] == 0
							|| Juego.mat[fanx][fany + 1] == 1) {

						Juego.mat[fanx][fany] = Juego.matAux[fanx][fany];
						fany += 1;
						Juego.mat[fanx][fany] = 21;
						Juego.pintarMatriz();

					} else if (fany < 14 && Juego.mat[fanx][fany + 1] == 2 || Juego.mat[fanx][fany + 1] == 3
							|| Juego.mat[fanx][fany + 1] == 4 || Juego.mat[fanx][fany + 1] == 5
							|| Juego.mat[fanx][fany + 1] == 6 || Juego.mat[fanx][fany + 1] == 7
							|| Juego.mat[fanx][fany + 1] == 10 || Juego.mat[fanx][fany + 1] == 11
							|| Juego.mat[fanx][fany + 1] == 12 || Juego.mat[fanx][fany + 1] == 13
							|| Juego.mat[fanx][fany + 1] == 14 || Juego.mat[fanx][fany + 1] == 22
							|| Juego.mat[fanx][fany + 1] == 21 || Juego.mat[fanx][fany + 1] == 23
							|| Juego.mat[fanx][fany + 1] == 24 || Juego.mat[fanx][fany + 1] == 25) {
						// cambiar direccion si encuentra un muero
						direccion = aleatorio.nextInt(4);
						// } else
						// // choca con otro fantasma
						// if (Juego.mat[fanx][fany + 1] == 21) {
						// direccion = aleatorio.nextInt(4);

					}

				}

			}
		});
		timer.start();
	}// fin movimineto

}
