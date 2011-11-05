package br.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.mef.Expression;
import br.mef.Property;
import br.mef.State;
import br.util.GraphvizFileMaker;

public class Algorithms {

	static GraphvizFileMaker graphvizFileMaker; 
	
	public static boolean Alg_E(State st, Expression exp1, Expression exp2) {
		boolean resposta = false;
		// algoritmo para verificar se uma express�o e � v�lida
		return resposta;
	}

	public static boolean Alg_Id(State st, Expression exp1) {
		boolean resposta = false;
		// algoritmo para verificar se uma propriedade � v�lida em um estado
		// se o type dela for id, o name ser� uma propriedade...
		ArrayList<Property> properties = st.getValidProperties();
		for (int i = 0; i < properties.size(); ++i) {
			Property prop = (Property) properties.get(i);
			if (prop.getName().equals(exp1.getName())) {
				resposta = true;
				// st.addLabel(exp.getLabel());
				// ficou faltando a parte de associar um label a uma express�o
				// acho q daria certo se no icmcJCM.jj criasse um atributo
				// global
				// do tipo inteiro q fosse sendo incrementado sempre que uma
				// nova
				// express�o � criada e setando essa vari�vel no label
				// (expression.setLAbel());
				// ai, nesse trecho adicionaria o label da express�o
				// depois, para verificar se a express�o � v�lida em um
				// determinado
				// estado, bastaria verificar se o estado possui o label
				break;
			}
		}
		return resposta;
	}

	/**
	 * @author Pedro Pinheiro
	 * @param state
	 *            Consiste do estado especifico da MEF em que se deseja
	 *            verificar se uma expressao e' verdadeira
	 * @param expression
	 *            Consiste da expressao que se deseja verificar se e'
	 *            verdadeira.
	 * @return retorna true se a expressao e' verdadeira para aquele estado, e
	 *         caso contrario retorna 'false'
	 * @throws Exception
	 */
	public static boolean OR(State state, Expression expression)
			throws Exception {
		/*
		 * Ex: Expressao: p OR q
		 * 
		 * Expression 1 name = p OR q exp1 = p exp2 = q
		 * 
		 * Expression 2 name = p exp1 = null exp2 = null
		 * 
		 * Expression 3 name = q exp1 = null exp2 = null
		 */
		Expression expression1 = expression.getExp1(); // = p
		Expression expression2 = expression.getExp2(); // = q
		if (state.getLabelsString().contains(expression1.getName())
				|| state.getLabelsString().contains(expression2.getName())) {
			// adiciona o label pois a expressao e verdadeira
			state.addLabelsString(expression.getName());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo que executa o OR para todos os estados do parametro states
	 * @author Pedro Pinheiro
	 * @param states
	 *            Consiste do array de estados da MEF inteira.
	 * @param expression
	 *            Consiste da expressao que se deseja verificar se e'
	 *            verdadeira.
	 * @return retorna os estados em que a expressao fornecida e' valida
	 * @throws Exception
	 */
	public static ArrayList<State> OR(ArrayList<State> states,
			Expression expression) throws Exception {
		ArrayList<State> validStates = new ArrayList<State>();

		for (Iterator<State> iterator = states.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			if (OR(state, expression)) {
				validStates.add(state);
			}
		}
		return validStates;
	}

	/**
	 * @author Pedro Pinheiro and Andr� Luiz
	 * @param state
	 *            Consiste do estado especifico da MEF em que se deseja
	 *            verificar se uma expressao e' verdadeira
	 * @param expression
	 *            Consiste da expressao que se deseja verificar se e'
	 *            verdadeira.
	 * @return retorna true se a expressao e' verdadeira para aquele estado, e
	 *         caso contrario retorna 'false'
	 * @throws Exception
	 */
	public static boolean AND(State state, Expression expression)
			throws Exception {
		// Expression expression = "p AND q"
		Expression expression1 = expression.getExp1(); // = p
		Expression expression2 = expression.getExp2(); // = q
		if (state.getLabelsString().contains(expression1.getName())
				&& state.getLabelsString().contains(expression2.getName())) {
			// adiciona o label pois a expressao e verdadeira
			state.addLabelsString(expression.getName());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo que executa o AND para todos os estados do parametro states
	 * @author Pedro Pinheiro e Andre Luiz
	 * @param states
	 *            Consiste do array de estados da MEF inteira.
	 * @param expression
	 *            Consiste da expressao que se deseja verificar se e'
	 *            verdadeira.
	 * @return retorna os estados em que a expressao fornecida e' valida
	 * @throws Exception
	 */
	public static ArrayList<State> AND(ArrayList<State> states,
			Expression expression) throws Exception {
		ArrayList<State> validStates = new ArrayList<State>();

		for (Iterator<State> iterator = states.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			if (AND(state, expression)) {
				validStates.add(state);
			}
		}
		return validStates;
	}

	/**
	 * @author Pedro Pinheiro and Andr� Luiz
	 * @param state
	 *            Consiste do estado especifico da MEF em que se deseja
	 *            verificar se uma expressao e' verdadeira
	 * @param expression
	 *            Consiste da expressao que se deseja verificar se e'
	 *            verdadeira.
	 * @return retorna true se a expressao e' verdadeira para aquele estado, e
	 *         caso contrario retorna false
	 */
	public static boolean NOT(State state, Expression expression) {
		boolean validExpression = false;
		// ex: Expression.name = ~p
		Expression expression1 = expression.getExp1(); // = p
//		Expression expression2 = expression.getExp2(); // = null
		// sempre a expression 1 não pode ser nula, se não ocasionara o nullpointerexception 

		if (!state.getLabelsString().contains(expression1.getName())) {
			state.addLabelsString(expression.getName());
			validExpression = true;
		}

		return validExpression;
	}

	/**
	 * Metodo que executa o NOT para todos os estados do parametro states
	 * @author Pedro Pinheiro e Andre Luiz
	 * @param states
	 *            Consiste do array de estados da MEF inteira.
	 * @param expression
	 *            Consiste da expressao que se deseja verificar se e'
	 *            verdadeira.
	 * @return retorna os estados em que a expressao fornecida e' valida
	 * @throws Exception
	 */
	public static ArrayList<State> NOT(ArrayList<State> states,
			Expression expression) throws Exception {
		ArrayList<State> validStates = new ArrayList<State>();

		for (Iterator<State> iterator = states.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			if (NOT(state, expression)) {
				validStates.add(state);
			}
		}
		return validStates;
	}

	/**
	 * OLD - Ate a r52 este método era assim: Esse metodo implementa o algoritmo de 'implica', ex: p -> q ele converte
	 * o p -> q para seu equivalente ~p v q, ou seja, (NOT p) OR q e assim usa
	 * os algoritmos OR e NOT para verificar a validade da expressao.
	 * 
	 * NEW: A partir da r53 ele foi melhorado para simplesmente utilizar os conceitos
	 * da tabela verdade do implica.
	 * 
	 * @author Pedro Pinheiro
	 * @param state
	 *            estado em que se deseja verificar se a expressao e valida
	 * @param expression
	 *            a expressao a ser verificada
	 * @return retorna true se a expressao e valida e false caso contrario
	 * @throws Exception
	 */
	public static boolean IMP(State state, Expression expression)
			throws Exception {
		boolean validExpression = true;
		// Expression expression = "p -> q"
		Expression expression1 = expression.getExp1(); // = p
		Expression expression2 = expression.getExp2(); // = q

//		tabela verdade
//		p   q  p->q
//		V 	V 	V
//		V 	F 	F
//		F 	V 	V
//		F 	F 	V
		
		if(state.getLabelsString().contains(expression1.getName()) && 
				!state.getLabelsString().contains(expression2.getName())) {
			validExpression = false;
		}
		if(validExpression) {
			state.addLabelsString(expression.getName());
		}
		return validExpression;
	}
	
	/**
	 * @author Pedro Pinheiro e Andre Luiz
	 * Metodo que executa o IMP para todos os estados do parametro states
	 * @param states
	 *            Consiste do array de estados da MEF inteira.
	 * @param expression
	 *            Consiste da expressao que se deseja verificar se e'
	 *            verdadeira.
	 * @return retorna os estados em que a expressao fornecida e' valida
	 * @throws Exception
	 */
	public static ArrayList<State> IMP(ArrayList<State> states,
			Expression expression) throws Exception {
		ArrayList<State> validStates = new ArrayList<State>();

		for (Iterator<State> iterator = states.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			if (IMP(state, expression)) {
				validStates.add(state);
			}
		}
		return validStates;
	}
	
	/**
	 * This formula is TRUE in a state s0 if formula f holds in
	 * the future of some path from s0.
	 * 
	 * Esse método executa o algoritmo EF. O EF consiste do seguinte: - Dada
	 * uma expressao, verificar se ela e valida para pelo menos um estado a
	 * partir de um estado inicial
	 * 
	 * O EF para fucionar utiliza dois métodos: - static boolean EF(State
	 * state, Expression expression) - static boolean recursiveEF(State state,
	 * String label)
	 * 
	 * O primeiro metodo checa o primeiro estado enviado. E possivel descobrir
	 * se a expressao e valida logo a partir desse primeiro estado. Caso ele
	 * ainda nao seja valido e necessario checar suas conexoes (filhos) para ver
	 * se pelo menos um deles e valido. Para isso usa-se o segundo metodo, o
	 * qual tem o comportamento recursivo.
	 * 
	 * @author Pedro Pinheiro
	 * @param state
	 * @param expression
	 * @return
	 * @throws MalformedExpression
	 */
	public static boolean EF(State state, Expression expression) {
		boolean validExpression = false;
		// ex: Expression.name = EF p
		Expression expression1 = expression.getExp1(); // = p
//		Expression expression2 = expression.getExp2(); // = null

		if (state.getLabelsString().contains(expression1.getName())) {
			validExpression = true;
			state.addLabelsString(expression.getName());
		} else {
			state.setVisited(true);
			ArrayList<State> children = state.getChildren();
			for (Iterator<State> iterator = children.iterator(); iterator
					.hasNext();) {
				State state2 = (State) iterator.next();
				if (recursiveEF(state2, expression1.getName())) {
					validExpression = true;
					state.addLabelsString(expression.getName());
					break;
				}
			}
		}

		return validExpression;
	}

	/**
	 * Este metodo e o responsavel por achar pelo menos UM estado que contem a
	 * label enviada pelo metodo EF. Ele possui o comportamento recursivo para
	 * checar todos os seus filhos, e os filhos desses filhos, e assim em
	 * diante. Para evitar que o algoritmo fique em um loop entre dois ou mais
	 * estados, foi declarada a variavel 'visited' na classe State. Devido a
	 * isso sempre que o algoritmo de EF ou EG for executado sempre deve-se
	 * resetar todas as variaveis 'visited' dos estados da MEF para o valor
	 * 'false', caso contrario o algoritmo ira achar que todos os estados ja
	 * foram visitados. Caso alguem tenha uma sugestao melhor de implementacao
	 * dessa informacao de visitado, por favor se manifeste.
	 * 
	 * @author Pedro Pinheiro
	 * @param state
	 * @param label
	 * @return
	 */
	private static boolean recursiveEF(State state, String label) {
		boolean valid = false;

		if (state.getLabelsString().contains(label)) {
			valid = true;
		} else {
			ArrayList<State> children = state.getChildren();
			for (Iterator<State> iterator = children.iterator(); iterator.hasNext();) {
				State state2 = (State) iterator.next();
				if (!state2.isVisited()) {
					state.setVisited(true);
					valid = recursiveEF(state2, label);
					if (valid) {
						break;
					}
				}
			}
		}
		return valid;
	}

	/**
	 * This formula is TRUE in a state s0 if there exists some path
	 * from s0 on which f holds at every state. Formally
	 * 
	 * Esse método executa o algoritmo EG. O EG consiste do seguinte: - Dada
	 * uma expressao, verificar se ela e' valida para TODOS os estados de pelo
	 * menos UM caminho na minha MEF a partir de um estado inicial
	 * 
	 * O EG para fucionar utiliza dois métodos: - static boolean EG(State
	 * state, Expression expression) - static boolean recursiveEG(State state,
	 * String label)
	 * 
	 * O primeiro metodo checa o primeiro estado enviado. Apos isso e usado o
	 * segundo metodo para percorrer os filhos e achar um caminho para
	 * satisfazer o EG. Para mais informacoes checar os comentarios do metodo
	 * 'recursiveEG'
	 * 
	 * @author Pedro Pinheiro
	 * @param state
	 * @param expression
	 * @return
	 * @throws MalformedExpression
	 */
	public static boolean EG(State state, Expression expression) {
		boolean validExpression = false;
		state.setVisited(true);
		// ex: Expression.name = EG p
		Expression expression1 = expression.getExp1(); // = p
//		Expression expression2 = expression.getExp2(); // = null

		if (state.getLabelsString().contains(expression1.getName())) {
			ArrayList<State> children = state.getChildren();
			for (Iterator<State> iterator = children.iterator(); iterator
					.hasNext();) {
				State state2 = (State) iterator.next();
				// se ele achar um caminho (ciclo) em que a expressao seja
				// valida
				if (recursiveEG(state2, expression1.getName())) {
					// entao marcar a expressao como valida
					validExpression = true;
					state.addLabelsString(expression.getName());
					break;
				}
			}
		}

		return validExpression;
	}

	/**
	 * Este metodo e o responsavel por achar pelo menos UM CAMINHO onde TODOS os
	 * estados satisfazem uma propriedade. Ele possui o comportamento recursivo
	 * para checar todos os seus filhos, e os filhos desses filhos, e assim em
	 * diante. Para evitar que o algoritmo fique em um loop entre dois ou mais
	 * estados, foi declarada a variavel 'visited' na classe State. Devido a
	 * isso sempre que o algoritmo de EF ou EG for executado sempre deve-se
	 * resetar todas as variaveis 'visited' dos estados da MEF para o valor
	 * 'false', caso contrario o algoritmo ira achar que todos os estados ja
	 * foram visitados. Caso alguem tenha uma sugestao melhor de implementacao
	 * dessa informacao de visitado, por favor se manifeste.
	 * 
	 * O ponto principal deste algoritmo e' que para saber se ja foi encontrado
	 * um caminho onde sempre a propriedade se repete (ou seja o EG e' valido),
	 * o estado atual deve ser valido e o proximo estado a ser checado deve
	 * obrigatoriamente satisfazer essas duas condicoes: 1 - Ele ja deve ter
	 * sido visitado 2 - Ele deve ser valido para aquela propriedade
	 * 
	 * Se ele ja foi visitado e tambem e valido para a propriedade, significa
	 * que foi encontrado um caminho que leva a um loop onde aquela propriedade
	 * sempre vale. Ao encontrar isso o algoritmo retorna 'true' ate chegar no
	 * metodo principal EG.
	 * 
	 * @author Pedro Pinheiro
	 * @param state
	 * @param label
	 * @return
	 */
	private static boolean recursiveEG(State state, String label) {
		boolean valid = false;

		if (state.getLabelsString().contains(label)) {
			state.setVisited(true);

			ArrayList<State> children = state.getChildren();
			for (Iterator<State> iterator = children.iterator(); iterator.hasNext();) {
				State state2 = (State) iterator.next();

				if (state2.isVisited()
						&& state2.getLabelsString().contains(label)) {
					valid = true;
					break;
				}

				if (state2.isVisited()
						&& !state2.getLabelsString().contains(label)) {
					valid = false;
				}

				if (!state2.isVisited()) {
					valid = recursiveEG(state2, label);
				}
			}

		} else {
			valid = false;
		}

		return valid;
	}

	/**
	 * This formula is TRUE in a state s0 if formula f holds at 
	 * every state on every path from s0.
	 * 
	 * @author Andr� Luiz e Pedro Pinheiro
	 * @param states
	 *            Conjunto de estados da MEF em que se deseja verificar se uma
	 *            expressao � verdadeira
	 * @param expression
	 *            Expressao CTL que deseja verificar se � verdadeira.
	 * @return retorna true se a expressao � verdadeira para aquele estado, e
	 *         caso contrario retorna 'false'
	 */
	public static boolean AG(State state, Expression expression) {
		boolean validExpression = true;
		graphvizFileMaker = new GraphvizFileMaker(expression.getType()+(int)(Math.random()*1000)+".dot"); 
		
		state.setVisited(true);
		// ex: Expression.name = AG p
		Expression expression1 = expression.getExp1(); // = p
//		Expression expression2 = expression.getExp2(); // = null
		System.out.println(state.getName());
		if (state.getLabelsString().contains(expression1.getName())) {
			graphvizFileMaker.addValidState(state.getName());
			ArrayList<State> children = state.getChildren();
			for (Iterator<State> iterator = children.iterator(); iterator
					.hasNext();) {
				State state2 = (State) iterator.next();
				graphvizFileMaker.addBranch(state.getName(), state2.getName());
				// se ele achar um caminho (ciclo) em que a expressao não seja valida
				if (!recursiveAG(state2, expression1.getName())) {
					// entao marcar a expressao como não valida
					validExpression = false;
					state.addLabelsString(expression.getName());
					break;
				}
			}
		} else {
			graphvizFileMaker.addInvalidState(state.getName());
			validExpression = false;
		}

		graphvizFileMaker.finishFile();
		return validExpression;
	}

	private static boolean recursiveAG(State state, String label) {
		boolean valid = true;
		System.out.println("Recursive:" + state.getName());
		if (state.getLabelsString().contains(label)) {
			graphvizFileMaker.addValidState(state.getName());
			state.setVisited(true);

			ArrayList<State> children = state.getChildren();
			for (Iterator<State> iterator = children.iterator(); iterator.hasNext();) {
				State state2 = (State) iterator.next();
				graphvizFileMaker.addBranch(state.getName(), state2.getName());
//				if(state2 == state) {
//					continue;
//				}
				
				System.out.println("Recursive2:" + state2.getName());
				if (!state2.isVisited()) {
					System.out.println("Entrei");	
					valid = recursiveAG(state2, label);
					if (!valid) {
						break;
					}
				}
			}

		} else {
			graphvizFileMaker.addInvalidState(state.getName());
			valid = false;
		}

		return valid;
	}

	/**
	 * M�todo para avaliar a express�o CTL AX, que significa que no
	 * pr�ximo estado a propriedade � v�lida
	 * 
	 * @param states
	 *            Conjunto de estados da MEF
	 * @param expression
	 *            Representa a express�o CTL que est� sendo avaliada
	 * */
	public static List<State> AX(ArrayList<State> states, Expression expression) {
		List<State> validStates = new ArrayList<State>();

		for (int i = 0; i < states.size(); i++) {
			int nextState = i + 1;
			if (states.get(nextState).getLabelsString()
					.contains(expression.getName())) {
				validStates.add(states.get(nextState));
			}
		}
		return validStates;
	}

	public static boolean AF(ArrayList<State> states, Expression expression) {
		boolean validExpression = false;
		// ex: Expression.name = AF p
		Expression expression1 = expression.getExp1(); // = p
		Expression expression2 = expression.getExp2(); // = null

		for (int i = 0; i < states.size(); i++) {
			if (expression1 == null) {
				if (states.get(i).getLabelsString()
						.contains(expression2.getName())) {
					validExpression = true;
					states.get(i).addLabelsString(expression.getName());
				} else {
					states.get(i).setVisited(true);
					validExpression = false;

				}
			}

			if (expression2 == null) {

				if (states.get(i).getLabelsString()
						.contains(expression1.getName())) {
					validExpression = true;
					states.get(i).addLabelsString(expression.getName());
				} else {
					states.get(i).setVisited(true);
					validExpression = false;
				}
			}

		}
		return validExpression;

	}

	/**
	 * Implementacao do algoritmo EX exp - se existe um cominho tal que no 
	 * proximo esta a propriedade e' verdade
	 * 
	 * @author Mauricio Arimoto
	 * @param st
	 *            Conjunto de estados da MEF em que se deseja verificar se uma
	 *      	  expressao e' valida
	 * @param exp
	 *            Expressao CTL que se deseja verificar.
	 *
	 */
	public static List<State> EX(ArrayList<State> st, Expression exp) {
		
		List<State> validStates = new ArrayList<State>();

		for (int i = 0; i < st.size(); i++) {
			State state = st.get(i);
			
			for (Iterator<State> iterator = state.getChildren().iterator(); iterator.hasNext();) {
				State state_child = (State) iterator.next();
				
				if (state_child.getLabelsString().contains(exp.getName())){
					validStates.add(state_child);
				}
			}
		}
		return validStates;
	}
	
	
	/**
	 * Implementando o algoritmo EU -- E(p U q) --> se existe um caminho tal que
	 * existe um estado em que p e' verdade ate que q seja verdade
	 * 
	 * @author Mauricio Arimoto
	 * @param st
	 *            Conjunto de estados da MEF em que se deseja verificar se uma
	 *      	  expressao e' valida
	 * @param exp
	 *            Expressao CTL que se deseja verificar.
	 *
	 */
	
	public static boolean EU(State st, Expression exp) {
		
		boolean validExpression = false;
		st.setVisited(true);
		
		Expression exp1 = exp.getExp1(); 
		Expression exp2 = exp.getExp2();
		
		if (st.getLabelsString().contains(exp1.getName())) {
			ArrayList<State> children = st.getChildren();
			for (Iterator<State> iterator = children.iterator(); iterator.hasNext();) {
				State st2 = (State) iterator.next();
				
				if (st.getLabelsString().contains(exp2.getName())) {
				
					//....
					
				}
				
			}
		}

		return validExpression;
	}
	

	//Implementando algoritmo AU
	public static boolean AU(ArrayList<State> states, Expression expression) {
		//TODO
		return true;
	}
	
	/**
	 * This formula is TRUE in a state s0 if for SOME PATH starting 
	 * with s0, there exists an initial prefix of that path such that f2 
	 * holds at the last state of the prefix and f1 holds at all other 
	 * states along the prefix
	 * @param state
	 * @param expression
	 * @return
	 */
//	public static boolean EU(State state, Expression expression) {
//		TODO
//		boolean validExpression = false;
//		state.setVisited(true);
		
		// ex: Expression.name = E (p U q)
//		Expression expression1 = expression.getExp1(); // = p
//		Expression expression2 = expression.getExp2(); // = q
		
//		if(state.getLabelsString().contains(expression2.getName())) {
//			validExpression = true;
//		} else if (state.getLabelsString().contains(expression1.getName())) {
//			 I need to find at least one path that contains the expression q, if I don't find it, then I need at least
			// to find the expression1 and keep looking in other states.
//			ArrayList<State> children = state.getChildren();
//			for (Iterator<State> iterator = children.iterator(); iterator.hasNext();) {
//				State state2 = (State) iterator.next();
//				validExpression = recursiveEU(state2, expression1.getName(),expression2.getName());
//				if (validExpression) {
//					state.addLabelsString(expression.getName());
//					break;
//				}
//			}
//		}
		
//		return validExpression;
//	}

//	private static boolean recursiveEU(State state, String firstExpression, String secondExpression) {
		
		
//		return false;
//	}

	/**
	 * This formula is TRUE in a state s0 if for EVERY PATH starting 
	 * with s0, there exists an initial prefix of that path such that f2 
	 * holds at the last state of the prefix and f1 holds at all other 
	 * states along the prefix.
	 * @param states
	 * @param expression
	 * @return
	 */
//	public static boolean AU(ArrayList<State> states, Expression expression) {
//		TODO
//		return true;
//	}

}
