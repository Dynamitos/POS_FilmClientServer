package gui;

import beans.Film;
import bl.FilmTableModel;
import client.DocumentClient;
import java.util.List;
import javax.swing.DefaultListModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.XmlTester;

public class FilmClientGui extends javax.swing.JFrame {

    DefaultListModel<String> lModel;
    FilmTableModel tModel;
    
    public FilmClientGui() {
        try {
            initComponents();
            List<String> kategorien = loadCategoriesFromFile();
            lModel = new DefaultListModel<>();
            for (String s : kategorien) {
                lModel.addElement(s);
            }
            liCategories.setModel(lModel);
            tModel = new FilmTableModel();
            taFilms.setModel(tModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private List<String> loadCategoriesFromFile() throws Exception{
        return DocumentClient.getKategorien();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taLog = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        liCategories = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        taFilms = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Film Client");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Log Information"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        taLog.setColumns(20);
        taLog.setRows(5);
        jScrollPane1.setViewportView(taLog);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setLayout(new java.awt.BorderLayout());

        liCategories.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        liCategories.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                onListSelectionChanged(evt);
            }
        });
        jScrollPane2.setViewportView(liCategories);

        jSplitPane1.setLeftComponent(jScrollPane2);

        taFilms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(taFilms);

        jSplitPane1.setRightComponent(jScrollPane3);

        jPanel2.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onListSelectionChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_onListSelectionChanged
        String kategorie = liCategories.getSelectedValue();
        tModel.clear();
        try {
            Document doc = DocumentClient.getDom(kategorie);
            Element root = doc.getDocumentElement();
            NodeList nodes = root.getElementsByTagName("film");
            for(int i = 0; i < nodes.getLength(); i++) {
                Node n = nodes.item(i);
                if(n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;
                    String desc = e.getElementsByTagName("description").item(0).getTextContent();
                    String lang = e.getElementsByTagName("language").item(0).getTextContent();
                    Double price = Double.parseDouble(e.getElementsByTagName("price").item(0).getTextContent());
                    String title = e.getElementsByTagName("title").item(0).getTextContent();
                    int year = Integer.parseInt(e.getElementsByTagName("year").item(0).getTextContent());
                    tModel.add(new Film(title, desc, lang, price, year));
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_onListSelectionChanged

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
            java.util.logging.Logger.getLogger(FilmClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FilmClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FilmClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FilmClientGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FilmClientGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList<String> liCategories;
    private javax.swing.JTable taFilms;
    private javax.swing.JTextArea taLog;
    // End of variables declaration//GEN-END:variables
}
