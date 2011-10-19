/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gui.util;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import br.mef.Define;

/**
 *
 * @author Vânia
 */
public class TableModelDefine  extends AbstractTableModel {

    private ArrayList<Define> defines;

    public TableModelDefine(ArrayList<Define> defines) {
        this.defines = defines;
        fireTableDataChanged();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Define define = defines.get(rowIndex);
        switch(columnIndex) {
            case 0: return define.getState().getName();
            case 1: return define.getNamesProperties();
            case 2: return define.getNamesStates();
        }
        return null;
    }

    public int getRowCount() {
        return defines.size();
    }

    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName (int ci){
        switch(ci) {
            case 0: return "State";
            case 1: return "Properties";
            case 2: return "States";
        }
       return "";
    }

    public Define getValores(int rowIndex) {
        if (rowIndex > defines.size() )
            return null;
        else
            return defines.get(rowIndex);
    }
    
   @Override
   public boolean isCellEditable(int row, int col) {  
     return false;  
   }
   
   public ArrayList<Define> getLinhas(){
       return defines;
   }

    public void addRow(Define defines) {
            getLinhas().add(defines);
            // Informa a jtable de que houve linhas incluidas no modelo
            // Como adicionamos no final, pegamos o tamanho total do modelo
            // menos 1 para obter a linha incluida.
            int linha = getLinhas().size() - 1;
            fireTableRowsInserted(linha, linha);
            return;
    }

    public void removeRow(Define defines) {
            int linha = getLinhas().indexOf(defines);
            getLinhas().remove(defines);
            // informa a jtable que houve dados deletados passando a
            // linha reovida
            fireTableRowsDeleted(linha, linha);
    }

    public void removeRows() {
        this.defines.removeAll(defines);
        fireTableDataChanged();
    }

}
