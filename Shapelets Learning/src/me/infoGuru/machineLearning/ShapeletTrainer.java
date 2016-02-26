/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.infoGuru.machineLearning;

import java.util.List;
import javax.swing.JProgressBar;

/**
 *
 * @author Raef M
 */
public class ShapeletTrainer implements Trainer {


    
    private Invoker invoker;
    private TrainerThread trainerThread;
    
    @Override
    public void train(List<TimeSeries> data, int minL, int maxL, int temperatureIndex) {
        startThread(data,minL,maxL,temperatureIndex,1);
    }


    @Override
    public void multiThreadedTrain(List<TimeSeries> data, int minL, int maxL, int temperatureIndex, int nbThreads) {
        startThread(data,minL,maxL,temperatureIndex, nbThreads);
    }
    
    @Override
    public void train(Invoker invoker, List<TimeSeries> data, int minL, int maxL, int temperatureIndex) {
        this.invoker = invoker;
        startThread(data,minL,maxL,temperatureIndex,1);
        
    }
    
    @Override
    public void train(Invoker invoker, List<TimeSeries> data, int minL, int maxL, int temperatureIndex, JProgressBar progressBar) {
        this.invoker = invoker;
        startThread(data,minL,maxL,temperatureIndex,1, progressBar);
        
    }
    

    @Override
    public void multiThreadedTrain(Invoker invoker, List<TimeSeries> data, int minL, int maxL, int temperatureIndex, int nbThreads) {
       this.invoker = invoker;
        startThread(data,minL,maxL,temperatureIndex,nbThreads);
    }
    
    @Override
    public void multiThreadedTrain(Invoker invoker, List<TimeSeries> data, int minL, int maxL, int temperatureIndex, int nbThreads, JProgressBar progressBar) {
       this.invoker = invoker;
        startThread(data,minL,maxL,temperatureIndex,nbThreads, progressBar);
    }
    
    @Override
    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void setTrainingResults(List<Shapelet> shapelets) {
        invoker.setExtractedShapelets(shapelets);
    }
    
    
    private void startThread(List<TimeSeries> data, int minL, int maxL, int temperatureIndex, int nbThreads){
        trainerThread = new TrainerThread(this, temperatureIndex, minL, maxL, data, nbThreads);
        trainerThread.setName("Main Trainer Thread");
        trainerThread.start();
    }
    
     private void startThread(List<TimeSeries> data, int minL, int maxL, int temperatureIndex, int nbThreads, JProgressBar progressBar){
        trainerThread = new TrainerThread(this, temperatureIndex, minL, maxL, data, nbThreads, progressBar);
        trainerThread.setName("Main Trainer Thread");
        trainerThread.start();
    }

    @Override
    public void signalException(String desc) {
        invoker.signalException(desc);
    }

    @Override
    public void pauseThread() {
        trainerThread.pauseThread();
    }

    @Override
    public void continueThread() {
        trainerThread.continueThread();
    }

}
