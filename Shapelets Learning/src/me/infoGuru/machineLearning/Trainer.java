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
public interface Trainer {
    
    public void train(List<TimeSeries> data, int minL, int maxL, int temperatureIndex);
    public void train(Invoker invoker, List<TimeSeries> data, int minL, int maxL, int temperatureIndex);
    public void train(Invoker invoker, List<TimeSeries> data, int minL, int maxL, int temperatureIndex, JProgressBar progressBar);
    
    public void multiThreadedTrain(List<TimeSeries> data, int minL, int maxL, int temperatureIndex, int nbThreads);
    public void multiThreadedTrain(Invoker invoker, List<TimeSeries> data, int minL, int maxL, int temperatureIndex, int nbThreads);
    public void multiThreadedTrain(Invoker invoker, List<TimeSeries> data, int minL, int maxL, int temperatureIndex, int nbThreads, JProgressBar progressBar);
    
    public void setTrainingResults(List<Shapelet> shapelets);
    
    public void setInvoker(Invoker invoker);

    public void signalException(String desc);
    
    public void pauseThread();
    public void continueThread();
}
