/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.cep.esper;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esperio.AdapterInputSource;
import com.espertech.esperio.csv.CSVInputAdapter;
import com.espertech.esperio.csv.CSVInputAdapterSpec;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.cep.gui.EsperFrame;
import me.infoGuru.global.Global;
import me.infoGuru.machineLearning.Shapelet;

/**
 *
 * @author Raef M
 */
public class Esper extends Thread {

    public Esper(EsperFrame esperFrame) {
        this.esperFrame = esperFrame;
    }

    private void configureEngine() {
        Configuration config = new Configuration();
        config.addEventTypeAutoName("esper.example");
        config.getEngineDefaults().getThreading().setInternalTimerEnabled(false);

        Map<String, Object> typeMap = new HashMap<>();
        typeMap.put("Time", String.class);
        typeMap.put("Value", Double.class);
        config.addEventType("SensorReading", typeMap);

        epService = EPServiceProviderManager.getDefaultProvider(config);
        admin = epService.getEPAdministrator();
        // cepShapelets = getViolatedShapelets(esperFrame.getShapelets());
        cepShapelets = getAllShapelets(esperFrame.getShapelets());
        this.registerQuery();
        Global.writeLog(esperFrame.getInfoTextPane(), Color.black, "Transformation Complete");
    }

    private EPServiceProvider epService;
    private EPAdministrator admin;
    private final EsperFrame esperFrame;
    private List<CEPShapelet> cepShapelets;
    private File eventSource;

    private void registerQuery() {
        String query = "select * from SensorReading";
        final EPStatement statement = admin.createEPL(query);
        statement.addListener((EventBean[] events, EventBean[] arg1) -> {
            if (events != null) {
                Map inMap = (Map) events[0].getUnderlying();
                String time = inMap.get("Time").toString();
                Double value = Double.parseDouble(inMap.get("Value").toString());
                handle(time, value);
            }

        });
    }

    @Override
    public void run() {
        this.configureEngine();
        while (true) {
            synchronized (this) {
                try {
                    this.wait();
                    if (eventSource != null) {
                        bestMatch = Double.MAX_VALUE;
                        violationTime = "";
                        classification = "";
                        dispatchEvents();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(EsperFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    private void handle(String time, Double value) {
        boolean match;
        for (CEPShapelet s : cepShapelets) {
            s.add(time, value);
            match = s.existSimilarity();
            System.out.println("Matching with shapelet: the similarity = " + s.calculateDistance());
            System.out.println("delta = " + s.getDelta());
            System.out.println("class = " + s.getClassification());
            System.out.println("Match = " + match);
            if (match) {
                double matchValue = s.calculateDistance();
                if (matchValue < bestMatch) {
                    bestMatch = matchValue;
                    violationTime = s.getTimes().get(s.getTimes().size() - 1);
                    classification = s.getClassification();
                }
            }
        }

    }

    public void notifyEsperThread(File eventSource) {
        this.eventSource = eventSource;
        //    admin.startAllStatements();
        //    bestMatch = Double.MAX_VALUE;
        cepShapelets.stream().forEach((s) -> {
            s.getTimes().clear();
            s.getValues().clear();
        });
        synchronized (this) {
            this.notify();
        }
    }

    private void dispatchEvents() {
        AdapterInputSource adapterInputSource = new AdapterInputSource(eventSource);
        CSVInputAdapterSpec spec = new CSVInputAdapterSpec(adapterInputSource, "SensorReading");
        (new CSVInputAdapter(epService, spec)).start();
        Global.writeLog(esperFrame.getInfoTextPane(), Color.black, "Dispatching Ended");
        if (!classification.equals("")) {
            Global.writeLog(esperFrame.getInfoTextPane(), Color.red, "Predicted at: " + violationTime);
            Global.writeLog(esperFrame.getInfoTextPane(), Color.red, "Class: " + classification);
            System.out.println("Predicted at time: " + violationTime);
            //   admin.stopAllStatements();
        }
    }

    private List<CEPShapelet> getViolatedShapelets(List<Shapelet> shapelets) {
        List<CEPShapelet> results = new ArrayList<>();
        shapelets.stream().forEach((s) -> {
            if (s.getClassification().equalsIgnoreCase("VIOLATED")) {
                results.add(new CEPShapelet(s));
            }
        });
        return results;
    }

    private List<CEPShapelet> getAllShapelets(List<Shapelet> shapelets) {
        List<CEPShapelet> results = new ArrayList<>();
        shapelets.stream().forEach((s) -> {
            results.add(new CEPShapelet(s));
        });
        return results;
    }

    private double bestMatch;
    private String violationTime;
    private String classification;
}
