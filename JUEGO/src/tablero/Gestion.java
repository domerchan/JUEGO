package tablero;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class Gestion {
	private jugador inicio;
	private String cabecera1[]={"Nombre","Record"};
	private String datos[][]={};
	DefaultTableModel modelo=new DefaultTableModel(datos,cabecera1);
	

	public void NuevoJugador(String nombre, String puntaje){
		//modelo=new DefaultTableModel(datos,cabecera1);
		 Object datos[]={nombre,puntaje};
		modelo.addRow(datos);	
		
	}
	
	
	public DefaultTableModel enviar(){
		return modelo;
	}
	

}
