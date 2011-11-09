package br.mef;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import javax.swing.JOptionPane;

import br.algorithms.CounterExample;
import br.algorithms.Information;

public class MEF {
	
	private static MEF instance;
	
	private ArrayList<Property> properties = new ArrayList<Property>();
	private ArrayList<State> states = new ArrayList<State>();
	private ArrayList<Expression> expressions = new ArrayList<Expression>();
	private ArrayList<CounterExample> counterExample = new ArrayList<CounterExample>();
	private State firsState;
	private String imagem = "";
	
	private Escrita novoDotArq;
	private Graphviz novoDot;
	private File out;	
	
   public static MEF getInstance() {
	      if (instance == null)
	         instance = new MEF();
	      return instance;
	}
   
   public void cleanMEF(){
	   this.states.clear();
	   this.properties.clear();
	   this.expressions.clear();
	   this.firsState = null;
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
	
	public void addState(State n){
		if(!states.contains(n)){
			states.add(n);		  
		}
	}

	public State getFirstState(){
		return firsState;
	}
	
	public void setFirstState(State n){
		this.firsState = n;
	}
	
	public ArrayList<State> getStates(){
		return this.states;
	}
	
	public State getState(String name){

		for (State st : states) {
			if(st.getName().equals(name)){
				return st;
			}
		}
		return null;
	}
	
	public void createMEF(){
		this.novoDotArq = new Escrita("mef.dot");
		novoDotArq.abrir();
		novoDotArq.escreverLinha("digraph mef {");
		novoDotArq.novaLinha();		
    	for (int i = 0; i < states.size(); ++i) {
    		State state = (State) states.get(i);
			String linhaState = state.getName() + "[ label= \""+state.getName()+"\\n{";
			ArrayList<Property> properties = state.getValidProperties();
			for(int j=0; j<properties.size(); j++){
				Property property = (Property) properties.get(j);
				if (!property.getName().equals("TRUE")){
					linhaState = linhaState + property.getName() + ", ";
					state.addLabelsString(property.getName());					
				}
			}
			
			linhaState = linhaState.substring(0, linhaState.length() -2) + "}\"]";    		
    		novoDotArq.escreverLinha(linhaState);
    		novoDotArq.novaLinha();
    	    ArrayList<State> filhos = state.getChildren();
	    	for (int j = 0; j < filhos.size(); ++j) {
	    		State filho = (State) filhos.get(j);
	    		novoDotArq.escreverLinha(state.getName()+"->"+filho.getName());
	    		novoDotArq.novaLinha();	    		
	    	}    		
    	}		
    	novoDotArq.escreverLinha("node [shape = doublecircle]; "+ this.firsState.getName()+";");
		novoDotArq.escreverLinha("}");
		novoDotArq.fechar();
		
		try{
			this.novoDot = new Graphviz();
			novoDot.addln(novoDot.start_graph());
			for(int i=0; i<states.size(); i++){
				State state = (State) states.get(i);
				String linhaState = state.getName() + "[ label= \""+state.getName()+"\\n{";
				ArrayList<Property> properties = state.getValidProperties();
				for(int j=0; j<properties.size(); j++){					
					Property property = (Property) properties.get(j);
					if (!property.getName().equals("TRUE")){
						linhaState = linhaState + property.getName() + ", ";	
					}					
				}
				linhaState = linhaState.substring(0, linhaState.length() -2) + "}\"]";
				novoDot.addln(linhaState);
				ArrayList<State> filhos = state.getChildren();
				for (int j = 0; j < filhos.size(); ++j) {
		    		State filho = (State) filhos.get(j);
		    		novoDot.addln(state.getName()+"->"+filho.getName());    		
		    	}    						
			}
			novoDot.addln(novoDot.end_graph());
			System.out.println(novoDot.getDotSource());
			this.imagem = UUID.randomUUID().toString()+ ".jpg";
			this.out = new File(this.imagem);		
			novoDot.writeGraphToFile(novoDot.getGraph(novoDot.getDotSource()), out);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Erro ao gerar o MEF!");
		    System.exit(0);
		}		
	}
	
	
	
	
	
	//adicionando parte que gerencia as propriedades
	
	public void addProperty(Property n){
		if(!properties.contains(n)){
			properties.add(n);		  
		}
	}

	public Property getProperty(String name){

		for (Property p : properties) {
			if(p.getName().equals(name)){
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Property> getProperties(){
		return this.properties;
	}
	
	public void setState(ArrayList<State> states){
		this.states = states;
	}	

	
	//adicionando parte que gerencia as expressions
	
	public void addExpressions(Expression exp){
		if(!expressions.contains(exp)){
			expressions.add(exp);		  
		}
	}

	public Expression getExpression(String name){

		for (Expression exp : expressions) {
			if(exp.getName().equals(name)){
				return exp;
			}
		}
		return null;
	}
	
	public ArrayList<Expression> getExpressions(){
		return this.expressions;
	}

	public String getImagem(){
		return this.imagem;
	}

	public ArrayList<CounterExample> getCounterExample() {
		return this.counterExample;
	}

	public void addCounterExample(CounterExample counterExample) {
		if(!this.counterExample.contains(counterExample)){
			this.counterExample.add(counterExample);	
		}		
	}
	
	public ArrayList<CounterExample> findCounterExamplesByState(State state) {
		ArrayList<CounterExample> counterExamples = new ArrayList<CounterExample>();
		
		for (Iterator<CounterExample> iterator = this.counterExample.iterator(); iterator.hasNext();) {
			CounterExample counterExample = (CounterExample) iterator.next();
			if(counterExample.getState().getName().equals(state.getName())) {
				counterExamples.add(counterExample);
			}
		}
		
		return counterExamples;
	}
	
	public ArrayList<CounterExample> findCounterExamplesByExpression(Expression expression) {
		ArrayList<CounterExample> counterExamples = new ArrayList<CounterExample>();
		
		for (Iterator<CounterExample> iterator = this.counterExample.iterator(); iterator.hasNext();) {
			CounterExample counterExample = (CounterExample) iterator.next();
			if(counterExample.getExp().getName().equals(expression.getName())) {
				counterExamples.add(counterExample);
			}
		}
		
		return counterExamples;
	}
	
	public ArrayList<CounterExample> findCounterExample(State state, Expression expression) {
		ArrayList<CounterExample> counterExamples = new ArrayList<CounterExample>();
		
		for (Iterator<CounterExample> iterator = this.counterExample.iterator(); iterator.hasNext();) {
			CounterExample counterExample = (CounterExample) iterator.next();
			if(counterExample.getState().getName().equals(state.getName()) && 
					counterExample.getExp().getName().equals(expression.getName())) {
				counterExamples.add(counterExample);
			}
		}
		return counterExamples;
	}
}
