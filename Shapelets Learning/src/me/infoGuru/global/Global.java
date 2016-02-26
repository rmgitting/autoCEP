/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.infoGuru.global;

import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import me.infoGuru.machineLearning.Classification;
import me.infoGuru.machineLearning.TimeSeries;

/**
 *
 * @author Raef M
 */
public class Global {
    
    
    public static void writeLog(JTextPane textPane, Color color, String text) {
        Style style = textPane.addStyle("Style", null);
        StyledDocument doc = textPane.getStyledDocument();
        StyleConstants.setForeground(style, color);
        
        try {
            doc.insertString(doc.getLength(), text + "\n", style);

        } catch (BadLocationException ex) {
            Logger.getLogger(Global.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static double datasetEntropy(List<TimeSeries> data) {
        double ent = 0.0;
        double[] dataC = new double[Classification.classification.size()];
        int i=0;
        for (String s : Classification.classification) {
            for (TimeSeries t : data) {
                if (s.equalsIgnoreCase(t.getClassification())) {
                    dataC[i]++;
                }
            }
            ent += entropy(dataC[i], data.size());
            i++;
        }
        return -ent;
    }

    public static double entropy(double d1, double d2) {
        double result = d1 / d2;
        result *= (Math.log((d1 / d2)) / Math.log(2));
        return result;
    }
}
