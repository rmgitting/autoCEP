/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.cep.gui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultStyledDocument;
import me.cep.esper.Esper;
import me.infoGuru.GUI.SaveToDatabaseDialog;
import me.infoGuru.GUI.ShapeletTrainerFrame;
import me.infoGuru.global.Global;
import me.infoGuru.machineLearning.Shapelet;

/**
 *
 * @author Raef M
 */
public class EsperFrame extends javax.swing.JFrame {

    /**
     * Creates new form EsperFrame
     */
    public EsperFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoTextPane = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        learnShapeletBtn = new javax.swing.JButton();
        transformShapeletsBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        getShapeletsBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        dispatchEventsBtn = new javax.swing.JButton();
        selectTestBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        quitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        clearMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Information"));

        jScrollPane1.setViewportView(infoTextPane);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuration"));

        learnShapeletBtn.setText("Learn Shapelets");
        learnShapeletBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                learnShapeletBtnActionPerformed(evt);
            }
        });

        transformShapeletsBtn.setText("Transform Shapelets into CEP Rules");
        transformShapeletsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transformShapeletsBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("OR");

        getShapeletsBtn.setText("Get Shapelets from a DB");
        getShapeletsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getShapeletsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(learnShapeletBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(getShapeletsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(transformShapeletsBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(getShapeletsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(learnShapeletBtn)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transformShapeletsBtn)
                .addGap(53, 53, 53))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Testing"));

        dispatchEventsBtn.setText("Dispatch Events");
        dispatchEventsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispatchEventsBtnActionPerformed(evt);
            }
        });

        selectTestBtn.setText("Select Test File");
        selectTestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectTestBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(220, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dispatchEventsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(selectTestBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(253, 253, 253))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(selectTestBtn)
                .addGap(18, 18, 18)
                .addComponent(dispatchEventsBtn)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        quitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quitMenuItem.setText("Quit");
        quitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(quitMenuItem);

        jMenuBar1.add(fileMenu);

        editMenu.setText("Edit");

        clearMenuItem.setText("Clear Information Pane");
        clearMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(clearMenuItem);

        jMenuBar1.add(editMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuItemActionPerformed

        this.dispose();
    }//GEN-LAST:event_quitMenuItemActionPerformed

    private void clearMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearMenuItemActionPerformed

        infoTextPane.setDocument(new DefaultStyledDocument());
    }//GEN-LAST:event_clearMenuItemActionPerformed

    private void learnShapeletBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_learnShapeletBtnActionPerformed
        Global.writeLog(infoTextPane, Color.black, "Opening the learning tool");
        Global.writeLog(infoTextPane, Color.black, "After the learning procedure ends, press the Transform Shapelets button");
        shapeletTrainer = new ShapeletTrainerFrame();
        shapeletTrainer.setVisible(true);
        shapeletTrainer.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        shapeletTrainer.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String ObjButtons[] = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null, "Make sure you transfer the shapelets to the CEP tool before closing by clicking on the Transform Shapelets into CEP Rules button, continue?", "Online Examination System", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                if (PromptResult == JOptionPane.YES_OPTION) {
                    shapeletTrainer.dispose();
                }
            }
        });
    }//GEN-LAST:event_learnShapeletBtnActionPerformed

    private void transformShapeletsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transformShapeletsBtnActionPerformed
        if (shapeletTrainer != null) {
            this.setShapelets(shapeletTrainer.getShapelets());
        }
        if (this.getShapelets() == null) {
            Global.writeLog(infoTextPane, Color.red, "There is no learned shapelets, you need to start the learning tool by clicking on the Learn Shapelets button, wait till the learning finishes, and then click this button");
        } else {
            Global.writeLog(infoTextPane, Color.black, "Shapelets extracted with success");
            Global.writeLog(infoTextPane, Color.black, "Transforming the shapelets...");
            esper = new Esper(this);
            esper.start();
            
        }
    }//GEN-LAST:event_transformShapeletsBtnActionPerformed

    private void dispatchEventsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dispatchEventsBtnActionPerformed
       
        if (getEsper() == null){
            Global.writeLog(infoTextPane, Color.red, "You need to transform the shapelets into CEP Rules first");
        }
        else if(getChosenFile() == null){
            Global.writeLog(infoTextPane, Color.red, "You need to select the test file first");
        } else {
            esper.notifyEsperThread(chosenFile);
        }
    }//GEN-LAST:event_dispatchEventsBtnActionPerformed

    private void selectTestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectTestBtnActionPerformed
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV files (*.csv)", "csv");
        fc.setFileFilter(csvFilter);
        fc.setDialogTitle("Choose your Test File");
        fc.setAcceptAllFileFilterUsed(false);
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            chosenFile = fc.getSelectedFile();                                   
        } else {
            System.out.println("User Canceled");
        }
    }//GEN-LAST:event_selectTestBtnActionPerformed

    private void getShapeletsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getShapeletsBtnActionPerformed

        GetFromDatabaseDialog std = new GetFromDatabaseDialog(this, true);
        std.setVisible(true);
    }//GEN-LAST:event_getShapeletsBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EsperFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EsperFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EsperFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EsperFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EsperFrame().setVisible(true);
        });
    }

    public List<Shapelet> getShapelets() {
        return shapelets;
    }

    public void setShapelets(List<Shapelet> shapelets) {
        this.shapelets = shapelets;
    }

    public JTextPane getInfoTextPane() {
        return infoTextPane;
    }

    public Esper getEsper() {
        return esper;
    }

    public void setEsper(Esper esper) {
        this.esper = esper;
    }

    public File getChosenFile() {
        return chosenFile;
    }

    public void setChosenFile(File chosenFile) {
        this.chosenFile = chosenFile;
    }
    
    
    
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem clearMenuItem;
    private javax.swing.JButton dispatchEventsBtn;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton getShapeletsBtn;
    private javax.swing.JTextPane infoTextPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton learnShapeletBtn;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JButton selectTestBtn;
    private javax.swing.JButton transformShapeletsBtn;
    // End of variables declaration//GEN-END:variables
    private ShapeletTrainerFrame shapeletTrainer;
    private List<Shapelet> shapelets;
    private Esper esper;
    private File chosenFile;
}
