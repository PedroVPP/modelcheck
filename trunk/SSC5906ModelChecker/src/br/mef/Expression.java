package br.mef;

import java.util.ArrayList;

public class Expression {
	
	private String name;
	private ArrayList<Expression> expressions;

	public Expression (String name){
		this.name = name;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void removeExpression(int index){
		expressions.remove(index);
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

}
