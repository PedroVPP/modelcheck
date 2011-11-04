package br.mef;

import java.util.ArrayList;

public class Expression {
	
	private String name;
	private Expression exp1 = null;
	private Expression exp2 = null;
	//(o type = null indica que a expressão em questão e' uma propriedade e nao uma expressao)
	private String type = null;
	
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

	public void setName(String name) {
		this.name = name;
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

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	/**
	 * @return retorna o nome da expressao
	 */
	public String toString() {
		return name;
	}
}
