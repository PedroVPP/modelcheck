package br.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.exceptions.MalformedExpression;
import br.mef.Expression;
import br.mef.Property;
import br.mef.State;

public class Algorithms {

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

		for (Iterator iterator = states.iterator(); iterator.hasNext();) {
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

		for (Iterator iterator = states.iterator(); iterator.hasNext();) {
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
	public static boolean NOT(State state, Expression expression)
			throws MalformedExpression {
		boolean validExpression = false;
		// ex: Expression.name = ~p
		Expression expression1 = expression.getExp1(); // = p
		Expression expression2 = expression.getExp2(); // = null

		if (expression1 == null && expression2 == null || expression1 != null
				&& expression2 != null) {
			throw new MalformedExpression();
		}

		if (expression1 == null) {
			if (!state.getLabelsString().contains(expression2.getName())) {
				state.addLabelsString(expression.getName());
				validExpression = true;
			}
		}

		if (expression2 == null) {
			if (!state.getLabelsString().contains(expression1.getName())) {
				state.addLabelsString(expression.getName());
				validExpression = true;
			}
		}

		return validExpression;
	}

	/**
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

		for (Iterator iterator = states.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			if (NOT(state, expression)) {
				validStates.add(state);
			}
		}
		return validStates;
	}

	/**
	 * Metodo 'implica'. Ex: p -> q
	 * 
	 * @param state
	 * @param expression
	 * @return
	 * @throws Exception
	 */
	public static boolean IMP(State state, Expression expression)
			throws Exception {
		boolean validExpression = false;
		// Expression expression = "p -> q"
		Expression expression1 = expression.getExp1(); // = p
		Expression expression2 = expression.getExp2(); // = q

		// p -> q = (NOT p) OR q
		Expression not = new Expression("NOT " + expression1.getName()); // NOT
																			// p
		not.setExp1(new Expression(expression1.getName()));

		Expression or = new Expression(not.getName() + " OR "
				+ expression2.getName());
		or.setExp1(not);
		or.setExp2(new Expression(expression2.getName()));

		NOT(state, not);

		if (OR(state, or)) {
			validExpression = true;
			state.addLabelsString(expression.getName());
		}

		return validExpression;
	}

	public static boolean EF(State state, Expression expression)
			throws MalformedExpression {
		boolean validExpression = false;
		// ex: Expression.name = EF p
		Expression expression1 = expression.getExp1(); // = p
		Expression expression2 = expression.getExp2(); // = null

		if (expression1 == null && expression2 == null || expression1 != null
				&& expression2 != null) {
			throw new MalformedExpression();
		}

		if (expression1 == null) {
			if (state.getLabelsString().contains(expression2.getName())) {
				validExpression = true;
				state.addLabelsString(expression.getName());
			} else {
				state.setVisited(true);
				ArrayList<State> children = state.getChildren();
				for (Iterator iterator = children.iterator(); iterator
						.hasNext();) {
					State state2 = (State) iterator.next();
					if (recursiveEF(state2, expression2.getName())) {
						validExpression = true;
						state.addLabelsString(expression.getName());
					}
				}
			}
		}

		if (expression2 == null) {

			if (state.getLabelsString().contains(expression1.getName())) {
				validExpression = true;
				state.addLabelsString(expression.getName());
			} else {
				state.setVisited(true);
				ArrayList<State> children = state.getChildren();
				for (Iterator iterator = children.iterator(); iterator
						.hasNext();) {
					State state2 = (State) iterator.next();
					if (recursiveEF(state2, expression1.getName())) {
						validExpression = true;
						state.addLabelsString(expression.getName());
					}
				}
			}
		}

		return validExpression;
	}

	// find one STATE that contains this "label" among its children
	public static boolean recursiveEF(State state, String label) {
		boolean valid = false;
		
		if (state.getLabelsString().contains(label)) {
			valid = true;
		} else {
			ArrayList<State> children = state.getChildren();
			for (Iterator iterator = children.iterator(); iterator.hasNext();) {
				State state2 = (State) iterator.next();
				if (!state2.isVisited()) {
					state.setVisited(true);
					valid = recursiveEF(state2, label);
					if(valid) {
						break;
					}
				}
			}
		}
		return valid;
	}

	public static boolean EG(State state, Expression expression)
			throws MalformedExpression {
		boolean validExpression = false;
		state.setVisited(true);
		// ex: Expression.name = EG p
		Expression expression1 = expression.getExp1(); // = p
		Expression expression2 = expression.getExp2(); // = null

		if (expression1 == null && expression2 == null || expression1 != null
				&& expression2 != null) {
			throw new MalformedExpression();
		}

		if (expression1 == null) {
		}

		if (expression2 == null) {
			if (state.getLabelsString().contains(expression1.getName())) {

				ArrayList<State> children = state.getChildren();
				for (Iterator iterator = children.iterator(); iterator
						.hasNext();) {
					State state2 = (State) iterator.next();
					// se ele achar um caminho (ciclo) em que a expressao seja valida
					if (recursiveEF(state2, expression1.getName())) {
						// entao marcar a expressao como valida
						validExpression = true;
						state.addLabelsString(expression.getName());
					}
				}
			}
		}

		return validExpression;
	}

	// find one PATH that contains this "label" among its children
	public static boolean recursiveEG(State state, String label) {
		boolean valid = false;
		
		if(state.getLabelsString().contains(label)) {
			state.setVisited(true);
			
			ArrayList<State> children = state.getChildren();
			for (Iterator iterator = children.iterator(); iterator
					.hasNext();) {
				State state2 = (State) iterator.next();
				
				if(state2.isVisited() && state2.getLabelsString().contains(label)) {
					valid = true;
					break;
				}
				
				if(state2.isVisited() && !state2.getLabelsString().contains(label)) {
					valid = false;
				}
				
				if(!state2.isVisited()) {
					valid = recursiveEG(state2, label);
				}
			}
			
		} else {
			valid = false;
		}
		
		return valid;
	}

	/**
	 * @author Andr� Luiz
	 * @param states
	 *            Conjunto de estados da MEF em que se deseja verificar se uma
	 *            expressao � verdadeira
	 * @param expression
	 *            Expressao CTL que deseja verificar se � verdadeira.
	 * @return retorna true se a expressao � verdadeira para aquele estado, e
	 *         caso contrario retorna 'false'
	 */
	public static boolean AG(ArrayList<State> states, Expression expression) {
		boolean flag = false;
		if (states.get(0).getLabelsString().contains(expression.getName())) {
			for (int i = 1; i < states.size(); i++) {
				// marca os estados da MEF caso o estado inicial contenha AG
				states.get(i).addLabel(i);
			}
			flag = true;
		} else {

			for (int i = 0; i < states.size(); i++) {
				if (states.get(i).getLabelsString()
						.contains(expression.getName())) {
					// marca os estados da MEF caso o estado inicial contenha AG
					int value = 0;
					states.get(i).addLabel(value);

					for (int j = i + 1; j < states.size(); j++) {
						int auxvalue = value;
						states.get(j).addLabel(auxvalue++);
					}
					flag = true;
				} else {
					flag = false;
				}
			}
		}
		return flag;

	}

	/**
	 * M�todo para avaliar a express�o CTL AX, que significa que no pr�ximo
	 * estado a propriedade � v�lida
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
			if (states.get(nextState).getLabelsString().contains(expression.getName())) {
				validStates.add(states.get(nextState));
			}
		}
		return validStates;
	}

	public static boolean AF(ArrayList<State> states, Expression expression) {
		return true;
	}

}
