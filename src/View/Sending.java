/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Email.MailSender;
import General.Configuration;
import General.Nimbus;
import db.Dbcon;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Jithinpv
 */
public class Sending extends javax.swing.JFrame {

    int history_id;
    File outputCipherFile;

    /**
     * Creates new form Sending
     */
    public Sending() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadValuesToTable();
    }

    public Sending(int history_id, File outputCipherFile) {
        Nimbus.loadLoogAndFeel();
        initComponents();
        this.setLocationRelativeTo(null);
        this.history_id = history_id;
        this.outputCipherFile = outputCipherFile;
        loadValuesToTable();
        Configuration.setIconOnLabel("blocks_green.jpg", main_label);
    }

    private void clearTable() {
        DefaultTableModel dm = (DefaultTableModel) user_table.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }

    private void loadValuesToTable() {
        clearTable();
        try {
            String sql = "select * from tbl_user_details";
            System.out.println(sql);
            ResultSet rs = new Dbcon().select(sql);
            DefaultTableModel model = (DefaultTableModel) user_table.getModel();
            Object arr[] = new Object[6];
            int count = 0;
            while (rs.next()) {
                String user_name = rs.getString("user_name");
                String email_id = rs.getString("email_id");
                String phone_number = rs.getString("phone_number");
                String user_id = rs.getString("user_id");

                arr[0] = (++count) + "";
                arr[1] = user_name;
                arr[2] = email_id;
                arr[3] = phone_number;
                arr[4] = false;
                arr[5] = user_id;

                model.addRow(arr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        jButton1 = new javax.swing.JButton();
        send_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        user_table = new javax.swing.JTable();
        progress_bar = new javax.swing.JProgressBar();
        jButton3 = new javax.swing.JButton();
        main_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Select Recepients");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 18, 166, -1));

        jButton1.setText("HOME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 209, -1, -1));

        send_button.setText("SEND");
        send_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(send_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 209, 68, -1));

        user_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "User name", "Email Id", "Phone number", "Send", "userid"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(user_table);
        user_table.getColumnModel().getColumn(0).setMinWidth(50);
        user_table.getColumnModel().getColumn(0).setPreferredWidth(50);
        user_table.getColumnModel().getColumn(0).setMaxWidth(50);
        user_table.getColumnModel().getColumn(4).setMinWidth(50);
        user_table.getColumnModel().getColumn(4).setPreferredWidth(50);
        user_table.getColumnModel().getColumn(4).setMaxWidth(50);
        user_table.getColumnModel().getColumn(5).setMinWidth(0);
        user_table.getColumnModel().getColumn(5).setPreferredWidth(0);
        user_table.getColumnModel().getColumn(5).setMaxWidth(0);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 43, 522, 155));
        getContentPane().add(progress_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 250, 522, 25));

        jButton3.setText("SEND VIA EMAIL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 209, 131, -1));

        main_label.setText("jLabel2");
        getContentPane().add(main_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 580, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Home home = new Home();
        home.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void sendFile(int toUserId) {
        Configuration.loadUserFileTransferLocation(Configuration.fileTransfers + toUserId);
        //outputCipherFile
        boolean sendFile = sendFile(outputCipherFile, new File(Configuration.fileTransfers + toUserId + "/" + outputCipherFile.getName()));
        int insert = new Dbcon().insert("insert into tbl_transactions (sender_id, received_id, file, transaction_date, history_id,send) values (" + Login.logged_in_user_id + "," + toUserId + " ,  '" + outputCipherFile.getName() + "' , '" + System.currentTimeMillis() + "' , " + history_id + ",1)");
        System.out.println("Insert " + insert);
    }

    private boolean sendFile(File fromFile, File toFile) {
        System.out.println("From file " + fromFile.getAbsolutePath());
        System.out.println("To file " + toFile.getAbsolutePath());

        boolean sendSucess = false;
        try {
            FileUtils.copyFile(fromFile, toFile);
            sendSucess = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendSucess;
    }

    class sendFileThread extends Thread {

        public void run() {
            try {
                DefaultTableModel dtm = (DefaultTableModel) user_table.getModel();
                int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();

                double chunk = 100 / nRow;
                double chunkSum = 0;

                for (int i = 0; i < nRow; i++) {
                    boolean isSelected = (boolean) dtm.getValueAt(i, 4);
                    if (isSelected) {
                        System.out.println(dtm.getValueAt(i, 1) + " is selected");
                        int userId = Integer.parseInt(dtm.getValueAt(i, 5).toString());
                        sendFile(userId);
                        Thread.sleep(1000);
                    }
                    progress_bar.setValue((int) chunkSum);
                    chunkSum = chunkSum + chunk;
                }
                progress_bar.setValue(100);
                JOptionPane.showMessageDialog(rootPane, "Send successfully...");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void send_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) user_table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Boolean count = false;
        for (int i = 0; i < nRow; i++) {
            boolean isSelected = (boolean) dtm.getValueAt(i, 4);
            if (isSelected) {
                count = true;
                new sendFileThread().start();
                send_button.setEnabled(false);
            }

        }
        if (count == false) {
            JOptionPane.showMessageDialog(rootPane, "Select user");
        }

    }//GEN-LAST:event_send_buttonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Dbcon dbcon = new Dbcon();
        ResultSet rs = dbcon.select("select * from tbl_user_details");
        try {
            while (rs.next()) {
                String email = rs.getString(3);
                System.out.println(email);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_formWindowOpened

    class MailSenderThread extends Thread {

        String[] recepients = new String[1];

        public MailSenderThread(String recepients) {
            this.recepients[0] = recepients;

        }

        public void start() {
            Dbcon dbcon = new Dbcon();
            String password = "";
            String algorithmId = "";
            String cover_file_name = "";
            ResultSet rs = dbcon.select("select password, encryption_algorithm_id , cover_file_name from tbl_file_process_history where history_id='" + history_id + "'");
            try {
                if (rs.next()) {
                    password = rs.getString(1);
                    algorithmId = rs.getString(2);
                    cover_file_name = rs.getString(3);
                    algorithmId  = algorithmId.trim();
                    
                    if(algorithmId.equals("1")) {
                        algorithmId = "DES";
                    } else if(algorithmId.equals("2")) {
                        algorithmId = "TDES";
                    } else {
                        algorithmId = "RSA";
                    }
                    System.out.println(password);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Starting mail sending");
            System.out.println(password + " algo id = " + algorithmId);
            
            MailSender.sendFromGMail(recepients, Configuration.sendImageSubject + " " + System.currentTimeMillis(), "Hi, File named " + cover_file_name + " is encrypted using " + algorithmId + " with password " + password );
            //MailSender.sendFromGMail(recepients, Configuration.sendImageSubject + " " + System.currentTimeMillis(), "Data from particular user", outputCipherFile.getPath(), password);

            System.out.println("Send sucess");
            JOptionPane.showMessageDialog(null, "success");
        }
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) user_table.getModel();
        Dbcon dbcon = new Dbcon();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        for (int i = 0; i < nRow; i++) {
            boolean isSelected = (boolean) dtm.getValueAt(i, 4);
            if (isSelected) {
                System.out.println(dtm.getValueAt(i, 1) + " is selected");
                int userId = Integer.parseInt(dtm.getValueAt(i, 5).toString());


                ResultSet rs = dbcon.select("select * from tbl_user_details where user_id='" + userId + "'");
                try {
                    if (rs.next()) {
                        String receiver_id = rs.getString("email_id");
                        System.out.println(receiver_id);
                        MailSenderThread mailSenderThread = new MailSenderThread(receiver_id);
                        mailSenderThread.start();
                        int ins = dbcon.insert("insert into tbl_transactions(sender_id, received_id,file,send, transaction_date,history_id) values (" + Login.logged_in_user_id + " ,' " + userId + "' ,'" + outputCipherFile.getName() + "',1, '" + System.currentTimeMillis() + "'," + history_id + ")");
                        if (ins > 0) {
                            System.out.println("inserted");

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Sending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Sending().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel main_label;
    private javax.swing.JProgressBar progress_bar;
    private javax.swing.JButton send_button;
    private javax.swing.JTable user_table;
    // End of variables declaration//GEN-END:variables
}
