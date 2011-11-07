package br.algorithms;

import java.util.ArrayList;

import br.mef.State;

public class Information {
	private String stateName = "";
	private String algorithmName = "";
	private boolean result = false;
	private ArrayList<State> states = new ArrayList<State>();
	private ArrayList<State> validStates = new ArrayList<State>();
	private ArrayList<State> invalidStates = new ArrayList<State>();
	private String exp1, exp2;

	public Information(String stateName, String algorithmName, boolean result) {
		this.stateName = stateName;
		this.algorithmName = algorithmName;
		this.result = result;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Information() {

	}

	public ArrayList<State> getStates() {
		return states;
	}

	public void addStates(State state) {
		this.states.add(state);
	}

	public String getExp1() {
		return exp1;
	}

	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}

	public String getExp2() {
		return exp2;
	}

	public void setExp2(String exp2) {
		this.exp2 = exp2;
	}

	public ArrayList<State> getValidStates() {
		return validStates;
	}

	public void addValidStates(State validState) {
		this.validStates.add(validState);
	}

	public ArrayList<State> getInvalidStates() {
		return invalidStates;
	}

	public void addInvalidStates(State invalidState) {
		this.invalidStates.add(invalidState);
	}

}
