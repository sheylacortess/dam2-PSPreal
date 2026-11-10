
import java.util.TreeMap;

public class Monitor {
	// Mapa que recoge el resultado de la encuesta
	private TreeMap <Integer,Integer> tmColores = new TreeMap <Integer,Integer> ();
	private int iNumEncNoPar = 0;		// Numero de encuestados que no dejan recogido su resultado
	
	// El acceso al mapa es sincronizado porque lo hacen varios hilos RecopilaInfo simultaneamente
	public synchronized void IncColor (int iValor)
	{
		if (tmColores.get(iValor) == null)
		{
			tmColores.put(iValor, 1);
		}
		else
		{
			int iValAux = (int) tmColores.get(iValor); 
			iValAux ++ ;
			tmColores.put(iValor, iValAux);
		}
	}
	
	// Incremente el numero de encuestados que no han dejado fichero respuesta de sus resultados
	public synchronized void IncNoEnc ()
	{
		iNumEncNoPar++;
	}

	// Getters en los atributos de clase. Para asegurar que estos sean privados y asi 
	// para asegurar que el acceso a las variables se hace syncronized
	public TreeMap <Integer,Integer> getListaColores ()
	{
		return tmColores;
	}

	public int getNumNoPar ()
	{
		return iNumEncNoPar;
	}


}
