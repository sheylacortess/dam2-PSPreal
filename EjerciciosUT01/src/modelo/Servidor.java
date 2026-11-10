import java.io.IOException;
import java.util.Scanner;
import java.util.Map;

public class Servidor {
	static private Process [] encuestados;				// Array de encuestados	
	static private ProcesaCli [] hilosCli;				// Array de hilos que atiende a encuestados
	static private RecopilaInfo [] hilosLeeFich;		// Array de hilos que analiza cada encuestado
	// Tabla de correspondencia numero - color
	static private String sColor [] = {"Blanco","Azul","Verde","Rojo","Amarillo","Naranja","Marron","Gris","Negro"};
	
	public static void main (String [] args) throws IOException
	{
		Monitor miMon = new Monitor();					// Seccion critica. Se guarda resultado de encuesta
		Scanner miScan = new Scanner(System.in);		// Acceso a teclado

		// Recuperar por teclado el numero de encuestados. Si no es valido se consideran cinco por defecto
		System.out.print("\n\tIndica el numero de encuestados que participan: ");
		String sAux = miScan.nextLine();
		System.out.println();
		
		int iNumEnc;
		try
		{
			iNumEnc = Integer.parseInt(sAux); 
		}
		catch (Exception e)
		{
			iNumEnc = 5;		
		}
		
		// Dimensiona los arrays al tamaño de encuestados para esta ejecucion
		encuestados = new Process [iNumEnc];
		hilosCli = new ProcesaCli [iNumEnc];
		hilosLeeFich  = new RecopilaInfo [iNumEnc];


		// ** Lanza procesos de encuestados. Dejan resultado en ficheros con su nombre.txt  ** //
		System.out.println("\t ** Participan " + iNumEnc + " encuestados con nombres **");
		
		for (int i=0;i<iNumEnc;i++)		
		{
			String [] sArg = new String [2];
			sArg[0] = "/usr/java/jdk-24.0.2/bin/java";	// Consultar localizacion con which java
			sArg[1] = "Encuestado";						// Con esta ruta hay que copiar servidor y cliente en el mismo directorio
			
			ProcessBuilder mipb = null;
			mipb = new ProcessBuilder(sArg);
			
			try {
				encuestados[i] = mipb.start();			// Inicia proceso encuestado
				hilosCli[i] = new ProcesaCli(encuestados[i]);	// Inicia hilo para atender al encuestado
				hilosCli[i].start();	
			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		for (int i=0;i<iNumEnc;i++)						// Espera que terminen todos los procesos
		{
			try {
				encuestados[i].waitFor();			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}	

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Aqui ya habran terminado todos los procesos
		System.out.println("\n\t** Todos los encuestados han terminado **");
		
		// ** Procesa los resultados de la encuesta  ** //
		System.out.println("\n\t** Procesando los resultados **");

		// Cada encuesta (recogida en en un fichero, se procesa con un hilo)
		// El resultado se almacena en memoria (con seccion critico), definida en la clase Monitor
		for (int i=0;i<iNumEnc;i++)		
		{
			hilosLeeFich[i] = new RecopilaInfo(hilosCli[i].sNombre,miMon);
			hilosLeeFich[i].start(); 
		}

		for (int i=0;i<iNumEnc;i++)		// Espera a que terminen todos los hilos
		{
			try {
				hilosLeeFich[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Aqui ya habran terminado todos los procesos
		// System.out.println("\n\t** Todos los hilos han terminado **");
		System.out.println("\n\t** El resultado de la encuesta es **");
		
		// Se recorre el mapa con el resultado de la encuestra para mostrar en pantalla
		for (Map.Entry<Integer, Integer> entry : miMon.getListaColores().entrySet())
		{
			System.out.printf("\t\t%-8s - %d\n",sColor[entry.getKey() - 1],entry.getValue());
		}
		
		System.out.println("\n\t\t" + miMon.getNumNoPar() + " enuestados no han participado en la encuesta");
		
		miScan.close();
	}
}
