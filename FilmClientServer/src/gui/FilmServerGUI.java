package gui;

import server.FilmServer;

public class FilmServerGUI extends javax.swing.JFrame {

    private FilmServer server;

    public FilmServerGUI() {
        initComponents();
        onStart(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btStart = new javax.swing.JButton();
        btStop = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Film Server");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Server Action"));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btStart.setText("Start Server");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onStart(evt);
            }
        });
        jPanel1.add(btStart);

        btStop.setText("Stop Server");
        btStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onStop(evt);
            }
        });
        jPanel1.add(btStop);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Log Information"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        taLog.setColumns(20);
        taLog.setRows(5);
        jScrollPane1.setViewportView(taLog);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onStart(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onStart
        if (server == null) {
            server = new FilmServer(9999, taLog);
            server.startServer();
        }
    }//GEN-LAST:event_onStart

    private void onStop(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onStop
        if (server != null) {
            server.stopServer();
        }
    }//GEN-LAST:event_onStop

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
            java.util.logging.Logger.getLogger(FilmServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FilmServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FilmServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FilmServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FilmServerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btStart;
    private javax.swing.JButton btStop;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taLog;
    // End of variables declaration//GEN-END:variables
}
