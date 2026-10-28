package guionTeoria;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class T2TimerTarea extends TimerTask {
    @Override
    public void run() {
        DateFormat dateF = new SimpleDateFormat("HH:mm:ss");
        Date miDate = new Date();
        System.out.println(dateF.format(miDate));
    }
}