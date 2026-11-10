## 📋 Especificaciones de las Aplicaciones

### 1. Aplicación Principal (`Servidor.java`)
* **Parámetros de entrada:** Lee por teclado (o parámetro) el número de encuestados a procesar. Si el dato introducido es erróneo o no válido, tomará **5 encuestados por defecto**.
* **Gestión de Procesos Hijos:**
  * Crea los procesos hijo de la clase `Encuestado` de forma simultánea usando `ProcessBuilder` (añadiendo `-cp bin`).
  * Guarda referencia de cada proceso en un array de tipo `Process[]`.
* **Atención mediante Hilos (`ProcesaCli.java`):**
  * Asigna a cada proceso hijo un hilo de la clase `ProcesaCli` (`extends Thread`).
  * Captura la salida estándar del hijo (`getInputStream()`) para obtener su nombre (marca de tiempo) y almacenarlo.
* **Sincronización de Procesos:**
  * El Servidor **debe bloquearse y esperar** a que todos los procesos encuestados terminen (`waitFor()`).
  * Añade una pausa de seguridad de 1 segundo tras la finalización de los procesos.
* **Procesamiento Concurrente de Ficheros (`RecopilaInfo.java`):**
  * Lanza tantos hilos como encuestados participen para leer simultáneamente los ficheros de resultado (`nombre_encuestado.txt`).
  * Almacena los datos procesados en la sección crítica administrada por el `Monitor`.
  * Espera a la finalización de todos los hilos (`join()`).
* **Visualización:**
  * Muestra en pantalla el recuento final por colores y el total de encuestados que **no respondieron**.

---

### 2. Aplicación Hijo (`Encuestado.java`)
* **Simulación de retardo inicial:** Aplica un `Thread.sleep` aleatorio entre 0 y 0.5 segundos.
* **Identificación:** Genera su nombre usando la marca de tiempo actual del sistema (`System.currentTimeMillis()`) y la **imprime por su salida estándar** (`System.out.print`) para que el Servidor la capture.
* **Tiempo de reflexión:** Aplica una pausa aleatoria entre 1 y 3 segundos.
* **Generación de respuesta:**
  * Genera una probabilidad del 20% de **no responder** (no crear fichero).
  * Si responde, selecciona un valor aleatorio entre **1 y 9** y crea un archivo de texto llamado `<nombre_encuestado>.txt` que contendrá dicho valor.

---

### 3. Hilo Lector de Ficheros (`RecopilaInfo.java`)
* Recibe en el constructor el nombre del encuestado y la referencia al `Monitor`.
* Intenta abrir el fichero `<nombre_encuestado>.txt`:
  * Si el fichero existe: Lee el número del color e incrementa el contador correspondiente en el Monitor (`IncColor`).
  * Si el fichero no existe (`FileNotFoundException` / `IOException`): Notifica al Monitor que el encuestado no participó (`IncNoEnc`).

---

### 4. Control de Sección Crítica (`Monitor.java`)
* Clase encargada de proteger los datos compartidos accedidos por múltiples hilos simultáneamente.
* Mantiene un mapa (`TreeMap<Integer, Integer>`) con el recuento de cada color y un contador de abstenciones.
* Todos los métodos que modifiquen o lean datos compartidos deben incluir la palabra clave **`synchronized`**:
  * `synchronized void IncColor(int iValor)`
  * `synchronized void IncNoEnc()`
  * `synchronized TreeMap<Integer, Integer> getListaColores()`
  * `synchronized int getNumNoPar()`
