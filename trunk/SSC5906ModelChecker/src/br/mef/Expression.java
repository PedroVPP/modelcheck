package br.mef;

import java.util.ArrayList;

public class Expression {
	
	private String name;
	private String allName;
	private Expression exp1 = null;
	private Expression exp2 = null;
	//(o type = null indica que a expressão em questão e' uma propriedade e nao uma expressao)
	private ExpressionType type = null;
	
	//unnecessary?
	private ArrayList<Expression> expressions;
	private Integer label; // no caso os labels não poderiam ser o próprio nome das expressoes? entao essa
	//variavel e' desnecessaria?
		
	public Expression (String name){
		this.name = name;
	}	
	
	public String getName() {
		return name;
	}

	public String getAllName() {
		return allName;
	}	
/*	public String getAllName(){
		return getAllNameRec(this, "");		
	}
	
	public String getAllNameRec(Expression e, String name)
	{
		if (!e.isTemporalOperator()){
			name =  name + e.getName();
		}
		if (e.getExp1() != null)
		{	
			name = getAllNameRec(e.getExp1(), name);
		} 
        if (e.getExp2() != null)
		{
        	name = getAllNameRec(e.getExp2(), name);
		}
        return name;
			  
	}*/

	public void setName(String name) {
		this.name = name;
	}

	public void setAllName(String name) {
		this.allName = name;
	}
	
	public void removeExpression(int index){
		expressions.remove(index);
	}

	
	public Expression getExp1() {
		return exp1;
	}

	public void setExp1(Expression exp1) {
		this.exp1 = exp1;
	}

	public Expression getExp2() {
		return exp2;
	}

	public void setExp2(Expression exp2) {
		this.exp2 = exp2;
	}

	
	public ExpressionType getType() {
		return type;
	}

	public void setType(ExpressionType type) {
		this.type = type;
	}

	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
		this.label = label;
	}

	public void removeExpression(Expression expression){

		for (int i = 0; i < expressions.size(); i++) {
			if(expressions.get(i).equals(expressions)){
				removeExpression(i);
				break;
			}
		}
	}
	
	public void close(){
		expressions.clear();
	}	
	
	public Expression getExpression(int i){
		return expressions.get(i);
	}

	public int getNumExpressions(){ 
		return expressions.size();
	}
	
	public void addExpression(Expression exp){
		if(!expressions.contains(exp)){
			expressions.add(exp);	
		}		
	}	
	
	public ArrayList<Expression> getExpressions(){
		return this.expressions;
	}

	public boolean isProperty() {
		if (exp1 == null && exp2 == null &&
				type == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isLogicalOperator() {
		return ExpressionType.isLogicalOperator(type);
	}
	
	public boolean isTemporalOperator() {
		return ExpressionType.isTemporalOperator(type);
	}
	
	public boolean isNOT() {
		if(type == ExpressionType.NOT) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAND() {
		if(type == ExpressionType.AND) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isOR() {
		if(type == ExpressionType.OR) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isIMP() {
		if(type == ExpressionType.IMP) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isBIC() {
		if(type == ExpressionType.BIC) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEX() {
		if(type == ExpressionType.EX) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAX() {
		if(type == ExpressionType.AX) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAU() {
		if(type == ExpressionType.AU) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEU() {
		if(type == ExpressionType.EU) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAF() {
		if(type == ExpressionType.AF) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEF() {
		if(type == ExpressionType.EF) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEG() {
		if(type == ExpressionType.EG) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAG() {
		if(type == ExpressionType.AG) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return retorna o nome da expressao
	 */
	public String toString() {
		return allName;
	}
	
    @Override
    public boolean equals(Object exp){
        try{
        	return (this.getName().equals(((Expression) exp).getName()));
        }
        catch(java.lang.RuntimeException e){
            return false;
        }

    }	
}
