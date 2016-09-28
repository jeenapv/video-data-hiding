/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import EncryptionAlgorithms.DesEncrypter;
import EncryptionAlgorithms.RSAEncryptor;
import EncryptionAlgorithms.TrippleDes;
import General.Configuration;
import General.Nimbus;
import Security.Encryption;
import Security.VedioByLoader;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Jithinpv
 */
public class Decrypt extends javax.swing.JFrame {

    File fileToBeDecrypted;
    int encryption_file_type;
    String password;
    boolean isCompleted = false;
    boolean isSuccess = false;
    String decryptedData;
    String errorMesage = "";
    String temporaryFilePath;
    int encryption_algorithm_id;

    /**
     * Creates new form Decrypt
     */
    public Decrypt() {
        initComponents();
        this.setLocationRelativeTo(null);
        Configuration.setIconOnLabel("encrypt.jpg", jLabel2);
    }

    public Decrypt(File fileToBeDecrypted, int encryption_file_type, String password, int encryption_algorithm_id) {
        Nimbus.loadLoogAndFeel();
        initComponents();
        this.setLocationRelativeTo(null);
        this.fileToBeDecrypted = fileToBeDecrypted;
        this.encryption_file_type = encryption_file_type;
        this.password = password;
        this.encryption_algorithm_id = encryption_algorithm_id;
        open_decypt_file_button.setEnabled(false);
        Configuration.setIconOnLabel("encrypt.jpg", jLabel2);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        progress_bar = new javax.swing.JProgressBar();
        processing_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        message_area = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        start_button = new javax.swing.JButton();
        open_decypt_file_button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Decrypting Status");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 71, 110, -1));
        getContentPane().add(progress_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 63, 225, 22));

        processing_label.setText("Process not started");
        getContentPane().add(processing_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 91, 225, -1));

        message_area.setEditable(false);
        message_area.setColumns(1);
        message_area.setRows(5);
        jScrollPane1.setViewportView(message_area);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 150, 142));

        jButton1.setText("HOME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        start_button.setText("START");
        start_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(start_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 11, -1, 41));

        open_decypt_file_button.setText("Open decrpted file");
        open_decypt_file_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open_decypt_file_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(open_decypt_file_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 170, -1));

        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 410, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Home home = new Home();
        home.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    class progressBarThread extends Thread {

        public void run() {
            int val = 0;
            try {
                progress_bar.setValue(val);
                while (val < 100) {
                    processing_label.setText("Data retreival percentage - " + val + " %");
                    progress_bar.setValue(++val);
                    Thread.sleep(100);

                }
                while (!isCompleted) {
                    System.out.println("Chekcing is complete " + isCompleted);
                    Thread.sleep(1000);
                }
                if (isSuccess) {
                    processing_label.setText("Data retreival completed!");
                    JOptionPane.showMessageDialog(rootPane, "Sucessfully retreved data!");
                    
                    message_area.setText(decryptedData);
                    if (encryption_file_type == 1) {
                        open_decypt_file_button.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, errorMesage);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String decryptMessage(String message) {
       
        open_decypt_file_button.setVisible(false);
        String plainText = "";
        System.out.println("Message received as : " + message);
        System.out.println("encryption_algorithm_id" + encryption_algorithm_id);
         
        switch (encryption_algorithm_id) {
            case 1:
                //des
                System.out.println("Trying to decrpt with DES");
                try {
                    String desKey = "0123456789abcdef";
                    byte[] keyBytes = DatatypeConverter.parseHexBinary(desKey);
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
                    SecretKey key = factory.generateSecret(new DESKeySpec(keyBytes));
                    DesEncrypter encrypter = new DesEncrypter(key);
                    plainText = encrypter.decrypt(message);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Trying to decrpt with TRIPPLE DES");
                try {
                    TrippleDes trippleDes = new TrippleDes();
                    plainText = trippleDes.decrypt(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Trying to decrpt with RSA");
                try {
                    ObjectInputStream inputStream = null;
                    inputStream = new ObjectInputStream(new FileInputStream(RSAEncryptor.PRIVATE_KEY_FILE));
                    final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
                    plainText = RSAEncryptor.decrypt(message, privateKey);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            default:
                // Do nothihng
                break;
        }

        return plainText;
    }

    class DecrptyThread extends Thread {

        public void run() {
            try {
                 open_decypt_file_button.setVisible(true);
                message_area.setVisible(false);
                System.out.println("Starting decrption thread " + encryption_file_type);
                switch (encryption_file_type) {
                    case 0:
                        // decrpt text
                        System.out.println("Going to decrypt");
                        Encryption encryption = new Encryption(fileToBeDecrypted);
                        if (!encryption.checkForbiddenZone()) {
                            JOptionPane.showMessageDialog(rootPane, "Could not find any embedded data in the video");
                        } else {
                            System.out.println("Start processing the image file");
                            isCompleted = false;
                            isSuccess = false;
                            new progressBarThread().start();
                            try {
                                decryptedData = VedioByLoader.retrieveMessage(encryption, password);

                                decryptedData = decryptMessage(decryptedData);
                                System.out.println(decryptedData);
                                isSuccess = true;

                            } catch (Exception e) {
                                errorMesage = "Sorry, wrong password";
                                //e.printStackTrace();
                            }
                            isCompleted = true;

                        }

                        break;
                    case 1:
                        // decrypt file
                        Encryption encryption_file = new Encryption(fileToBeDecrypted);
                        if (!encryption_file.checkForbiddenZone()) {
                            JOptionPane.showMessageDialog(rootPane, "Could not find any embedded data in the video");
                        } else {
                            try {
                                isCompleted = false;
                                isSuccess = false;
                                new progressBarThread().start();
                                File retrieveFileLocation = new File(Configuration.tempFiles + System.currentTimeMillis());
                                if (retrieveFileLocation.mkdir()) {
                                    File retrieveFile = VedioByLoader.retrieveFile(encryption_file, password, true, retrieveFileLocation.getAbsolutePath());
                                    if (retrieveFile != null) {
                                        isSuccess = true;
                                        temporaryFilePath = retrieveFileLocation.getAbsolutePath();
                                    } else {
                                        errorMesage = "Incorrect password";
                                    }
                                }
                            } catch (Exception e) {
                                errorMesage = "Incorrect password";
                            }
                            isCompleted = true;
                        }
                        break;
                    default:
                        // do nothing
                        break;
                }

                message_area.setVisible(true);
                //VedioByLoader.retrieveFile(null, null, null, null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

private void start_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_buttonActionPerformed

    isCompleted = false;
    isSuccess = false;
    start_button.setEnabled(false);
    new DecrptyThread().start();
    // TODO add your handling code here:
}//GEN-LAST:event_start_buttonActionPerformed

private void open_decypt_file_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_open_decypt_file_buttonActionPerformed
    try {
        System.out.println("ReceiveHistory.encryption_algorithm_id " + ReceiveHistory.encryption_algorithm_id);
        System.out.println("encryption_algorithm_id " + encryption_algorithm_id);
        if (ReceiveHistory.encryption_algorithm_id.trim().equals(encryption_algorithm_id + "".trim())) {
            Desktop.getDesktop().open(new File(temporaryFilePath));
        } else {
            JOptionPane.showMessageDialog(rootPane, "Could not open the file");
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}//GEN-LAST:event_open_decypt_file_buttonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        message_area.setVisible(false);
         open_decypt_file_button.setVisible(false);
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
            java.util.logging.Logger.getLogger(Decrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Decrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Decrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Decrypt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Decrypt().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea message_area;
    private javax.swing.JButton open_decypt_file_button;
    private javax.swing.JLabel processing_label;
    private javax.swing.JProgressBar progress_bar;
    private javax.swing.JButton start_button;
    // End of variables declaration//GEN-END:variables
}
