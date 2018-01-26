package tablero;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Juego.Juego;

public class VentanaPuntajes extends JFrame implements ActionListener{

	private JDesktopPane escritorio;
	private Gestion ges;
	private JTable tabla;
	
	public VentanaPuntajes(Gestion ges){
		this.ges=ges;
		initComponents();
	}
	private void initComponents(){
		setSize(500,500);
		setTitle("Puntajes Altos");
		Container c=getContentPane();
		c.setLayout(new BorderLayout());
		escritorio=new JDesktopPane();
		c.add(escritorio, BorderLayout.CENTER);
		JPanel pa=new JPanel();	
		
		JButton bot=new JButton("Ver puntajes");
		//pa.add(bot);
		bot.setActionCommand("OK");
		bot.addActionListener(this);
//		JButton but=new JButton("nuevo jugador");
//		but.setActionCommand("OK1");
//		but.addActionListener(this);
//		pa.add(but);
		JButton bot1=new JButton("Ver puntaje");
		bot1.setActionCommand("OK2");
		bot1.addActionListener(this);
		pa.add(bot1);
		
		tabla = new JTable();
		JScrollPane scrollinscripciones = new JScrollPane(tabla);
		
		JPanel scrollPane = new JPanel();
		scrollPane.setLayout(new GridLayout(1, 1));
		scrollPane.add(scrollinscripciones);
		
		
		pa.add(scrollPane, BorderLayout.CENTER);
		
		
		c.add(pa);
				}
	//String nombre =JOptionPane.showInputDialog(this, "Ingrese su nombre");
/*
	public void Guardarper(){
		int puntajes=(int) (Math.random()*100)+1;
		ges.NuevoJugador(nombre, puntajes);
		listarTabla();
	}*/
	public void listarTabla(){
		tabla.setModel(ges.enviar());
		}
	public void nuevoJugador(){
		//String nombre1 =JOptionPane.showInputDialog(this, "Ingrese su nombre");
		int puntajes1=(int) (Math.random()*100)+1;
		System.out.println("si esta quiiii ");
		System.out.println(Juego.jugador);
		System.out.println(Juego.puntos);
		ges.NuevoJugador(Juego.jugador, Integer.toString(Juego.puntos));
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		
		switch(comando){
		case "OK":
			//Guardarper();
			break;
		case "OK2":
			listarTabla();
			break;
//		case "OK1":
//			nuevoJugador();
			default:
//			break;
		}
		
	}
	

}
