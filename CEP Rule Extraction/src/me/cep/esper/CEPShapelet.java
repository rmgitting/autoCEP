/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.cep.esper;

import java.util.ArrayList;
import java.util.List;
import me.infoGuru.machineLearning.Shapelet;

/**
 *
 * @author Raef M
 */
public class CEPShapelet {

    public CEPShapelet(Shapelet s) {
        data = s.getData();
        classification = s.getClassification();
        delta = s.getDistanceThreshold();
        times = new ArrayList<>();
        values = new ArrayList<>();
    }

    public List<String[]> getData() {
        return data;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void add(String time, Double value) {
        int l = data.size();
        times.add(time);
        values.add(value);
        if (times.size() > l) {
            times.remove(0);
            values.remove(0);
        }

    }

    public boolean existSimilarity() {
        boolean similar = false;
        if (data.size() == times.size()) {
            if (calculateDistance() <= getDelta()) {
                similar = true;
            }
        }
        return similar;
    }

    public double calculateDistance() {
        if(data.size() != times.size())
            return -1;
        double result = 0.0;
        for (int i = 0; i < getData().size(); i++) {
            String[] sRow = getData().get(i);
            double sTemp = Double.parseDouble(sRow[1]);
            double hTemp = getValues().get(i);
            result += Math.pow(sTemp - hTemp, 2);
        }
        result = Math.sqrt(result);
        return result;
    }

    private List<String[]> data;
    private List<String> times;
    private List<Double> values;
    private double delta;
    private String classification;

}
