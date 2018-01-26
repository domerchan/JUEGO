package Juego;

import javazoom.jl.decoder.*;
import javazoom.jl.player.*;
import java.io.*;

public class Sonido {
	public void sonido() throws Exception {
		FileInputStream direccion;
		direccion = new FileInputStream("sonidoPacman/Sonidos Pacman.mp3");
		Player play;
		BufferedInputStream rep = new BufferedInputStream(direccion);
		play = new Player(rep);
		play.play();
	}
}
