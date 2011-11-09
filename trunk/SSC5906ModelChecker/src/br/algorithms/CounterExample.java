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
	
	public CounterExample(State state, Expression expression) {
		this.state = state;
		this.exp = expression;
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
	
	public ArrayList<Transicao> getTransicoes(){
		return this.states;
	}
	
	public void addTransicao(State state, State state2){
		Transicao transicao = new Transicao();
		transicao.setOrigem(state);
		transicao.setDestino(state2);
		this.states.add(transicao);
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
}
