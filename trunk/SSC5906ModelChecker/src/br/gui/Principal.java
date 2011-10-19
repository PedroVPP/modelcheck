/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 17/10/2011, 21:28:48
 */

package br.gui;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.gui.util.TableModelDefine;
import br.gui.util.TableModelProperties;
import br.gui.util.TableModelState;
import br.mef.Define;
import br.mef.Property;
import br.mef.State;

/**
 *
 * @author Vânia
 */
public class Principal extends javax.swing.JFrame {


    private State state = null;
    private ArrayList<State> states = null;
    private Property property = null;
    private ArrayList<Property> properties = null;
    private Define define = null;
    private ArrayList<Define> defines = null;


    /** Creates new form Principal */
    public Principal() {
        initComponents();
        inicializa();
    }

   
    private void btnDelStatesActionPerformed(java.awt.event.ActionEvent evt) {
        int[] indices = jtStates.getSelectedRows();
        for (int i : indices){
            this.states.remove(((TableModelState)jtStates.getModel()).getValores(i));
            //((TableModelProperties)jtProperties.getModel()).removeRow(((TableModelProperties)jtProperties.getModel()).getValores(i));
        }
        ((TableModelState)jtStates.getModel()).fireTableDataChanged();
        ((TableModelState)jtStates2.getModel()).fireTableDataChanged();
        //this.desfazSelecao();
    }

    private void btnAddStatesActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.state == null){
            State newState = new State(txtState.getText());
            this.states.add(newState);
            //((TableModelState)jtStates.getModel()).addRow(newState);
            //((TableModelState)jtStates2.getModel()).addRow(newState);
        }
        else{
            state.setName(txtState.getText());
            //this.desfazSelecao();
        }
        ((TableModelState)jtStates.getModel()).fireTableDataChanged();
        ((TableModelState)jtStates2.getModel()).fireTableDataChanged();        
        txtState.setText("");
    }


    private void jtfDiretorioActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jbtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {
	    try{
	        JFileChooser jfc = new JFileChooser();
	        int returnVal = jfc.showOpenDialog(this);
	        if (returnVal == jfc.APPROVE_OPTION) {
	            File file = jfc.getSelectedFile();
	            jtfDiretorio.setText(file.getAbsolutePath());
	        }        
	    }
	    catch(java.lang.Exception e){
	        JOptionPane.showMessageDialog(null,"Erro ao selecionar arquivo.","Alerta",JOptionPane.WARNING_MESSAGE);
	    }
    }

    private void btnDelPropertiesActionPerformed(java.awt.event.ActionEvent evt) {
        int[] indices = jtProperties.getSelectedRows();
        for (int i : indices){
            this.properties.remove(((TableModelProperties)jtProperties.getModel()).getValores(i));
            //((TableModelProperties)jtProperties.getModel()).removeRow(((TableModelProperties)jtProperties.getModel()).getValores(i));
        }
        ((TableModelProperties)jtProperties.getModel()).fireTableDataChanged();
        //this.desfazSelecao();
    }

    private void btnAddPropertiesActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.property == null){
            Property newProperty = new Property(txtProperties.getText());
            this.properties.add(newProperty); 
            //((TableModelProperties)jtProperties.getModel()).addRow(newProperty);
        }
        else{
            state.setName(txtState.getText());
            
            //this.desfazSelecao();
        }
        ((TableModelProperties)jtProperties.getModel()).fireTableDataChanged();
        txtProperties.setText("");
    }

    private void jbDefineActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.define == null){
            Define newDefine = new Define();
            int indice = jtStates.getSelectedRow();            	
            newDefine.setState(((TableModelState)jtStates.getModel()).getValores(indice));
            
            int[] indProperties = jtProperties.getSelectedRows();
            for (int i : indProperties){
            	newDefine.addProperty(((TableModelProperties)jtProperties.getModel()).getValores(i));
            }            
            
            int[] indStates = jtStates2.getSelectedRows();
            for (int i : indStates){
            	newDefine.addState(((TableModelState)jtStates2.getModel()).getValores(i));
            }            
            
            this.defines.add(newDefine); 
            //((TableModelProperties)jtProperties.getModel()).addRow(newProperty);
        }
        ((TableModelDefine)jtDefine.getModel()).fireTableDataChanged();
    }


    private void jbImportActionPerformed(java.awt.event.ActionEvent evt) {
        //código import
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }


   public void setStates(ArrayList<State> states){
       try {
    	   this.states = states;
           TableModelState modelo = new TableModelState(states);
           jtStates.setModel(modelo);
           jtStates2.setModel(modelo);

       }
        catch(java.lang.Exception e){
            JOptionPane.showMessageDialog(null,"Error when initialize States.","Alert",JOptionPane.WARNING_MESSAGE);
        }
   }

   public void setProperties(ArrayList<Property> properties){
       try {
    	   this.properties = properties;
           TableModelProperties modelo = new TableModelProperties(properties);
           jtProperties.setModel(modelo);

       }
        catch(java.lang.Exception e){
            JOptionPane.showMessageDialog(null,"Error when initialize Properties.","Alert",JOptionPane.WARNING_MESSAGE);
        }
   }

   public void setDefines(ArrayList<Define> defines){
       try {
    	   this.defines = defines;
           TableModelDefine modelo = new TableModelDefine(defines);
           jtDefine.setModel(modelo); 

       }
        catch(java.lang.Exception e){
            JOptionPane.showMessageDialog(null,"Error when initialize Defines.","Alert",JOptionPane.WARNING_MESSAGE);
        }
   }
   
   public void inicializa(){
	   this.setStates(new ArrayList(0));
	   this.setProperties(new ArrayList(0));
	   this.setDefines(new ArrayList(0));
	   
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private static javax.swing.JButton btnAddProperties;
   private static javax.swing.JButton btnAddStates;
   private static javax.swing.JButton btnDelProperties;
   private static javax.swing.JButton btnDelStates;
   private javax.swing.JLabel jLabel31;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JScrollPane jScrollPane4;
   private javax.swing.JSeparator jSeparator1;
   private javax.swing.JSeparator jSeparator2;
   private javax.swing.JTabbedPane jTabbedPane1;
   private javax.swing.JButton jbDefine;
   private javax.swing.JButton jbImport;
   private javax.swing.JToggleButton jbtPesquisar;
   private javax.swing.JTable jtProperties;
   private javax.swing.JTable jtStates;
   private javax.swing.JTable jtDefine;
   private javax.swing.JTable jtStates2;
   private javax.swing.JTextField jtfDiretorio;
   private javax.swing.JPanel pnlStates;
   private javax.swing.JPanel pnlStates1;
   private javax.swing.JPanel pnlStates2;
   private javax.swing.JPanel pnlStates3;
   private javax.swing.JTextField txtProperties;
   private javax.swing.JTextField txtState;


   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

       jTabbedPane1 = new javax.swing.JTabbedPane();
       jPanel1 = new javax.swing.JPanel();
       pnlStates = new javax.swing.JPanel();
       txtState = new javax.swing.JTextField();
       btnDelStates = new javax.swing.JButton();
       btnAddStates = new javax.swing.JButton();
       jScrollPane1 = new javax.swing.JScrollPane();
       jtStates = new javax.swing.JTable();
       jLabel31 = new javax.swing.JLabel();
       jtfDiretorio = new javax.swing.JTextField();
       jbtPesquisar = new javax.swing.JToggleButton();
       jSeparator1 = new javax.swing.JSeparator();
       pnlStates1 = new javax.swing.JPanel();
       txtProperties = new javax.swing.JTextField();
       btnDelProperties = new javax.swing.JButton();
       btnAddProperties = new javax.swing.JButton();
       jScrollPane2 = new javax.swing.JScrollPane();
       jtProperties = new javax.swing.JTable();
       jSeparator2 = new javax.swing.JSeparator();
       pnlStates2 = new javax.swing.JPanel();
       jScrollPane3 = new javax.swing.JScrollPane();
       jtStates2 = new javax.swing.JTable();
       jbDefine = new javax.swing.JButton();
       pnlStates3 = new javax.swing.JPanel();
       jScrollPane4 = new javax.swing.JScrollPane();
       jtDefine = new javax.swing.JTable();
       jPanel3 = new javax.swing.JPanel();
       jbImport = new javax.swing.JButton();
       jPanel2 = new javax.swing.JPanel();

       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       getContentPane().setLayout(new java.awt.GridLayout(1, 1));

       jTabbedPane1.setMinimumSize(new java.awt.Dimension(40, 800));
       jTabbedPane1.setPreferredSize(new java.awt.Dimension(700, 600));

       pnlStates.setBorder(javax.swing.BorderFactory.createTitledBorder("States"));
       pnlStates.setName("pnlStates"); // NOI18N
       pnlStates.setPreferredSize(new java.awt.Dimension(895, 140));
       pnlStates.setRequestFocusEnabled(false);

       btnDelStates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
       btnDelStates.setFocusable(false);
       btnDelStates.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       btnDelStates.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       btnDelStates.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               btnDelStatesActionPerformed(evt);
           }
       });

       btnAddStates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add2.png"))); // NOI18N
       btnAddStates.setFocusable(false);
       btnAddStates.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       btnAddStates.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       btnAddStates.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               btnAddStatesActionPerformed(evt);
           }
       });

       jScrollPane1.setViewportView(jtStates);

       javax.swing.GroupLayout pnlStatesLayout = new javax.swing.GroupLayout(pnlStates);
       pnlStates.setLayout(pnlStatesLayout);
       pnlStatesLayout.setHorizontalGroup(
           pnlStatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(pnlStatesLayout.createSequentialGroup()
               .addContainerGap()
               .addGroup(pnlStatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(pnlStatesLayout.createSequentialGroup()
                       .addComponent(txtState, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(btnAddStates, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(btnDelStates, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                   .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addContainerGap())
       );
       pnlStatesLayout.setVerticalGroup(
           pnlStatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStatesLayout.createSequentialGroup()
               .addGroup(pnlStatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(txtState, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                   .addComponent(btnAddStates, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(btnDelStates, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(13, 13, 13))
       );

       jLabel31.setText("Choose File");

       jtfDiretorio.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jtfDiretorioActionPerformed(evt);
           }
       });

       jbtPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/novo.png"))); // NOI18N
       jbtPesquisar.setSelected(true);
       jbtPesquisar.setToolTipText("Selecionar Pessoas");
       jbtPesquisar.setActionCommand("selecionarPessoas");
       jbtPesquisar.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jbtPesquisarActionPerformed(evt);
           }
       });

       jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

       pnlStates1.setBorder(javax.swing.BorderFactory.createTitledBorder("Properties"));
       pnlStates1.setName("pnlStates"); // NOI18N
       pnlStates1.setPreferredSize(new java.awt.Dimension(895, 140));
       pnlStates1.setRequestFocusEnabled(false);


       btnDelProperties.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
       btnDelProperties.setFocusable(false);
       btnDelProperties.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       btnDelProperties.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       btnDelProperties.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               btnDelPropertiesActionPerformed(evt);
           }
       });

       btnAddProperties.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add2.png"))); // NOI18N
       btnAddProperties.setFocusable(false);
       btnAddProperties.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
       btnAddProperties.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
       btnAddProperties.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               btnAddPropertiesActionPerformed(evt);
           }
       });

       jScrollPane2.setViewportView(jtProperties);

       javax.swing.GroupLayout pnlStates1Layout = new javax.swing.GroupLayout(pnlStates1);
       pnlStates1.setLayout(pnlStates1Layout);
       pnlStates1Layout.setHorizontalGroup(
           pnlStates1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(pnlStates1Layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(pnlStates1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(pnlStates1Layout.createSequentialGroup()
                       .addComponent(txtProperties, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(btnAddProperties, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(btnDelProperties, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                   .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addContainerGap())
       );
       pnlStates1Layout.setVerticalGroup(
           pnlStates1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStates1Layout.createSequentialGroup()
               .addGroup(pnlStates1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(txtProperties, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                   .addComponent(btnAddProperties, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(btnDelProperties, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(13, 13, 13))
       );

       jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

       pnlStates2.setBorder(javax.swing.BorderFactory.createTitledBorder("States"));
       pnlStates2.setName("pnlStates"); // NOI18N
       pnlStates2.setPreferredSize(new java.awt.Dimension(895, 140));
       pnlStates2.setRequestFocusEnabled(false);
       jScrollPane3.setViewportView(jtStates2);

       javax.swing.GroupLayout pnlStates2Layout = new javax.swing.GroupLayout(pnlStates2);
       pnlStates2.setLayout(pnlStates2Layout);
       pnlStates2Layout.setHorizontalGroup(
           pnlStates2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(pnlStates2Layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addContainerGap())
       );
       pnlStates2Layout.setVerticalGroup(
           pnlStates2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStates2Layout.createSequentialGroup()
               .addContainerGap(27, Short.MAX_VALUE)
               .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(11, 11, 11))
       );

       jbDefine.setText("New Define");
       jbDefine.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jbDefineActionPerformed(evt);
           }
       });

       pnlStates3.setBorder(javax.swing.BorderFactory.createTitledBorder("Define"));
       pnlStates3.setName("pnlStates"); // NOI18N
       pnlStates3.setPreferredSize(new java.awt.Dimension(895, 140));
       pnlStates3.setRequestFocusEnabled(false);

       jScrollPane4.setViewportView(jtDefine);

       javax.swing.GroupLayout pnlStates3Layout = new javax.swing.GroupLayout(pnlStates3);
       pnlStates3.setLayout(pnlStates3Layout);
       pnlStates3Layout.setHorizontalGroup(
           pnlStates3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(pnlStates3Layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addContainerGap(12, Short.MAX_VALUE))
       );
       pnlStates3Layout.setVerticalGroup(
           pnlStates3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(pnlStates3Layout.createSequentialGroup()
               .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );

       javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
       jPanel3.setLayout(jPanel3Layout);
       jPanel3Layout.setHorizontalGroup(
           jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 100, Short.MAX_VALUE)
       );
       jPanel3Layout.setVerticalGroup(
           jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 111, Short.MAX_VALUE)
       );

       jbImport.setText("OK");
       jbImport.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               jbImportActionPerformed(evt);
           }
       });

       javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
       jPanel1.setLayout(jPanel1Layout);
       jPanel1Layout.setHorizontalGroup(
           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addComponent(jLabel31)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(jtfDiretorio, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(jbtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(jbImport)
                       .addContainerGap())
                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                       .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                           .addComponent(pnlStates3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                           .addGroup(jPanel1Layout.createSequentialGroup()
                               .addComponent(pnlStates, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                               .addComponent(pnlStates1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addGap(10, 10, 10)
                               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                   .addComponent(jSeparator2)
                                   .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, Short.MAX_VALUE))
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                               .addComponent(pnlStates2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                       .addGap(148, 148, 148))))
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addContainerGap(762, Short.MAX_VALUE))
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addGap(287, 287, 287)
               .addComponent(jbDefine, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addContainerGap(444, Short.MAX_VALUE))
       );
       jPanel1Layout.setVerticalGroup(
           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                   .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                       .addComponent(jLabel31)
                       .addComponent(jtfDiretorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                   .addComponent(jbtPesquisar)
                   .addComponent(jbImport))
               .addGap(18, 18, 18)
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addComponent(pnlStates, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(pnlStates1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(pnlStates2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                           .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(jbDefine)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addComponent(pnlStates3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
               .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
       );

       jTabbedPane1.addTab("Start", jPanel1);

       javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
       jPanel2.setLayout(jPanel2Layout);
       jPanel2Layout.setHorizontalGroup(
           jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 726, Short.MAX_VALUE)
       );
       jPanel2Layout.setVerticalGroup(
           jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 772, Short.MAX_VALUE)
       );

       jTabbedPane1.addTab("MEF", jPanel2);

       getContentPane().add(jTabbedPane1);

       pack();
   }// </editor-fold>//GEN-END:initComponents

}
