package Juego;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame implements SerialPortEventListener {

	//Panel base para visualización de datos
	private JPanel contentPane;
	
	// Variable que representa el puerto USB y permite realizar la conexion con el mismo
    private gnu.io.NRSerialPort puertoUSB;
    // Variable que representa el flujo de datos que envia el puerto USB
    private java.io.DataInputStream flujoUSB;
    // Variable con la coordenada x que envia la tarjeta electronica
  
    
    // Variable con la coordenada y que envia la tarjeta electronica
    static int y;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		inicializarComponentes();
		conectar();
	}
	
	public void inicializarComponentes(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(java.awt.Color.WHITE);
		setContentPane(contentPane);
		
		
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent we){
                System.out.println("Cierra puerto...");
                // Es importante cerrar el puerto cuando ya no se lo utilice
                puertoUSB.disconnect();
                System.exit(0);
            }
        });
		
	}
	
	private void conectar(){
		
		try{
            // Abrimos el puerto en el puerto COM4 y con 9600 baudios de velocidad
            puertoUSB=new gnu.io.NRSerialPort("COM4",9600);
            // Nos conectamos al puerto USB
            puertoUSB.connect();
            // Indicamos que queremos saber cuando lleguen nuevos datos. Que nos notifique.
            puertoUSB.notifyOnDataAvailable(true);
            // Agregamos un listener para saber que hacer cuando llegan nuevos datos.
            puertoUSB.addEventListener(this);
            System.out.println("Puerto USB Listo...");
        }catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
		
	}

	/**
	 * Listener para control de datos entrantes por el puerto USB
	 */
	@Override
	public void serialEvent(SerialPortEvent evento) {
		// TODO Auto-generated method stub
		
		try{
            // Verificamos que el evento generado sea de llegada de datos del puerto
            if(evento.getEventType()==gnu.io.SerialPortEvent.DATA_AVAILABLE){
                //System.out.println("LLegan datos....");
                // Obtenemos el lector del flujo
                flujoUSB=new java.io.DataInputStream(puertoUSB.getInputStream());
                if(flujoUSB.available()>0){
                    y=flujoUSB.read();
					if (y == 1) {
						
						//arriba = y;
						System.out.println("Arriba" );
						//Juego.palanca.start();
					}
					if (y == 2) {
						
						//Juego.palanca.start();
						System.out.println("izquierda" );
					}
					if (y == 3) {
						
						//Juego.palanca.start();
						System.out.println("abajo" );
					}
					if (y == 4) {
						
						//Juego.palanca.start();
						System.out.println("derecha" );
					}
					if(y==5){
					    //Juego.palanca.start();
						System.out.println("boton");
					}
					if(y==6){
					    //Juego.palanca.start();
						System.out.println("cuadrado");
					}
					
					repaint();
				}
            }
        }catch(Exception e){
            e.printStackTrace();
        }
	}

}
