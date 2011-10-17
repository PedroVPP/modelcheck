package br.mef;

import java.util.ArrayList;

public class State {
	
	private ArrayList<State> children = new ArrayList<State>();
	private String name;
	
	public State (String name){
		this.name = name;
	}
	
	public void removeState(int index){
		children.remove(index);
	}

	public void removeState(State state){

		for (int i = 0; i < children.size(); i++) {
			if(children.get(i).equals(state)){
				removeState(i);
				break;
			}
		}
	}
	
	public void close(){
		children.clear();
	}	
	
	public State getChild(int i){
		return children.get(i);
	}

	public int getNumChildren(){
		return children.size();
	}
	
	public void addChild(State n){
		children.add(n);
	}	

	public String getName() {
		return name;
	}


	public boolean hasChild(State child){

		for (State child_state : children) {
			if(child_state.getName().equals(child.getName())){
				return true;
			}
		}

		return false;
	}	
	
	public ArrayList<State> getChildren(){
		return this.children;
	}
}
