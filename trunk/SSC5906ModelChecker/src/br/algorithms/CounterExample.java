package br.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import br.mef.Expression;
import br.mef.State;

public class CounterExample {
	private State state;
	private Expression exp;
	private ArrayList<Transicao> states = new ArrayList<Transicao>();
	private ArrayList<State> validos = new ArrayList<State>();
	private ArrayList <State> statesMEF = new ArrayList<State>();
	

	/**
	 * @author Vania Neves
	 * Construtor CounterExample, cria inicializa o estado e a expressão analisados
	 * @param state
	 *            Estado analisado	             
	 * @param expression
	 * 			  Expressão analisada 	           
	 * 
	 */	
	
	public CounterExample(State state, Expression expression) {
		this.state = state;
		this.exp = expression;
		this.addStateMEF(state);
	}
	
	/**
	 * @author Vania Neves	 
	 *  Obtém o estado analisado
	 *  
	 * @return state
	 * 
	 */	

	public State getState() {
		return state;
	}
	
	/**
	 * @author Vania Neves	 
	 *  Configura o estado analisado
	 *  
	 * @param state
	 * 
	 */			
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * @author Vania Neves	 
	 *  Obtém um objeto de {@link Expression} referente a expressão analisada
	 *  
	 * @return exp
	 * 
	 */			
	public Expression getExp() {
		return exp;
	}
	
	/**
	 * @author Vania Neves	 
	 *  Configura a expressão analisada
	 *  
	 * @param exp
	 * 		Objeto da classe {@link Expression}  referente a expressão analisada
	 * 
	 */			
	public void setExp(Expression exp) {
		this.exp = exp;
	}
	
	/**
	 * @author Vania Neves	 
	 *  Obtém a lista de estados em que a {@link CounterExample#exp} é válida
	 *  
	 * @return validos
	 * 
	 */			
	public ArrayList<State> getValidos() {
		return this.validos;
	}
	
	/**
	 * @author Vania Neves	 
	 *  Armazena os estados que a MEF conterá 
	 *  
	 * @param state
	 * 
	 */	
	private void addStateMEF(State state){
		if (!statesMEF.contains(state)){
			statesMEF.add(state);
		}
	}
	
	/**
	 * @author Vania Neves	 
	 *  Armazena os estados que a MEF conterá 
	 *  
	 * @param state
	 * 
	 */	
	public void addStateValido(State state) {
		boolean contains = false;
		for (Iterator<State> iterator = this.validos.iterator(); iterator.hasNext();) {
			State state2 = (State) iterator.next();
			if(state.getName().equals(state2.getName())) {
				contains = true;
			}
		}
		if(!contains) {
			this.validos.add(state);
		}
	}		
	
	
	public void removeStateValido(State state) {
		for (int i = 0; i < this.validos.size(); i++) {
			State state2 = this.validos.get(i);
			if(state.getName().equals(state2.getName())) {
				this.validos.remove(state2);
			}
		}
	}	
	public ArrayList<Transicao> getTransicoes(){
		return this.states;
	}
	
	public void addTransicao(State state, State state2){
		Transicao transicao = new Transicao();
		transicao.setOrigem(state);
		transicao.setDestino(state2);
		this.states.add(transicao);
		addStateMEF(state);
		addStateMEF(state2);
	}
	
	public ArrayList<State> getStatesMEF(){
		return this.statesMEF;
	}

    @Override
    public boolean equals(Object st){
        try{
        	return (this.exp.getName().equals(((CounterExample) st).exp.getName()) && 
        			this.state.getName().equals(((CounterExample) st).state.getName()));
        }
        catch(java.lang.RuntimeException e){
            return false;
        }

    }
    
    //for testing, by pedro
    public ArrayList<State> getInvalidos() {
    	ArrayList<State> invalidStates = new ArrayList<State>();
    	
    	for (Iterator iterator = this.statesMEF.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			if(!this.validos.contains(state) && !invalidStates.contains(state)) {
				invalidStates.add(state);
			}
		}
    	
    	return invalidStates;
    }    	
}
