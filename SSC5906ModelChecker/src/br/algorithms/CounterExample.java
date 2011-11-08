package br.algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import br.mef.Expression;
import br.mef.State;

public class CounterExample {
	private State state;
	private Expression exp;
	private HashMap<State, State> states = new HashMap<State, State>();
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
		this.validos.add(state);
	}	
	
	public HashMap<State, State> getTransicoes(){
		return this.states;
	}
	
	public void addTransicao(State state, State state2){
		this.states.put(state, state2);
	}

}
