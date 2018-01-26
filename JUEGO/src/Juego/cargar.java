package Juego;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class cargar {
	static JFrame carga;
	JPanel barra;
	int matriz[];
	JLabel matrizC[];
	JLabel aux[];
	Timer timer;
int i=0;
private static int i1;
	public cargar() {
		carga = new JFrame();
		carga.setTitle("Espere porfavor");
		carga.setSize(400, 200);
		carga.setLayout(null);
		
		carga.setVisible(true);
		carga.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//barra = new JPanel();
		barra= new JPanel();
		barra.setBackground(Color.BLACK);
		barra.setLayout(null);
		barra.setBounds(0, 0, carga.getWidth(),carga.getHeight());
		
		barra.setVisible(true);
		
		matriz = new int[8];
		matrizC = new JLabel[8];
		aux = new JLabel[8];
		for (int j = 0; j < matriz.length; j++) {
			matriz[j] = 2;
		}

		for (int i3 = 0; i3 < matriz.length; i3++) {
			matrizC[i3]=new JLabel();
			aux[i3]=new JLabel();
		}
		
		JLabel mensaje = new JLabel("CARGANDO ........");
		mensaje.setBounds(100,50,80,30);
		mensaje.setVisible(true);
		barra.add(mensaje);
		timer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i2 = 0; i2 < matriz.length; i2++) {
					matrizC[i2].setIcon(new ImageIcon("proyectoPacman/" + matriz[i2] + ".png"));
					matrizC[i2].setBounds(90+(i2*30),90,30,30);
					matrizC[i2].setVisible(true);
					barra.add(matrizC[i2]);			
				}
				
		matriz[i] = 10;
				matrizC[i].setIcon(new ImageIcon("proyectoPacman/" + matriz[i] + ".png"));
				matrizC[i].setBounds(90+(i*30),90,30,30);
				matrizC[i].setVisible(true);
				barra.add(matrizC[i]);
				matriz[i]=2;
				aux[i].setIcon(new ImageIcon("proyectoPacman/" + matriz[i] + ".png"));
				aux[i].setBounds(90+(i*30),90,30,30);
				aux[i].setVisible(true);
				barra.add(aux[i]);
				i++;
				if(i==8){
					Juego.tercer++;
					enviarDato();
					nivel2();
					timer.stop();
				}
			}
		});
		timer.start();
		carga.add(barra);
	}
	public void enviarDato() {
		// TODO Auto-generated method stub
		VentanaMenu.bandera =8;
	}
	public void nivel2(){
		Juego.banderan1=2;
	}
	
}
