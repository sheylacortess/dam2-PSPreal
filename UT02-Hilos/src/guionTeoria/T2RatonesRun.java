package guionTeoria;

public class T2RatonesRun implements Runnable {
    int iValor = 0;

    public static void main(String[] args) {
        T2RatonesRun miRat = new T2RatonesRun();
        for (int i = 0; i < 10000; i++) {
            new Thread(miRat).start();
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(miRat.iValor);
    }

    @Override
    public void run() {
        iValor++;
    }
}