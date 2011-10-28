package br.gui;

import java.util.ArrayList;

public class StateAnswer {
	
	private ArrayList<StateAnswer> children = new ArrayList<StateAnswer>();
	
	private String name;
	private String valid = "";
	
	public StateAnswer (String name){
		this.name = name;
	}
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public String getValid() {
		return valid;
	}


	public void setValid(String valid) {
		this.valid = valid;
	}
	
	

}
