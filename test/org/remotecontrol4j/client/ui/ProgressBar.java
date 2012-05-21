package org.remotecontrol4j.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.ProgressMonitor;
import javax.swing.Timer;






public class ProgressBar implements Runnable {
    static Timer tm;
    ProgressMonitor pm;
    JFrame poi;

    int i;

    public ProgressBar() {
        
    }

    public void run() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pm.setProgress(++i == 100 ? 1 : i);
            }
        };
        tm = new Timer(50, actionListener);
        pm = new ProgressMonitor(poi,"","正在发生，请稍后...", 0, 100);
        pm.setMillisToDecideToPopup(0);
        pm.setMillisToPopup(0);
        tm.start();

    }

    public static void main(String[] args) {
        ProgressBar p = new ProgressBar();
        Thread th = new Thread(p);
        try {
            th.start();
        } catch (Exception e) {
            // th.interrupt();  
        }
    }
    
    public void stop() {
        ProgressBar p = new ProgressBar();
        Thread th = new Thread(p);
        try {
             pm.close();
        return;
        } catch (Exception e) {
           System.out.println(e);
        }
    }
    



}
