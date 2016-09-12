/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import General.Configuration;
import Security.FileBitPack;
import Security.TextBitPack;
import Security.VedioByLoader;
import db.Dbcon;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Jithinpv
 */
public class Processing extends javax.swing.JFrame {

    int history_id;
    File coverFile;
    String password;
    int id;
    boolean isCompleted = false;
    boolean isSuccess = false;
    File outputCipherFile;

    /**
     * Creates new form Processing
     */
    public Processing() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public Processing(int history_id, File coverFile, String password, int id) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.history_id = history_id;
        this.coverFile = coverFile;
        this.password = password;
        this.id = id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        progress_bar = new javax.swing.JProgressBar();
        processing_label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        start_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Processing");

        processing_label.setText("Processing Details");

        jButton1.setText("SEND");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("HOME");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        start_button.setText("Start");
        start_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(start_button, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(43, 43, 43))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(processing_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                    .addComponent(progress_bar, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(start_button, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progress_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(processing_label)
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Sending sending = new Sending(history_id, outputCipherFile);
        sending.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Home home = new Home();
        home.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    class progressBarThread extends Thread {

        public void run() {
            int val = 0;
            try {
                progress_bar.setValue(val);
                while (val <= 100) {
                    progress_bar.setValue(++val);
                    processing_label.setText("Data encryption percentage - " + val + " %");
                    Thread.sleep(100);

                }
                while (!isCompleted) {
                    System.out.println("Chekcing is complete " + isCompleted);
                    Thread.sleep(1000);
                }
                if (isSuccess) {
                    JOptionPane.showMessageDialog(rootPane, "Sucessfully embeded!");
                    jButton1.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Failed! Please try again");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String encryptMessage(String message) {
        String cipher = null;
        try {
            String encptionAlgorithm = "DES";
            if (id == 1) {
            }
            SecretKey key = KeyGenerator.getInstance(encptionAlgorithm).generateKey();
            
            
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return cipher;
    }

    
    class videoEncryptionThread extends Thread {

        public void run() {
            new progressBarThread().start();
            int type = InputContent.encryptionDataType;
            isCompleted = false;
            isSuccess = false;
            boolean result = false;
            switch (type) {
                case 0: // Message encryption
                    TextBitPack textBitPack = new TextBitPack();
                    textBitPack.setCoverFile(coverFile);
                    textBitPack.setCompression(50);

                    String cipher = InputContent.message;
                    //kakes
                    textBitPack.setMsg(cipher);
                    textBitPack.setPassword(password);
                    outputCipherFile = new File(Configuration.videoPool + "hash_t_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(coverFile.getPath()));
                    textBitPack.setOutputFile(outputCipherFile);

                    result = VedioByLoader.embedMessage(textBitPack);
                    isCompleted = true;
                    if (result) {
                        isSuccess = true;
                        new Dbcon().update("update tbl_file_process_history set cipher_file = '" + outputCipherFile.getName() + "' , cipher_file_name='" + outputCipherFile.getName() + "' , cipher_file_size=" + (outputCipherFile.length() / 1024) + " , encryption_complete_time ='" + System.currentTimeMillis() + "',file_processed_date='" + System.currentTimeMillis() + "'   where history_id = " + history_id);
                    }
                    break;
                case 1: // File encryption
                    FileBitPack fileBitPack = new FileBitPack();
                    fileBitPack.setCompression(50);
                    fileBitPack.setCovelFile(coverFile);
                    fileBitPack.setDataFile(InputContent.fileToBeEmbedded);
                    outputCipherFile = new File(Configuration.videoPool + "hash_f_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(coverFile.getPath()));
                    fileBitPack.setOutputFile(outputCipherFile);
                    fileBitPack.setPassword(password);

                    result = VedioByLoader.embedFile(fileBitPack);
                    isCompleted = true;
                    if (result) {
                        isSuccess = true;
                        new Dbcon().update("update tbl_file_process_history set cipher_file = '" + outputCipherFile.getName() + "' , cipher_file_name='" + outputCipherFile.getName() + "' , cipher_file_size=" + (outputCipherFile.length() / 1024) + " , encryption_complete_time ='" + System.currentTimeMillis() + "',file_processed_date='" + System.currentTimeMillis() + "'  where history_id = " + history_id);
                    }
                    break;
                default:
                    // do nothing
                    break;
            }
        }
    }

private void start_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_buttonActionPerformed

    start_button.setEnabled(false);
    new videoEncryptionThread().start();
    
    // TODO add your handling code here:
}//GEN-LAST:event_start_buttonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        jButton1.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(Processing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Processing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Processing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Processing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Processing().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel processing_label;
    private javax.swing.JProgressBar progress_bar;
    private javax.swing.JButton start_button;
    // End of variables declaration//GEN-END:variables
}
