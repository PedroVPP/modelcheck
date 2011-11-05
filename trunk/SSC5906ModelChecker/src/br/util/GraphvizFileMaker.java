package br.util;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class GraphvizFileMaker {

	private File file;
	private String content;
	private ArrayList<String> states, validStates, invalidStates, connections;
	
	public GraphvizFileMaker(String fileName) {
		try {
			file = new File(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setContent("digraph " + fileName.substring(0, fileName.indexOf(".")) +" {\n");
		states = new ArrayList<String>();
		validStates = new ArrayList<String>();
		invalidStates = new ArrayList<String>();
		connections = new ArrayList<String>();
	}
	
	public void addValidState(String stateName) {
		if(!states.contains(stateName)) {
			states.add(stateName);
			validStates.add(stateName + " [style=filled, color=green]");
		}
	}
	
	public void addInvalidState(String stateName) {
		if(!states.contains(stateName)) {
			states.add(stateName);
			invalidStates.add(stateName + " [style=filled, color=red]");
		}
	}
	
	public void addBranch(String firstState, String secondState) {
		connections.add(firstState + " -> " + secondState);
	}

	public void finishFile() {
		for (Iterator<String> iterator = validStates.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			appendContent(string+"\n");
		}
		appendContent("\n");
		for (Iterator<String> iterator = invalidStates.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			appendContent(string+"\n");
		}
		appendContent("\n");
		for (Iterator<String> iterator = connections.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			appendContent(string+"\n");
		}
		appendContent("}");
		
		String finalContent = getContent();
		try{  
	         FileWriter writer = new FileWriter(file, true);   
	         writer.write(finalContent);           
	         writer.close();           
	      }  
	      catch(Exception e){
	    	  e.printStackTrace();
	      }  
	}
	
	private String getContent() {
		return content;
	}

	private void setContent(String content) {
		this.content = content;
	}
	
	private void appendContent(String content) {
		this.content += content;
	}
}
