/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.infoGuru.machineLearning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author Raef M
 */
public class TrainerThread extends Thread implements Comparator<Shapelet> {

    private final Lock lock = new Lock(false);
    private JProgressBar progressBar;
    private int temperatureIndex;
    private final int minL;
    private final int maxL;
    private final List<TimeSeries> data;
    private final Trainer trainer;
    private final int threads;

    public TrainerThread(Trainer trainer, int temperatureIndex, int minL, int maxL, List<TimeSeries> data, int threads, JProgressBar progressBar) {
        this.temperatureIndex = temperatureIndex;
        this.minL = minL;
        this.maxL = maxL;
        this.data = data;
        this.trainer = trainer;
        this.threads = threads;
        this.progressBar = progressBar;
    }

    public TrainerThread(Trainer trainer, int temperatureIndex, int minL, int maxL, List<TimeSeries> data, int threads) {
        this.temperatureIndex = temperatureIndex;
        this.minL = minL;
        this.maxL = maxL;
        this.data = data;
        this.trainer = trainer;
        this.threads = threads;
    }

    @Override
    public void run() {
        System.out.println(getName() + ": Started");
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        List<Shapelet> shapelets = new ArrayList<>();
        for (TimeSeries t : data) {
            if (t.getData().size() >= maxL) {
                for (int l = minL; l <= maxL; l++) {
                    for (int k = 0; k < t.getData().size() - l + 1; k++) {
                        synchronized (lock) {
                            if (lock.isStop()) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(TrainerThread.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        Shapelet s = Shapelet.buildShapelet(t, k, l);
                        //List<TimeSeries> cloned = cloneData(data);
                        pool.execute(new TrainerMiniWorker(s, data, shapelets, temperatureIndex, progressBar, lock));
                    }
                }
            }
        }
        System.out.println("Waiting the pool to finish...");
        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Shapelets Before:" + shapelets.size());
        Collections.sort(shapelets, this);
        List<TimeSeries> clonedData = cloneData(data);
        shapelets = pruneShapelets(shapelets, clonedData);
        trainer.setTrainingResults(shapelets);
        System.out.println("Shapletes: " + shapelets);
        System.out.println(getName() + ": Finished");
    }

    public int getTemperatureIndex() {
        return temperatureIndex;
    }

    public void setTemperatureIndex(int temperatureIndex) {
        this.temperatureIndex = temperatureIndex;
    }

    @Override
    public int compare(Shapelet o1, Shapelet o2) {
        if (o1.getUtility() < o2.getUtility()) {
            return 1;
        }
        if (o1.getUtility() > o2.getUtility()) {
            return -1;
        }
        return 0;
    }

    private List<TimeSeries> cloneData(List<TimeSeries> data) {
        List<TimeSeries> cloned = new ArrayList<>();
        data.stream().forEach((t) -> {
            try {
                cloned.add(t.clone());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(TrainerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return cloned;
    }

    private List<Shapelet> pruneShapelets(List<Shapelet> shapelets, List<TimeSeries> data) {
        List<Shapelet> results = new ArrayList<>();
        for (Shapelet s : shapelets) {
            if (data.isEmpty()) {
                break;
            }
            List<TimeSeries> toRemove = new ArrayList<>();
            boolean support = false;
            for (TimeSeries t : data) {
                if (s.support(t, temperatureIndex)) {
                    support = true;
                    toRemove.add(t);
                }
            }
            if (support) {
                data.removeAll(toRemove);
                results.add(s);
            }
        }
        return results;
    }


    public void pauseThread() {
        lock.setStop(true);
    }

    public void continueThread() {
        lock.setStop(false);
        synchronized (lock) {
            lock.notifyAll();
        }
    }

}
