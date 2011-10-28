/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gui.util;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import br.gui.StateAnswer;


/**
 *
 * @author Vânia
 */
public class TableModelStateAnswer  extends AbstractTableModel {

    private ArrayList<StateAnswer> states;

    public TableModelStateAnswer(ArrayList<StateAnswer> states) {
        this.states = states;
        fireTableDataChanged();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	StateAnswer state = states.get(rowIndex);
        switch(columnIndex) {
            case 0: return state.getName();
            case 1: return state.getValid();
        }
        return null;
    }

    public int getRowCount() {
        return states.size();
    }

    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName (int ci){
        switch(ci) {
            case 0: return "States";
            case 1: return "Valid";
        }
       return "";
    }

    public StateAnswer getValores(int rowIndex) {
        if (rowIndex > states.size() )
            return null;
        else
            return states.get(rowIndex);
    }
    
   @Override
   public boolean isCellEditable(int row, int col) {  
     return false;  
   }
   
   public ArrayList<StateAnswer> getLinhas(){
       return states;
   }

    public void addRow(StateAnswer states) {
            getLinhas().add(states);
            // Informa a jtable de que houve linhas incluidas no modelo
            // Como adicionamos no final, pegamos o tamanho total do modelo
            // menos 1 para obter a linha incluida.
            int linha = getLinhas().size() - 1;
            fireTableRowsInserted(linha, linha);
            return;
    }

    public void removeRow(StateAnswer states) {
            int linha = getLinhas().indexOf(states);
            getLinhas().remove(states);
            // informa a jtable que houve dados deletados passando a
            // linha reovida
            fireTableRowsDeleted(linha, linha);
    }

    public void removeRows() {
        this.states.removeAll(states);
        fireTableDataChanged();
    }

}
