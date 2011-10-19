package br.mef;

import java.util.ArrayList;

import br.gui.util.TableModelState;

public class Define {
	
	private State state;
	private ArrayList<State> states = new ArrayList<State>();
	private ArrayList<Property> properties = new ArrayList<Property>();
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public ArrayList<State> getStates(){
		return this.states;
	}
	
	public ArrayList<Property> getProperties(){
		return this.properties;
	}
	
	public void removeState(int index){
		states.remove(index);
	}

	public void removeState(State state){

		for (int i = 0; i < states.size(); i++) {
			if(states.get(i).equals(state)){
				removeState(i);
				break;
			}
		}
	}
	
	
	public void removeProperty(int index){
		properties.remove(index);
	}

	public void removeProperties(Property property){

		for (int i = 0; i < properties.size(); i++) {
			if(properties.get(i).equals(property)){
				removeProperty(i);
				break;
			}
		}
	}
	
	public void addState(State n){
		states.add(n);
	}	
	
	public void addProperty(Property n){
		properties.add(n);
	}	
	
	public String getNamesProperties(){
		String name = "{ ";
		for (int i = 0; i < this.properties.size(); ++i) {
    		Property prop = (Property) this.properties.get(i);
    		name = name + prop.getName()+ ", ";   		
    	}  
		name = name.substring(0, name.length()-2) + " }";
		return name;
	}
	
	public String getNamesStates(){
		String name = "{ ";
		for (int i = 0; i < this.states.size(); ++i) {
    		State st = (State) this.states.get(i);
    		name = name + st.getName()+ ", ";   		
    	}
		name = name.substring(0, name.length()-2) + " }";
		return name;
	}	
}
