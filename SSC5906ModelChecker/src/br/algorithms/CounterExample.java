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
	

	public CounterExample(State state, Expression expression) {
		this.state = state;
		this.exp = expression;
		this.addStateMEF(state);
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Expression getExp() {
		return exp;
	}
	public void setExp(Expression exp) {
		this.exp = exp;
	}
	
	public ArrayList<State> getValidos() {
		return this.validos;
	}
	
	private void addStateMEF(State state){
		if (!statesMEF.contains(state)){
			statesMEF.add(state);
		}
	}
	
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
