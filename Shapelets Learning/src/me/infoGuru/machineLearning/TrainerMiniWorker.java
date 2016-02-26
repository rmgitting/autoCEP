/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.infoGuru.machineLearning;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author Raef M
 */
public class TrainerMiniWorker implements Runnable {

    private final Shapelet s;
    private final List<TimeSeries> data;
    private final List<Shapelet> shapelets;
    private final int index;
    private final JProgressBar progressBar;
    private final Lock lock;

    public TrainerMiniWorker(Shapelet s, List<TimeSeries> data, List<Shapelet> shapelets, int index, JProgressBar progressBar, Lock lock) {
        this.s = s;
        this.data = data;
        this.shapelets = shapelets;
        this.index = index;
        this.progressBar = progressBar;
        this.lock = lock;
    }
    
    


    @Override
    public void run() {
        synchronized(lock){
            if(lock.isStop())
                try {
                    lock.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(TrainerMiniWorker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        s.calculateRowDistances(data, index);
        if (s.calculateDistanceThreshold(data)) {
            s.calculateUtilityScore(data, index);
            synchronized (shapelets) {
                shapelets.add(s);
                int v = progressBar.getValue();
                progressBar.setValue(++v);
            }
        }
    }

}
