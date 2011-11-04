package br.mef;

import java.util.ArrayList;

public class State {

	private ArrayList<State> children = new ArrayList<State>();

	private String name;
	private ArrayList<Integer> labels = new ArrayList<Integer>();
	// nesse atributo ficaria guardado todos os labels das express�es
	// fiz isso considerando o exemplo que o Ades deu em aula
	// por ex, a express�o EX EG r, o label de r � (1), o label de EG r � (2)
	// o label de EX EG r � (3)
	// ao chamar os algoritmos, eles adicionariam os labels v�lidos em cada
	// state
	// depois, pra saber se a express�o � v�lida, bastaria verificar se ela
	// possui o label
	// a parte que ficou faltando � associar um label para cada express�o...

	// Criei essa outra variável porque achei melhor que os labels sejam as
	// expressoes
	// ao inves inteiros representando elas. Alem de expressoes tambem sao
	// colocadas aqui
	// as propriedades dos estados by Pedro
	private ArrayList<String> labelsString = new ArrayList<String>();

	// essa variavel serve como um indicador se aquele estado ja foi visitado ou
	// nao pelo algortimo
	private boolean visited = false;

	public State(String name) {
		this.name = name;
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

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void resetVisited() {
		this.visited = false;
	}

}
