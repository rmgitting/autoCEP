/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.infoGuru.machineLearning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.infoGuru.global.Global;

/**
 *
 * @author Raef M
 */
public class Shapelet extends TimeSeries {

    private double distanceThreshold;
    private ArrayList<Double> rowDistances;
    private double utility;
    private double tp;
    private double fp;
    private double tn;
    private double fn;
    private final int W = 1;

    public Shapelet() {
        super();
        rowDistances = new ArrayList<>();
    }

    public double getTp() {
        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public double getFp() {
        return fp;
    }

    public void setFp(double fp) {
        this.fp = fp;
    }

    public double getTn() {
        return tn;
    }

    public void setTn(double tn) {
        this.tn = tn;
    }

    public double getFn() {
        return fn;
    }

    public void setFn(double fn) {
        this.fn = fn;
    }

    public double getUtility() {
        return utility;
    }

    public void setUtility(double utility) {
        this.utility = utility;
    }

    public ArrayList<Double> getRowDistances() {
        return rowDistances;
    }

    public void setRowDistances(ArrayList<Double> rowDistances) {
        this.rowDistances = rowDistances;
    }

    public double getDistanceThreshold() {
        return distanceThreshold;
    }

    public void setDistanceThreshold(double distanceThreshold) {
        this.distanceThreshold = distanceThreshold;
    }

    public boolean support(TimeSeries t, int index) {
        return ((distanceTwoInequalLength(t, index) <= getDistanceThreshold()) && (t.getClassification().equalsIgnoreCase(getClassification())));
    }

    public static Shapelet buildShapelet(TimeSeries t, int k, int l) {
        Shapelet s = new Shapelet();
        int i = 0;
        while (i < l) {
            s.getData().add(t.getData().get(k + i));
            i++;
        }
        s.setClassification(t.getClassification());
        s.setName(t.getName());
        return s;
    }

    public void calculateRowDistances(List<TimeSeries> data, int index) {
        data.stream().forEach((t) -> {
            getRowDistances().add(distanceTwoInequalLength(t, index));
        });
    }

    public boolean calculateDistanceThreshold(List<TimeSeries> data) {
        double maxIG = 0;
        ArrayList<Double> dist = getRowDistances();
        ArrayList<Double> sortedDist = new ArrayList<>(dist);
        Collections.sort(sortedDist);
        for (int j = 0; j < sortedDist.size() - 1; j++) {
            double candTP = 0.0;
            double candFP = 0.0;
            double candTN = 0.0;
            double candFN = 0.0;
            double candidateThreshold = (sortedDist.get(j) + sortedDist.get(j + 1)) / 2.0;
            for (int i = 0; i < dist.size(); i++) {
                if (dist.get(i) <= candidateThreshold) {
                    if (data.get(i).getClassification().equalsIgnoreCase(getClassification())) {
                        candTP += 1.0;
                    } else {
                        candFP += 1.0;
                    }
                } else {
                    if (!data.get(i).getClassification().equalsIgnoreCase(getClassification())) {
                        candTN += 1.0;
                    } else {
                        candFN += 1.0;
                    }
                }
            }
            if (candTP == 0 || candTN == 0) {
                continue;
            }
            double eL = entropyHelper(candTP, candFP);
            double eR = entropyHelper(candTN, candFN);
            double candidateIG = Global.datasetEntropy(data)
                    - ((candTP + candFP) / data.size()) * eL - ((candTN + candFN) / data.size()) * eR;
            if (candidateIG > maxIG) {
                maxIG = candidateIG;
                setFn(candFN);
                setFp(candFP);
                setTn(candTN);
                setTp(candTP);
                setDistanceThreshold(candidateThreshold);
            }

        }
        return maxIG != 0;
    }

    public void calculateUtilityScore(List<TimeSeries> data) {
        ArrayList<Double> dist = getRowDistances();
        double mL = 0.0, mR = 0.0;
        for (int i = 0; i < dist.size(); i++) {
            if (dist.get(i) <= getDistanceThreshold()) {
                if (data.get(i).getClassification().equalsIgnoreCase(getClassification())) {
                    mL += dist.get(i);
                } else {
                    mL++;
                }
            } else {
                mR++;
            }
        }
        double eL = entropyHelper(getTp(), getFp());
        double eR = entropyHelper(getTn(), getFn());
        double weightedIG = Global.datasetEntropy(data) - (mL / data.size()) * eL - (mR / data.size()) * eR;
        setUtility(weightedIG);
    }

    public void calculateUtilityScore(List<TimeSeries> data, int index) {
        double wsup = 0.0;
        wsup = data.stream().filter((t) -> (t.contain(this, index))).map((t) -> 1.0 / calculateEMT(t, index)).reduce(wsup, (accumulator, _item) -> accumulator + _item);
        wsup /= data.size();
        double dataRightEntropy = entropyHelper(getTp(), getFp());
        double rUtility = Math.pow(Global.datasetEntropy(data) - dataRightEntropy, W);
        rUtility *= wsup;
        setUtility(rUtility);
    }

    public double calculateEMT(TimeSeries t, int index) {
        double result = Double.MAX_VALUE;
        double EMT = 0.0;
        if (getData().size() > t.getData().size()) {
            return result;
        }
        for (int k = 0; k < t.getData().size() - getData().size() + 1; k++) {
            Shapelet window = Shapelet.buildShapelet(t, k, getData().size());
            double candidateResult = distanceTwoEqualLength(window, index);
            if (candidateResult < result) {
                EMT = k + getData().size() - 1;
                result = candidateResult;
            }
        }
        return EMT;
    }
    
    private double entropyHelper(double c1, double c2){
        return (c2 != 0 ? -1.0 * Global.entropy(c1, c1 + c2) - 1.0 * Global.entropy(c2, c1 + c2)
                    : -1.0 * Global.entropy(c1, c1 + c2));
    }

    @Override
    public String toString() {
        String s = super.toString();
        s += "\n Row Distances: " + getRowDistances();
        s += "\n Delta: " + getDistanceThreshold();
        s += "\n Information [tp,fp,tn,fn]: [" + getTp() + ", " + getFp() + ", " + getTn() + ", " + getFn() + "]";
        s += "\n Utility: " + getUtility();
        return s;
    }

}
