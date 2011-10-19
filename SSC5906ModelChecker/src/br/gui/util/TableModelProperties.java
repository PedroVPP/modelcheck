/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gui.util;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import br.mef.Property;

/**
 *
 * @author Vânia
 */
public class TableModelProperties  extends AbstractTableModel {

    private ArrayList<Property> properties;

    public TableModelProperties(ArrayList<Property> properties) {
        this.properties = properties;
        fireTableDataChanged();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Property prop = properties.get(rowIndex);
        switch(columnIndex) {
            case 0: return prop.getName();
        }
        return null;
    }

    public int getRowCount() {
        return properties.size();
    }

    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName (int ci){
        switch(ci) {
            case 0: return "Properties";
        }
       return "";
    }

    public Property getValores(int rowIndex) {
        if (rowIndex > properties.size() )
            return null;
        else
            return properties.get(rowIndex);
    }
    
   @Override
   public boolean isCellEditable(int row, int col) {  
     return false;  
   }
   
   public ArrayList<Property> getLinhas(){
       return properties;
   }

    public void addRow(Property properties) {
            getLinhas().add(properties);
            // Informa a jtable de que houve linhas incluidas no modelo
            // Como adicionamos no final, pegamos o tamanho total do modelo
            // menos 1 para obter a linha incluida.
            int linha = getLinhas().size() - 1;
            fireTableRowsInserted(linha, linha);
            return;
    }

    public void removeRow(Property properties) {
            int linha = getLinhas().indexOf(properties);
            getLinhas().remove(properties);
            // informa a jtable que houve dados deletados passando a
            // linha reovida
            fireTableRowsDeleted(linha, linha);
    }

    public void removeRows() {
        this.properties.removeAll(properties);
        fireTableDataChanged();
    }

}
