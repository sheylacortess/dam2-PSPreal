import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// Hilo del servidor que atiende a cada encuestado para recibir su nombre
public class ProcesaCli extends Thread {
	
	public String sNombre;
	Process miProcess;
	
	public ProcesaCli (Process miProcess)
	{
		this.miProcess = miProcess;
	}
	
	public void run ()
	{
		try {
			// Enlazar salida estandar del proceso hijo con este proceso padre
			InputStream miIs = miProcess.getInputStream();
			InputStreamReader miIsr = new InputStreamReader(miIs);
			BufferedReader miIsrb = new BufferedReader(miIsr);
			String sInfo = ""; 
			
			while((sInfo = miIsrb.readLine()) != null)
			{
				sNombre = sInfo;
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\t\t "  + sNombre);
	}
}
