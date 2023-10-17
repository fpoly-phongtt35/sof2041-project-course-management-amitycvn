/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class TimeClockThread {
    public static void startClock(JLabel lblTime){
        class TimeClock extends Thread{

            @Override
            public void run() {
                while (true) {                    
                    SimpleDateFormat date = new SimpleDateFormat("hh:mm:ss");
                    String dateTime = date.format(Calendar.getInstance().getTime());
                    lblTime.setText(dateTime);
                }
            }
        }
        TimeClock t = new TimeClock();
        t.start();
    }
}
