/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.infoGuru.machineLearning;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Raef M
 */
public class TimeSeries {

    private List<String[]> data;
    private String classification;
    private String name;

    public TimeSeries() {
        data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public TimeSeries clone() throws CloneNotSupportedException {
        TimeSeries t = new TimeSeries();
        t.setClassification(getClassification());
        t.setName(getName());
        t.setData(new ArrayList<>(getData()));
        return t;
    }

    public double distanceTwoInequalLength(TimeSeries t, int index) {
        double result = Double.MAX_VALUE;
        if (getData().size() > t.getData().size()) {
            return result;
        }
        for (int k = 0; k < t.getData().size() - getData().size() + 1; k++){
            Shapelet window = Shapelet.buildShapelet(t, k, getData().size());
            double candidateResult = distanceTwoEqualLength(window, index);
            if (candidateResult < result) {
                result = candidateResult;
            }
        }
        return result;
    }

    public double distanceTwoEqualLength(TimeSeries h, int index) {
        double result = Double.MAX_VALUE;
        if (getData().size() != h.getData().size()) {
            return result;
        }
        result = 0.0;
        for (int i = 0; i < getData().size(); i++) {
            String[] sRow = getData().get(i);
            String[] hRow = h.getData().get(i);
            BigDecimal sBig = new BigDecimal(sRow[index]);
            BigDecimal hBig = new BigDecimal(hRow[index]);
            double sTemp = sBig.doubleValue();
            double hTemp = hBig.doubleValue();
            result += Math.pow(sTemp - hTemp, 2);
        }
        result = Math.sqrt(result);
        return result;
    }
    

    public boolean contain(Shapelet s, int index) {
        return (s.distanceTwoInequalLength(this, index) <= s.getDistanceThreshold());
    }
    
    @Override
    public String toString() {
        String s = "\nName: " + getName();
        s += "\nClass: " + getClassification();
        s += "\ndata: [";
        s = getData().stream().map((row) -> Arrays.toString(row) + ", ").reduce(s, String::concat);
        s += " size: " + getData().size() + "]";
        return s;
    }

}
