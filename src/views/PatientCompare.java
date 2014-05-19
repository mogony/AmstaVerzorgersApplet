package views;

import connectivity.QueryManager;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ldgraph.LDGraph;
import models.Patient;

/**
 * This form retrieves a list of patients and shows them in a table.
 * @author Riekelt Goucem, AMS04
 */
public class PatientCompare extends javax.swing.JFrame {
    private DefaultTableModel patientTableModel;
    
    /**
     * Creates new form PatientOverview
     */
    public PatientCompare() {
        initComponents();
        this.setTitle(ldgraph.Session.storedUserName + " - Bewoners overzicht");
        updateTable();
    }
    
    /**
     * This method retrieves a list of all patients and sets up a table containing
     * all retrieved data.
     */
    private void updateTable() {
        System.out.println("Setting up patients overview for compare");
        patientTableModel = (DefaultTableModel) this.patientTable.getModel();
        LinkedList<Patient> patients = QueryManager.getPatientList();
        patientTableModel.setRowCount(0);
        for(Patient patient : patients) {
            patientTableModel.addRow(new Object[]{
                patient.getPatientId(),
                patient.getName()});
        }
        System.out.println("Done setting up patients overview.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        patientTable = new javax.swing.JTable();
        jlError = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        patientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Bewoner"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(patientTable);
        if (patientTable.getColumnModel().getColumnCount() > 0) {
            patientTable.getColumnModel().getColumn(0).setMaxWidth(20);
        }

        jButton2.setText("Compare");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(163, 163, 163))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlError)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jlError)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = patientTable.getSelectedRow();
        if(selectedRow >= 0) 
        {
            ldgraph.Session.storedPatientId2 = 
                    (int) patientTable.getValueAt(selectedRow, 0);
            ldgraph.Session.storedPatientName2 = 
                     (String) patientTable.getValueAt(selectedRow, 1);
            this.setVisible(false);
            LDGraph.showPatientCompareGraph();
            
        } else 
        {
            System.out.println("No row selected!");
            jlError.setText("U moet eerst een regel selecteren!");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlError;
    private javax.swing.JTable patientTable;
    // End of variables declaration//GEN-END:variables
}