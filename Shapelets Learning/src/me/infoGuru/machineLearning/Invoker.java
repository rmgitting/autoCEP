/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.infoGuru.machineLearning;

import java.util.List;

/**
 *
 * @author Raef M
 */
public interface Invoker {
    
    public void setExtractedShapelets(List<Shapelet> shapelets);
    
    public void signalException(String desc);
}
