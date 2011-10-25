/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gui.util;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import br.mef.Expression;
import br.mef.Property;

/**
 *
 * @author Vânia
 */
public class TableModelExpressions  extends AbstractTableModel {

    private ArrayList<Expression> expressions;

    public TableModelExpressions(ArrayList<Expression> expressions) {
        this.expressions = expressions;
        fireTableDataChanged();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Expression exp = expressions.get(rowIndex);
        switch(columnIndex) {
            case 0: return exp.getName();
        }
        return null;
    }

    public int getRowCount() {
        return expressions.size();
    }

    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName (int ci){
        switch(ci) {
            case 0: return "Expression";
        }
       return "";
    }

    public Expression getValores(int rowIndex) {
        if (rowIndex > expressions.size() )
            return null;
        else
            return expressions.get(rowIndex);
    }
    
   @Override
   public boolean isCellEditable(int row, int col) {  
     return false;  
   }
   
   public ArrayList<Expression> getLinhas(){
       return expressions;
   }

    public void addRow(Expression expression) {
            getLinhas().add(expression);
            // Informa a jtable de que houve linhas incluidas no modelo
            // Como adicionamos no final, pegamos o tamanho total do modelo
            // menos 1 para obter a linha incluida.
            int linha = getLinhas().size() - 1;
            fireTableRowsInserted(linha, linha);
            return;
    }

    public void removeRow(Expression expression) {
            int linha = getLinhas().indexOf(expression);
            getLinhas().remove(expression);
            // informa a jtable que houve dados deletados passando a
            // linha reovida
            fireTableRowsDeleted(linha, linha);
    }

    public void removeRows() {
        this.expressions.removeAll(expressions);
        fireTableDataChanged();
    }

}
