package br.mef;

import java.util.ArrayList;

public class State {

	private ArrayList<State> children = new ArrayList<State>();

	private String name;
	private ArrayList<Integer> labels = new ArrayList<Integer>();
	private ArrayList<String> labelsString = new ArrayList<String>();
	private static ArrayList<State> visitedStates = new ArrayList<State>();

	public State(String name) {
		this.name = name;
		addValidProperties(new Property("TRUE"));
	}

	public void removeState(int index) {
		children.remove(index);
	}

	public void removeState(State state) {

		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).equals(state)) {
				removeState(i);
				break;
			}
		}
	}

	public void close() {
		children.clear();
	}

	public State getChild(int i) {
		return children.get(i);
	}

	public int getNumChildren() {
		return children.size();
	}

	public void addChild(State n) {
		if (!children.contains(n)) {
			children.add(n);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasChild(State child) {

		for (State child_state : children) {
			if (child_state.getName().equals(child.getName())) {
				return true;
			}
		}

		return false;
	}

	public ArrayList<State> getChildren() {
		return this.children;
	}

	public void addLabel(Integer lb) {
		if (!labels.contains(lb)) {
			labels.add(lb);
		}
	}

	public ArrayList<Integer> getLabels() {
		return this.labels;
	}

	// parte de propriedades - adcionado por Frota em 19/10
	private ArrayList<Property> validProperties = new ArrayList<Property>();

	public void addValidProperties(Property p) {
		if (!validProperties.contains(p)) {
			validProperties.add(p);
		}
	}

	public ArrayList<Property> getValidProperties() {
		return this.validProperties;
	}

	public void setLabelsString(ArrayList<String> labelsString) {
		this.labelsString = labelsString;
	}

	public void addLabelsString(String label) {
		if (!labelsString.contains(label)) {
			labelsString.add(label);
		}
	}

	public ArrayList<String> getLabelsString() {
		return this.labelsString;
	}

	public static ArrayList<State> getVisitedStates() {
		return visitedStates;
	}

	public static void addVisitedState(State state) {
		State.visitedStates.add(state);
	}
	
	public static void clearVisitedStates() {
		State.visitedStates.clear();
	}
	
	public static boolean isStateVisited(State state) {
		return State.visitedStates.contains(state);
	}
	
    @Override
    public boolean equals(Object st){
        try{
        	return (this.getName().equals(((State) st).getName()));
        }
        catch(java.lang.RuntimeException e){
            return false;
        }

    }	
}
